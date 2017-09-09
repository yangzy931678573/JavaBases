package com.yzy.easyArithmetic;



public class BinarySearch {
    public static int rank(int[] array, int key) {
        return rank(0, array.length - 1, array, key);
    }

    public static int rank(int lo, int hi, int[] array, int key) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (array[mid] > key) return rank(mid + 1, hi, array, key);
        if (array[mid] < key) return rank(lo, mid - 1, array, key);
        return mid;
    }

    public static int rankTwo(int[] array, int key) {
        int lo = 0;
        int hi = array.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (array[mid] < key) lo = mid + 1;
            else if (array[mid] > key) hi = mid - 1;
            else return mid;
        }
        return -1;
    }
}