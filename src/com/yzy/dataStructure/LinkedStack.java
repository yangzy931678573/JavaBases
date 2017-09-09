package com.yzy.dataStructure;

public class LinkedStack {
    private int N;
    private Node first;

    private class Node {
        int i;
        Node next;
    }

    public boolean isEmpty() {
        return N == 0;
    }
    public int size(){
        return N;
    }
    public void push(int i){
        Node oldNode = first;
        first = new Node();
        first.i= i;
        first.next = oldNode;//注意新插入的数据是头
        N++;
    }
    public int  pop(){
        int i = first.i;
        //first.next = null;没必要，因为first指向了新的引用
        first = first.next;
        N--;
        return i;
    }
}
