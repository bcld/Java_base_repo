package com.derek.demo;

//数字是有绝对值的，负数的绝对值是它本身取反，非负数的绝对值是它本身。请定义一个方法，方法能够得到小数类型数字的绝对值并返回。请定义方法并测试。

import java.util.Scanner;

public class SelfTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一位整数:");
        int a = sc.nextInt();

        int b=abs(a);
        System.out.println("它的绝对值为："+b);


    }

    public static int abs(int a) {
        if(a<0){
            a=-a;
        }
        return a;
    }


}

