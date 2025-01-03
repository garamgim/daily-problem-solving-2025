/*
 * 백준 G5 1911 흙길 보수하기
 * https://www.acmicpc.net/problem/1911
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B1911R {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] infos = br.readLine().split(" ");
        int N = Integer.parseInt(infos[0]);
        int L = Integer.parseInt(infos[1]);

        int[][] waterLoc = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] waterInfo = br.readLine().split(" ");
            waterLoc[i][0] = Integer.parseInt(waterInfo[0]);
            waterLoc[i][1] = Integer.parseInt(waterInfo[1]);
        }

        // 웅덩이 배열을 시작 위치를 기준으로 오름차순 정렬
        Arrays.sort(waterLoc, (o1, o2) -> o1[0] - o2[0]);

        int ans = 0;
        int lastCovered = 0;

        for (int[] water : waterLoc) {
            int start = Math.max(lastCovered, water[0]);    // 이미 덮인 부분은 건너뛰기 위해 max 사용
            int end = water[1];

            // 덮어야 할 새로운 구간이 존재하는 경우
            if (start < end) {
                int waterSize = end - start;
                int boards = (waterSize + L - 1) / L;   // 필요한 널빤지 개수 계산 (올림 효과)
                ans += boards;
                lastCovered = start + boards * L;   // 마지막으로 덮은 위치 업데이트
            }
        }

        System.out.println(ans);
    }
}
