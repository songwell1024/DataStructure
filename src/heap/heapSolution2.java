package heap;

import java.util.*;
import java.util.PriorityQueue;

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
public class heapSolution2 {
    public static List<Integer> topKFrequent(int[] nums, int k) {

        /**
         * 定义一个频次的私有类
         */
        class Freq implements Comparable<Freq>{
            public int e;
            public int freq;

            public Freq(int e, int freq){
                this.e = e;
                this.freq = freq;
            }

            @Override
            public int compareTo(Freq anotherFreq){
                if (this.freq < anotherFreq.freq) {
                    return -1;
                }
                else if (this.freq > anotherFreq.freq){
                    return 1;
                }
                else{
                    return 0;
                }
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums){
            if (!map.containsKey(num)){
                map.put(num,1);
            }else {
                map.put(num, map.get(num) + 1);
            }
        }

        PriorityQueue<Freq> priorityQueue = new PriorityQueue<>();  //按照优先级排列，出现频次低的优先级高
        for (int key : map.keySet()){
            if (priorityQueue.size() < k){
                priorityQueue.add(new Freq(key, map.get(key)));
            }else {
                if (map.get(key) > priorityQueue.peek().freq){
                    priorityQueue.remove();
                    priorityQueue.add(new Freq(key, map.get(key)));
                }
            }

        }
        List list = new ArrayList();
        while (!priorityQueue.isEmpty()){
            list.add(priorityQueue.poll().e);
        }
        return list;
    }

    public static void main(String[]  args){
        int[] array = {1,2,2,3,3,3};
        List<Integer> list = topKFrequent(array,2);
        System.out.println(list.toString());
    }

}
