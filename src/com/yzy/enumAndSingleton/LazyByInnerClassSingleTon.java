package com.yzy.enumAndSingleton;

public class LazyByInnerClassSingleTon {
    // private static LazyByInnerClassSingleTon singleTon ;//不能用final修饰，有问题
    private static class CreateSingleTon{
        private final static LazyByInnerClassSingleTon singleTon  = new LazyByInnerClassSingleTon();
    }

    private LazyByInnerClassSingleTon(){}

    public static LazyByInnerClassSingleTon getInstance(){
        return CreateSingleTon.singleTon;
    }
}
