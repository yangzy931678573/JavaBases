package com.yzy.dataStructure;

public class LinkedQueue {
    private Node first;
    private Node last;
    private int N;
    private class Node{
        Node next;
        int i;
    }
    public boolean isEmpty(){
        return N==0;
    }
    public int size(){
        return N;
    }
    //向队列的队尾中添加元素
    public void enqueue(int i){
       Node oldNode = last;//要修改最后一个元素。就先保存原来的元素
       last  = new Node();
       last.i = i;
       if (isEmpty()) first = last;
       else oldNode.next = last;//当队列是空时，不能把last.next的引用指向last
        N++;
    }
    //从队列的队首取出元素
    public int dequeue(){

        int result = first.i;
        first = first.next;
        N--;
        if (isEmpty()) last = null;
        return result;
    }

    public static void main(String[] args) {
        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.enqueue(1);
        linkedQueue.dequeue();
        linkedQueue.dequeue();
        System.out.println(linkedQueue);
    }
}
