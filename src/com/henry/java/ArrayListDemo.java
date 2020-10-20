package com.henry.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {
    // List Interface
        //Define
        List<Integer> list = new ArrayList<>();
        //CRUD
        list.add(9);list.add(3);list.add(1);list.add(5);list.add(7);
        list.add(1, 13);
        List<Integer> list2 = new ArrayList<>(list);
        list.addAll(list2);
        Integer removedElem = list.remove(0);
        boolean res = list.remove(new Integer(5));
        Integer i = list.get(0);
        Integer j = list.set(2,15);
        //search
        int index = list.indexOf(7);
        //sort
        list.sort((a1, a2) -> {return a1 - a2;});
        //traverse
        for (int num:list) {
            System.out.print(num);
            System.out.print(" ");
        }
        System.out.println();
        //sub
        List<Integer> subList = list.subList(0,3);
    // ArrayList special method
        ArrayList<Integer> clonedList = new ArrayList<>(list);
    // Collection Interface
        //size()
        System.out.println(list.size());
        //contains()
        System.out.println(list.contains(5));
        //clear()
        list.clear();
        //isEmpty()
        System.out.println(list.isEmpty());

    // Collections
        //sort
        //Collections.sort(list);
        //Collections.sort(list, Collections.reverseOrder());
        Collections.sort(list2, (Integer a, Integer b) -> {return b-a;});
        System.out.println(list2);

        //shuffle
        Collections.shuffle(list2);
        System.out.println(list2);

        //reverse
        Collections.reverse(list2);
        System.out.println(list2);

        //binarySearch
        int indexC = Collections.binarySearch(list2, 2);

        //max and min
        Integer max = Collections.max(list2);
        Integer min = Collections.min(list2);

        //fill
        list.add(9);list.add(3);list.add(1);list.add(5);list.add(7);
        Collections.fill(list,0);
        System.out.println(list);

        //Synchronization
        List<String> syncList = Collections.synchronizedList(new ArrayList<>(20));
        syncList.add("aaa"); syncList.add("bbb"); syncList.add("ccc");
        synchronized (syncList){
            Iterator<String> iterator = syncList.iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
        }
    }
}
