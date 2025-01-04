/*
 * LeetCode Hard 42 Trapping Rain Water
 * https://leetcode.com/problems/trapping-rain-water/description/
 * */

public class L42 {
    class Solution {
        public int trap(int[] height) {
            int l = 0;
            int r = height.length - 1;
            int leftMax = 0;
            int rightMax = 0;
            int ans = 0;

            while (l <= r) {
                leftMax = Math.max(height[l], leftMax);
                rightMax = Math.max(height[r], rightMax);

                // Two pointers move to the highest point in the middle
                if (leftMax <= rightMax) {
                    ans += leftMax - height[l];
                    l++;
                } else {
                    ans += rightMax - height[r];
                    r--;
                }
            }

            return ans;
        }
    }
}
