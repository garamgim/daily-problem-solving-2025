/*
 * 백준 G1 16933 벽 부수고 이동하기 3
 * https://www.acmicpc.net/problem/16933
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class B16933 {

    static int N, M, K;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] infos = br.readLine().split(" ");
        N = Integer.parseInt(infos[0]);
        M = Integer.parseInt(infos[1]);
        K = Integer.parseInt(infos[2]);

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(String.valueOf(row.charAt(j)));
            }
        }

        // BFS 탐색을 통해 최단 거리 출력
        System.out.println(bfs());
    }

    public static int bfs() {
        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};

        ArrayDeque<int[]> q = new ArrayDeque<>();

        // visited[낮=0/밤=1][벽 부순 횟수][행][열]
        boolean[][][][] visited = new boolean[2][K + 1][N][M];

        // 시작 위치 (0,0)에서 벽을 부수지 않고 시작, 낮이고 벽을 부순 횟수가 0일 때 시작점을 방문 처리
        q.addLast(new int[]{0, 0, 0, 0});
        visited[0][0][0][0] = true;

        // 현재까지 이동한 거리 (초기값은 0)
        int dist = 0;

        while (!q.isEmpty()) {
            dist++;

            int currSize = q.size();

            // 현재 레벨에 있는 모든 노드를 탐색
            for (int i = 0; i < currSize; i++) {
                int[] curr = q.pollFirst();

                // 목표 지점 (N-1, M-1)에 도달하면 현재까지의 거리를 반환
                if (curr[0] == N - 1 && curr[1] == M - 1) {
                    return dist;
                }

                int didBreak = curr[2];     // 현재까지 부순 벽 개수
                int didRest = curr[3];      // 현재 정지 상태 여부 (0: 이동, 1: 정지)

                for (int d = 0; d < 4; d++) {
                    int nr = curr[0] + dr[d];
                    int nc = curr[1] + dc[d];

                    // 벽이 아닌 곳으로 이동할 경우
                    if (0 <= nr && nr < N && 0 <= nc && nc < M && board[nr][nc] == 0 && !visited[0][didBreak][nr][nc]) {
                        visited[0][didBreak][nr][nc] = true;
                        q.addLast(new int[]{nr, nc, didBreak, didRest});
                    }
                    // 벽을 부수고 이동할 경우
                    else if (dist % 2 == 1 &&   // 낮일 때만 벽을 부수고 이동 가능
                            0 <= nr && nr < N && 0 <= nc && nc < M && board[nr][nc] == 1 &&
                            didBreak < K && !visited[0][didBreak + 1][nr][nc]
                    ) {
                        visited[0][didBreak + 1][nr][nc] = true;
                        q.addLast(new int[]{nr, nc, didBreak + 1, didRest});
                    }
                }

                // 현재 위치에서 쉬어가는 경우 (밤일 때 벽을 못 부수기 때문에 머무를 수 있음)
                if (!visited[1][didBreak][curr[0]][curr[1]]) {
                    visited[1][didBreak][curr[0]][curr[1]] = true;
                    q.addLast(new int[]{curr[0], curr[1], didBreak, 1});
                }
            }
        }

        // 목적지에 도달할 수 없는 경우 -1 반환
        return -1;
    }
}
