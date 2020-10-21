package com.henry.amazonOA;

import java.util.ArrayList;
import java.util.List;

public class BetaTesting {
    public int findMinComplexity(int[] complexity, int days) {
        List<Integer> indexList = new ArrayList<>();

        int maxValue = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < complexity.length; i++) {
            if (complexity[i]>maxValue){
                maxValue=complexity[i];
                maxIndex=i;
            }
        }

        if (days==1){
            return maxValue;
        }

        return maxIndex;
    }
}
