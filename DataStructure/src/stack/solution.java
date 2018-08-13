package stack;

import java.util.Stack;

/**
 * 描述 Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * @author WilsonSong
 * @date 2018/8/11/011
 */
public class solution {

    public boolean isVaild(String s){
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == '{' ||  c == '[' || c == '('){
                stack.push(c);
            }else {
                if (stack.isEmpty()){
                    return false;
                }

                char topChar = stack.pop();
                if (c=='}' && topChar != '{'){
                    return false;
                }
                if (c==']' && topChar != '['){
                    return false;
                }
                if (c == ')' && topChar != '('){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args){
        solution s = new solution();
        System.out.println(s.isVaild("{{[]}}"));
    }
}

