package com.mickaelgeronimo.mastery.core.geometry;

public class Triangle extends Shape{

    public double base;
    public double height;

    public Triangle(double base, double height){
        this.base = base;
        this.height = height;
    }

    @Override
    public double area(){
        return 0.5 * base * height;
    }
}
