package com.yzy.sort;

public abstract class AbstractSort {
    public boolean aIsLessThanB(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    public void exchange(Comparable[] array,int a, int b){
         Comparable t = array[a];
         array[a] = array[b];
         array[b] = t;
    }
    public abstract  void sort(Comparable[] array);
    public void  show(Comparable[] array){
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            if ( i == array.length - 1){
                result.append(array[i]+"]");
                break;
            }
            result.append(array[i]+",");
        }
        System.out.println(result);
    }
    public boolean isSorted(Comparable[] array){
        for (int i = 1; i < array.length; i++) {
            if (aIsLessThanB(array[i],array[i-1]))return false;
        }
        return true;
    }
}
