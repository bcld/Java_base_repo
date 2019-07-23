package com.derek.demo.Thread;

import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) {
        //共同的容器
        ArrayList<String> longzi = new ArrayList<>();

        ChiHuo ch=new ChiHuo(longzi);
        BaoZiPu bzp=new BaoZiPu(longzi);
        Thread t1 = new Thread(ch);
        Thread t2 = new Thread(bzp);

        t1.start();
        t2.start();


    }
}
