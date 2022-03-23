package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17070 {
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } }; // 가로, 세로, 대각선
	static int N; // 집의 크기
	static int[][] house; // 집의 상태 저장 배열
	static int count = 0; // 가능한 횟수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		house = new int[N][N];
		String[] input;

		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				house[i][j] = Integer.parseInt(input[j]);
			}
		}
		recur(0, 1, 0); //// 파이프 오른쪽 또는 아래쪽 좌표
		System.out.println(count);
	}

	// x, y : 파이프 오른쪽 아래의 좌표 , cur : 현재 파이프 상태(가로(0), 세로(1), 대각선(2))
	static void recur(int x, int y, int cur) {
		if (x == N - 1 && y == N - 1) { // 파이프의 오른쪽 아래가 마지막 칸에 도착하면 카운트하고 리턴
			count++;
			return;
		}
		for (int d = 0; d < 3; d++) {
			if ((cur == 0 && d == 1) || (cur == 1 && d == 0)) // 현재 가로일때 세로방향을 시도하지 않고 현재 세로일때 가로방향을 시도하지 않음
				continue;

			boolean isPossible = true; // 대각선으로 갈 수 있는 지 여부 확인
			if (d == 2) {
				for (int check = 0; check < 2; check++) {
					int nx = x + dir[check][0];
					int ny = y + dir[check][1];
					if (nx >= N || ny >= N || house[nx][ny] == 1) { // 대각선으로 가려고 할때 오른쪽과 아래쪽으로 갈 수 없으면 false
						isPossible = false;
					}
				}
			}

			int nx = x + dir[d][0];
			int ny = y + dir[d][1];
			if (isPossible && nx < N && ny < N && house[nx][ny] != 1) { // 가고자 하는 방향으로 갈 수 있는 지 확인
				recur(nx, ny, d); // 갈 수 있다면 재귀
			}

		}
	}
}