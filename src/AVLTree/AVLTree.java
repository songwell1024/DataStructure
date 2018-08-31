package AVLTree;

import java.util.ArrayList;

/**
 * @author WilsonSong
 * @date 2018/8/30/030
 */
public class AVLTree<K extends Comparable<K>, V> {

    /**
     * 节点私有类初始化
     */
    private class Node{
        public K key;
        public V value;
        public int height;

        public Node leftNode;
        public Node rightNode;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            height = 1;
            leftNode = null;
            rightNode = null;
        }
    }

    private int size;    //树空间
    private Node root;   //根节点

    /**
     * 构造函数
     */
    public AVLTree(){
        root = null;
        size = 0;
    }

    /**
     * size
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 树是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 是否是二分搜索树
     * 其实就是根据二分搜索树的性质中序遍历后是从小到大顺序排序，不满足该条件即不是二分搜索树
     * @return
     */
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList();
        inOrder(root, keys);
        for ( int i = 1; i < keys.size(); i++ ){
            if (keys.get(i-1).compareTo(keys.get(i)) > 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 中序遍历
     * @param node 以node为根的子树
     * @param keys  keys
     */
    private void inOrder(Node node, ArrayList<K> keys){
        if (node == null){
            return;
        }
        inOrder(node.leftNode, keys);         //执行到node.leftNode == null的时候就跳出这一次的inOrder,执行下面的语句
        keys.add(node.key);
        inOrder(node.rightNode,keys);
    }

    /**
     * 是否是一颗平衡树
     * @return
     */
    public boolean isBalanced(){
        return isBalanced(root);
    }

    /**
     * 判断以node为根节点的树是否平衡
     * 其实就是看树中每个节点的平衡因子的绝对值是否大于1
     * @param node node
     * @return
     */
    private boolean isBalanced(Node node){
        if (node == null){
            return true;
        }
        if (balanceFactor(node) > 1 || balanceFactor(node) < -1){
            return false;
        }
        return isBalanced(node.leftNode) && isBalanced(node.rightNode);
    }

    /**
     * 获取某一个节点的高度值
     * @param node
     * @return
     */
    public int getHeight(Node node){
        if (node == null){
            return 0;
        }
        return node.height;
    }

    /**
     * 获取某个节点的平衡因子
     * 平衡因子其实就是左孩子的高度减去右孩子的高度,有正负
     * @param node
     * @return
     */
    public int balanceFactor(Node node){
        if (node == null){
            return 0;
        }
        return getHeight(node.leftNode) - getHeight(node.rightNode);
    }

    /**
     * 对以node为根节点的树进行右旋转
     // 对节点y进行向右旋转操作，返回旋转后新的根节点x
     //        y                              x
     //       / \                           /   \
     //      x   T4     向右旋转 (y)        z     y
     //     / \       - - - - - - - ->    / \   / \
     //    z   T3                       T1  T2 T3 T4
     //   / \
     // T1   T2
     * @param node node
     * @return 旋转后新的子树的根节点
     */
    public Node rightRoate(Node node){
        Node x = node.leftNode;
        Node T3 = x.rightNode;
        x.rightNode = node;
        node.leftNode = T3;

        //旋转完成之后更新树的高度,其实只有x和y的高度发生变化
        x.height = Math.max(getHeight(x.leftNode), getHeight(x.rightNode)) + 1;
        node.height = Math.max(getHeight(node.leftNode), getHeight(node.rightNode)) + 1;

        return x;
    }

    /**
     * 对以node为根节点的树进行左旋转
     // 对节点y进行向左旋转操作，返回旋转后新的根节点x
     //    y                             x
     //  /  \                          /   \
     // T1   x      向左旋转 (y)       y     z
     //     / \   - - - - - - - ->   / \   / \
     //   T2  z                     T1 T2 T3 T4
     //      / \
     //     T3 T4
     * @param node node
     * @return 返回旋转后新的子树的根
     */
    public Node leftRoate(Node node){
        Node x = node.rightNode;
        Node T2 = x.leftNode;
        x.leftNode = node;
        node.rightNode = T2;
        //旋转完成之后更新树的高度，其实只有x和y的高度发生变化
        x.height = Math.max(getHeight(x.leftNode), getHeight(x.rightNode)) + 1;
        node.height = Math.max(getHeight(node.leftNode), getHeight(node.rightNode)) + 1;

        return x;
    }

    /**
     * 插入元素
     * @param key
     * @param value
     */
    public void add(K key, V value){
        root = add(root, key, value);
    }

    /**
     * 向以node为根节点的树上插入
     * @param node node
     * @param key key
     * @param value value
     * @return
     */
    private Node add(Node node, K key, V value){
        if (node == null){
            node = new Node(key,value);
            size ++;
            return node;
        }

        if (node.key.compareTo(key) > 0){
            node.leftNode = add(node.leftNode, key, value);
        }else if (node.key.compareTo(key) < 0){
            node.rightNode = add(node.rightNode, key, value);
        }else{
            node.value = value;
        }

        //更新树的高度
        node.height = Math.max(getHeight(node.leftNode), getHeight(node.rightNode)) + 1;

        //LL
        if (balanceFactor(node) >  1 && (balanceFactor(node.leftNode) > 0)){
            node = rightRoate(node);
        }
        //RR
        if (balanceFactor(node) < -1 && (balanceFactor(node.rightNode ) > 0)){
            node = leftRoate(node);
        }

        //LR
        //        y      -                 y                                x
        //       / \                     /  \                             /   \
        //      x   T4    向左旋转(x)   z    T4        向右旋转 (y)       z     y
        //     / \        - - - - - >  / \           - - - - - - - ->    / \   / \
        //    z   T3                  x  T3                             T1  T2 T3 T4
        //       / \                 /  \
        //     T1   T2              T1  T2
        if (balanceFactor(node) > 1 && (balanceFactor(node.leftNode) < 0)){
            node.leftNode = leftRoate(node.leftNode);
            node = rightRoate(node);
        }

        //RL
        //      y      -                 y                                    z
        //     / \                      /  \                                /   \
        //    T1  x         向右旋转(x) T1  z             向左旋转 (y)      y    x
        //       / \        - - - - - >    / \           - - - - - - - ->  / \   / \
        //      z   T4                    T2  x                           T1  T2 T3 T4
        //     / \                          /  \
        //   T2   T3                       T3  T4
        if (balanceFactor(node) < -1 && (balanceFactor(node.rightNode) > 0)){
            node.rightNode = rightRoate(node.rightNode);
            node = leftRoate(node);
        }

        return node;
    }

    /**
     * 返回以node为根的子树的key所在的节点
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node, K key){
        if (node == null){
            return null;
        }
        if (node.key.equals(key)){
            return node;
        }else if (node.key.compareTo(key) > 0){
            return getNode(node.leftNode, key);
        }else {
            return getNode(node.rightNode, key);
        }
    }

    /**
     * 是否包含k这个节点
     * @param key key
     * @return
     */
    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    /**
     * 返回key节点对应的value
     * @param key key
     * @return
     */
    public V get(K key){
        Node node = getNode(root, key);
        return node == null? null : node.value;
    }

    /**
     * 更新某个节点的值
     * @param key
     * @param value
     */
    public void set(K key, V value){
        Node node = getNode(root, key);
        if (node == null){
            throw new IllegalArgumentException("This key does not contains");
        }
        node.value = value;
    }

    /**
     * 删除某个节点对应的元素
     * @param key
     * @return
     */
    public V remove(K key){
        Node node = getNode(root,key);
        if (node == null){
            throw  new IllegalArgumentException("Error");
        }
        root = remove(root, key);
        return node.value;
    }

    /**
     * 返回树中最小的元素的节点
     * @param node
     * @return
     */
    public Node minimum(Node node){
        if (node == null){
            return null;
        }
        if (node.leftNode == null){
            return node;
        }
        return minimum(node.leftNode);
    }

    /**
     * 删除node为节点的树中key的元素
     * @param node
     * @param key
     * @return
     */
    private Node remove(Node node, K key){

        if (node == null){
            return null;
        }
        Node retNode;

        if (node.key.compareTo(key) > 0){
            node.leftNode = remove(node.leftNode, key);
            retNode = node;
        }else if (node.key.compareTo(key) < 0){
            node.rightNode = remove(node.rightNode, key);
            retNode = node;
        }else {

            //左子树为空
            if (node.leftNode == null){
                Node right = node.rightNode;
                node.rightNode = null;
                retNode = right;
                size--;
            }
            //右子树为空
            else if (node.rightNode == null){
                Node left = node.leftNode;
                node.leftNode = null;
                retNode = left;
                size --;
            }
            //左右子树均不为空的时候
            else {
                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.rightNode);
                successor.rightNode = remove(node.rightNode, successor.key);
                successor.leftNode = node.leftNode;

                node.leftNode = node.rightNode = null;
                 retNode = successor;
            }

            if (retNode == null){
                return null;
            }
            retNode.height = Math.max(getHeight(retNode.rightNode), getHeight(retNode.leftNode));

            //LL
            if (balanceFactor(retNode) >  1 && (balanceFactor(retNode.leftNode) > 0)){
                retNode = rightRoate(retNode);
            }
            //RR
            if (balanceFactor(retNode) < -1 && (balanceFactor(retNode.rightNode ) > 0)){
                retNode = leftRoate(retNode);
            }
            //LR
            if (balanceFactor(retNode) > 1 && (balanceFactor(retNode.leftNode) < 0)){
                retNode.leftNode = leftRoate(retNode.leftNode);
                retNode = rightRoate(retNode);
            }
            //RL
            if (balanceFactor(node) < -1 && (balanceFactor(node.rightNode) > 0)){
                retNode.rightNode = rightRoate(retNode.rightNode);
                retNode = leftRoate(retNode);
            }
        }
        return retNode;
    }
}
