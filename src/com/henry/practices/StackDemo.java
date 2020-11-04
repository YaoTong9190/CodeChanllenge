package com.henry.practices;

import java.util.ArrayDeque;

public class StackDemo {

    public static boolean leetcode20(String input){
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            //1. store what kind of characters
            if (c=='(' || c=='[' || c=='{'){
                stack.push(c);
            }

            //2. when compare, make sure the symbol exactly match
            if (c==')'){
                if (stack.peek()=='('){
                    stack.pop();
                }else {
                    return false;
                }
            }

            if (c==']'){
                if (stack.peek()=='['){
                    stack.pop();
                }else {
                    return false;
                }
            }

            if (c=='}'){
                if (stack.peek()=='{'){
                    stack.pop();
                }else {
                    return false;
                }
            }

        }

        //3.check if the symbols comes with pairs
        if (stack.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    public int[] leetcode739(int[] T) {
        //1. Define a stack to store the index of T
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[T.length];

        //2. Traverse the array
        for (int i = 0; i < T.length; i++) {
            //if the temperature lower than the top of stack, push it into stack
            if (stack.isEmpty() || T[i] <= T[stack.peek()]){
                stack.push(i);
            }else {
                // a. popup until the top is higher than T[i]
                while (!stack.isEmpty() && T[stack.peek()]< T[i]){
                    int index = stack.poll();
                    //b. the days equals i -index
                    int days = i - index;
                    res[index] = days;
                }
                //c. push i into stack
                stack.push(i);
            }
        }
        //3. After traverse, if stack not empty, that means temperatre never goes up after those days, set the value as 0.
        while (!stack.isEmpty()){
            int index = stack.poll();
            res[index] = 0;
        }
        return  res;
    }

    public static void main(String[] args) {
        System.out.println(leetcode20("{[a(b)]}"));
        System.out.println(leetcode20("aa{{({}"));
        System.out.println(leetcode20("[{abc)]"));
    }
}
