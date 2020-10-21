package com.henry.amazonOA;

import java.util.*;

public class BST {
    public static class Node{
        int data;
        Node parent;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    public Node root;

    public void buildTree(int[] nums){
        root = new Node(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            Node n = root;
            while (n!=null){
                if (nums[i]<=n.data){
                    if (n.left==null){
                        n.left = new Node(nums[i]);
                        n.left.parent = n;
                        break;
                    }else {
                        n = n.left;
                    }
                }else {
                    if (n.right==null){
                        n.right=new Node(nums[i]);
                        n.right.parent=n;
                        break;
                    }else {
                        n=n.right;
                    }
                }
            }
        }
    }

    public Node findNodeByData(int data){
        Node n = root;
        while (n!=null) {
            if (data == n.data) {
                return n;
            }
            if (n.data>data){
                n=n.left;
            }else {
                n=n.right;
            }
        }
        return n;
    }

    public int nodeDistance(int n1, int n2){
        Node node1 = findNodeByData(n1);
        Node node2 = findNodeByData(n2);
        List<Node> list = new ArrayList<>();
        Node n = node1;
        while (n!=null){
            list.add(n);
            n=n.parent;
        }

        n = node2;
        int count2 = 0;
        while (n!=null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == n) {
                    return i + count2;
                }
            }
            n = n.parent;
            count2++;

        }
        return -1;
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] nums = { 2, 1, 3};
        bst.buildTree(nums);
        System.out.println(bst.nodeDistance(1,3));
    }
}
