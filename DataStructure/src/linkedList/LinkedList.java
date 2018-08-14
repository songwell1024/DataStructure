package linkedList;

/**
 * 链表
 * @author WilsonSong
 * @date 2018/8/14
 */
public class LinkedList<E> {

    /**
     * 节点类
     */
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }
        public  Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private  int size;   //链表大小
    private Node dummyHead;  //虚拟头结点

    /**
     * 获取链表空间大小
     * @return
     */
    public int getrSize(){
        return size;
    }

    /**
     * 链表是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     *  向链表中任意位置添加元素
     * @param index 添加元素的位置
     * @param e 添加的元素
     */
    public void add(int index, E e){
        if (index < 0 || index >size){
            throw new IllegalArgumentException("Add failed, index is illegal");
        }

        Node prev = dummyHead;
        for (int i =0; i < index; i++){
            prev = prev.next;
        }
        Node node = new Node(e);
        node.next = prev.next;
        prev.next = node;
        size ++;
    }

    /**
     * 链表的头部添加元素
     * @param e 添加的元素
     */
    public void addFirst(E e){
         add(0,e);
    }

    /**
     * 链表的尾部添加元素
     * @param e 添加的元素
     */
    public void addLast(E e){
        add(size-1,e);
    }

    /**
     * 获取元素
     * @param index 元素的索引位置
     * @return
     */
    public E get(int index){
        if (index < 0 || index >size){
            throw new IllegalArgumentException("Add failed, index is illegal");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i< index; i++){
            cur = cur.next;
        }
        return cur.e;
    }

    /**
     * 获取头部元素
     * @return
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * 获取尾部元素
     * @return
     */
    public E getLast(){
        return get(size-1);
    }

    /**
     * 判断是否包含元素e
     * @param e 元素
     * @return
     */
    public boolean contains(E e){
        Node node = dummyHead.next;
        while (node != null){
            if ((node.e).equals(e)){
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * 删除元素
     * @param index 元素索引
     * @return
     */
    public E delete(int index){
        if (index < 0 || index >size){
            throw new IllegalArgumentException("Add failed, index is illegal");
        }
        Node prev = dummyHead;
        for (int i= 0; i< index; i++){
            prev = prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size --;

        return retNode.e;
    }

    /**
     * 从链表中删除第一个元素, 返回删除的元素
     */
    public E deleteFirst(){
        return delete(0);
    }

    /**
     * 从链表中删除最后一个元素, 返回删除的元素
     * @return
     */
    public E deleteLast(){
        return delete(size - 1);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("LinkedList: ");
        Node cur = dummyHead.next;
        while (cur != null){
            res.append(cur);
            res.append("-->");
        }
        res.append("NULL");
        return res.toString();
    }





}
