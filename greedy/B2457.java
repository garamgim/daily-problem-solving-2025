/*
 * 백준 G3 2457 공주님의 정원
 * https://www.acmicpc.net/problem/2457
 * */

import java.util.*;
import java.io.*;

public class B2457 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 꽃의 개수 입력
        int[][] flowers = new int[N][4];            // 꽃 정보 저장 배열

        // 꽃들의 정보를 입력받아 배열에 저장
        for (int i = 0; i < N; i++) {
            String[] info = br.readLine().split(" ");
            int[] flower = new int[4];
            for (int j = 0; j < 4; j++) {
                flower[j] = Integer.parseInt(info[j]);
            }
            flowers[i] = flower;
        }

        // 꽃을 피는 날짜 기준으로 오름차순, 지는 날짜 기준으로 내림차순 정렬
        Arrays.sort(flowers, (o1, o2) -> {
            if (o1[0] != o2[0]) return o1[0] - o2[0];
            if (o1[1] != o2[1]) return o1[1] - o2[1];
            if (o1[2] != o2[2]) return o2[2] - o1[2];
            return o2[3] - o1[3];
        });

        // 시작 날짜: 3월 1일
        int startMonth = 3, startDay = 1;

        // 종료 날짜: 11월 30일
        int endMonth = 11, endDay = 30;

        // 가장 늦게까지 유지되는 날짜
        int[] latest = new int[]{0, 0};

        // 꽃 배열에서 현재 탐색 중인 인덱스
        int idx = 0;

        // 필요한 꽃의 개수 (답)
        int cnt = 0;

        // 3월 1일부터 11월 30일까지 모든 기간을 덮을 때까지 반복
        while (isEarlierOrSame(startMonth, startDay, endMonth, endDay)) {
            boolean found = false;

            // 현재 기간을 덮을 수 있는 모든 꽃 중에서 가장 늦게 지는 꽃 찾기
            while (idx < N && isEarlierOrSame(flowers[idx][0], flowers[idx][1], startMonth, startDay)) {
                latest = getLatestDate(latest, new int[]{flowers[idx][2], flowers[idx][3]});
                idx++;
                found = true;   // 덮을 꽃을 찾았음
            }

            if (!found) {   // 덮을 꽃을 찾지 못한 경우
                cnt = 0;    // 모든 기간을 덮을 수 없으므로 0 출력
                break;
            }
            // 다음 탐색을 위해 시작 날짜를 최신 날짜로 갱신
            startMonth = latest[0];
            startDay = latest[1];
            cnt++;  // 사용한 꽃 개수 증가
        }

        System.out.println(cnt);
    }

    // 첫 번째 날짜(from)가 두 번째 날짜(to)보다 같거나 빠른지 확인
    public static boolean isEarlierOrSame(int fromMonth, int fromDay, int toMonth, int toDay) {
        if (fromMonth < toMonth) return true;
        if (fromMonth > toMonth) return false;
        if (fromDay <= toDay) return true;
        return false;
    }

    // 두 날짜 중 더 늦은 날짜를 반환
    public static int[] getLatestDate(int[] o1, int[] o2) {
        if (o1[0] > o2[0]) return o1;
        if (o1[0] < o2[0]) return o2;
        if (o1[1] > o2[1]) return o1;
        return o2;
    }
}