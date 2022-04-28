package com.algostudy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ14503 {

	static int N, M;
	static int map[][];
	static int delta[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int startI, startJ;
	static int nr, nc, d, count, resultCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");

		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		str = br.readLine().split(" ");
		startI = Integer.parseInt(str[0]);
		startJ = Integer.parseInt(str[1]);
		d = Integer.parseInt(str[2]);

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		robot(startI, startJ, count,d);
		for(int i = 0 ; i< N ; i++) {
			for(int j = 0 ; j<M;j++) {
				if(map[i][j]==2) {
					resultCnt++;
				}
			}
		}
		System.out.println(resultCnt);

	}

	public static void robot(int r, int c, int cnt, int d) {
		clean(r, c);
		while(true) {
			if(cnt==4) {
				if(map[r+delta[(d+4-2)%4][0]][c+delta[(d+4-2)%4][1]]!=1) {
					robot(r+delta[(d+4-2)%4][0],c+delta[(d+4-2)%4][1],0,d);
					return;
				} else {
					return ;
				}
			}
			d = (d + 4 - 1) % 4;
			nr = r + delta[d][0];
			nc = c + delta[d][1];
			if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
				robot(nr, nc, 0, d);
				return;
			} else {
				cnt++;
				continue;
			}
		}
	}

	public static void clean(int r, int c) {
		map[r][c] = 2;
	}

}
