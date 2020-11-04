package com.henry.java;

import java.util.*;

public class ArrayListDemo {
    public static void main(String[] args) {
        // Collection Interface
        //1. Define
        Collection<Integer> coll = new ArrayList<>();
        //2. add and addAll
        coll.add(9);coll.add(3);coll.add(1);coll.add(5);coll.add(7);
        coll.addAll(Arrays.asList(5,4,3,2,1));
        //3. size
        System.out.println(coll.size());
        //4. contains
        System.out.println(coll.contains(7));
        //5. isEmpty
        System.out.println(coll.isEmpty());
        //6. remove
        boolean res = coll.remove(7);
        //7. clear
        coll.clear();
        //8. toArray
        Object[] objs = coll.toArray();

        //List Interface
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>(list);
        //1. insert at index
        list.add(1, 13);
        //2. delete with index
        Integer removedElem = list.remove(0);
        //3. visit and modify with index
        Integer i = list.get(2);
        Integer j = list.set(2,15);
        //4. search the index
        int index = list.indexOf(7);
        //5. sort
        list.sort((a1, a2) -> {return a1 - a2;});
        //6. traverse
        for (int num:list) {
            System.out.print(num);
            System.out.print(" ");
        }
        System.out.println();
        //7. subList
        List<Integer> subList = list.subList(0,3);

        //ArrayList shallow copy
        //method 1
        ArrayList<Integer> clonedList = new ArrayList<>(list);
        //method 2
        clonedList.clone();

        // Collection Interface
        //1. size()
        System.out.println(list.size());
        //2. contains()
        System.out.println(list.contains(5));
        //3. clear()
        list.clear();
        //4. isEmpty()
        System.out.println(list.isEmpty());
        //5. add()
        list.add(9);

        // Collections
        //1. sort
        Collections.sort(list2);
        Collections.sort(list2, Collections.reverseOrder());
        Collections.sort(list2, (Integer a, Integer b) -> {return b-a;});
        System.out.println(list2);

        //2. shuffle
        Collections.shuffle(list2);
        System.out.println(list2);

        //3. reverse
        Collections.reverse(list2);
        System.out.println(list2);

        //4. binarySearch
        int indexC = Collections.binarySearch(list2, 2);

        //5. max and min
        Integer max = Collections.max(list2);
        Integer min = Collections.min(list2);

        //6. fill
        list.add(9);list.add(3);list.add(1);list.add(5);list.add(7);
        Collections.fill(list,0);
        System.out.println(list);

        //6. Synchronization
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
