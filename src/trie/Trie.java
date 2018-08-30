package trie;

import java.util.TreeMap;

/**
 * @author WilsonSong
 * @date 2018/8/28/028
 */
public class Trie {

        private class Node {
            public boolean isWord;
            public TreeMap<Character, Node> next;

            public Node(boolean isWord) {
                this.isWord = isWord;
                this.next = new TreeMap<>();
            }
            public Node(){
                this(false);
            }
        }

        public Node root;
        /** Initialize your data structure here. */
        public Trie() {
            root = new Node();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.next.get(c) == null){
                    node.next.put(c, new Node());
                }
                node = node.next.get(c);
            }
            node.isWord = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node node = root;
            for (int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if (node.next.get(c) != null){
                    node = node.next.get(c);
                }else {
                    return false;
                }
            }
            return node.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Node node = root;
            for (int  i = 0; i < prefix.length(); i++){
                char c = prefix.charAt(i);
                if (node.next.get(c) != null){
                    node  = node.next.get(c);
                }else {
                    return false;
                }
            }
            return true;
        }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

