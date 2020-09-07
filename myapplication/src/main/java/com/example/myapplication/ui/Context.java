package com.example.myapplication.ui;

public class Context {
    public static void main(String[] args) {
//        https://blog.csdn.net/jason0539/article/details/23020989
       //简单工厂模式，又称静态工厂模式
        Factory factory = new Factory();
        System.out.println(factory.getCar("BMW"));
        System.out.println(factory.getCar("Benz"));
        System.out.println(factory.getCar("Ford"));

        //工厂方法模式
        BMW320Factory bmw320Factory = new BMW320Factory();
        BMW520Factory bmw520Factory = new BMW520Factory();
        System.out.println(bmw320Factory.createCar());
        System.out.println(bmw520Factory.createCar());

    }

}
