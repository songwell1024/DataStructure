package heap;

import java.util.*;

/**
 * Given a non-empty array of integers, return the k most frequent elements.

 Example 1:

 Input: nums = [1,1,1,2,2,3], k = 2
 Output: [1,2]
 Example 2:

 Input: nums = [1], k = 1
 Output: [1]
 Note:

 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * @author WilsonSong
 * @date 2018/8/27/027
 */
public class heapSolution {

        public static   List<Integer> topKFrequent(int[] nums, int k) {
            List<Integer> list = new ArrayList<>();
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums){
                if (!map.containsKey(num)){
                    map.put(num,1);
                }
                map.put(num, map.get(num)+1);
            }

            List<Map.Entry<Integer,Integer>> list1 = new ArrayList<>();
            list1.addAll(map.entrySet());
            Collections.sort(list1, new Comparator<Map.Entry<Integer, Integer>>() {
                @Override
                public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                    return o2.getValue().compareTo( o1.getValue());
                }
            });
            for (Map.Entry<Integer, Integer> mapping : list1){
                if (list.size() >= k){
                    break;
                }
                list.add(mapping.getKey());
            }
            return list;
    }

    public static void main(String[]  args){
            int[] array = {2,2,2,3,3,5,5,5,4,5,4,4,4,4,5};
        List<Integer> list = topKFrequent(array,2);
        System.out.println(list.toString());

    }

}
