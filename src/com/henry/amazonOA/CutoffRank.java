package com.henry.amazonOA;

import java.util.Arrays;
import java.util.Collections;

public class CutoffRank {
    public static int cutOffRank(int cutOffRank, int num, int[] scores) {
        if (cutOffRank>=num){
            return num;
        }
        Arrays.sort(scores);
        int rank=1; int helper = rank;
        int count = 1;
        for (int i = scores.length-2; i >=0; i--) {
            helper++;
            if(scores[i]<scores[i+1]){
                rank=helper;
            }
            if (rank<=cutOffRank){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] scores = {100,50,50,25};
        int cutOffRank = 3;
        System.out.println(cutOffRank(cutOffRank,scores.length,scores));

        int[] scores2 = {2,2,3,4,5};
        int cutOffRank2 = 4;
        System.out.println(cutOffRank(cutOffRank2,scores2.length,scores2));
    }
}
