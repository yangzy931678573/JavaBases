package com.yzy.enumAndSingleton;

public class LazyByLockSingleTon {
    private volatile static LazyByLockSingleTon singleTon;
    private LazyByLockSingleTon(){

    }
    public static LazyByLockSingleTon getSingleTon(){
        if (singleTon==null){
            synchronized (LazyByLockSingleTon.class){
                if (singleTon==null){
                    singleTon = new LazyByLockSingleTon();
                }
            }
        }
        return singleTon;
    }
}
