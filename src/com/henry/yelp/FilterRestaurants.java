package com.henry.yelp;
import java.util.*;
public class FilterRestaurants {
    static class Restaurant{
        int id;
        int rating;
        int veganFriendly;
        int price;
        int distance;

        public Restaurant(int[] restaurantArr){
            id = restaurantArr[0];
            rating = restaurantArr[1];
            veganFriendly =restaurantArr[2];
            price = restaurantArr[3];
            distance = restaurantArr[4];
        }
    }

    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        PriorityQueue<Restaurant> pQueue = new PriorityQueue<>(
                (r1, r2) -> {
                    if(r1.rating == r2.rating){
                        return r2.id - r1.id;
                    }
                    return r2.rating - r1.rating;
                }
        );

        for(int[] restaurantArr:restaurants){
            Restaurant r = new Restaurant(restaurantArr);
            if(veganFriendly==1 && r.veganFriendly !=veganFriendly){
                continue;
            }
            if(r.distance> maxDistance){
                continue;
            }
            if(r.price>maxPrice){
                continue;
            }
            pQueue.offer(r);
        }

        List<Integer> res = new ArrayList<>();

        while(!pQueue.isEmpty()){
            res.add(pQueue.poll().id);
        }

        return res;
    }
}
