package queue;

import arrary.Array;

/**
 * @author WilsonSong
 * @date 2018/8/12/012
 */
public class ArrayQueue<E> implements Queue{

    Array<E> array;

    public ArrayQueue(int capacity){
        array = new Array<>(capacity);
    }
    public ArrayQueue(){
        array = new Array<>();
    }

    @Override
    public E dequeue(){
        return array.deleteFirst();
    }

    @Override
    public void enqueue( Object o){
        array.addLast((E) o);
    }

    @Override
    public E getFront(){
        return array.getFirst();
    }

    @Override
    public  int getSize(){
        return array.getSize();
    }

    @Override
    public boolean isEmpty(){
        return array.isEmpty();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [ ");
        for(int i = 0; i < array.getSize(); i++){
            res.append(array.get(i));
            if (i != array.getSize() - 1){
                res.append(',');
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args){
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++){
            arrayQueue.enqueue(i);
        }
        System.out.println(arrayQueue);
    }
}
