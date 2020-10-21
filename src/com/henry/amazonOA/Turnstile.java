package com.henry.amazonOA;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Turnstile {
    public static int[] getTimes(int numCustomers, int[] arrTime, int[] direction) {
        int[] res = new int[numCustomers];//store index

        //比较下标
        Comparator<Integer> comparator = (i,j) -> {
            if (arrTime[i]!=arrTime[j]){
                return arrTime[i]-arrTime[j];
            }else {
                if (direction[i]==direction[j]){
                    return i-j;
                }
                if (i==0||j==0||arrTime[i-1]+1<arrTime[i]){
                    //previous second was not used, direction bigger one has high priority
                    return direction[j]-direction[i];
                }
                if (arrTime[i-1]+1==arrTime[i]){
                    return direction[i-1] == direction[i]?-1:1;
                }

                if (arrTime[j-1]+1==arrTime[j]){
                    return direction[j-1] == direction[j]?-1:1;
                }
            }
            return 0;
        };
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(comparator);

        for (int i = 0; i < numCustomers; i++) {
            priorityQueue.offer(i);
        }

        int count =0;
        while (!priorityQueue.isEmpty()){
            res[count++] = priorityQueue.poll();
        }

        return res;
    }

    public static void main(String[] args) {
        int number = 5;
        int[] arrTime = {0,1,1,3,3};
        int[] direction = {0,1,0,0,1};
        System.out.println(Arrays.toString(getTimes(number,arrTime,direction)));

        int number2 = 4;
        int[] arrTime2 = {0,0,1,5};
        int[] direction2 = {0,1,1,0};
        System.out.println(Arrays.toString(getTimes(number2,arrTime2,direction2)));
    }
}
