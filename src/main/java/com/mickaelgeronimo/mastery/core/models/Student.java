package com.mickaelgeronimo.mastery.core.models;

public class Student extends Person{

    double gpa;

    public Student(String first, String last, double gpa){
        super(first, last);
        this.gpa = gpa;
    }

    void showGPA(){
        System.out.println(this.first + "'s gpa is: " + this.gpa);
    }
}
