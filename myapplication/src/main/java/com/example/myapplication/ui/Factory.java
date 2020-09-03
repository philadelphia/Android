package com.example.myapplication.ui;

public class Factory {
//    public static void main(String[] args) {
////        System.out.println(getCar("BMW"));
////        System.out.println(getCar("Benz"));
////        System.out.println(getCar("Ford"));
////        System.out.println(new LenovoFactoryctory().createComputer().desc());
////        System.out.println(new HPactory().createComputer().desc());
//
//    }

    public   ICar getCar(String brandName ){
        switch (brandName){
            case "BMW":
                return new BMW();
            case "Benz":
                return new BenZ();
            case "Ford":
                return new Ford();
            default:
                return null;
        }
    }
}
