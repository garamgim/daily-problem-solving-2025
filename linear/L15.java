/*
 * LeetCode Medium 15 3Sum
 * https://leetcode.com/problems/3sum/description/
 * O(N^2)
 * */

import java.util.*;

public class L15 {
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            Set<List<Integer>> resultSet = new HashSet<>();
            List<List<Integer>> ans = new ArrayList<>();
            int l, r;

            for (int i = 1; i < nums.length - 1; i++) {
                l = 0;
                r = nums.length - 1;
                while (l < i && i < r) {
                    if (nums[l] + nums[i] + nums[r] > 0) {
                        r--;
                    } else if (nums[l] + nums[i] + nums[r] < 0) {
                        l++;
                    } else {
                        // List의 equals는 요소의 순서와 값을 모두 비교하여 동일 여부를 판단
                        resultSet.add(Arrays.asList(nums[l], nums[i], nums[r]));
                        r--;
                        l++;
                    }
                }
            }

            ans.addAll(resultSet);
            return ans;
        }
    }
}
