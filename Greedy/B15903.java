/*
 * 백준 S1 15903 카드 합체 놀이
 * https://www.acmicpc.net/problem/15903
 * */

import java.util.*;
import java.io.*;

public class B15903 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        int N = Integer.parseInt(info[0]);
        int M = Integer.parseInt(info[1]);
        PriorityQueue<Long> pq = new PriorityQueue<>(N);

        String[] cardInfo = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            pq.add(Long.parseLong(cardInfo[i]));
        }

        for (int i = 0; i < M; i++) {
            long p = pq.poll();
            long q = pq.poll();
            pq.add(p + q);
            pq.add(p + q);
        }

        long ans = 0;

        for (int i = 0; i < N; i++) {
            ans += pq.poll();
        }

        System.out.println(ans);
    }
}