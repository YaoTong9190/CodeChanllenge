package com.henry.yelp;

import java.util.*;

public class BusinessSimilarity {
    static class PositiveReview {
        Integer userId;
        Integer businessId;

        public PositiveReview(Integer userId, Integer businessId) {
            this.userId = userId;
            this.businessId = businessId;
        }

        public Integer getUserId() {
            return this.userId;
        }
        public Integer getBusinessId() {
            return this.businessId;
        }

    }

    public static Integer findMostSimilarBusiness(
            Integer businessOfInterestId,
            List<PositiveReview> positiveReviews
    ) {
        Map<Integer, Set<Integer>> businessIdMap = new HashMap<>();
        for (PositiveReview review: positiveReviews){
            if (businessIdMap.containsKey(review.businessId)){
                businessIdMap.get(review.businessId).add(review.userId);
            }else {
                Set<Integer> set = new HashSet<>();
                set.add(review.userId);
                businessIdMap.put(review.businessId, set);
            }
        }

        int resBusinessId = -1;
        double maxPercentage = -1.0;

        Set<Integer> userSetA = businessIdMap.get(businessOfInterestId);
        for (Integer businessId: businessIdMap.keySet()){
            if (businessId == businessOfInterestId) {
                continue;
            }
            Set<Integer> userSetB = businessIdMap.get(businessId);

            Set<Integer> retainSet = new HashSet<>(userSetB);
            retainSet.retainAll(userSetA);

            Set<Integer> unionSet = new HashSet<>(userSetB);
            unionSet.addAll(userSetA);

            System.out.println(retainSet);
            System.out.println(unionSet);

            double percentage = (retainSet.size() * 1.0)/ unionSet.size();
            if (percentage>maxPercentage){
                resBusinessId = businessId;
                maxPercentage = percentage;
            }
        }
        return  resBusinessId;
    }

    public static void main(String[] args) {

    }
}
