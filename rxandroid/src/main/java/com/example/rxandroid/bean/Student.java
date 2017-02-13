package com.example.rxandroid.bean;

import java.util.List;

/**
 * Author:   Tao.ZT.Zhang
 * Date:     2017/2/13.
 */

public class Student {
    private int age;
    private String  name;
    private List<Course> courseList;

    public Student(int age, List<Course> courseList, String name) {
        this.age = age;
        this.courseList = courseList;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", courseList=" + courseList +
                '}';
    }
}
