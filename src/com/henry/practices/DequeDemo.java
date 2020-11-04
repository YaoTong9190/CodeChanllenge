package com.henry.practices;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class DequeDemo {
    public static  int[] leetcode239(int[] nums, int k) {
        if (nums.length<k){
            return null;
        }
        //1. initial the queue, the queue stores the value first is the max of window
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        //2. Go over the nums
        for (int i = 0; i < nums.length; i++) {
            if (i>=k && nums[i-k]==deque.peekFirst()){
                deque.pollFirst();
            }

            if (deque.isEmpty() || deque.peekLast() >= nums[i]){
                deque.offerLast(nums[i]);
            }else {
                //remove the values in deque which smaller than num[i]
                while (!deque.isEmpty() && deque.peekLast() < nums[i]){
                    deque.pollLast();
                }
                deque.offerLast(nums[i]);
            }
            if (i>=k-1) {
                int max = deque.peekFirst();
                res.add(max);
            }
        }
        //3. return
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }
}
