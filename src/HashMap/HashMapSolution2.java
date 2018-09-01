package HashMap;

import java.util.Scanner;

/**
 Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 Examples:
 s = "leetcode"
 return 0.
 s = "loveleetcode",
 return 2.
 * @author WilsonSong
 * @date 2018/9/1/001
 */
public class HashMapSolution2 {
    public static int firstUniqChar(String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++){
            freq[s.charAt(i) - 'a'] ++;
        }
        for (int i = 0; i < freq.length; i++){
            if (freq[s.charAt(i) - 'a'] == 1){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        if (in.hasNext()){
            stringBuilder.append(in.next());
        }
        System.out.println(firstUniqChar(stringBuilder.toString()));
    }

}


