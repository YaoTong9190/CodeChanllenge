package com.henry.amazonOA;
import java.util.*;

public class MusicPairs {
    public static long getSongPairCount(int[] songs) {
        int sum = 0;
        for (int i:songs){
            sum+=i;
        }
        int n = sum/60;
        int count = 0;
        for (int i = 1; i < n; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int len : songs){
                if (map.get(60*i-len)!=null){
                    count += map.get(60*i-len);
                }

                if (map.get(len)==null){
                    map.put(len, 1);
                }else {
                    map.put(len, map.get(len)+1);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums1 = { 37, 23, 60 };
        int[] nums2 = { 10, 50, 90, 30 };
        int[] nums3 = { 30, 20, 150, 100, 40 };
        int[] nums4 = { 60, 60, 60 };

        System.out.println(getSongPairCount(nums1));
        System.out.println(getSongPairCount(nums2));
        System.out.println(getSongPairCount(nums3));
        System.out.println(getSongPairCount(nums4));
    }
}
