package com.henry.basic;

public class InterfaceDemo {
    public static void main(String[] args) {
        Animal a = new Animal(); // 创建子类对象，调用默认方法
        a.fly();

        ILivable livable = new Animal();
        livable.fly(); //通过接口对象，调用默认方法

        a.SayYourName(); //通过子类对象调用默认方法，默认方法中调用抽象方法
        livable.SayYourName();//通过接口对象调用默认方法，默认方法中调用抽象方法
    }
}
