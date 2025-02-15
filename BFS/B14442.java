/*
 * 백준 G3 14442 벽 부수고 이동하기 2
 * https://www.acmicpc.net/problem/14442
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class B14442 {
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

        // BFS를 통해 최단 경로를 구한 후 출력
        System.out.println(bfs());
    }

    public static int bfs() {
        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};

        ArrayDeque<int[]> q = new ArrayDeque<>();

        // 방문 여부를 체크하기 위한 3차원 배열 (visited[부순 벽의 개수][i][j])
        boolean[][][] visited = new boolean[K + 1][N][M];

        // 시작 위치 (0, 0)에서 벽을 부수지 않고 시작
        q.addLast(new int[]{0, 0, 0});
        visited[0][0][0] = true;

        int dist = 0;   // 현재까지 이동한 거리 (초기값은 0)

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

                // 현재까지 부순 벽의 개수
                int didBreak = curr[2];

                for (int d = 0; d < 4; d++) {
                    int nr = curr[0] + dr[d];
                    int nc = curr[1] + dc[d];

                    // 벽이 아닌 곳으로 이동할 경우
                    if (0 <= nr && nr < N && 0 <= nc && nc < M && board[nr][nc] == 0 && !visited[didBreak][nr][nc]) {
                        visited[didBreak][nr][nc] = true;
                        q.addLast(new int[]{nr, nc, didBreak});
                    }
                    // 벽을 부수고 이동할 경우
                    else if (0 <= nr && nr < N && 0 <= nc && nc < M && board[nr][nc] == 1 &&
                            didBreak < K && !visited[didBreak + 1][nr][nc]
                    ) {
                        visited[didBreak + 1][nr][nc] = true;       // 벽을 부수고 방문 처리
                        q.addLast(new int[]{nr, nc, didBreak + 1}); // 큐에 새 위치와 부순 벽의 개수 증가하여 추가
                    }
                }
            }
        }

        // 목표 지점에 도달할 수 없으면 -1 반환
        return -1;
    }
}
