package com.yzy.sort;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author by  Administrator
 * \* Date: 2017/9/11
 * \* Description:
 * \
 */

public class MergeSort extends AbstractSort {
    private Comparable[] aux;

    @Override
    public void sort(Comparable[] array) {
        aux = new Comparable[array.length];
        sort(array, 0, array.length - 1);
    }
    //时间复杂度为NlgN,空间复杂度为N的正比
    private void sort(Comparable[] array, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        //把原数组一分为二，分别归并，依次循环，最小到长度为1的数组
        sort(array, lo, mid);
        sort(array, mid + 1, hi);
        merge(array, lo, mid, hi);
    }

    //原地归并方法，不需要很多的额外数组,但是每次需要从原数组中拷贝数据，所以要传array作为参数
    public void merge(Comparable[] array, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;//lo为需要排序的数组的最小下标，mid为中间左标，hi为最大下标
        for (int k = 0; k <= hi; k++) {
            aux[k] = array[k];
        }
        //思路：先拷贝一份数据到auk中，把auk中的元素拿到需要排序的数组中，不是aux[i++],就是aux[j++]
        // 赋值过程中兼并比较，一次过程能确保 两个数组的元素  左小于右（升序）
        for (int k = lo; k <= hi; k++) {
            //这里mid是不变的
            if (i > mid) array[k] = aux[j++];//mid左侧已经用尽
            else if (j > hi) array[k] = aux[i++];//mid 右侧已经用尽
            else if (aIsLessThanB(aux[j], aux[i])) array[k] = aux[j++];//升序排列
            else array[k] = aux[i++];
        }
    }
}
