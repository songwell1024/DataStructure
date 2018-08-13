package arrary;

/**
 * 数组
 * @author WilsonSong
 * @date 2018/8/10
 */
public class Array<E> {

    private  E[] data;
    private int size;

    /**
     * 构造函数，传入数组容量capacity
     * @param capacity 数组容量
     */
    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    /**
     * 无参数的构造函数，默认数组容量为10
     */
    public Array(){
        this(10);
    }

    /**
     * 获取数组的元素个数
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 获取数组的容量
     * @return
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * 判断数组是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 向数组中指定位置插入元素
     * @param index 插入位置
     * @param e 插入表元素
     */
    public void add(int index, E e){
        if(size == data.length){
            resize(2*data.length);
        }
        if (index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Required index >= 0 & index <= size ");
        }
        for (int i =  size -1; i >= index; i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size ++ ;
    }

    /**
     * 向数组的末尾插入元素
     * @param e 元素
     */
    public void addLast(E e){
        add(size, e);
    }

    /**
     *向数组第一个位置插入元素
     * @param e 元素
     */
    public void addFirst(E e){
        add(0,e);
    }

    /**
     * 查询指定位置元素
     * @param index
     * @return
     */
    public E get(int index){
        if ( index < 0||index > size){
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    public E getLast(){
        return get(size -1);
    }

    public E getFirst(){
        return get(0);
    }
    /**
     * 重设指定位置元素
     * @param index
     * @return
     */
    public void set(int index, E e){
        if ( index < 0||index > size){
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }
        data[index] = e;
    }

    /**
     * 查询是否包含某个元素
     * @param e 查询的元素
     * @return
     */
    public boolean contains(E e){
        for (int i =0; i<size; i++){
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    /**
     * 查询某个元素的位置，如果不存在元素e，则返回-1
     * @param e 元素
     * @return
     */
    public int find(E e){
        for (int i = 0; i< size; i++){
            if (data [i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除索引位置的元素
     * @param index 索引
     * @return
     */
    public E delete(int index){
        if (index < 0 ||  index > size){
            throw new IllegalArgumentException("Delete failed. Index is illegal.");
        }
        E ret = data[index];
        for (int i = index +1 ; i< size; i++){
            data[i-1] = data[i];
        }
        size--;
        data[size] = null;
        if (size == data.length / 2){
            resize(data.length/2);
        }
        return ret;
    }

    /**
     * 删除第一个位置元素
     * @return
     */
    public E deleteFirst(){
        return delete(0);
    }
    /**
     * 删除最后一个位置元素
     * @return
     */
    public E deleteLast(){
         return delete(size -1);
    }

    /**
     * 删除元素
     * @param e 元素
     * @return
     */
    public void deleteElement(E e){
        int index = find(e);
        if (index != -1){
            delete(index);
        }else {
            throw new IllegalArgumentException("Delete failed. Element is not exist");
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d.\n",size,getCapacity() ));
        res.append("[");
        for (int i = 0; i < size; i++){
            res.append(data[i]);
            if (i != size -1){
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }

    /**
     * 数组扩容
     * @param newCapacity 数组容量
     */
    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++){
            newData[i] =data[i];
        }
        data = newData;
    }

}
