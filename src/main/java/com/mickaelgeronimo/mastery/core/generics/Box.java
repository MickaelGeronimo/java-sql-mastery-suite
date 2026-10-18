package com.mickaelgeronimo.mastery.core.generics;

public class Box<T> {

    T item;

    public void setItem(T item){
        this.item = item;
    }

    public T getItem(){
        return this.item;
    }
}
