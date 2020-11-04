package com.henry.yelp;

import java.util.*;

public class RecommandBusiness {

    /**
     * Business class that consists of a business name and a hashmap of nearby businesses.
     */
    class Business {
        /**
         * The name of the business.
         */
        String name;

        /**
         * A Map of nearbyBusinesses where the key is the nearby Business object
         * and the value is distance from the current Business to the nearby Business.
         */
        Map<Business, Integer> nearbyBusinesses;

        public Business(String name) {
            this.name = name;
            this.nearbyBusinesses = new HashMap<>();
        }

        public String getName() {
            return this.name;
        }

        public Map<Business, Integer> getNearbyBusinesses() {
            return this.nearbyBusinesses;
        }
    }

    public static List<String> findReachableBusinesses(Business startingBusiness, int distance) {
        List<String> res = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        dfs(startingBusiness,distance,0, set);
        for (String str: set){
            res.add(str);
        }
        return res;
    }

    private static void dfs(Business business, int distance, int currentDistance, HashSet<String> res){
        Map<Business,Integer> nearByBusinesses = business.getNearbyBusinesses();
        if (nearByBusinesses.isEmpty()){
            return;
        }
        for (Map.Entry<Business,Integer> entry : nearByBusinesses.entrySet()){
            Business business1 = entry.getKey();
            int distance1 = entry.getValue();
            if (!res.contains(business1.getName())){
                if (currentDistance+distance1<=distance){
                    res.add(business1.getName());
                    dfs(business1, distance, currentDistance+distance1, res);
                }
            }

        }
    }
}
