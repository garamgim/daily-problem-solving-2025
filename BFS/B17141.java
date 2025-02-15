/*
 * 백준 G4 17141 연구소 2
 * https://www.acmicpc.net/problem/17141
 * */

import java.io.*;
import java.util.*;

public class B17141 {
    static int N, M;
    static int[][] board;
    static int minTime = 1000;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static ArrayList<int[]> virusPlace = new ArrayList<>();
    static int virusPlaceSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] infos = br.readLine().split(" ");
        N = Integer.parseInt(infos[0]);
        M = Integer.parseInt(infos[1]);

        board = new int[N][N];
        virusPlace = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(row[j]);
                if (board[i][j] == 2) virusPlace.add(new int[]{i, j});  // 바이러스 배치 가능한 위치 저장
            }
        }

        // 바이러스 배치 가능한 위치 개수 저장
        virusPlaceSize = virusPlace.size();

        // M개의 바이러스를 선택하는 조합을 생성하여 BFS 실행
        comb(new int[M], 0, 0);

        // 최소 시간이 갱신되지 않았다면 모든 빈 칸에 퍼질 수 없는 경우이므로 -1 출력
        System.out.println(minTime == 1000? -1 : minTime);
    }

    /**
     * M개의 바이러스를 선택하는 조합을 생성하는 함수
     * @param selected 현재 선택된 바이러스 위치의 인덱스 배열
     * @param start    조합을 만들 때 시작할 인덱스
     * @param lev      현재 선택한 개수 (재귀 레벨)
     */
    public static void comb(int[] selected, int start, int lev) {
        // M개를 모두 선택한 경우 BFS 실행하여 최소 시간 계산
        if (lev == M) {
            bfs(selected);
            return;
        }

        for (int i = start; i < virusPlaceSize; i++) {
            selected[lev] = i;
            comb(selected, i + 1, lev + 1);
        }
    }

    /**
     * 선택한 M개의 바이러스 위치에서 BFS 탐색을 수행하여 최소 시간 계산
     * @param indices 선택된 바이러스 위치의 인덱스 배열
     */
    public static void bfs(int[] indices) {
        ArrayDeque<int[]> q = new ArrayDeque<>();

        // 연구소 상태를 복사하여 사용 (원본 보존)
        int[][] tempBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tempBoard[i][j] = board[i][j];
            }
        }

        // 선택된 M개의 바이러스를 큐에 추가하고 방문 표시 (3으로 변경)
        for (int idx : indices) {
            int[] loc = virusPlace.get(idx);
            q.add(loc);
            tempBoard[loc[0]][loc[1]] = 3;
        }

        // BFS 탐색 시간을 저장하는 변수 (0초부터 시작)
        int time = -1;

        while (!q.isEmpty()) {
            time++;

            int sz = q.size();

            for (int i = 0; i < sz; i++) {
                int[] curr = q.pollFirst();

                for (int d = 0; d < 4; d++) {
                    int nr = curr[0] + dr[d];
                    int nc = curr[1] + dc[d];

                    // 연구소 범위 내에 있고, 빈 칸(0, 2)이라면 감염 가능
                    if (0 <= nr && nr < N && 0 <= nc && nc < N && (tempBoard[nr][nc] == 0 || tempBoard[nr][nc] == 2)) {
                        tempBoard[nr][nc] = 3;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }

        // 바이러스가 모든 빈 칸을 감염시켰는지 확인
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 빈 칸이 남아 있다면 실패
                if (tempBoard[i][j] == 0 || tempBoard[i][j] == 2) return;
            }
        }

        // 모든 빈 칸을 감염시킨 경우, 최소 시간 갱신
        minTime = Math.min(time, minTime);
        return;
    }
}