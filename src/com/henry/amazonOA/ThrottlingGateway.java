package com.henry.amazonOA;

import java.util.HashMap;
import java.util.Map;

public class ThrottlingGateway {
    public static int dropRequest(int num, int[] requestTime){
        //key is the request time, value is the count
        Map<Integer, Integer> map = new HashMap<>();
        int dropNum = 0;
        int count10 = 0; int switch10 = 0;
        int count60 = 0; int switch60 = 0;
        for (int time:requestTime){
            if (time/10!=switch10){
                count10=1;
                switch10=time/10;
            }else {
                count10++;
            }

            if (time/60!=switch60){
                count60=1;
                switch60=time/60;
            }else {
                count60++;
            }

            if (!map.containsKey(time)){
                map.put(time,1);
            }else {
                map.put(time, map.get(time)+1);
            }

            if (count10>20 || count60>60 || map.get(time)>3){
                dropNum++;
            }

        }
        return dropNum;
    }

    public static void main(String[] args) {
        int[] requestTime = {1,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,7,11,11,11,11};
        System.out.println(dropRequest(requestTime.length,requestTime));
    }
}
