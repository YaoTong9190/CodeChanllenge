package com.henry.practices;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple BST
 */
public class BSTDemo {
    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    Node root;
    public BSTDemo() {
        root = null;
    }
    public BSTDemo(int data){
        root = new Node(data);
    }

    public void add(int elem){
        if (root== null){
            root = new Node(elem);
        }

        Node n = root;
        while (n!=null){
            if (elem<n.data){
                if (n.left==null){
                    Node p =new Node(elem);
                    n.left = p;
                    return;
                }else {
                    n = n.left;
                }
            }else {
                if (n.right== null){
                    Node p = new Node(elem);
                    n.right = p;
                    return;
                }else {
                    n = n.right;
                }
            }
        }
    }

    public Node get(int elem) {
        if (root== null){
            return null;
        }
        Node n = root;
        while (n!=null){
            if (n.data == elem){
                return n;
            }else if (n.data < elem){
                n = n.left;
            }else {
                n = n.right;
            }
        }
        return n;
    }

    public Node remove(int elem){
        //1. find the Node
        if (root== null){
            return null;
        }
        Node n = root;
        Node parent = null;
        while (n!=null){
            if (n.data == elem){
                break;
            }else if (n.data < elem){
                parent = n;
                n = n.left;
            }else {
                parent = n;
                n = n.right;
            }
        }
        //Not found
        if (n == null){
            return n;
        }

        //2. remove Node n

        //a. if n is the leaf node, remove it directly
        if (n.left ==null && n.right == null){
            if (parent!=null) {
                if (n == parent.left) {
                    parent.left = null;
                }
                if (n == parent.right) {
                    parent.right = null;
                }
            }else {
                root = null;
            }
        }

        //b. if n only has left subtree, use its left child to replace it
        if (n.left!=null && n.right== null){
            Node p = n.left;
            if (parent!=null) {
                if (n == parent.left) {
                    parent.left = p;
                }
                if (n == parent.right) {
                    parent.right = p;
                }
            }else {
                root=p;
            }
        }

        //c. if n only has right subtree, find the right child to replace it
        if (n.left==null && n.right!=null){
            Node q = n.right;
            if (parent!=null){
                if (n==parent.left){
                    parent.left = q;
                }
                if (n==parent.right){
                    parent.right = q;
                }
            }else {
                root = q;
            }
        }

        //d. if n has both left and right subtree, find the largest node in its left subtree to replace it
        if (n.left!=null && n.right!=null){
            Node p = n.left;
            Node q = n;
            while (p.right!=null){
                q=p;
                p=p.right;
            }
            q.right = null;
            p.left = n.left;
            p.right = n.right;
            if (parent!=null){
                if (n == parent.left){
                    parent.left = p;
                }
                if (n == parent.right){
                    parent.right = p;
                }
            }else {
                root = p;
            }
        }

        return n;
    }

    //DFS midOrder
    public List<Integer> midOrder(Node node){
        List<Integer> list = new ArrayList<>();
        if (node==null){
            return list;
        }
        list.addAll(midOrder(node.left));
        list.add(node.data);
        list.addAll(midOrder(node.right));
        return list;
    }

    //BFS
    public List<Integer> bfs() {
        if (root==null){
            return null;
        }
        List<Integer> res = new ArrayList<>();

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node n = queue.poll();
            res.add(n.data);
            if (n.left!=null){
                queue.offer(n.left);
            }
            if (n.right!=null){
                queue.offer(n.right);
            }
        }
        return res;
    }

    public List<Integer> preOder(Node node){
        List<Integer> list = new ArrayList<>();
        if (node==null){
            return list;
        }

        list.add(node.data);
        list.addAll(preOder(node.left));
        list.addAll(preOder(node.right));
        return list;
    }

    public String searilize(){
        if (root==null){
            return null;
        }
        return preOder(root).toString();
    }

    public Node desearilize(String data){
        if (data == null || data.length()==0){
            return null;
        }
        String[] nums = data.substring(1,data.length()-1).split(",");
        if (nums.length==0){
            return null;
        }
        Node root = new Node(Integer.parseInt(nums[0]));
        for (int i = 1; i < nums.length; i++) {
            int elem = Integer.parseInt(nums[i]);
            add(elem, root);
        }

        return root;
    }

    private void add(int elem, Node root){
        Node n = root;
        while (n!=null){
            if (n.data < elem){
                if (n.right!=null){
                    n = n.right;
                }else {
                    n.right = new Node(elem);
                    return;
                }
            }else {
                if (n.left !=null){
                    n = n.left;
                }else {
                    n.left = new Node(elem);
                    return;
                }
            }
        }
    }

    public int depth(Node node){
        if (node==null){
            return 0;
        }
        return Math.max(depth(node.left), depth(node.right)) + 1;
    }

    public Node lowestCommonAncestor(Node root, Node p, Node q){
        if (root==null || root==p || root==q){
            return root;
        }
        /**
         * 如果 p,q 在一个节点的左右子树，那么这个节点就是 lowestCommonAncestor
         * 如果 p, q 都在节点的一侧，可以在root子树中肯定有lowestCommonAncestor
         */
        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);

        return left!=null?left:(right!=null?right:root);
    }
}
