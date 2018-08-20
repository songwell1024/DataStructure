package binarySearchTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author WilsonSong
 * @date 2018/8/16/016
 */
public class BSTSolution {
        public static int uniqueMorseRepresentations(String[] words) {
            if (words == null){
                return 0;
            }
            String[] morse = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
            Map<Character,String> map = new HashMap();
            for (int i = 0;i < 26; i++){
                map.put((char) (97 + i),morse[i]);
            }
//            String[] word = new String[words.length];
            HashSet<String> finalResult = new HashSet<>();  //HashSet里面是没有重复元素的
            for (int i = 0; i < words.length; i++){
                String str = words[i];
                StringBuilder strB = new StringBuilder();
                for (int j = 0; j < str.length(); j++){
                    strB.append(map.get(str.charAt(j)));
                }
//                word[i] = strB.toString();
                finalResult.add(strB.toString());

            }
           return finalResult.size();
    }

//        public static int repeat(String[] morse, int head){
//
//            for (int i = head+1; i < morse.length; i++){
//                if (morse[head].equals(morse[i]) && !(morse[head].equals("morse"))){
//                    morse[i] = "morse";
//                }
//            }
//            if (head < morse.length){
//                head++;
//                repeat(morse, head);
//            }
//            int size = morse.length;
//            for (int i = 0; i< morse.length; i++){
//                if (morse[i].equals("morse")){
//                    size -- ;
//                }
//            }
//            return size;
//        }

        public static void main(String[] args){
            String[] a = new String[]{"gin", "zen", "gig", "msg"};
            System.out.println(uniqueMorseRepresentations(a));
        }
}
