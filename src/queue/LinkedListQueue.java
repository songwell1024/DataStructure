package queue;

/**
 * @author WilsonSong
 * @date 2018/8/14/014
 */
public class LinkedListQueue<E> implements Queue {

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }
        public Node(){
            this(null,null);
        }
    }

    private Node front, tail;
    private int size;

    public LinkedListQueue(){
        this.front = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public void enqueue(Object o){
        if (tail == null){
            Node node = new Node((E) o);
            front = tail;
        }else {
            Node node = new Node((E) o);
            tail = tail.next;

        }
        size ++;
    }

    @Override
    public E getFront(){
        if (isEmpty()){
            throw new IllegalArgumentException("getFront failed, Queue can not be empty");
        }
        return  front.e;
    }

    @Override
    public E dequeue(){
        if (isEmpty()){
            throw new IllegalArgumentException("getFront failed, Queue can not be empty");
        }
        Node node = front;
        front = node.next;
        if(front == null)
            tail = null;
        size --;
        node.next = null;
        return node.e;
    }


}
