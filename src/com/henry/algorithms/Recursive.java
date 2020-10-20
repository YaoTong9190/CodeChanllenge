package com.henry.algorithms;

import java.util.*;

public class Recursive {
    /**
     * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
     * 'A' -> 1
     * 'B' -> 2
     * …
     * 'Z' -> 26
     * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
     */
    public static void leetcode91Test(){
        String str0 = "23";
        System.out.println(leetcode91(str0.toCharArray(), str0.length()-1));
        String str1 = "232";
        System.out.println(leetcode91(str1.toCharArray(), str1.length()-1));
        String str2 = "2323";
        System.out.println(leetcode91(str2.toCharArray(), str2.length()-1));
        String str3 = "22112";
        System.out.println(leetcode91(str3.toCharArray(), str3.length()-1));
        String str4 = "1";
        System.out.println(leetcode91(str4.toCharArray(), str4.length()-1));
        String str5 = "2009";
        System.out.println(leetcode91(str5.toCharArray(), str5.length()-1));
    }
    private static int leetcode91(char[] str, int index){

        if (index<0){
            return 1;
        }

        if (str[0] == '0'){
            return 0;
        }

        if (index==0){
            return 1;
        }

        int count = 0;
        char prev = str[index-1];
        char current = str[index];
        if (current>'0'){
            //如果单独算会有k(n-1)种
            count+= leetcode91(str, index-1);
        }

        if (prev == '1' || (prev=='2' && current <= '6')){
            //如果末两位能合成一个字符，那么会有 k(n-2)种
            count += leetcode91(str, index-2);
        }

        return count;
    }

    /**
     * 找到所有长度为 n 的中心对称数。
     */
    public static void leetcode274Test(){
        System.out.println(leetcode274(3));
        System.out.println(leetcode274(4));
        System.out.println(leetcode274(5));
    }

    private static Set<String> leetcode274(int n) {
        if (n<=0){
            return null;
        }
        Set<String> set = new HashSet<>();
        if (n==1){
            set.add("0");
            set.add("1");
            set.add("8");
        }else if (n==2){
            set.add("11");
            set.add("69");
            set.add("96");
            set.add("88");
        }else {
            if (n%2!=0){
                Set<String> previousList = leetcode274(n-1);
                for (String s: previousList){
                    for (String c: new String[]{"0","1","8"} ) {
                        StringBuffer sb = new StringBuffer(s);
                        set.add(sb.insert((n-1)/2,c).toString());
                    }
                }
            }else {
                Set<String> previousList = leetcode274(n-2);
                for (String s:previousList){
                    for (String c: new String[]{"00","11","69","96","88"}) {
                        StringBuffer sb = new StringBuffer(s);
                        set.add(sb.insert((n-2)/2,c).toString());
                    }
                }
            }
        }
        return set;
    }

    /**
     * LeetCode 第 39 题：给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。
     */
    public static void leetcode39Test(){
        int[] candidates = {1,2,3,4,5,6,7,8,9};
        int target = 10;
        ArrayDeque<Integer> solution = new ArrayDeque<>();
        List<ArrayDeque<Integer>> res = new ArrayList<>();
        leetcode39(candidates, target, 0, solution, res);
        for (ArrayDeque<Integer> sol: res){
            System.out.println(sol);
        }
    }

    public static void leetcode39(int[] candidates, int target, int start, ArrayDeque<Integer> solution, List<ArrayDeque<Integer>> res) {
        if (target<0){
            return;
        }
        if (target==0){
            res.add(solution.clone());
            return;
        }

        for (int i=start; i<candidates.length;i++){
            solution.offerLast(candidates[i]); //加入一个数试一试，验证在下一次递归中实现
            leetcode39(candidates,target-candidates[i], i,solution,res);
            solution.pollLast(); //回到这步初始状态
        }
    }

    /**
     * LeetCode 第 51 题， 在一个 N×N 的国际象棋棋盘上放置 N 个皇后，每行一个并使她们不能互相攻击。给定一个整数 N，返回 N 皇后不同的的解决方案的数量。
     */
    public static void leetcode51Test(){
        int n = 4;
        List<Integer> colNums = new ArrayList<>();
        for (int col =0; col<n; col++){
            colNums.add(col);
        }
        ArrayDeque<Pair> solution = new ArrayDeque<>();
        List<ArrayDeque<Pair>> res = new ArrayList<>();
        leetcode51(n,0, colNums,solution,res);
        for (ArrayDeque<Pair> sol:res){
            System.out.println(sol);
        }
    }

    public static void leetcode51(int n, int row, List<Integer> colNums,ArrayDeque<Pair> solution, List<ArrayDeque<Pair>> res){
        // Invalid case
        if (!isValidPair(solution)){
            return;
        }
        //Find the solution
        if (row>=n){
            res.add(solution.clone());
        }
        //continue find
        for (Integer col:colNums){
            Pair pair = new Pair(row, col);
            solution.offerLast(pair);
            List<Integer> newColNums = new ArrayList<>(colNums);
            newColNums.remove(col);
            leetcode51(n,row+1,newColNums, solution, res);
            solution.pollLast();
        }
    }

    public static class Pair{
        int x;
        int y;

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private static boolean canBeAttacked(Pair pair1, Pair pair2){
        //y=x+b => y2-y1=x2-x1; y=-x+b => y2-y1= -1(x2-x1)
        int diffY = pair2.y - pair1.y;
        int diffX = pair2.x -pair1.x;
        return diffY==diffX || diffY==-diffX;
    }

    private static boolean isValidPair(ArrayDeque<Pair> solution){
        if (solution.isEmpty()){
            return true;
        }
        Pair newPair = solution.getLast();
        for (Pair p:solution){
            if (newPair==p){
                continue;
            }
            if (canBeAttacked(newPair,p)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //leetcode91Test();
        //leetcode274Test();
        //leetcode39Test();
        leetcode51Test();
    }
}
