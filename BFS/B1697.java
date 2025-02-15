/*
 * 백준 G1 1697 숨바꼭질
 * https://www.acmicpc.net/problem/1697
 * */

import java.io.*;
import java.util.*;

public class B1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] infos = br.readLine().split(" ");
        int N = Integer.parseInt(infos[0]);
        int K = Integer.parseInt(infos[1]);

        System.out.println(bfs(N, K));
    }

    public static int bfs(int start, int dest) {
        boolean[] visited = new boolean[100001];
        Deque<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.add(start);
        int time = 0;

        while (!q.isEmpty()) {
            int qSize = q.size();

            for (int j = 0; j < qSize; j++) {
                int curr = q.poll();
                if (curr == dest) return time;

                for (int d = -1; d <= 1; d += 2) {
                    int next = curr + d;
                    if (0 <= next && next <= 100000 && !visited[next]) {
                        visited[next] = true;
                        q.addLast(next);
                    }
                }

                int next = 2 * curr;
                if (next <= 100000 && !visited[next]) {
                    visited[next] = true;
                    q.addLast(next);
                }
            }

            time++;
        }

        return time;
    }
}