package com.yzy.test;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author by  Administrator
 * \* Date: 2017/9/10
 * \* Description:
 * \
 */

public class ClassLoaderTest {
    public static void main(String[] args) {
        try {
            Class.forName("SortTest");//必须是绝对路径？
            //Thread.currentThread().getContextClassLoader().loadClass("SortTest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
