package com.henry.yelp;
import sun.tools.tree.DoubleExpression;

import java.util.*;
public class ActiveBusiness {
    static class Item {
        String event_type;
        int occurance;
        String biz_id;
    }

    List<String> findActiveItem(List<Item> items) {
        Map<String, Integer> eventCountMap = new HashMap<>();
        Map<String, Integer> eventOccuranceMap = new HashMap<>();
        Map<String, Double> eventAvgOccuranceMap = new HashMap<>();

        Map<String, Map<String, Integer>> bizEventsMap = new HashMap<>();

        for (Item i: items){
            if(eventCountMap.containsKey(i.event_type)){
                int newValue = eventCountMap.get(i.event_type) + 1;
                eventCountMap.put(i.event_type, newValue);
            }else {
                eventCountMap.put(i.event_type, 1);
            }

            if (eventOccuranceMap.containsKey(i.event_type)){
                int newValue = eventOccuranceMap.get(i.event_type)+i.occurance;
                eventOccuranceMap.put(i.event_type, newValue);
            }else {
                eventOccuranceMap.put(i.event_type, i.occurance);
            }

            if (bizEventsMap.containsKey(i.biz_id)){
                Map<String, Integer> eventMap = bizEventsMap.get(i.biz_id);
                eventMap.put(i.event_type, i.occurance);
            }else {
                Map<String, Integer> eventMap = new HashMap<>();
                eventMap.put(i.event_type, i.occurance);
                bizEventsMap.put(i.biz_id, eventMap);
            }
        }
        for (String event: eventCountMap.keySet()){
            int count = eventCountMap.get(event);
            int occurance = eventOccuranceMap.get(event);
            double avg = occurance*1.0/count;
            eventAvgOccuranceMap.put(event,avg);
        }

        List<String> res = new ArrayList<>();
        for (String biz_id: bizEventsMap.keySet()){
            Map<String, Integer> eventMap = bizEventsMap.get(biz_id);
            if (eventMap.size()>=2){
                boolean isSatisfy = true;
                for (String event: eventMap.keySet()){
                    if (eventAvgOccuranceMap.get(event) >= eventMap.get(event)){
                        isSatisfy = false;
                    }
                }
                if (isSatisfy){
                    res.add(biz_id);
                }
            }
        }
        return res;
    }
}
