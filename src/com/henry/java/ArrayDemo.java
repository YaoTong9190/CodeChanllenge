package com.henry.java;

import java.util.Arrays;
import java.util.Collections;

public class ArrayDemo {
    public static void main(String[] args) {

        //one dimensional array
        //1. define
        int[] arr = new int[5];
        //CRUD
        arr[0]=9; arr[1]=3; arr[2] = 1; arr[3] = 5; arr[4] = 7;
        //2. get length
        System.out.println(arr.length);
        //3. traverse
        for (int i: arr) {
            System.out.println(i);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        //two dimensional array
        //1. define
        int[][] arr2 = new int[5][0];
        arr2[0] = new int[3];
            arr2[1] = new int[] {1,2,3,4,5};
            arr2[2] = new int[2];
            arr2[3] = new int[10];
            arr2[4] = new int[1];
        //2. CRUD
        arr2[4][0] = 100;
        //3. get length
        System.out.println(arr2.length);
        System.out.println(arr2[0].length);
        //4. traverse
        for (int i=0;i<arr2.length;i++){
            for (int j=0;j<arr2[i].length;j++){
                System.out.print(arr2[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        //Arrays
        //1. sort
        Arrays.sort(arr);

        //2. cannot do reverse sort for primitive types
        Integer[] arrInt = {3,2,1};
        Arrays.sort(arrInt, Collections.reverseOrder());

        //3. asList
        Collections.reverse(Arrays.asList(arr));

        //4. search
        int indexOf9 = Arrays.binarySearch(arr, 9);
        int indexOf2 = Arrays.binarySearch(arr, 2);

        //5. Expand size
        int[] newArr = Arrays.copyOf(arr, 10);
        System.out.println(newArr.length);

        //6. toString
        System.out.println(Arrays.toString(arr));

    }
}
