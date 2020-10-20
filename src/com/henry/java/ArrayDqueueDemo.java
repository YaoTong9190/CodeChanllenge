package com.henry.java;

import java.util.ArrayDeque;
import java.util.Collections;

public class ArrayDqueueDemo {
    public static void main(String[] args) {
    //Stack
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.peek();
        stack.pop();
    //Dequeue
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.offer(1);
        deque.offer(2);
        deque.offerLast(0);
        deque.peek();
        deque.peekLast();
        deque.poll();
        deque.pollLast();
    }
}
