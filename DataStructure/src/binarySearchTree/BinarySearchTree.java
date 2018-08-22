package binarySearchTree;

/**
 * 二叉搜索树：特点 每一个节点的值都大于其左子树所有节点的值，小于其右子树所有节点的值
 * 树中每个元素都要具有可比较性
 * @author WilsonSong
 * @date 2018/8/16/016
 */
public class BinarySearchTree<E extends Comparable<E>> {
    /**
     * 定义树节点的私有类
     * @param <E>
     */
    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;         //根节点
    private int size;          //树的大小

    public BinarySearchTree(){
        root = null;
        size = 0;
    }

    /**
     * 获取树的大小
     * @return
     */
    public int size(){
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
     * 添加元素
     * @param e  添加的元素
     */
    public void add(E e){
        root = add(root, e);
    }

    /**
     * 递归的添加元素
     * 向以node为根的二分搜索树中插入元素e，递归算法
     * 返回插入新节点后二分搜索树的根
     * @param node  树节点
     * @param e 元素
     * @return
     */
    private Node add(Node node, E e){
        if (node == null){
            node.e = e;
        }

        if (e.compareTo(node.e) > 0){
            add(node.right, e);
        }
        if (e.compareTo(node.e) < 0){
            add(node.left, e);
        }
        return  node;
    }

    /**
     * 查询是否包含元素
     * @param e 元素
     * @return
     */
    public boolean contain(E e){
        return contains(root, e);
    }

    /**
     * 递归的查询是否包含某个元素
     * @param node 树节点
     * @param e  元素
     * @return
     */
    private boolean contains(Node node, E e){
        if (node == null){
            return  false;
        }
        if (e.compareTo(node.e) < 0){
            return contains(node.left, e);
        }else if (e.compareTo(node.e) > 0){
            return contains(node.right, e);
        }else {
            return true;
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }

    /**
     * 递归前序遍历
     * @param node
     */
    public void preOrder(Node node){
        if (node == null){
            return;
        }else {
            System.out.println(node.e);
            preOrder(node.left);
            preOrder(node.right);
        }
    }


}
