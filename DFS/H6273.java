/*
 * 소프티어 Level 3 6273 택배 마스터 광우
 * https://softeer.ai/practice/6273
 * */

import java.io.*;

public class H6273 {
    // 전역 변수 선언
    static int N; // 레일의 개수
    static int M; // 바구니 최대 무게
    static int K; // 작업 시행 횟수
    static int[] weights; // 각 레일의 무게 배열
    static int minWeight = 100000; // 최소 무게를 저장하기 위한 변수 (초기값은 큰 값으로 설정)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄 입력 처리: N, M, K 값 초기화
        String[] info1 = br.readLine().split(" ");
        N = Integer.parseInt(info1[0]);
        M = Integer.parseInt(info1[1]);
        K = Integer.parseInt(info1[2]);

        // 두 번째 줄 입력 처리: 레일의 무게 배열 초기화
        weights = new int[N];
        String[] info2 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(info2[i]);
        }

        // 레일의 모든 순열을 생성하여 최소 무게를 계산
        perm(new int[N], new boolean[N], 0);

        // 결과 출력
        System.out.println(minWeight);
    }

    // 순열 생성 함수
    public static void perm(int[] selected, boolean[] visited, int lev) {
        // 순열이 완성된 경우
        if (lev == N) {
            calculateWeight(selected); // 해당 순열로 최소 무게 계산
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                selected[lev] = weights[i];
                visited[i] = true;
                perm(selected, visited, lev + 1);
                visited[i] = false;
            }
        }
    }

    // 특정 순열에 대해 최소 무게를 계산하는 함수
    public static void calculateWeight(int[] rails) {
        int cnt = K; // 작업 횟수
        int idx = 0; // 현재 레일의 인덱스
        int totalWeight = 0; // 전체 무게
        int currWeight = 0; // 현재 바구니의 무게

        // K번 작업을 수행할 때까지 반복
        while (cnt > 0) {
            currWeight += rails[idx]; // 현재 레일의 무게를 바구니에 추가
            if (currWeight > M) { // 바구니의 무게가 최대 무게를 초과한 경우
                totalWeight += currWeight - rails[idx]; // 초과 이전의 무게를 총합에 추가
                currWeight = rails[idx]; // 현재 레일부터 다시 시작
                cnt--; // 작업 횟수 감소
            }
            idx = (idx + 1) % N; // 다음 레일로 이동 (순환 구조)
        }

        // 최소 무게 갱신
        minWeight = Math.min(minWeight, totalWeight);
    }
}