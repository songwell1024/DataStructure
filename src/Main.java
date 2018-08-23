import arrary.Array;

import java.util.LinkedList;

/**
 * Remove all elements from a linked list of integers that have value val.
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

        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        System.out.println(linkedList.size());

        int[] arr1 = new int[]{1,2,3,4,5};

        System.out.println(sum(arr1,0));
        }

        public  static int sum(int[] arr, int l){
            if (arr.length == l){
                return 0;
            }
            return arr[l] + sum(arr, l+1);
        }
}
