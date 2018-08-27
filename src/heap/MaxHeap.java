package heap;

import arrary.Array;

/**
 * 最大堆
 * @author WilsonSong
 * @date 2018/8/27
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    /**
     * 最大堆初始化
     * @param capacity
     */
    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    /**
     * heapify 操作
     * @param arr
     */
    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        int index= parent(arr.length-1);
        for (int i = 0; i < index ; i++){
            siftDown(index);
        }
    }

    public MaxHeap(){
        data = new Array<>();
    }

    /**
     * size
     * @return
     */
    public int getSize(){
        return data.getSize();
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
        return data.isEmpty();
    }

    /**
     * f返回某一节点的父节点
     * @param index node
     * @return
     */
    private int parent(int index){
        if (index == 0 || index > data.getSize()){
            throw  new IllegalArgumentException("index has no parent node");
        }
        return (index-1)/2;
    }

    /**
     * 返回当前节点的左孩子节点
     * @param index node
     * @return
     */
    public int leftChild(int index){
        if (index == 0 || index > data.getSize()){
            throw  new IllegalArgumentException("index has no parent node");
        }
        return index * 2 + 1;
    }

    /**
     * 返回当前节点的右孩子节点
     * @param index
     * @return
     */
    public int rightChild(int index){
        if (index == 0 || index > data.getSize()){
            throw  new IllegalArgumentException("index has no parent node");
        }
        return index * 2 + 2;
    }

    /**
     * 添加元素
     * @param e
     */
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }

    /**
     * 从节点index开始元素上浮
     * @param index
     */
    public void siftUp(int index){
        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0 ){
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    /**
     * 最大堆中的元素
     * @return
     */
    public E findMax(){
        if (data.getSize() == 0){
            throw new IllegalArgumentException("This Heap has no elements");
        }
        return data.get(0);
    }

    /**
     * 取出堆中的最大元素
     * @return
     */
    public E extractMax(){
        E ret = findMax();
        data.swap(0, data.getSize()- 1);
        data.deleteLast();
        siftDown(0);
        return ret;
    }

    /**
     * 元素下沉操作
     * @param index
     */
    public void siftDown(int index){
        while (leftChild(index) <  data.getSize()){
            if (rightChild(index) < data.getSize() && data.get(leftChild(index)).compareTo(data.get(rightChild(index))) < 0){
                if (data.get(index).compareTo(data.get(rightChild(index))) < 0){
                    data.swap(index, rightChild(index));
                    index = rightChild(index);
                }
            }
            if (data.get(index).compareTo(data.get(leftChild(index))) > 0){
                break;
            }
            data.swap(index, leftChild(index));
            index = leftChild(index);
        }
    }

    /**
     *   取出堆中的最大元素，并且替换成元素e
     * @param e 元素
     * @return
     */
    public E replace(E e){

        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

}
