/*
 * 백준 G2 16946 벽 부수고 이동하기 4
 * https://www.acmicpc.net/problem/16946
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class B16946 {
    static int N, M;
    static int[][] board;
    static int[][] ans;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] infos = br.readLine().split(" ");
        N = Integer.parseInt(infos[0]);
        M = Integer.parseInt(infos[1]);
        board = new int[N][M];
        ans = new int[N][M]; // 각 0 영역에 고유한 ID를 부여할 배열

        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(String.valueOf(row.charAt(j)));
            }
        }

        int id = 1; // 각 연결된 0 영역에 부여할 ID (1부터 시작)
        Map<Integer, Integer> sizeMap = new HashMap<>(); // 각 ID별 영역 크기 저장

        // BFS를 이용해 모든 0 영역 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 방문하지 않은 0 영역이면 탐색 시작
                if (board[i][j] == 0 && ans[i][j] == 0) {
                    int size = bfs(i, j, id); // 해당 영역의 크기 계산
                    sizeMap.put(id, size); // ID별 영역 크기 저장
                    id++; // 다음 ID로 증가
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) { // 벽인 경우 주변 0 영역의 크기를 계산
                    HashSet<Integer> seen = new HashSet<>(); // 중복 ID 방지를 위한 HashSet
                    int currCount = 1; // 기본적으로 벽을 부쉈을 때 자기 자신 포함

                    for (int d = 0; d < 4; d++) { // 상하좌우 탐색
                        int nr = i + dr[d];
                        int nc = j + dc[d];

                        if (nr >= 0 && nr < N && nc >= 0 && nc < M && board[nr][nc] == 0) {
                            int currId = ans[nr][nc]; // 현재 위치의 영역 ID 가져오기
                            if (!seen.contains(currId)) { // 이미 추가한 영역의 ID가 아니라면
                                seen.add(currId);
                                currCount += sizeMap.get(currId); // 해당 ID의 영역 크기 더해주기
                            }
                        }
                    }
                    sb.append(currCount % 10); // 10으로 나눈 나머지 출력
                } else {
                    sb.append(0); // 원래 0이었던 곳은 그대로 0 출력
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    // BFS를 이용해 연결된 0 영역의 크기를 계산하는 함수
    static int bfs(int r, int c, int id) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});
        ans[r][c] = id; // 해당 위치에 ID 부여하며 방문체크

        int size = 1;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = curr[0] + dr[d];
                int nc = curr[1] + dc[d];

                if (nr >= 0 && nr < N && nc >= 0 && nc < M && ans[nr][nc] == 0 && board[nr][nc] == 0) {
                    ans[nr][nc] = id;
                    q.add(new int[]{nr, nc});
                    size++;
                }
            }
        }

        return size;
    }
}
