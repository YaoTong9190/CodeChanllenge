package com.henry.amazonOA;
import java.util.*;
public class FiveStarReviews {
    public static int fiveStarReviews(List<List<Integer>> productRatings, int ratingsThreshold){
        int count = 0;

        while (averageRating(productRatings)<ratingsThreshold){
            List<Integer> rating = Collections.max(productRatings,(r1, r2) -> {
                float diff1 = (r1.get(0)+1)*1.0f/(r1.get(1)+1) - r1.get(0)*1.0f/r1.get(1);
                float diff2 = (r2.get(0)+1)*1.0f/(r2.get(1)+1) - r2.get(0)*1.0f/r2.get(1);
                if(diff1 < diff2){
                    return -1;
                }else if (diff1 > diff2){
                    return 1;
                }else {
                    return 0;
                }
            });
            rating.set(0, rating.get(0)+1);
            rating.set(1, rating.get(1)+1);
            System.out.println(productRatings);
            count++;
        }


        return count;
    }

    private static float averageRating(List<List<Integer>> productRatings){
        float sum = 0;
        for (List<Integer> review: productRatings){
            sum += (review.get(0)*100.0f)/review.get(1);
        }
        return sum/productRatings.size();
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> list1 = Arrays.asList(4,4);
        List<Integer> list2 = Arrays.asList(1,2);
        List<Integer> list3 = Arrays.asList(3,6);
        list.add(list1);
        list.add(list2);
        list.add(list3);

        System.out.println(fiveStarReviews(list, 77));
    }
}
