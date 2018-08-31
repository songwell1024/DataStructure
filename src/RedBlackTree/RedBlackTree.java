package RedBlackTree;


/**
 * 红黑树的底层简单实现
 * @author WilsonSong
 * @date 2018/8/31/031
 */
public class RedBlackTree<K extends Comparable<K>, V> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;
    /**
     * 树节点的底层实现
     */
    private class Node{
        public K key;
        public V value;
        public boolean color;

        public Node leftNode;
        public Node rightnode;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            color = RED;     //从红黑树的定义来看，每个节点初始化都是红色节点
            leftNode = null;
            rightnode = null;
        }
    }

    private Node root;
    private int size;

    /**
     * 构造函数
     */
    public RedBlackTree(){
        root = null;
        size = 0;
    }

    /**
     * 获取某个key对应的node
     * @param node node
     * @param key key
     * @return Node node
     */
    public Node getNode(Node node,K key){
        if (node == null){
            return null;
        }

        if (node.key.equals(key)){
            return node;
        }else if (node.key.compareTo(key) > 0){
            return getNode(node.leftNode, key);
        }else {
            return getNode(node.rightnode, key);
        }
    }

    /**
     * 判断节点是否为红色
     * @param node node
     * @return
     */
    public boolean isRed(Node node){
        if (node == null){
            return BLACK;
        }
        return node.color;
    }


    /**
     * 为了保持红黑树性质进行左旋转
     //   node                     x
     //  /   \     左旋转         /  \
     // T1   x   --------->   node   T3
     //     / \              /   \
     //    T2 T3            T1   T2
     * @param node node
     * @return
     */
    private Node leftRotate(Node node){
        Node x = node.rightnode;
        node.rightnode = x.leftNode;
        x.leftNode = node;

        //变更节点的颜色，现在的根节点的颜色要与原来根节点颜色相同，
        //原来的根节点现在变成孩子节点，颜色是红色
        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     * 为了保持红黑树性质进行右旋转
     //     node                   x
     //    /   \     右旋转       /  \
     //   x    T2   ------->   y   node
     //  / \                       /  \
     // y  T1                     T1  T2
     * @param node node
     * @return
     */
    private Node rightRotate(Node node){

        Node x = node.leftNode;
        // 右旋转
        node.leftNode = x.rightnode;
        x.rightnode = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     * 颜色反转
     * @param node node
     */
    private void flipColors(Node node){
        node.leftNode.color = BLACK;
        node.rightnode.color = BLACK;
        node.color = RED;
    }

    /**
     * 添加元素
     * @param key key
     * @param value value
     */
    public void add(K key, V value){
        root = add(root, key, value);
        root.color = BLACK;             //根节点的颜色一定是黑色的
    }

    /**
     * 递归的向以node为根节点的树中添加元素
     * @param node node
     * @param key key
     * @param value value
     * @return 返回添加元素后的子树的根
     */
    private Node add(Node node, K key, V value){
        if (node == null){
            node = new Node(key, value);
            size ++;
            return node;
        }

        if (node.key.compareTo(key) > 0){
            node =  add(node.leftNode, key, value);
        }else if (node.key.compareTo(key) < 0){
            node =  add(node.rightnode, key, value);
        }else {
            node.value =value;
        }

        //下面的每一步都要进行判断的，其实就是一个连接整合的过程
        //右子树红色，左子树是黑色
        if (isRed(node.rightnode) && !isRed(node.leftNode))
            node = leftRotate(node);

        //左子树为红色，左子树的左子树也为红色，右旋转
        if (isRed(node.leftNode) && isRed(node.leftNode.leftNode))
            node = rightRotate(node);

        //左右子树均为红色，只要颜色反转即可
        if (isRed(node.leftNode) && isRed(node.rightnode))
            flipColors(node);

        return node;
    }

}
