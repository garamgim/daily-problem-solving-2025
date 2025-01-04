/*
 * LeetCode Easy 1 Two Sum
 * https://leetcode.com/problems/two-sum/
 * O(N)
 * */

import java.util.HashMap;

public class L1 {
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            HashMap<Integer, Integer> numMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                numMap.put(nums[i], i);
            }

            // HashMap의 containsKey는 해시 값을 이용해 즉시 버킷을 찾아가므로 평균적으로 O(1)의 시간 복잡도를 가짐
            // 해시 충돌 시 동일 버킷 내에서 탐색이 필요하므로 최악의 경우 O(n)
            for (int i = 0; i < nums.length; i++) {
                if (numMap.containsKey(target - nums[i]) && i != numMap.get(target - nums[i])) {
                    return new int[]{i, numMap.get(target - nums[i])};
                }
            }
            return null;
        }
    }
}
