package com.henry.basic;

public class Animal implements ILivable{
    // 继承fly，什么都不用写，直接调用
    // 继承SayYourName，不重写sayYourName，但是重写sayClassName
    //实现sayClassName
    @Override
    public void sayClassName() {
        System.out.println("I am an animal.");
    }
}
