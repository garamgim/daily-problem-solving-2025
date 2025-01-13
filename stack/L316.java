/*
 * LeetCode Medium 316 Remove Duplicate Letters
 * https://leetcode.com/problems/remove-duplicate-letters/description/
 * */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;

public class L316 {
    class Solution {
        public String removeDuplicateLetters(String s) {
            // 각 문자의 등장 횟수를 저장하는 HashMap
            HashMap<Character, Integer> count = new HashMap<>();
            // 이미 처리했는지에 대한 여부를 나타내는 HashSet
            HashSet<Character> done = new HashSet<>();
            // 문자 순서를 유지하면서 처리하기 위한 스택
            Deque<Character> stk = new ArrayDeque<>(s.length());

            // 문자열 s의 각 문자의 등장 횟수를 카운트
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                count.put(c, count.get(c) == null? 1 : count.get(c) + 1);
            }

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                // 이미 처리된 문자라면 count만 감소시키고 넘어감
                if (done.contains(c)) {
                    count.put(c, count.get(c) - 1);
                    continue;
                }

                // 현재 문자 c가 스택의 top보다 작고, top 문자가 이후에 다시 등장할 수 있다면
                // 스택에서 제거하여 사전순을 더 작게 만듦
                while (!stk.isEmpty() && stk.peek() > c && count.get(stk.peek()) > 0) {
                    done.remove(stk.pop()); // 스택에서 제거된 문자는 done에서도 제거
                }

                // 현재 문자를 스택에 추가하고 처리 완료 표시
                stk.push(c);
                done.add(c);
                count.put(c, count.get(c) - 1);
            }

            StringBuilder ans = new StringBuilder();
            for (char c : stk) {
                ans.append(c);
            }

            return ans.reverse().toString();
        }
    }
}
