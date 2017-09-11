package com.yzy.test;

import com.yzy.sort.AbstractSort;
import com.yzy.sort.InsertSort;
import com.yzy.sort.MergeSort;
import com.yzy.sort.ShellSort;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author by  Administrator
 * \* Date: 2017/9/10
 * \* Description:
 * \
 */

public class SortTest {
    public static void main(String[] args) {
        Integer[] array = {1,2,6,7,4,4,3,10};
        AbstractSort sort = new MergeSort();
        sort.sort(array);
        sort.show(array);
    }
}
