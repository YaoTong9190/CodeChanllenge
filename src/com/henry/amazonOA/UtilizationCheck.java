package com.henry.amazonOA;
import java.util.*;
public class UtilizationCheck {
    public static int finalInstances(int instances, int[] averageUtil) {
        int numInstances = instances;
        int i=0;
        while (i<averageUtil.length){
            if (averageUtil[i]<25){
                if (numInstances!=1){
                    if (numInstances%2==0){
                        numInstances = numInstances/2;
                    }else {
                        numInstances = numInstances/2+1;
                    }
                }
                i+=11;
            }else if (averageUtil[i]>60){
                if (numInstances< 2*1000*10000){
                    numInstances=numInstances*2;
                }
                i+=11;
            }else {
                i++;
            }
        }

        return numInstances;
    }

    public static void main(String[] args) {
        int[] averageUtil = {25,23,1,2,3,4,5,6,7,8,9,10,76,80};
        System.out.println(finalInstances(2,averageUtil));
    }
}
