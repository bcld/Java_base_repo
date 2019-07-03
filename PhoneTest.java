package com.itheima.test;

/*
    create by dee on 2019/6/21
*/
public class PhoneTest {
    public static void main(String[] args) {
        //创建手机对象
        Phone p = new Phone();
        p.setBrand("小米");
        //p.setPrice(2999);
        p.call("林志玲");

        //使用有参构造方法创建对象
        Phone p2 = new Phone("华为",2999,"白色");
        p2.call("马云");
    }
}
