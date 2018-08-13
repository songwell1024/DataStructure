import arrary.Array;

/**
 * @author WilsonSong
 * @date 2018/8/10/010
 */
public class Main {
    public static void main(String[] args){
        Array<Integer> arr = new Array(10);
        for (int i = 0; i < 10; i++){
            arr.addLast(i);
        }
        System.out.println(arr);
        arr.add(1,100);
        System.out.println(arr);
    }
}
