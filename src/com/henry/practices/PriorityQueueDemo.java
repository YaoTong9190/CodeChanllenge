package com.henry.practices;

import java.util.*;

public class PriorityQueueDemo {
    public int[] leetcode347(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();

        PriorityQueue<Map.Entry<Integer,Integer>> priorityQueue = new PriorityQueue<>(
                (entry1, entry2) -> {
                    return entry2.getValue() - entry1.getValue();
                }
        );

        for (int num: nums){
            if (countMap.containsKey(num)){
                int newValue = countMap.get(num)+1;
                countMap.put(num, newValue);
            }else {
                countMap.put(num, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry:countMap.entrySet()){
            priorityQueue.offer(entry);
        }

        int[] res = new int[k];
        for (int i=0;i<k;i++){
            res[i] = priorityQueue.poll().getKey();
        }

        return res;
    }

    public int[] leetcode347M2(int[] nums, int k) {
        List<Integer>[] countArr = new ArrayList[nums.length+1];

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num: nums){
            if (countMap.containsKey(num)){
                int newValue = countMap.get(num)+1;
                countMap.put(num,newValue);
            }else {
                countMap.put(num,1);
            }
        }

        for (Map.Entry<Integer, Integer> entry: countMap.entrySet()){
            if(countArr[entry.getValue()]==null){
                countArr[entry.getValue()] = new ArrayList<Integer>();
            }
            countArr[entry.getValue()].add(entry.getKey());
        }

        int[] res = new int[k];
        int count = 0;int i=countArr.length-1;
        while (count<k && i>=1){
            if (countArr[i]!=null){

                for(int num: countArr[i]){
                    res[count] = num;
                    count++;
                }
            }
            i--;
        }
        return res;
    }

    public List<List<Integer>> leetcode373(int[] nums1, int[] nums2, int k) {
        class Pair{
            int i;
            int j;
            int sum;
            public Pair(int i, int j){
                sum = i+j;
                this.i = i;
                this.j = j;
            }
        }

        List<List<Integer>> res = new ArrayList<>();

        if(nums1.length==0 || nums2.length==0){
            return res;
        }

        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(
                (p1, p2) -> {return p1.sum - p2.sum;}
        );

        for(int i=0; i< nums1.length; i++){
            for(int j=0; j< nums2.length;j++){
                Pair pair = new Pair(nums1[i],nums2[j]);
                priorityQueue.add(pair);
            }
        }


        for(int i=0; i<k; i++){
            if(priorityQueue.isEmpty()){
                break;
            }
            Pair pair = priorityQueue.poll();
            List<Integer> list = new ArrayList<>();
            list.add(pair.i);
            list.add(pair.j);
            res.add(list);
        }
        return res;
    }

    public int leetcode1046(int[] stones) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        for(int stone: stones){
            priorityQueue.offer(stone);
        }

        while(priorityQueue.size()>1){
            int x = priorityQueue.poll();
            int y = priorityQueue.poll();
            if(x>y){
                priorityQueue.offer(x-y);
            }else if(x<y){
                priorityQueue.offer(y-x);
            }else{
                continue;
            }
        }

        if(priorityQueue.isEmpty()){
            return 0;
        }
        return  priorityQueue.poll();
    }
}
