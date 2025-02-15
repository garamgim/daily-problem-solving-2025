/*
 * 백준 G1 2042 구간 합 구하기
 * https://www.acmicpc.net/problem/2042
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class B2042 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        int N = Integer.parseInt(info[0]);
        int M = Integer.parseInt(info[1]);
        int K = Integer.parseInt(info[2]);

        // 숫자 배열 및 누적 합 배열 선언
        long[] nums = new long[N + 1];
        long[] acc = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = Long.parseLong(br.readLine());
            acc[i] = acc[i - 1] + nums[i];
        }

        // 변경된 값들을 저장하는 해시맵 <index, 변화량>
        HashMap<Long, Long> changeMap = new HashMap<>();

        // M개의 변경 연산과 K개의 구간 합 연산을 처리
        for (int i = 0; i < M + K; i++) {
            String[] numInfo = br.readLine().split(" ");
            int a = Integer.parseInt(numInfo[0]);
            long b = Long.parseLong(numInfo[1]);
            long c = Long.parseLong(numInfo[2]);
            if (a == 1) {
                // 값 변경 연산: b번 인덱스를 c로 변경
                // 변화량을 저장 (이후 구간 합 계산 시 반영)
                changeMap.put(b, c - nums[(int) b]);
            } else {
                // 구간 합 연산: b부터 c까지의 합을 구함
                long currSum = acc[(int) c] - acc[(int) b - 1];

                // 변경된 값이 해당 범위에 포함되면 반영
                for (Map.Entry<Long, Long> entry : changeMap.entrySet()) {
                    long key = entry.getKey();      // 변경된 인덱스
                    long val = entry.getValue();    // 해당 인덱스의 변화량
                    if (b <= key && key <= c) {
                        currSum += val;             // 변동 값 반영
                    }
                }

                System.out.println(currSum);
            }
        }
    }
}
