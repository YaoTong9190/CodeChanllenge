package com.henry.amazonOA;

import java.util.*;

public class TopKWords {
    public static String[] getWordList(int k, String[] keywords, String[] reviews){
        class KeyWord {
            String value;
            int times;
            public KeyWord(String value, int times){
                this.value=value;
                this.times=times;
            }
        }
        Map<String,KeyWord> map = new HashMap<>();
        for (String keyword:keywords){
            map.put(keyword, new KeyWord(keyword,0));
        }

        for (String review: reviews){
            String[] words = review.toLowerCase().replaceAll("[^0-9a-zA-z]\\s"," ").split(" ");
            Set<String> set = new HashSet<>();
            for (String word: words){
                set.add(word);
            }
            for (String word: set){
                if (map.containsKey(word)){
                    map.get(word).times = map.get(word).times +1;
                }
            }
        }

        PriorityQueue<KeyWord> priorityQueue = new PriorityQueue<>((keyword1,keyword2) -> {
           if (keyword1.times==keyword2.times){
               return keyword2.value.compareTo(keyword1.value);
           }else {
              return keyword2.times - keyword1.times;
           }
        });

        for (KeyWord keyWord: map.values()){
            priorityQueue.offer(keyWord);
        }

        String[] res = new String[k];
        for (int i=0; i<k;i++){
            KeyWord keyWord = priorityQueue.poll();
            res[i] = keyWord.value;
        }

        return res;
    }

    public static void main(String[] args) {
        String[] keywords = {"anacell", "cetracular", "betacellular"};
        String[] reviews = {
                "Anacell provides the best services in the city",
                "betacellular has; awesome services",
                "Best services provided by anacell, everyone should use anacell",
        };
        System.out.println(Arrays.toString(getWordList(2, keywords, reviews)));

        String[] keywords2 = {"anacell", "betacellular", "cetracular", "deltacellular", "eurocell"};
        String[] reviews2 = {
                "I love anacell Best services; Best services provided by anacell",
                "betacellular has great services",
                "deltacellular provides much better services than betacellular",
                "cetracular is worse than anacell",
                "Betacellular is better than deltacellular."
        };
        System.out.println(Arrays.toString(getWordList(2, keywords2, reviews2)));
    }
}
