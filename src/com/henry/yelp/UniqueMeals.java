package com.henry.yelp;

import java.util.*;

public class UniqueMeals {

    static class Meal{
        List<String> ingredients;
        String name;
    }

    public int getUniqueMealNum(List<Meal> meals){
        Map<String, List<Meal>> ingredientMap = new HashMap<>();
        for (Meal meal : meals){
            String key = convertIngerdients(meal.ingredients);
            if (ingredientMap.containsKey(key)){
                ingredientMap.get(key).add(meal);
            }else {
                List<Meal> mealList = new ArrayList<>();
                mealList.add(meal);
                ingredientMap.put(key,mealList);
            }
        }

        int count = 0;
        for (Map.Entry<String, List<Meal>> entry: ingredientMap.entrySet()){
            if (entry.getValue().size()==1){
                count++;
            }
        }
        return count;
    }

    private String convertIngerdients(List<String> ingredients){
        StringBuffer sb = new StringBuffer();
        for (String str: ingredients){
            sb.append(str);
        }
        String temp = sb.toString();
        char[] chars = temp.toCharArray();
        Arrays.sort(chars);
        return chars.toString();
    }
}

