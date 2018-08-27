package Map;

/**
 * 基于二分搜索树实现Map
 * @author WilsonSong
 * @date 2018/8/24/024
 */
public class BSTMap<K extends Comparable<K>, V>  implements Map<K, V> {


    /**
     * 二分搜索树节点的实现
     */
    private class Node{
        public K key;
        public V value;
        public Node left;
        private Node right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public Node root;      //树的根节点
    public int size;       //树的大小

    public BSTMap(){
        size = 0;
        root = null;
    }

    /**
     * 树的大小
     */
    @Override
    public int getSize(){
        return size;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 添加元素
     * @param key
     * @param value
     */
   public void add(K key, V value){
        root = add(root, key, value);
   }

    /**
     * 递归的向node为节点的树中添加元素
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node add(Node node, K key, V value){
       if (node == null){
           size ++;
           return node = new Node(key,value);
       }
       if (key.compareTo(node.key) > 0){
           node.right = add(node.right,key,value);
       }else if (key.compareTo(node.key) < 0){
           node.left = add(node.left, key, value);
       }else {
           node.value = value;
       }
       return node;
    }

    /**
     * 递归查询key对应的节点
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node,K key){
        if (node == null){
            return node;
        }
        if (node.key.equals(key)){
            return node;
        }else if (node.key.compareTo(key) > 0){
            return getNode(node.left, key);
        }else{
            return getNode(node.right, key);
        }
    }

    /**
     * 包含某个元素
     * @param key
     * @return
     */
    @Override
    public boolean contains(K key){
        return getNode(root,key)!= null;
    }

    /**
     * 获取key对应的value
     * @param key
     * @return
     */
    @Override
    public V get(K key){
        return contains(key)? getNode(root,key).value:null;
    }

    /**
     * 更新key 对应的value
     * @param key
     * @param newValue
     */
    @Override
    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if (node == null){
            throw new IllegalArgumentException("Key don't exist");
        }
        node.value = newValue;
    }

    /**
     * 返回以node为根的子树最小值的节点
     * @param node
     */
     public Node minimum(Node node){
        if (node == null){
            return node;
        }
        else {
            return minimum(node.left);
        }
     }

    /**
     * 删除以node为根节点的树中的最小元素，并返回新的子树的根
     * @param node
     * @return
     */
     public Node removeMin(Node node){
         if (node.left == null){
             Node delNode = minimum(node);
             node.left = delNode.right;
             delNode.right = null;
             size--;
             return node;
         }
        node.left =  removeMin(node.left);
         return node;
     }


    /**
     * 删除key对应的节点，并返回该key对应的值
     * @param key
     * @return
     */
    @Override
     public V remove(K key){
         return contains(key)?remove(root, key).value: null;
     }

    /**
     * 删除以node为子节点的树的值，并返回新的子树的根
     * @param node
     * @param key
     * @return
     */
     private Node remove(Node node, K key){

         if (node == null){
             return null;
         }

         if (key.compareTo(node.key) > 0){
             node.right = remove(node.right, key);
             return node;
         } else if (key.compareTo(node.key) < 0){
             node.left = remove(node.left, key);
             return node;
         }else {
             if (node.left == null){
                 Node rightNode = node.right;
                 node.right = null;
                 size --;
                 return rightNode;
             }
             if (node.right == null){
                 Node leftNode = node.left;
                 node.left = null;
                 size--;
                 return leftNode;
             }

             Node successor  = minimum(node);
             successor.left = node.left;
             successor.right = removeMin(node.right);
             node.left = null;
             node.right = null;
             return successor;
         }
     }

}
