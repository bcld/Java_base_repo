package com.derek.demo;

import java.util.Objects;

public class Books {
    private String name;
    private double price;

    public Books() {
    }

    public Books(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        boolean judge=Objects.equals(name, books.name);
        if(judge){
            System.out.println("这本书籍已经存在");
        }
        return judge;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "\t\t\t" + this.name + "\t\t" + this.price;
    }

}
