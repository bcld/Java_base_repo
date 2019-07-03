package com.itheima.test;

/*
定义一个标准手机类Phone。
定义品牌（brand），价格（price），颜色（color）等属性，
要求私有成员变量，提供get/set方法，无参和有参构造方法
定义打电话（call）方法，要求方法能接收name参数，
在方法中打印：使用XXX的手机给YYY打电话。其中XXX表示品牌，YYY表示方法的参数name。
*/
public class Phone {
    //1. 私有成员变量
    //品牌（brand），
    private String brand;

    // 价格（price），
    private int price;

    // 颜色（color）
    private String color;

    //2. 提供get/set方法
    public String getBrand(){
        return brand;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    //3. 无参和有参构造方法
    //创建对象，有参构造方法可以初始化成员变量
    //无参
    public Phone(){}

    //满参
    public Phone(String brand, int price, String color) {
        this.brand = brand;
        this.price = price;
        this.color = color;
    }

    //4 .定义打电话（call）方法，要求方法能接收name参数，
    //在方法中打印：使用XXX的手机给YYY打电话
    public void call(String name){
        System.out.println("使用"+brand+"的手机给"+name+"打电话");
    }

}



