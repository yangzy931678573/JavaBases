package com.yzy.easyArithmetic;
/*
* 为所有点连线
* 同一条线上的点为等价关系
* 每条直线是一个分量，有唯一标识
*
*
* 设计思路：
* 维护一个以触点为索引的数组，数组元素的值指向分量相关内容
*
* *
*/

public class UnionFind {
     int count ;
     int[] id;
     int[] sz ;//权重数组
     public UnionFind(int N){
         this.count = N;
         this.id = new int[N];
         this.sz = new int[N];
         for (int i = 0; i < N; i++) {
             id[i] = i;
             sz[i] = i;
         }
     }
     public int getCount(){
         return this.count;
     }
     public boolean connected(int p,int q){
         return id[p] == id[q];
     }
    /*
    最简单的写法。。增长级是N2
    public int find(int i){
         return id[i];
     }
     public void union(int p,int q){
         if (connected(p,q)) return;
         int pid = find(p);
         int qid = find(q) ;
         //把p所在分量合并到q所在分量
         for (int i=0;i<id.length;i++){
             if (id[i]==pid) id[i] = qid;
             count--;
         }
     }*/
     //第二种quick-find,每个元素指向所在分量的其他元素
    //这实际上是一种树的结构，最坏情况下依然是N2级别
   /* public int find(int i){
        while ( i !=id[i]) i = id[i];
        return i;//i为分量的根元素
    }
    public void union(int p,int q){
        int rp = find(p);
        int rq = find(q);
        if(rp == rq) return;
        id[rp] = rq;
        count--;
    }*/

    //加权quick-find算法：维护一个针对每个分量上元素数量的一个权重数组
    public int find(int i){
        while ( i !=id[i]) i = id[i];
        return i;//i为分量树的根节点
    }
    public void union(int p,int q){
        int i = find(p);
        int j = find(q);
        if(i == j) return;
        if(sz[i] < sz[j]){
            id[i]=j;
            sz[j]+=sz[i];//把小树挂在了大树的根节点上上
        }else {
            id[j]=i;
            sz[i]+=sz[j];
        }
        count--;
    }

}
