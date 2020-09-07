package com.example.myapplication.ui;

public class BMW320Factory implements BMWFactory {
     public   BMW createCar(){
              return new BMW320();
       }
}
