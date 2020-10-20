package com.henry.amazonOA;

public class FreshPromotion {
    public static int isWinner(String[][] codeList, String[] shoppingCart){
        if (shoppingCart==null){
            return 0;
        }
        if (codeList==null){
            return 1;
        }
        int res = 0;

        int i=0;
        int j=0;
        while (i < shoppingCart.length) {
            if (shoppingCart[i].equals(codeList[j][0])){
                int k=0;
                while (i<shoppingCart.length && k<codeList[j].length ){
                    if (shoppingCart[i].equals(codeList[j][k])||codeList[j][k].equals("anything")){
                        i++;
                        k++;
                    }else {
                        break;
                    }
                }
                if (k==codeList[j].length){
                    j++;
                    if (j==codeList.length){
                        return 1;
                    }
                }
            }else {
                i++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String[][] codeList = new String[2][];
        codeList[0]=new String[]{"apple", "apple"};
        codeList[1]= new String[]{"banana", "anything", "banana"};

        String[] shoppingCart1 = new String[]{"orange", "apple", "apple", "banana", "orange", "banana"};
        String[] shoppingCart2 = new String[]{"banana", "orange", "banana", "apple", "apple" };
        String[] shoppingCart3 = new String[]{"apple", "banana", "apple", "banana", "orange", "banana"};
        String[] shoppingCart4 = new String[]{"apple", "apple", "apple", "banana"};

        System.out.println(isWinner(codeList,shoppingCart1));
        System.out.println(isWinner(codeList,shoppingCart2));
        System.out.println(isWinner(codeList,shoppingCart3));
        System.out.println(isWinner(codeList,shoppingCart4));
    }
}
