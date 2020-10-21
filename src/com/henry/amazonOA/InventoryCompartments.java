package com.henry.amazonOA;

import java.util.*;

public class InventoryCompartments {
    public static List<Integer> numberOfItems(String s, int[] startIndices, int[] endIndices) {
        if (s==null || s.isEmpty()){
            return null;
        }
        if (startIndices.length!= endIndices.length){
            return null;
        }

        List<Integer> res = new ArrayList<>();
        for (int j = 0; j < startIndices.length; j++) {
            int start = startIndices[j]-1;
            int end = endIndices[j]+1;
            if (start<-1 || start > s.length() || end<-1 || end > s.length() ||start>=end ){
                res.add(0);
            }else {
                System.out.println(start);
                System.out.println(end);
                while (start<s.length() && s.charAt(++start)!='|');
                while (end>=0 && s.charAt(--end)!='|');
                System.out.println(start);
                System.out.println(end);
                if (end<=start){
                    res.add(0);
                }else {
                    int count = 0;
                    for (int i = start; i < end; i++) {
                        if (s.charAt(i)=='*'){
                            count++;
                        }
                    }
                    res.add(count);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s1 = "*|*|*|";

        System.out.println(numberOfItems(s1,new int[]{1,1,2},new int[]{4,5,4}));
    }
}
