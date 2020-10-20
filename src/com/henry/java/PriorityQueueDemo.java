package com.henry.java;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        Comparator<Item> comparator1 = (item1, item2) -> {
            return item1.value.compareTo(item2.value);
        };

        Comparator<Item> comparator2 = (item1, item2) -> {
            return item2.value.compareTo(item1.value);
        };
        //大顶堆
        PriorityQueue<Item> priorityQueue = new PriorityQueue<>(10,comparator2);
        priorityQueue.offer(new Item("2",2));
        priorityQueue.offer(new Item("5",5));
        priorityQueue.offer(new Item("1",1));
        priorityQueue.offer(new Item("6",6));
        System.out.println(priorityQueue.toString());

        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
    }
}
