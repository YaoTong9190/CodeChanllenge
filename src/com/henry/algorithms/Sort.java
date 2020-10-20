package com.henry.algorithms;

import java.util.Arrays;

public class Sort {

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void bubbleSort(int[] arr) {
        for (int n=0;n<arr.length-1;n++){ //n 代表的是次数
            boolean isSwitched = false;
            for (int i=1;i<arr.length-n;i++){ //i 代表是下标index
                if (arr[i-1]>arr[i]){
                    swap(arr,i-1, i);
                    isSwitched=true;
                }
            }
            if (!isSwitched){
                return;
            }
        }
    }

    public static void insertSort(int[] arr) {
        // 将数组的第一个元素当作已经排好序的，从第二个元素，即 i 从 1 开始遍历数组
        for (int i=1;i<arr.length-1;i++){ // i represent the index of unsorted part. i-1 represent the last index of sorted part
            int current = arr[i];
            // 指针 j 内循环，和 current 值比较，若 j 所指向的值比 current 值大，则该数右移一位
            int j = i-1;
            while (j>=0){
                if (arr[i]>=arr[j]){
                    break;
                }else {
                    arr[j+1] =  arr[j]; //move back for space to insert current.
                }
                j--;
            }
            // 内循环结束，j+1 所指向的位置就是 current 值插入的位置
            arr[j+1] = current;
        }
    }

    public static void mergeSort(int[] arr, int start, int end){
        //if only one item left just return
        if (end<=start){
            return;
        }
        int mid = (start+end)/2;
        //对左边排序
        mergeSort(arr, start, mid);//左边永远包含mid
        //对右边排序
        mergeSort(arr,mid+1, end);
        //merger
        merge(arr, start,mid,end);
    }

    private static void merge(int[] arr, int start, int mid, int end){
        int[] copy = arr.clone();
        int i = start; int j = mid+1;
        int index = start;
        while (index<=end){
            if (i>mid){ //左半边的数都处理完毕，只剩下右半边的数，只需要将右半边的数逐个拷贝过去。
                arr[index++] = copy[j++];
            }else if (j>end){ //右半边的数都处理完毕，只剩下左半边的数，只需要将左半边的数逐个拷贝过去就好。
                arr[index++] = copy[i++];
            }else { //两边都没处理好，先放入较小的
                if (copy[i]<copy[j]){
                    arr[index++] = copy[i++];
                }else {
                    arr[index++] = copy[j++];
                }
            }
        }
    }

    public static void quickSort(int[] arr, int start, int end){
        if (start >= end){
            return;
        }
        //pick a first number as base
        int base = arr[start];
        int i=start; int j=end+1;
        //swap the elements to put all smaller elements to left.
        while (true){
            while (arr[++i]<=base && i<end);
            while (arr[--j]>=base && j>0);
            if (i<j) {
                swap(arr, i, j);
            }else {
                break;
            }
        }
        //switch base with the arr[j],因为j停下时,arr[j]要么是将将比base小的数, 要么j=0,i=1的情况
        swap(arr,start,j);

        quickSort(arr,start,j);
        quickSort(arr,j+1, end);
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 7, 9, 5, 8};
        //Sort.bubbleSort(arr);
        //Sort.insertSort(arr);
        //Sort.mergeSort(arr, 0, arr.length-1);
        //Sort.quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
