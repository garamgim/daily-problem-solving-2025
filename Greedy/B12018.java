/*
* 백준 S3 12018 Yonsei TOTO
* https://www.acmicpc.net/problem/12018
* */

import java.util.*;
import java.io.*;

public class B12018 {
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);

        int[] scoreNeeded = new int[N];

        for (int i = 0; i < N; i++) {
            String[] subInfo = br.readLine().split(" ");
            int P = Integer.parseInt(subInfo[0]);
            int L = Integer.parseInt(subInfo[1]);

            if (P < L) {
                br.readLine();
                scoreNeeded[i] = 1;
            } else {
                List<Integer> scoreApplied = new ArrayList<>(5);
                String[] appliedInfo = br.readLine().split(" ");
                for (int j = 0; j < P; j++) {
                    scoreApplied.add(Integer.parseInt(appliedInfo[j]));
                }
                Collections.sort(scoreApplied, Collections.reverseOrder());
                scoreNeeded[i] = scoreApplied.get(L - 1);
            }
        }

        Arrays.sort(scoreNeeded);
        int ans = 0;
        for (int i = 0; i < N; i++) {
            M -= scoreNeeded[i];
            if (M >= 0) ans++;
        }

        System.out.println(ans);
    }
}