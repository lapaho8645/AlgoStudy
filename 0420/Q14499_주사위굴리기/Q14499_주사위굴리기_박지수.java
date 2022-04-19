package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_14499 {
	static int[] dr = { 0, 0, 0, -1, 1 }; // 1은 동쪽, 2는 서쪽, 3은 북쪽, 4는 남쪽
	static int[] dc = { 0, 1, -1, 0, 0 };
	static int[][] dice = new int[2][4];
	static int N, M, x, y, curR, curC, map[][];
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		x = Integer.parseInt(input[2]);
		y = Integer.parseInt(input[3]);
		int K = Integer.parseInt(input[4]);
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		input = br.readLine().split(" ");
		for (int k = 0; k < K; k++) {
			int dir = Integer.parseInt(input[k]);
			move(x, y, dir);

		}
		System.out.println(sb.toString());
	}

	public static void move(int r, int c, int dir) {
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		if (nr < 0 || nc < 0 || nr >= N || nc >= M)
			return;
		if (dir == 1) { // 동
			int temp = dice[1][3];
			for (int i = 3; i > 0; i--) {
				dice[1][i] = dice[1][i - 1];
			}
			dice[1][0] = temp;
			dice[0][1] = dice[1][1];
			dice[0][3] = dice[1][3];
		} else if (dir == 2) { // 서
			int temp = dice[1][0];
			for (int i = 0; i < 3; i++) {
				dice[1][i] = dice[1][i + 1];
			}
			dice[1][3] = temp;
			dice[0][1] = dice[1][1];
			dice[0][3] = dice[1][3];
		} else if (dir == 3) { // 북
			int temp = dice[0][0];
			for (int i = 0; i < 3; i++) {
				dice[0][i] = dice[0][i + 1];
			}
			dice[0][3] = temp;
			dice[1][1] = dice[0][1];
			dice[1][3] = dice[0][3];
		} else { // 남
			int temp = dice[0][3];
			for (int i = 3; i > 0; i--) {
				dice[0][i] = dice[0][i - 1];
			}
			dice[0][0] = temp;
			dice[1][1] = dice[0][1];
			dice[1][3] = dice[0][3];
		}
		if (map[nr][nc] == 0) {
			map[nr][nc] = dice[0][3];
		} else {
			dice[0][3] = dice[1][3] = map[nr][nc];
			map[nr][nc] = 0;
		}

		x = nr;
		y = nc;
		sb.append(dice[0][1]).append("\n");
	}

}
