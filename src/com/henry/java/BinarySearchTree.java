package com.henry.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable> {
    static class Node<T extends Comparable> {
        T data;
        Node parent = null;
        Node left = null;
        Node right = null;
        int level = 0;
        public Node(T data) {
            this.data = data;
        }
    }

    Node root = null;

    public void add(T elem) {
        Node newNode = new Node(elem);
        if (root == null){
            newNode.level = 1;
            root = newNode;
            return;
        }

        //1. find the node to be its parent
        Node parent = null;
        Node node = root;
        do {
            parent = node;
            if (elem.compareTo(parent.data) <= 0){
                node = node.left;
            }else {
                node = node.right;
            }
        } while (node != null);
        //System.out.println("parent:"+parent.data + " newNode:"+newNode.data);
        //2. insert under its parent
        newNode.parent = parent;
        newNode.level = parent.level + 1;
        if (elem.compareTo(parent.data) <=0){
            parent.left = newNode;
        }else {
            parent.right = newNode;
        }
    }

    public Node getNode(T elem) {
        Node node = root;
        while (node != null){
            int cmp = elem.compareTo(node.data);
            if (cmp < 0){
                node = node.left;
            }else if (cmp > 0) {
                 node = node.right;
            }else {
                return node;
            }
        }
        return null;
    }

    public boolean removeNode(T elem) {
        //1. Get Node
        Node node = getNode(elem);
        if (node == null){
            return false;
        }

        //2. delete node
        Node parent = node.parent;
        //a. If the node is leaf, delete it directly
        if(node.left==null && node.right==null){
            if (parent!=null){ //root node's parent is null, so it doesn't need to config parent node.
                if (parent.left == node){
                    parent.left=null;
                }else {
                    parent.right=null;
                }
            }else {
                root = null;
            }
        }
        //b. If the node only has left subtree, use its left child to replace it
        else if(node.left!=null && node.right==null){
            Node leftChild = node.left;
            leftChild.parent = parent;

            if (parent!=null){
                if (parent.left == node){
                    parent.left = leftChild;
                }else{
                    parent.right = leftChild;
                }
            }else {
                root = leftChild;
            }

            //left subtree level increase
            decreaseLevel(leftChild);
        }
        //c. If the node only has right subtree, use its right child to replace it
        else if(node.left==null && node.right!=null){
            Node rightChild = node.right;
            rightChild.parent = parent;

            if (parent!=null) {
                if (parent.left == node) {
                    parent.left = rightChild;
                } else {
                    parent.right = rightChild;
                }
            }else {
                root = rightChild;
            }

            //right subtree level increase
            decreaseLevel(rightChild);
        }
        //d. If the node has both left and right subtree, use the largest node in left subtree to replce it.
        else if (node.left!=null && node.right!=null){
            //find the node n
            Node n = node.left;
            while (n.right!=null){
                n=n.right;
            }
            //replace with node n
            //remove node n, pls consider the case the node n has left subtree
            n.parent.right = n.left;
            //config node n
            n.parent = parent;
            n.level = node.level;
            n.left = node.left;
            n.right = node.right;
            //config parent
            if (parent!=null) {
                if (parent.left == node) {
                    parent.left = n;
                } else {
                    parent.right = n;
                }
            }else {
                root = n;
            }
        }
        //3. Remove the node
        node = null;
        return true;
    }

    private void decreaseLevel(Node node){
        if (node==null){
            return;
        }
        node.level--;
        decreaseLevel(node.left);
        decreaseLevel(node.right);
    }

    //BFS -- print the tree
    public void print(){
        ArrayDeque<Node> queue = new ArrayDeque<>();
        if (root!=null){
            queue.offer(root);
        }
        int previousLevel = root.level;
        while (!queue.isEmpty()){
            Node node = queue.poll();
            if (previousLevel<node.level){
                System.out.println();
                previousLevel = node.level;
            }
            System.out.print(node.data+" ");

            if (node.left!=null){
                queue.offer(node.left);
            }
            if (node.right!=null){
                queue.offer(node.right);
            }
        }
        System.out.println();
    }

    //DFS-Inorder Traverse
    public List<Node> inIterator(Node node) {
        List<Node> list = new ArrayList<>();
        if (node.left != null){
            list.addAll(inIterator(node.left));
        }
        list.add(node);
        if (node.right != null){
            list.addAll(inIterator(node.right));
        }
        return list;
    }

    public int depth(Node node) {
        if (node == null){
            return 0;
        }
        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);

        int maxSubtreeDepth = leftDepth > rightDepth ? leftDepth : rightDepth;
        return maxSubtreeDepth+1;
    }

    public static void main(String[] args) {
        String[] strings = {"e","a","c","d", "f", "b"};
        BinarySearchTree bst = new BinarySearchTree();
        for (String str: strings){
            bst.add(str);
        }

        Node node = bst.getNode("a");
        System.out.println(node.data);

        bst.print();

        List<Node> nodeList = bst.inIterator(bst.root);
        for (Node<String> n:nodeList){
            System.out.print(n.data+" ");
        }
        System.out.println();

        bst.removeNode("e");
        bst.print();

        System.out.println(bst.depth(bst.root));
    }
}
