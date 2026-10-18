package com.mickaelgeronimo.mastery.core.geometry;

public abstract class Shape {

    public abstract double area(); // ABSTRACT

    public void display(){ // CONCRETE
        System.out.println("This is a shape");
    }
}
