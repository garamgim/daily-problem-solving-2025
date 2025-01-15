/*
 * LeetCode Medium 973 K Closest Points to Origin
 * https://leetcode.com/problems/k-closest-points-to-origin/description/
 * */

import java.util.Comparator;
import java.util.PriorityQueue;

public class L973 {
    class Solution {
        public int[][] kClosest(int[][] points, int k) {
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> (o[0] * o[0] + o[1] * o[1])));
//            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
//                return (o1[0] * o1[0] + o1[1] * o1[1]) - (o2[0] * o2[0] + o2[1] * o2[1]);
//            });
            for (int[] point : points) {
                pq.add(point);
            }
            int[][] ans = new int[k][2];
            for (int i = 0; i < k; i++) {
                ans[i] = pq.poll();
            }
            return ans;
        }
    }
}
