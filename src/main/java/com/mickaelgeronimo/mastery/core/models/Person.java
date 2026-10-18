package com.mickaelgeronimo.mastery.core.models;

public class Person {

    String first;
    String last;

    Person(){

    }

    Person(String first, String last){
        this.first = first;
        this.last = last;
    }

    void showName(){
        System.out.println(this.first + " " + this.last);
    }
}
