package Map;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Given two arrays, write a function to compute their intersection.

 Example 1:

 Input: nums1 = [1,2,2,1], nums2 = [2,2]
 Output: [2,2]
 Example 2:

 Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 Output: [4,9]
 * @author WilsonSong
 * @date 2018/8/24/024
 */
public class MapSolution2 {
    public int[] intersect(int[] nums1, int[] nums2) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums1){
            if (!map.containsKey(num)){
                map.put(num, 1);
            }else{
                map.put(num, map.get(num) +1);
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num :  nums2){
            if (map.containsKey(num)){
                list.add(num);
                map.put(num, map.get(num)-1);
                if (map.get(num) == 0){
                    map.remove(num);
                }
            }
        }
        int[] a = new int[list.size()];
        for (int i = 0; i < list.size(); i++){
            a[i] = list.get(i);
        }
        return a;
    }

}
