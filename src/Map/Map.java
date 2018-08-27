package Map;

/**
 * @author WilsonSong
 * @date 2018/8/24/024
 */
public interface Map<K, V> {
    int getSize();
    boolean isEmpty();
    boolean contains(K key);
    void add(K key, V value);
    V remove(K key);
    void set(K key, V newValue);
    V get(K key);


}
