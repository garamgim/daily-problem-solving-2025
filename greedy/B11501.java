/*
 * 백준 S2 11501 주식
 * https://www.acmicpc.net/problem/11501
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B11501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] price = new int[N];
            String[] priceInfo = br.readLine().split(" ");

            for (int i = 0; i < N; i++) {
                price[i] = Integer.parseInt(priceInfo[i]);
            }

            long ans = 0;
            int maxPrice = -1;

            // 거꾸로 탐색하며 가격 최댓값 갱신
            for (int i = N - 1; i >= 0; i--) {
                // 다음날보다 오늘의 가격이 더 높다면 최댓값 갱신해주고 팔지 않음
                if (price[i] >= maxPrice) {
                    maxPrice = price[i];
                }
                // 그 후 일수의 최대 가격보다 오늘의 가격이 낮다면 판다
                else {
                    ans += maxPrice - price[i];
                }
            }
            System.out.println(ans);
        }
    }
}
