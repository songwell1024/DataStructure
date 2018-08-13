package stack;


/**
 * 栈
 * @author WilsonSong
 * @date 2018/8/11/011
 */
public interface Stack<E> {

    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}

