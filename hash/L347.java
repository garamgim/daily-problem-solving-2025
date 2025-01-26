/*
 * LeetCode Medium 347 Top K Frequent Elements
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 * */

import java.util.HashMap;
import java.util.PriorityQueue;

public class L347 {
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            // 숫자의 빈도수를 저장할 HashMap 생성
            HashMap<Integer, Integer> cntMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                cntMap.put(num, cntMap.get(num) == null ? 1 : cntMap.get(num) + 1);
            }

            // 우선순위 큐를 사용해 빈도수 기준 내림차순으로 숫자들을 정렬
            PriorityQueue<Integer> sortedKeys = new PriorityQueue<>((o1, o2) -> cntMap.get(o2) - cntMap.get(o1));
            for (int key : cntMap.keySet()) {
                sortedKeys.add(key);
            }

            // 우선순위 큐에서 빈도수가 가장 높은 숫자를 k개 꺼내 배열에 저장
            int[] ans = new int[k];
            for (int i = 0; i < k; i++) {
                ans[i] = sortedKeys.poll();
            }

            return ans;
        }
    }
}
