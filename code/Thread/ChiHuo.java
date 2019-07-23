package com.derek.demo.Thread;

import java.util.ArrayList;

public class ChiHuo implements Runnable {
    private ArrayList<String> bz;

    public ChiHuo(ArrayList<String> bz) {
        this.bz = bz;
    }

    @Override
    public void run() {
        String chihuo = Thread.currentThread().getName();
        while (true) {
            synchronized (bz) {
                if (bz.size() == 0) {
                    try {
                        bz.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(""+"吃货" + chihuo + "吃掉一个" + bz.get(0));
                    bz.remove(0);
                    bz.notify();
                }
            }
        }
    }
}

//               没有包子，等待       有包子吃掉，通知
//               没有包子，生产，通知  有包子，等待