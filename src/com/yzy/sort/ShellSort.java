package com.yzy.sort;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author by  Administrator
 * \* Date: 2017/9/10
 * \* Description:
 * \
 */

public class ShellSort extends AbstractSort {

    @Override
    public void sort(Comparable[] array) {
        int N = array.length;
        int h = 1;
        while (h <= N / 3)
            h = h * 3 + 1;
        while (h >= 1) {
            for (int i = 1; i < N; i++) {
                for (int j = i; j >= h && aIsLessThanB(array[j], array[j - 1]); j -= h) {
                    exchange(array, j, j - 1);
                }
            }
            //h在每一次插入排序结束时变化，因此需要额外的循环
            h /= 3;
        }


    }
}
