package com.example.myapplication.ui;

public class LenovoFactory implements BMWFactory {
    @Override
    public Computer createComputer() {
        return new LenovoComputer();
    }
}
