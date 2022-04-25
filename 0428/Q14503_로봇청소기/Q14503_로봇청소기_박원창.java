package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준
// 로봇 청소기

public class Q14503 {
	
	private static final int[] dr = {-1, 0, 1, 0};
	private static final int[] dc = {0, 1, 0, -1};
	private static int N,M;

	static class Robot{
		int r;
		int c;
		int arrow;

		public Robot(int r, int c, int arrow) {
			super();
			this.r = r;
			this.c = c;
			this.arrow = arrow;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int robotR = Integer.parseInt(st.nextToken());
		int robotC = Integer.parseInt(st.nextToken());
		int robotArrow = Integer.parseInt(st.nextToken());
		Robot robot = new Robot(robotR, robotC, robotArrow); // 로봇 완성

		int[][] board = new int[N][M];
		boolean[][] check = new boolean[N][M];
		for(int i=0; i<N; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(temp[j]);
			}
		} // board 완성
		
		int ans = clean(robot, board, check); // 청소한 칸의 수 반환
		System.out.println(ans);
	}

	private static int clean(Robot robot, int[][] board, boolean[][] check) {
		int cleaned = 1;
		check[robot.r][robot.c] = true;
		while(true) {
			int cnt = 0;
			for(int i=0; i<4; i++) {
				int newArrow = robot.arrow==0 ? 3 : robot.arrow-1; 
				int nr = robot.r + dr[newArrow];
				int nc = robot.c + dc[newArrow];
				if(board[nr][nc]==0 && !check[nr][nc]) { // 새로 이동할 칸이 빈칸이며 청소가 안되어있을 시 이동
					robot.r = nr;
					robot.c = nc;
					robot.arrow = newArrow;
					check[nr][nc] = true;
					cleaned++;
					break;
				} else {
					robot.arrow = newArrow;
				}
				cnt++;
				
			}
			
			// 4번 반복을 했을 경우 
			if(cnt==4) {
				int backArrow = (robot.arrow+2)%4;
				int nr = robot.r + dr[backArrow];
				int nc = robot.c + dc[backArrow];
				if(board[nr][nc]==1) break;
				else { // 후진
					robot.r = nr;
					robot.c = nc;
				}
			}
			
		}
		
		return cleaned;
	}

}
