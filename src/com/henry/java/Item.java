package com.henry.java;

public class Item {
    String key;
    Integer value;

    public Item(String key, Integer value){
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Item{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
