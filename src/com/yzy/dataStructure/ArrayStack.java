package com.yzy.dataStructure;

import java.util.Iterator;

public class ArrayStack implements Iterable {
    private int N;
    private int[] content;

    public ArrayStack(int capacity) {
        content = new int[capacity];
    }

    private void resize(int max) {
        int[] temp = new int[max];
        for (int i = 0; i < N; i++) {
            temp[i] = content[i];
        }
        content = temp;
    }

    public void push(int value) {
        if (N == content.length) resize(content.length*2);
        content[N++] = value;
    }

    public int pop() {
        int temp = content[--N];
        content[N] = 0;//释放资源
        if(N>0&&N<content.length/4) resize(content.length/2);
        return temp;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator {
        private int index = N;

        @Override
        public boolean hasNext() {
            return isEmpty();
        }

        @Override
        public Integer next() {
            return content[--index];
        }

    }
}
