package com.yzy.sort;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author by  Administrator
 * \* Date: 2017/9/10
 * \* Description:插入排序
 * \
 */

public class InsertSort extends AbstractSort {
    //插入排序的 关键：把当前元素插入已经    有序   的数组中
    @Override
    public void sort(Comparable[] array) {
        int N = array.length;
        //i为0时，只有一个元素。不需要插入
        for (int i = 1; i < N; i++) {
            //当下标为i的元素比前边的元素小时，交换位置；当前元素比前边的元素大直接短路循环
            // 循环时 比较的元素下标变小，因为数组已经是有序的，因此只需要确定当前元素的位置即可
            for (int j = i; j >= 1 && aIsLessThanB(array[j], array[j - 1]); j--)
                exchange(array,j,j-1);
        }
    }
}
