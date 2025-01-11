/*
 * LeetCode Easy 20 Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/description/
 * */

import java.util.ArrayDeque;
import java.util.Deque;

public class L20 {
    class Solution {
        public boolean isValid(String s) {
            Deque<Character> stk = new ArrayDeque<>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(' || c == '[' || c == '{') {
                    stk.push(c);
                } else {
                    if (stk.peek() == null) return false;
                    char top = stk.peek();
                    if ((c == ')' && top == '(')
                            || (c == ']' && top == '[')
                            || (c == '}' && top == '{')) {
                        stk.pop();
                    } else {
                        stk.push(c);
                    }
                }
            }

            return stk.isEmpty();
        }
    }
}
