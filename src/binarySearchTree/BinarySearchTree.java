package binarySearchTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉搜索树：特点 每一个节点的值都大于其左子树所有节点的值，小于其右子树所有节点的值
 * 树中每个元素都要具有可比较性
 * @author WilsonSong
 * @date 2018/8/16/016
 */
public class BinarySearchTree<E extends Comparable<E>> {
    /**
     * 定义树节点的私有类
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
            size ++;
            return new Node(e);
        }

        if (e.compareTo(node.e) > 0){
            node.right = add(node.right, e);
        }
        if (e.compareTo(node.e) < 0){
           node.left= add(node.left, e);
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

    /**
     * 非递归的实现二分搜索树的前序遍历
     */
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();

        stack.push(root);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null){
                stack.push(cur.right);
            }
            if (cur.left  != null){
                stack.push(cur.left);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder(){
        inOrder(root);
    }

    /**
     * 递归的实现中序遍历
     * @param node 节点
     */
    public void inOrder(Node node){
        if(node == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);

    }


    /**
     * 后序遍历
     */
    public void postOrder(){
        postOrder(root);
    }

    /**
     * 递归的实现后序遍历
     * @param node 节点
     */
    private void postOrder(Node node){

        if(node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 层序遍历
     * 使用队列先进先出
     */
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node cur= queue.remove();
            if (cur.left != null){
                queue.add(cur.left);
            }
            if (cur.right != null){
                queue.add(cur.right);
            }
        }
    }

    /**
     * 查找树中最小元素
     * @return
     */
    public E minimum(){
        if (size == 0){
            throw  new IllegalArgumentException("BST can not be empty");
        }
        return minimum(root).e;
    }

    /**
     * 查找树中最小元素的节点
     * @param node
     * @return
     */
    private Node minimum(Node node){
        if (node.left == null){
            return null;
        }
        return minimum(node.left);
    }

    /**
     * 查找树中最大元素
     * @return
     */
    public E maximum(){
        if (size == 0){
            throw  new IllegalArgumentException("BST can not be empty");
        }
        return maximum(root).e;
    }

    /**
     * 查找树中最大元素的节点
     * @param node
     * @return
     */
    private Node maximum(Node node){
        if (node.right == null){
            return node;
        }
        return maximum(node.right);
    }

    /**
     * 删除最小元素
     * @return 返回最小元素的值
     */
    public  E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 删除最小元素的节点，并返回删除该节点之后新的二分搜索树的根
     * @param node 树节点
     * @return
     */
    public Node removeMin(Node node){
        if (node.left == null && node.right != null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除最大元素，并返回最大元素的值
     * @return
     */
    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    /**
     * s删除最大元素节点并返回删除该节点之后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMax(Node node){
        if (node.right == null && node.left != null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除元素
     * @param e 元素
     */
    public void remove(E e){
        root = remove(root, e);
    }

    /**
     * 删除以node为根节点的树的元素。并返回删除节点后的新的子树的根
     * @param node 根节点
     * @param e 待删除元素
     */
    private Node remove(Node node, E e){
        if (node == null){
            return null;
        }
        if (e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
            return node;
        }else if (e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }else {
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size -- ;
                return rightNode;
            }
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = node.right;
            successor.left = node.left;
            node.left = node.right = null;

            return successor;

        }
    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res){

        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e +"\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++)
            res.append("--");
        return res.toString();
    }


    public static void main(String[] args){
        int[] a = {1,2,5,6,7};
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < a.length; i++){
            bst.add(a[i]);
        }
        bst.preOrder();
        System.out.println("_______________________________________");
        bst.preOrderNR();
    }


}
