/*
 * 백준 G3 2206 벽 부수고 이동하기
 * https://www.acmicpc.net/problem/2206
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class B2206 {
    static int N, M;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] infos = br.readLine().split(" ");
        N = Integer.parseInt(infos[0]);
        M = Integer.parseInt(infos[1]);

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(String.valueOf(row.charAt(j)));
            }
        }

        // BFS를 통해 최단 경로를 구한 후 출력
        System.out.println(bfs());
    }

    public static int bfs() {
        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};

        ArrayDeque<int[]> q = new ArrayDeque<>();

        // 방문 체크 배열 (visited[0]은 벽을 안 부쉈을 때, visited[1]은 벽을 부쉈을 때)
        boolean[][][] visited = new boolean[2][N][M];

        // 시작점 (0, 0)에서 벽을 부수지 않은 상태로 시작
        q.addLast(new int[]{0, 0, 0});
        visited[0][0][0] = true;

        int dist = 0;  // 현재까지의 거리

        while (!q.isEmpty()) {
            dist++;  // 큐에서 한 단계를 처리할 때마다 거리가 1 증가

            int currSize = q.size();

            for (int i = 0; i < currSize; i++) {
                int[] curr = q.pollFirst();

                // (N-1, M-1)에 도달하면 그때의 거리를 반환
                if (curr[0] == N - 1 && curr[1] == M - 1) {
                    return dist;
                }

                int didBreak = curr[2];  // 벽을 부쉈는지 여부

                // 상하좌우로 이동
                for (int d = 0; d < 4; d++) {
                    int nr = curr[0] + dr[d];  // 이동할 새로운 행
                    int nc = curr[1] + dc[d];  // 이동할 새로운 열

                    // 벽을 부수지 않았고, 벽이 없는 칸으로 이동할 때에는 visited[0]에 표시하며 이동
                    if (0 <= nr && nr < N && 0 <= nc && nc < M && board[nr][nc] == 0 && didBreak == 0 && !visited[0][nr][nc]) {
                        visited[0][nr][nc] = true;
                        q.addLast(new int[]{nr, nc, 0});
                    }
                    // 벽을 이미 부쉈거나 부숴야 할 때에는 visited[1]에 표시하며 이동
                    else if (0 <= nr && nr < N && 0 <= nc && nc < M &&
                        (
                            // 아직 벽을 부수지 않은 상태에서 벽을 만났다면 한번 부순 후 이동
                            (board[nr][nc] == 1 && didBreak == 0 && !visited[1][nr][nc])
                                    ||
                            // 이미 벽을 부순 적 있을때에는 벽이 아닌 칸으로 이동
                            (board[nr][nc] == 0 && didBreak == 1 && !visited[1][nr][nc])
                        )
                    ) {
                        visited[1][nr][nc] = true;
                        q.addLast(new int[]{nr, nc, 1});
                    }
                }
            }
        }

        // 목표 지점에 도달할 수 없으면 -1을 반환
        return -1;
    }
}
