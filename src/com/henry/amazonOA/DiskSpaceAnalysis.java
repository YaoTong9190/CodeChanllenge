package com.henry.amazonOA;

import java.util.ArrayDeque;

public class DiskSpaceAnalysis {
    public static int maxMinimaSlidingWindow(int numComputer, int[] hardDiskSpace, int segmentLength){
        int maxMinima = Integer.MIN_VALUE;

        int i=0; int j=0;
        //the deque always stores the index of current minima and its following items' indices. the order of elements in queue is always asc
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        while (j<segmentLength){
            while (!deque.isEmpty()&& hardDiskSpace[deque.peekLast()]>hardDiskSpace[j]){
                deque.pollLast();
            }
            deque.offerLast(j);
            j++;
        }
        maxMinima = hardDiskSpace[deque.peekFirst()];

        while (j<numComputer){
            if (deque.peekFirst()==i){
                deque.pollFirst();
            }
            i++;
            while (!deque.isEmpty()&&hardDiskSpace[deque.peekLast()]>hardDiskSpace[j]){
                deque.pollLast();
            }
            deque.offerLast(j);
            if (hardDiskSpace[deque.peekFirst()]>maxMinima){
                maxMinima = hardDiskSpace[deque.peekFirst()];
            }
            j++;
        }

        return maxMinima;
    }

    public static void main(String[] args) {
        int numComputer=3;
        int[] hardDiskSpace = {8,2,4};
        int segmentLength=2;
        System.out.println(maxMinimaSlidingWindow(numComputer,hardDiskSpace,segmentLength));

        numComputer=6;
        hardDiskSpace = new int[] {1,2,3,4,5,6};
        segmentLength=4;
        System.out.println(maxMinimaSlidingWindow(numComputer,hardDiskSpace,segmentLength));

        numComputer=6;
        hardDiskSpace = new int[] {5,6,3,4,1,2};
        segmentLength=4;
        System.out.println(maxMinimaSlidingWindow(numComputer,hardDiskSpace,segmentLength));
    }
}
