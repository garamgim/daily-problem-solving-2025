/*
 * 백준 G5 13549 숨바꼭질 3
 * https://www.acmicpc.net/problem/13549
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class B13549 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] infos = br.readLine().split(" ");
        int N = Integer.parseInt(infos[0]);
        int K = Integer.parseInt(infos[1]);

        System.out.println(bfs(N, K));
    }

    public static int bfs(int start, int dest) {
        int[] visited = new int[100001];    // 방문 시간을 저장하는 배열
        Arrays.fill(visited, -1);       // 초기값을 -1로 설정 (방문하지 않은 상태)
        Deque<int[]> q = new ArrayDeque<>();

        visited[start]= 0;                  // 시작 위치의 방문 시간을 0으로 설정
        q.add(new int[]{start, 0});         // 시작 위치와 이동 시간을 큐에 추가

        while(!q.isEmpty()) {
            int[] curr = q.poll();

            int currLoc = curr[0];
            int currDist = curr[1];

            if (currLoc == dest) {
                return currDist;
            }

            // 순간이동하는 경우 (0초 소요)
            int next = currLoc * 2;
            if (0 <= next && next <= 100000 && (visited[next] == -1 || visited[next] > currDist)) {
                q.add(new int[]{next, currDist});   // 순간이동은 우선적으로 처리해야 하므로 덱의 앞에 추가
                visited[next] = currDist;
            }

            // 걷는 경우 (1초 소요)
            for (int d = -1; d <= 1; d += 2) {
                next = currLoc + d;
                if (0 <= next && next <= 100000 && (visited[next] == -1 || visited[next] > currDist)) {
                    visited[next] = currDist + 1;   // 1초 추가
                    q.add(new int[]{next, currDist + 1});
                }
            }
        }

        return 0;   // 기본적으로 도달할 수 없는 경우는 없으므로 실행되지 않음
    }
}
