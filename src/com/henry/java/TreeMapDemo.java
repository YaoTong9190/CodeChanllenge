package com.henry.java;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {
        //Integer表示英语成绩，String表示人名
        TreeMap<Integer, String> tree = new TreeMap<>();
        tree.put(86, "zhangsan");
        tree.put(95, "lisi");
        tree.put(68, "wangwu");
        tree.put(59, "zhaoliu");

        //Iterator
        for (Map.Entry<Integer, String> entry : tree.entrySet()) {
            System.out.println(entry.getValue()+":"+entry.getKey());
        }
        //toString
        System.out.println(tree.toString());

        //get
        String name = tree.get(68);
        System.out.println(name);
        //contains
        boolean isExisted = tree.containsValue("lisi");
        System.out.println(isExisted);

        //firstEntry
        String lowestName = tree.firstEntry().getValue();
        System.out.println(lowestName);
        //lastEntry
        String highestName = tree.lastEntry().getValue();
        System.out.println(highestName);

        //find the person whose score is just higher than 60
        System.out.println(tree.ceilingEntry(60).getValue());
        //find the person whose score is just lower than 90
        System.out.println(tree.floorEntry(90).getValue());

        //Comparator
        Comparator<Item> myComparator = new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.value.compareTo(o2.value);
            }
        };
        TreeMap<Item, Integer> bst = new TreeMap<>(myComparator);
        bst.put(new Item("1",1), 4);
        bst.put(new Item("2",2), 5);
        bst.put(new Item("3",3), 0);

        System.out.println(bst.toString());
    }


}
