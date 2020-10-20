package com.henry.java;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String, Integer> map =new HashMap<>();
        //add
        map.put("one", 1);
        map.put("two", 2);
        map.put("three",3);
        //remove
        map.remove("three");
        boolean res = map.remove("two", 3);
        System.out.println(res);
        //contain
        res = map.containsKey("one");
        System.out.println(res);
        map.containsValue(1);
        System.out.println(res);

        for (String key: map.keySet()){
            System.out.println(map.get(key));
        }

        for (Map.Entry<String, Integer> entry:map.entrySet()){
            System.out.println(entry.getKey()+"-"+entry.getValue());
        }
    }
}
