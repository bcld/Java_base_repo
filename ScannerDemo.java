package com.itheima.test;

import java.util.Scanner;
public class ScannerDemo {

    public static void main(String[] args) {
        //创建键盘录入对象
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串：");

        //调用方法获得字符串
        String str = sc.nextLine();
        System.out.println(str);
    }
}
