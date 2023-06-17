package com.study.base.study;

public class Q6 {

    /**
     * abc --- A-Bb-Ccc
     * magic --- M-Aa-Ggg-Iiii-Ccccc
     * @param args
     */
    public static void main(String[] args) {
        String str = "abc";
        // 在这⾥写代码
        char[] strArr = str.toCharArray();
        String strResult = "";
        for(int i = 0; i < strArr.length; i++ ){
            String s = String.valueOf(strArr[i]);
            String currStr = s.toUpperCase();
            for(int j = i; j > 0; j--){
                currStr += s.toLowerCase();
            }
            if(i != strArr.length-1){
                currStr += "-";
            }
            strResult += currStr;
        }
        System.out.println(strResult);
    }
}
