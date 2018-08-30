package SegmentTree;

/**
 * 以数组作为底层的线段树的实现
 * @author WilsonSong
 * @date 2018/8/28
 */
public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){
        this.merger = merger;
        data =  (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++){
            data[i] = arr[i];
        }
        tree = (E[])new Object[arr.length * 4 ];       //为保证树是一个满二叉树，包含n个节点最差的情况需要的空间是4n的，所以其中肯定有为空的节点
                                                        //其实线段是一颗平衡二叉树，它是一棵空树或它的左右两个子树的高度差的绝对值不超过1
        buildSegmentTree(0,0,arr.length-1);          //创建线段树
    }

    /**
     * 创建表示data区间[l...r]的线段树
     * @param treeIndex 以treeIndex为起点
     * @param L L
     * @param R R
     */
    public void buildSegmentTree(int treeIndex, int L, int R){
        if ( L == R){
            tree[treeIndex] = data[L];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = L + (R - L)/2;

        buildSegmentTree(leftTreeIndex, L, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, R);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);

    }

    /**
     * 获取线段树的大小
     * @return
     */
    public int getSize(){
        return data.length;
    }

    /**
     * 获取元素
     * @param index 元素索引
     * @return
     */
    public E get(int index){
        if (index < 0 || index > data.length){
            throw new IllegalArgumentException("index is illegal");
        }
        return data[index];
    }

    /**
     * 返回左孩子节点的索引值
     * @param index
     * @return
     */
    public int leftChild(int index){
        return 2 * index + 1;
    }

    /**
     * 返回右孩子节点的索引值
     * @param index
     * @return
     */
    public int rightChild(int index){
        return 2 * index + 2;
    }

    /**
     * 返回区间[queryL, queryR]的值
     * @param queryL queryL
     * @param queryR queryR
     * @return
     */
    public E query(int queryL, int queryR){
        return query(0,0,data.length-1, queryL,queryR);
    }

    /**
     *  以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
     * @param index
     * @param L
     * @param R
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int index, int L, int R, int queryL, int queryR){
        if (queryL < 0 || queryR < 0|| queryL > queryR || queryL > data.length || queryR > data.length){
            throw new IllegalArgumentException("Index is illegal");
        }

        if (L == queryL && R == queryR){
            return tree[index];
        }

        int mid = L + (R - L)/2;
        int leftChildIndex = leftChild(index);
        int rightChildIndex = rightChild(index);

        if (queryL > mid){
            return query(rightChildIndex, mid +1, R, queryL,queryR);
        }else if (queryR <= mid){
            return query(leftChildIndex, L, mid, queryL, queryR);
        }

        E leftResult = query(leftChildIndex,L,mid, queryL, mid);
        E rightResult = query(rightChildIndex, mid+1, R, mid +1, queryR);
        return merger.merge(leftResult,rightResult);
    }

    /**
     * 把索引为index位置的值更新为e
     * @param index index
     * @param e e
     */
    public void set(int index, E e){
        data[index] = e;
        set(0, 0,data.length-1, index, e);
    }

    /**
     * 更新以treeIndex为根节点的[L...R]区间内的index位置的元素的值
     * @param treeIndex
     * @param L
     * @param R
     * @param index
     * @param e
     */
    public void set(int treeIndex, int L, int R, int index, E e){
        if (index < 0 || index > R || index < L ||index > data.length-1){
            throw new IllegalArgumentException("Index is illegal");
        }
        if (L == R){
            tree[treeIndex] = e;
        }

        int mid = L + (R - L)/2;
        int leftTreeChild = leftChild(treeIndex);
        int rightTreeChild = rightChild(treeIndex);
        if (index <= mid){
            set(leftTreeChild, L, mid, index, e);
        }else if (index > mid ){
            set(rightTreeChild, mid +1, R, index, e);
        }
        tree[treeIndex] = merger.merge(tree[leftTreeChild], tree[rightTreeChild]);

    }

}
