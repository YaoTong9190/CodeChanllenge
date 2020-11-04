package com.henry.practices;

import java.util.*;

public class HashTableDemo {
    public static int[] leetcode1(int[] nums, int target) {
        //key stores num, value is index
        HashMap<Integer, Integer> map = new HashMap<>();

        int[] res = new int[2];

        for (int i = 0; i < nums.length; i++) {
            if (map.get(target-nums[i]) != null){
                res[0] = map.get(target-nums[i]);
                res[1] = i;
                return res;
            }

            if (!map.containsValue(i)){
                map.put(nums[i], i);
            }
        }
        return null;
    }

    public static int[] leetcode167(int[] numbers, int target) {
        //two pointers
        int left = 0;
        int right = numbers.length-1;
        int[] res = new int[2];

        while (left<right){
            if (numbers[left] + numbers[right] == target){
                res[0] = left+1;
                res[1] = right+1;
                return res;
            }
            if (numbers[left] + numbers[right] < target){
                left++;
            }

            if (numbers[left] + numbers[right] > target){
                right--;
            }
        }
        return null;
    }

    /**
     * Definition for a binary tree node.
     * */
      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }

      //错误解， 两数之和不适合用 二分法去做
    public static boolean leetcode653(TreeNode root, int k) {
        TreeNode n = root;
        TreeNode p; //node p is the nearest smaller elem to n
        if (n.left!=null){
            p = n.left;
        }else if (n.right!=null){
            p =n;
            n= n.right;
        }else {
            return false;
        }

        while (n!=null){
            System.out.println("p="+p.val+", n="+n.val);
            if (p.val+n.val == k){
                return true;
            }else if (p.val+n.val < k){
                //先走p再走n
                if (p==null){
                    n=n.right;
                }else {
                    p = p.right;
                }
            }else {
                if (p==null){
                    n=n.left;
                }else {
                    p = p.left;
                }
            }
        }
        return false;
    }

    public boolean leetcode242(String s, String t) {
        if (s.length()!=t.length()){
            return false;
        }

        Map<Character,Integer> map = new HashMap<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), map.get(s.charAt(i))+1);
            }else{
                map.put(s.charAt(i), 1);
            }

            if (map.containsKey(t.charAt(i))){
                map.put(t.charAt(i), map.get(t.charAt(i))-1);
            }else{
                map.put(t.charAt(i), -1);
            }
        }

        for (Integer num : map.values()){
            if (num!=0){
                return false;
            }
        }
        return true;
    }

    public static List<List<String>> leetcode49(String[] strs) {
          if (strs.length==1){
              List<String> list = new ArrayList<>();
              list.add(strs[0]);
              List<List<String>> res = new ArrayList<>();
              res.add(list);
              return res;
          }
        Map<String, List<Integer>> map = new HashMap<>();
        int index = 0;
        for (String str:strs){
            int[] counts = new int[26];
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i <str.length(); i++) {
                char c = str.charAt(i);
                counts[c-'a']++;
            }
            for (int i = 0; i < counts.length; i++) {
                if (counts[i]!=0){
                    while (counts[i]>0){
                        char c = (char) ('a' + i);
                        sb.append(c);
                        counts[i]--;
                    }
                }
            }
            String key = sb.toString();
            if (map.containsKey(key)){
                map.get(key).add(index);
            }else {
                map.put(key, new ArrayList<>());
                map.get(key).add(index);
            }

            index++;
        }
        List<List<String>> res = new ArrayList<>();
        for (List<Integer> list: map.values()){
            List<String> groupList = new ArrayList<>();
            for (Integer i: list){
                groupList.add(strs[i]);
            }
            res.add(groupList);
        }
        return res;
    }

    public void leetcode37(char[][] board) {
        List<Map<Character,Integer>> rowList = new ArrayList<>();
        List<Map<Character,Integer>> colList = new ArrayList<>();
        List<Map<Character,Integer>> blockList = new ArrayList<>();

        for(int i=0; i< board.length; i++){
            Map<Character,Integer> map1 = new HashMap<>();
            rowList.add(map1);
            Map<Character,Integer> map2 = new HashMap<>();
            colList.add(map2);
            Map<Character,Integer> map3 = new HashMap<>();
            blockList.add(map3);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {

                int k = getBlockIndex(i, j);

                if (board[i][j] != '.'){
                    rowList.get(i).put(board[i][j],1);
                    colList.get(j).put(board[i][j],1);
                    blockList.get(k).put(board[i][j],1);
                }
            }
        }

        fillNums(board,rowList,colList,blockList, 0 ,0);

        Arrays.toString(board);
    }

    boolean fillNums(char[][] board, List<Map<Character,Integer>> rowList, List<Map<Character,Integer>> colList, List<Map<Character,Integer>> blockList, int i, int j){

        if (i==board.length && j< board.length){
            if(j+1 == board.length){
                return true;
            }
            return fillNums(board,rowList,colList,blockList, 0, j+1);
        }

        if (board[i][j] != '.'){
            return fillNums(board,rowList,colList,blockList, i+1 ,j);
        }

        int k = getBlockIndex(i,j);
        List<Character> choice = new ArrayList<>();
        char c = '1';
        while (c<='9'){
            if (!rowList.get(i).containsKey(c)&&!colList.get(j).containsKey(c)&&!blockList.get(k).containsKey(c)){
                choice.add(c);
            }
            c++;
        }
        if (choice.size()==0){
            return false;
        }

        for (Character num: choice){
            if (board[i][j] == '.'){
                board[i][j] = num;
                rowList.get(i).put(num,1);
                colList.get(j).put(num,1);
                blockList.get(k).put(num,1);
                boolean res = fillNums(board,rowList,colList,blockList, i+1 ,j);
                if (res){
                    return res;
                }else {
                    board[i][j]='.';
                    rowList.get(i).remove(num);
                    colList.get(j).remove(num);
                    blockList.get(k).remove(num);
                }
            }

        }
        return false;
    }

    private int getBlockIndex(int i, int j){
        if (i<3){
            if (j<3){
                return 0;
            }else if (j<6){
                return 3;
            }else {
                return 6;
            }
        }else if (3<=i && i<6){
            if (j<3){
                return 1;
            }else if (j<6){
                return 4;
            }else {
                return 7;
            }
        }else {
            if (j<3){
                return 2;
            }else if (j<6){
                return 5;
            }else {
                return 8;
            }
        }
    }

    public String leetcode76(String s, String t) {
          if (s.length()<t.length()){
              return "";
          }

          Map<Character, Integer> mapT = new HashMap<>();
          Map<Character, Integer> map = new HashMap<>();

          int minLength = Integer.MAX_VALUE;
          String res = "";

          for (Character c: t.toCharArray()){
              if (mapT.containsKey(c)){
                  mapT.put(c, mapT.get(c)+1);
              }else {
                  mapT.put(c, 1);
              }
          }

          int i=0;
          int j=0;

          while (j<s.length()){
              if (isMatch(map, mapT)){
                  int length = j-i;
                  if (length<minLength){
                      minLength = length;
                      res = s.substring(i,j);
                      if (length==t.length()){
                          return res;
                      }
                  }

                  //i++ and remove s[i] from map
                  map.put(s.charAt(i),map.get(s.charAt(i))-1);
                  i++;
              }else {
                  char c = s.charAt(j);
                  if (map.containsKey(c)) {
                      map.put(c, map.get(c) + 1);
                  } else {
                      map.put(c, 1);
                  }
                  j++;
              }
          }

          while (i<s.length()) {
              if (isMatch(map, mapT)) {
                  int length = j - i;
                  if (length < minLength) {
                      minLength = length;
                      res = s.substring(i, j);
                      if (length == t.length()) {
                          return res;
                      }
                  }
              }
              map.put(s.charAt(i),map.get(s.charAt(i))-1);
              i++;
          }

          return res;
    }

    private boolean isMatch(Map<Character, Integer> map, Map<Character, Integer> mapT){
          for (char c : mapT.keySet()){
              if (map.containsKey(c) && map.get(c)>=mapT.get(c)){
                  continue;
              }else {
                  return false;
              }
          }
          return true;
    }
}
