package HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 *
 Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 Examples:
 s = "leetcode"
 return 0.
 s = "loveleetcode"
 return 2.
 * @author WilsonSong
 * @date 2018/9/1
 */
public class HashMapSolution {
        public static int firstUniqChar(String s) {
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                if (!map.containsKey(c)){
                    map.put(c, 1);
                }
                else {
                    map.put(c, map.get(c) +1);
                }
            }
            for (int j = 0; j < s.length(); j++){
                if (map.get(s.charAt(j)) == 1){
                    return j;
                }
            }
            return -1;
        }
        public static void main(String[] args){
            String s = "leetcode";
            System.out.println(firstUniqChar(s));
        }
}
