package com.derek.demo.Thread;

import java.util.ArrayList;

public class BaoZiPu implements Runnable {
    private ArrayList<String> bz;
    private int no=0;

    public BaoZiPu(ArrayList<String> bz) {
        this.bz = bz;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (bz) {
                if (bz.size() == 0) {
                    no++;
                    bz.add("包子"+no);
                    System.out.println("包子铺做好一个包子，编号是"+no);
                    bz.notify();
                } else {
                    try {
                        bz.wait(); //只能接收异常，重写的方法本身不抛出异常
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
