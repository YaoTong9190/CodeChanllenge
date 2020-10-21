package com.henry.amazonOA;
import com.henry.algorithms.Recursive;

import java.util.*;

class PairString {
    String first;
    String second;

    public PairString(String first, String second) {
        this.first = first;
        this.second = second;
    }
}


public class LargestItemAssociation {
    public static List<String> largestItemAssociation(List<PairString> itemAssociation) {
        HashMap<String, Integer> map = new HashMap<>();
        int count = 0;
        List<String> stringList = new ArrayList<>();
        for (PairString pair:itemAssociation){
            if (!map.containsKey(pair.first)){
                map.put(pair.first,count++);
                stringList.add(pair.first);
            }
            if (!map.containsKey(pair.second)){
                map.put(pair.second,count++);
                stringList.add(pair.second);
            }
        }

        int[][] edges = new int[map.size()][map.size()];
        for (PairString pair:itemAssociation){
            if (pair.first.equals(pair.second)){
                edges[map.get(pair.first)][map.get(pair.second)] = 0;
            }else {
                edges[map.get(pair.first)][map.get(pair.second)] = 1;
                edges[map.get(pair.second)][map.get(pair.first)] = 1;
            }
        }

        int[] visited = new int[map.size()];

        List<Integer> maxList = null;
        while (true){
            int index =-1;
            for (int i = 0; i <visited.length; i++) {
                if (visited[i]==0){
                    index = i;
                    break;
                }
            }
            if (index==-1){
                break;
            }

            ArrayDeque<Integer> queue = new ArrayDeque<>();
            queue.offer(index);
            visited[index] = 1;
            List<Integer> list = new ArrayList<>();
            while (!queue.isEmpty()){
                int i = queue.poll();
                list.add(i);

                for (int j = 0; j < edges.length; j++) {
                    if (edges[i][j]==1 && visited[j]==0){
                        queue.offer(j);
                        visited[j] = 1;
                    }
                }
            }
            if (maxList==null){
                maxList=list;
            }else {
                if (maxList.size()<list.size()){
                    maxList=list;
                }
            }
        }

        List<String> res = new ArrayList<>();
        for (int i: maxList){
            res.add(stringList.get(i));
        }
        return res;
    }

    public static void main(String[] args) {
        List<PairString> input = Arrays.asList(
                new PairString[]{
                        new PairString("item1", "item2"),
                        new PairString("item3", "item4"),
                        new PairString("item4", "item5")
                }
        );
        //System.out.println(largestItemAssociation(input));

        List<PairString> input2 =  Arrays.asList(
                new PairString[] {
                        new PairString("item1","item2"),
                        new PairString("item2","item3"),
                        new PairString("item4","item5"),
                        new PairString("item6","item7"),
                        new PairString("item5","item6"),
                        new PairString("item3","item7")
                }
        );
        //System.out.println(largestItemAssociation(input2));

        List<PairString> input3 =  Arrays.asList(
                new PairString[] {
                        new PairString("item1","item2"),
                        new PairString("item1","item3"),
                        new PairString("item2","item7"),
                        new PairString("item3","item7"),
                        new PairString("item5","item6"),
                        new PairString("item3","item7")
                }
        );
        System.out.println(largestItemAssociation(input3));
    }
}
