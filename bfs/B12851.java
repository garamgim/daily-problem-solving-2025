/*
 * 백준 G4 12851 숨바꼭질 2
 * https://www.acmicpc.net/problem/12851
 * */

import java.io.*;
import java.util.*;

public class B12851 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] infos = br.readLine().split(" ");
        int N = Integer.parseInt(infos[0]);
        int K = Integer.parseInt(infos[1]);

        int[] ans = bfs(N, K);

        System.out.printf("%d\n%d\n", ans[0], ans[1]);
    }

    public static int[] bfs(int start, int dest) {
        int[] visited = new int[100001];    // 방문 배열 (각 위치를 몇 초 만에 방문했는지를 저장)
        Arrays.fill(visited, -1);       // 초기값을 -1로 설정하여 방문 여부 체크

        Deque<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = 0;                 // 시작 위치 방문 시간 0초
        int count = 0;                      // 최단 거리로 도착하는 경우의 수
        int minTime = Integer.MAX_VALUE;    // 최소 시간 (최단 거리)

        while (!q.isEmpty()) {
            int curr = q.poll();

            if (curr == dest) {
                // 첫 번째로 도달한 경우 최단 시간 갱신 및 경우의 수 초기화
                if (visited[curr] < minTime) {
                    minTime = visited[curr];
                    count = 1;
                }
                // 동일한 최단 시간에 도착한 경우, 경우의 수 증가
                else if (visited[curr] == minTime) {
                    count++;
                }
                continue;
            }

            for (int next : new int[]{curr - 1, curr + 1, curr * 2}) {
                if (next >= 0 && next <= 100000) {
                    // 첫 방문 || 동일한 최단 시간으로 여러 경로가 나오는 경우
                    if (visited[next] == -1 || visited[next] == visited[curr] + 1) {
                        visited[next] = visited[curr] + 1;  // 방문 시간 갱신
                        q.add(next);
                    }
                }
            }
        }

        return new int[]{minTime, count};
    }

    // 오답 코드
    // boolean[3][100001]을 사용하면 같은 시간에 도착하는 경로임에도 불구하고 count를 올바르게 세지 못하는 경우가 발생
    // 예시 : 1 4 -> 1 2 4 (*2, +1로 2에 중복으로 도달가능)
    public static int[] wrongBfs(int start, int dest) {
        boolean[][] visited = new boolean[3][100001];
        Deque<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[0][start] = true;
        visited[1][start] = true;
        visited[2][start] = true;
        int time = 0;
        int count = 0;
        boolean reached = false;

        while (!q.isEmpty()) {
            int qSize = q.size();

            for (int j = 0; j < qSize; j++) {
                int curr = q.poll();
                if (curr == dest) {
                    reached = true;
                    count++;
                }

                int next = curr - 1;
                if (0 <= next && next <= 100000 && !visited[0][next]) {
                    visited[0][next] = true;
                    q.addLast(next);
                }

                next = curr + 1;
                if (0 <= next && next <= 100000 && !visited[1][next]) {
                    visited[1][next] = true;
                    q.addLast(next);
                }

                next = 2 * curr;
                if (next <= 100000 && !visited[2][next]) {
                    visited[2][next] = true;
                    q.addLast(next);
                }
            }

            if (reached) break;

            time++;
        }

        return new int[]{time, count};
    }
}


