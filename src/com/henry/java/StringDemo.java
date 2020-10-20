package com.henry.java;

import java.util.Arrays;

public class StringDemo {
    public static void main(String[] args) {
        String str = "This is a string!";
        //length
        str.length();
        //equals
        str.equals("aaaa");
        //empty
        str.isEmpty();
        //contains
        str.contains("is");
        //get char
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
        }
        //char array
        char[] charArr = str.toCharArray();
        //indexOf
        str.indexOf('s');
        str.indexOf('s', 5);
        str.indexOf("This");
        //matches
        str.matches("(.*)string(.*)");
        //substring
        str.substring(3, 7);
        //replace
        str.replace('s', 'S');
        str.replace("a", "A");
        String newStr = str.replaceAll("(\\s)string", "STRING");
        System.out.println(str); //This is a string!
        System.out.println(newStr); //This is aSTRING!
        //split
        String[] strings = str.split(" ");
        System.out.println(Arrays.toString(strings));

    //String conversion
        try{
            int i = Integer.parseInt("1");
            String iStr = String.valueOf(i);
        } catch (NumberFormatException e) {
            System.out.println("this string is not a integer");
        }

        byte[] bytes ={97,98,99,100}; String bStr = new String(bytes);
        byte[] newBytes = bStr.getBytes();

        for (char c:str.toCharArray()) {
            if (c != '.' && !Character.isDigit(c)){
                System.out.println("This is not a string");
                break;
            }
        }
        Character.isDigit('1');

    //StringBuffer
        StringBuffer sb = new StringBuffer(str);
        sb.append("aaaaa");
        sb.delete(0, 5);
        sb.insert(7, "cccc");
        System.out.println(sb.toString());
        sb.replace(0, 5, " ");
        sb.reverse();
        System.out.println(sb.toString());
    }
}
