/*
 * 백준 S2 2885 초콜릿 식사
 * https://www.acmicpc.net/problem/2885
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2885 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        int curr = 1;

        while (curr < K) {
            curr *= 2;
        }

        if (curr == K) {
            System.out.printf("%d %d", K, 0);
        } else {
            int ans1 = curr;
            int ans2 = 0;
            while (K > 0) {
                if (K - curr >= 0) {
                    K -= curr;
                } else {
                    ans2++;
                    curr /= 2;
                }
            }
            System.out.printf("%d %d", ans1, ans2);
        }
    }
}
