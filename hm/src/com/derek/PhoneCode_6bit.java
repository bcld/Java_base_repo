package com.derek.demo;

import java.util.Arrays;
import java.util.Random;

public class PhoneCode_6bit {
    public static void main(String[] args) {
        Random r = new Random();
        //初始化数组，不能动态初始化，否则不会出现0；
        int[] arr = {-1, -1, -1, -1, -1, -1};
        int num;
        int i = 0;


        while (i < 6) {
            num = r.nextInt(10);
            for (int j = 0; j <arr.length; j++) {
                if (num == arr[j]) {
                    break;
                }
                if (j == 5) {
                    arr[i] = num;
                    i++;
                }
            }
        }
        //打印出验证码
        System.out.println(Arrays.toString(arr));
    }
}
