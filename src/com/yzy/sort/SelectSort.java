package com.yzy.sort;

public class SelectSort extends AbstractSort {
    @Override
    public void sort(Comparable[] array) {
        int N = array.length;
        for (int i = 0; i < N; i++) {
            int max = i;
            //每轮外循环都能确定好一个位置，因此j的值随着i变化的
            //当i = 0时，显然j需要是后边的所有元素，因此是i+1开始
            for (int j = i + 1; j < N; j++) {
                //而排过序的元素不能再比较了
                //注意这里比较的是max元素和其他位置元素的值
                if (aIsLessThanB(array[max], array[j])) {
                    max = j;
                }
            }
            exchange(array, i, max);
        }
    }
}
