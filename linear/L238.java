/*
 * LeetCode Medium 238 Product of Array Except Self
 * https://leetcode.com/problems/product-of-array-except-self/
 * */

public class L238 {
    // 배열 2개 사용
    class Solution {
        public int[] productExceptSelf(int[] nums) {
            int N = nums.length;
            int[] toRight = new int[N]; // 오른쪽 방향 누적 곱을 저장하는 배열
            int[] toLeft = new int[N];  // 왼쪽 방향 누적 곱을 저장하는 배열

            toRight[0] = nums[0];       // 첫 번째 원소의 왼쪽 누적 곱은 nums[0]으로 초기화
            toLeft[N-1] = nums[N-1];    // 마지막 원소의 오른쪽 누적 곱은 nums[N-1]로 초기화

            // 왼쪽 방향 누적 곱 계산
            for (int i = 1; i < N; i++) {
                toRight[i] = nums[i] * toRight[i-1];
            }

            // 오른쪽 방향 누적 곱 계산
            for (int i = N - 2; i >= 0; i--) {
                toLeft[i] = nums[i] * toLeft[i + 1];
            }

            int[] ans = new int[N];
            for (int i = 0; i < N; i++) {
                int l = (i == 0) ? 1 : toRight[i-1];
                int r = (i == N-1) ? 1 : toLeft[i+1];
                ans[i] = l * r;         // 왼쪽 곱과 오른쪽 곱을 곱하여 결과값 저장
            }

            return ans;
        }
    }

    // 배열 1개 사용
    class OptimizedSpaceComplexity {
        public int[] productExceptSelf(int[] nums) {
            int N = nums.length;
            int[] ans = new int[N];

            // 왼쪽에서 오른쪽으로 누적 곱을 계산할 변수
            int p = 1;

            for (int i = 0; i < N; i++) {
                ans[i] = p;     // 현재 위치의 왼쪽 누적 곱을 결과 배열에 저장
                p *= nums[i];   // 누적 곱을 업데이트 (현재 값을 곱함)
            }

            // 오른쪽에서 왼쪽으로 누적 곱을 계산할 변수 초기화
            p = 1;

            for (int i = N - 1; i >= 0; i--) {
                ans[i] *= p;    // 현재 위치의 오른쪽 누적 곱을 기존 결과값에 곱함
                p *= nums[i];   // 누적 곱을 업데이트 (현재 값을 곱함)
            }
            return ans;
        }
    }
}
