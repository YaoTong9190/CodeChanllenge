package com.henry.java;

import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        linkedList.addLast(100);
        linkedList.getFirst();
        linkedList.getLast();
        linkedList.removeFirst();

        //Stack
        linkedList.push(5);
        linkedList.peek();
        linkedList.pop();

        //DQueue
        boolean res = linkedList.offer(5);
        res = linkedList.offerLast(10);
        int a = linkedList.peek();
        int b = linkedList.peekLast();
        a = linkedList.poll();
        b = linkedList.pollLast();
    }
}
