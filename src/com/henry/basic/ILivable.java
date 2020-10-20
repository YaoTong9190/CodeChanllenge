package com.henry.basic;

public interface ILivable {
    //默认方法
    public default void fly(){
        System.out.println("天上飞");
    }

    public default void SayYourName() {
        sayClassName();
    }

    //抽象方法
    public void sayClassName();
}
