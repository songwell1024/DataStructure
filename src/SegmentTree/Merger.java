package SegmentTree;

/**
 * @author WilsonSong
 * @date 2018/8/28/028
 */
public interface Merger<E> {
    E merge(E a, E b);
}
