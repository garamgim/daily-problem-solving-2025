/*
 * LeetCode Medium 739 Daily Temperatures
 * https://leetcode.com/problems/daily-temperatures/description/
 * */

import java.util.ArrayDeque;
import java.util.Deque;

public class L739 {
    class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            Deque<int[]> stk = new ArrayDeque<>();
            int[] ans = new int[temperatures.length];

            for (int i = 0; i < temperatures.length; i++) {
                int curr = temperatures[i];

                // 스택이 비어있지 않고, 현재 온도가 스택의 최상단에 있는 온도보다 높을 경우
                while (!stk.isEmpty() && stk.peek()[0] < curr) {
                    int[] prev = stk.pop();     // 스택에서 이전 온도와 인덱스를 꺼냄
                    ans[prev[1]] = i - prev[1]; // 현재 인덱스와 이전 인덱스 차이를 결과에 저장
                }

                // 현재 온도와 인덱스를 스택에 추가
                stk.push(new int[]{curr, i});
            }

            return ans;
        }
    }
}
