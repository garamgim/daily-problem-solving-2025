/*
 * 백준 S2 17087 숨바꼭질 6
 * https://www.acmicpc.net/problem/17087
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class B17087 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] info = br.readLine().split(" ");
        int N = Integer.parseInt(info[0]);
        int S = Integer.parseInt(info[1]);

        String[] locInfo = br.readLine().split(" ");
        ArrayDeque<Integer> q = new ArrayDeque<>();

        // 각 동생과 수빈이의 거리 차이를 큐에 추가
        for (int i = 0; i < N; i++) {
            q.add(Math.abs(S - Integer.parseInt(locInfo[i])));
        }

        // 큐에 있는 값들의 최대공약수 구하기
        while (q.size() > 1) {
            int a = q.poll();
            int b = q.poll();
            int c = gcd(a, b);
            q.add(c);
        }

        System.out.println(q.poll());
    }

    public static int gcd(int a, int b) {
        while (b > 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}
