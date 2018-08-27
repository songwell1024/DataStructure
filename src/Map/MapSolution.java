package Map;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Given two arrays, write a function to compute their intersection.

 Example 1:

 Input: nums1 = [1,2,2,1], nums2 = [2,2]
 Output: [2]
 Example 2:

 Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 Output: [9,4]
 * @author WilsonSong
 * @date 2018/8/24/024
 */
public class MapSolution {

    public int[] intersection(int[] nums1, int[] nums2) {

        TreeSet<Integer> set = new TreeSet();
        for (int num : nums1){
            set.add(num);
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums2){
            if (set.contains(num)){
                list.add(num);
                set.remove(num);
            }
        }
        int[] a = new int[list.size()];
        for (int i = 0; i < list.size(); i++){
            a[i] = list.get(i);
        }
        return a;
    }
}


