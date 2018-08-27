package Map;

/**
 * 以链表为底层基础实现map
 * @author WilsonSong
 * @date 2018/8/24/024
 */
public class LinkedListMap <K,V> implements Map<K,V> {

    /**
     * 初始化链表
     * 没有泛型的
     */
    private class Node{
        public Node next;
        public K key;
        public V value;

        public Node (K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public Node(){
            this(null, null, null);
        }

        @Override
        public String toString(){
            return key.toString() + " : " + value.toString();
        }
    }

    public Node dummyHead;   //虚拟头结点
    public int size;  //链表的大小

    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }

    /**
     * 获取size
     * @return
     */
    @Override
    public int getSize(){
        return size;
    }

    /**
     * 链表是否为空
     * @return
     */
    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 获取key对应的节点
     * @param key key
     * @return
     */
    private Node getNode(K key){
        Node cur = dummyHead.next;
        while (cur.next != null){
            if (cur.key.equals(key)){
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }


    /**
     * 查询树中包含某个key
     * @param key
     * @return
     */
    @Override
    public boolean contains(K key){
        return getNode(key) != null;
    }


    /**
     * 向Map中添加元素
     * @param key key
     * @param value value
     */
    @Override
    public void add(K key, V value){
        if (!contains(key)){
          Node prev = new Node(key,value);
          prev.next = dummyHead.next;
          dummyHead.next = prev;
          size ++;
        }
    }

    @Override
    public V remove(K key) {
        if (!contains(key)){
            throw new IllegalArgumentException("Error: Map do not contain this key");
        }

        Node prev = dummyHead;
        while (prev.next != null){
            if (prev.next.key.equals(key)){
                Node delNode  = prev.next;
                prev.next = delNode.next;
                delNode.next= null;
                size--;
                return delNode.value;
            }
            prev = prev.next;
        }
        return null;
    }


    /**
     * 更新key对应的value
     * @param key key
     * @param newValue newValue
     */
    public void set(K key, V newValue){
        Node node = getNode(key);
        if (node != null){
            node.value = newValue;
        }
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");
    }

    /**
     * 获取key对应的value
     * @param key key
     * @return
     */
    @Override
    public V get(K key){
       return contains(key)? getNode(key).value : null;
    }

}
