/*
 * 소프티어 Level 3 9496 Pipelined
 * https://softeer.ai/practice/9496
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class H9496 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] info = br.readLine().split(" ");
        int[] seconds = new int[N];
        for (int i = 0; i < N; i++) {
            seconds[i] = Integer.parseInt(info[i]);
        }

        // 생산 단계를 오름차순 정렬
        Arrays.sort(seconds);

        // 최소 시간 계산:
        // (N-1)초 동안 새로운 자동차를 하나씩 추가하면서 생산하고,
        // 가장 마지막 자동차가 끝나는 시간을 더함
        System.out.println(N - 1 + seconds[N - 1]);
    }
}
