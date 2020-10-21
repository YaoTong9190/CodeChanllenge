package com.henry.amazonOA;
import java.net.CookieHandler;
import java.util.*;

public class MaximumUnits {
    public static long getMaxUnit(int num, List<Integer> boxes, int unitSize, List<Integer> unitsPerBox, long truckSize){
        long count =0;
        for (int i=0;i<truckSize;i++){
            //remove unavailable choice
            for (int j = 0; j < boxes.size(); j++) {
                if (boxes.get(j)<=0){
                    unitsPerBox.set(j,-1);
                }
            }
            //find the maximum index in uinitsPerBox
            int maxIndex = -1;
            int max = -1;
            for (int j = 0; j < unitsPerBox.size(); j++) {
                if (unitsPerBox.get(j)>max){
                    max=unitsPerBox.get(j);
                    maxIndex=j;
                }
            }

            boxes.set(maxIndex,boxes.get(maxIndex)-1);
            count+=max;
        }
        return count;
    }

    public static void main(String[] args) {
        int num = 3;
        int unitSize=3;
        int truckSize =3;
        System.out.println(getMaxUnit(num,Arrays.asList(1,2,3),unitSize,Arrays.asList(3,2,1),truckSize));
    }

}
