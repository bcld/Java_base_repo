package com.derek;

public class SearchRepate {
    public static void main(String[] args) {
        int[] arr1 = {11, 23, 45, 23, 66, 23, 16, 27, 16, 11, 69, 88, 11, 45, 23, 11};
        tongJi(arr1);
    }

    public static void tongJi(int[] arr1) {
        int len = arr1.length;
        int j = 0;
        while (j < len) {
            int num = 0;
            for (int i = j; i < len; i++) {
                if (arr1[j] == arr1[i]) {
                    num++;
                }

            }
            System.out.println(arr1[j] + "出现了" + num + "次");
            j++;


            for (int i = 0; i < j & j < len; i++) {
                if (arr1[i] == arr1[j]) {
                    j++;
                    i = -1;
                }
            }
        }
    }

}
