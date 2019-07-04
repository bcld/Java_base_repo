package com.derek.demo;

import java.util.Random;

public class Verify_Code_4 {
    public static void main(String[] args) {
        System.out.println("请输入以下验证码：");

        Random r = new Random();
        int code;

        for (int i = 0; i < 4; i++) {
            int num = r.nextInt(3);//create 0-1
            switch (num) {
                case 0:
                    code = r.nextInt(26) + 65;//小写
                    System.out.print((char) code);
                    break;
                case 1:
                    code = r.nextInt(26) + 97;//大写
                    System.out.print((char) code);
                    break;
                default:
                    code = r.nextInt(10);//number
                    System.out.print(code);
                    break;

            }
        }

    }
}
