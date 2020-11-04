package com.henry.java;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringDemo {
    public static void main(String[] args) {
        String str = "This is a string!";
        //1. get length
        int length = str.length();
        //2. equals
        boolean isEqual = str.equals("aaaa");
        //3. check if string is empty
        if (str!=null && str.isEmpty()){
            // to do
        }
        //4. contains
        boolean isContained = str.contains("is");

        //5. get char at index
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
        }
        //6. char array
        char[] charArr = str.toCharArray();

        //7. indexOf
        int i = str.indexOf('s');
        i = str.indexOf('s', 5);
        i = str.indexOf("This");

        //8. substring
        String subStr = str.substring(3, 7);

        //9. matches
        boolean isMatched = str.matches("(.*)string(.*)");

        //10. replace
        String afterChangeStr = str.replace('s', 'S');
        afterChangeStr = str.replace("a", "A");
        afterChangeStr = str.replaceAll("(\\s)string", "STRING");
        System.out.println(str); //This is a string!
        System.out.println(afterChangeStr); //This is aSTRING!

        //11. split
        String[] strings = str.split(" ");
        System.out.println(Arrays.toString(strings));

        //Get a list of words in a sentence
        String[]words=str.toLowerCase().replaceAll("[^0-9a-zA-z]\\s"," ").split(" ");

        //12. String conversion
        try{
            int num = Integer.parseInt("1");
            String iStr = String.valueOf(num);
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
        boolean isNum = Character.isDigit('1');

        //StringBuffer
        StringBuffer sb = new StringBuffer(str);
        //1. CRUD
        sb.append("aaaaa");
        sb.delete(0, 5);
        sb.insert(7, "cccc");
        System.out.println(sb.toString());
        sb.replace(0, 5, " ");
        //2. reversion
        sb.reverse();
        //3. toString()
        System.out.println(sb.toString());

        //RegEx
        /**
         * Syntax:
         *      1. In java, use "\\" to represent \
         *      2. \w = [a-zA-Z0-9], \d=[0-9], . = Any, \s = whitespaces, \n 换行
         *      3. [abc] = a or b or c
         *         [^abc] = not a nor b nor c
         *      4. Repeat times: ？， +， *， {m,n}
         *      5. whole word \b<regex>/b
         */
        String regex = "\\bcat\\b";
        String string = "cat cat cat cattie cat";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);

        int count = 0;
        while (m.find()){
            count++;
            System.out.println("Match number:"+count++);
            System.out.println("start():" + m.start());
            System.out.println("end():"+m.end());
        }
        /**
         * Match number 1
         * start(): 0
         * end(): 3
         * Match number 2
         * start(): 4
         * end(): 7
         * Match number 3
         * start(): 8
         * end(): 11
         * Match number 4
         * start(): 19
         * end(): 22
         */
    }
}
