package queue;

/**
 * @author WilsonSong
 * @date 2018/8/12/012
 */
public interface Queue<E> {
    void enqueue(E e);
    E dequeue();
    E getFront();
    int getSize();
    boolean isEmpty();

}
