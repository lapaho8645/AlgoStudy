package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14499 {

	static int N,M;
	static int[][] map;
	static int[] dice;
	static int[] dx = {0, 0, 0, -1, 1}; // 동서북남
	static int[] dy = {0, 1, -1, 0, 0};  

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken()); // 지도의 세로 크기
		M = Integer.parseInt(st.nextToken()); // 지도의 가로 크기
		int x = Integer.parseInt(st.nextToken()); // 주사위의 x 좌표
		int y = Integer.parseInt(st.nextToken()); // 주사위의 y 좌표 
		int K = Integer.parseInt(st.nextToken()); // 명령의 개수
		int[] dicePos = {x,y};

		
		map = new int[N][M];
		int[] orders = new int[K];
		dice = new int[7]; // 1~6
		
		for(int i=0; i<N; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		} // map 완성
		
		// 이동하는 명령 : 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			orders[i] = Integer.parseInt(st.nextToken());
		} // orders 완성
		
		// 이동할때마다 주사위의 윗 면에 쓰여 있는 수를 출력. 만약 바깥으로 이동시키려고 하면 명령 무시.
		int[] currentState = new int[6];
		// 0-map과 맞닿은 면, 1-동, 2-서, 3-북, 4-남, 5-맞은편
		currentState[0] = 1;
		currentState[1] = 3;
		currentState[2] = 4;
		currentState[3] = 2;
		currentState[4] = 5;
		currentState[5] = 6;
		
		for(int i=0; i<K; i++) {
			int order = orders[i];
			// 명령, 현재바닥면 기준 주사위, 주사위의 x,y 좌표
			int out = go(order, currentState, dicePos);
			if(out>=0) {
				sb.append(out).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static int go(int order, int[] currentState, int[] dicePos) {
		int nx = dicePos[0] + dx[order];
		int ny = dicePos[1] + dy[order];
		// 바깥으로 이동할 시 -1 리턴
		if(nx<0 || nx>=N || ny<0 || ny>=M) return -1;
		moveDice(order, currentState);

		// map과 맞닿은 주사위와 숫자 넣기
		if(map[nx][ny]==0) { // map의 수가 0이면
			map[nx][ny] = dice[currentState[0]];
		} else {
			dice[currentState[0]] = map[nx][ny];
			map[nx][ny] = 0;
		}

		dicePos[0] = nx;
		dicePos[1] = ny;
		
		return dice[currentState[5]];
	}

	private static void moveDice(int order, int[] currentState) {
		switch(order) {
		case 1:
			moveEast(currentState);
			break;
		case 2:
			moveWest(currentState);
			break;
		case 3:
			moveNorth(currentState);
			break;
		case 4:
			moveSouth(currentState);
		}
	}

	private static void moveSouth(int[] currentState) {
		int temp = currentState[0];
		currentState[0] = currentState[4];
		currentState[4] = currentState[5];
		currentState[5] = currentState[3];
		currentState[3] = temp;
	}

	private static void moveNorth(int[] currentState) {
		int temp = currentState[0];
		currentState[0] = currentState[3];
		currentState[3] = currentState[5];
		currentState[5] = currentState[4];
		currentState[4] = temp;
	}

	private static void moveWest(int[] currentState) {
		int temp = currentState[0];
		currentState[0] = currentState[2];
		currentState[2] = currentState[5];
		currentState[5] = currentState[1];
		currentState[1] = temp;
	}

	private static void moveEast(int[] currentState) {
		int temp = currentState[0];
		currentState[0] = currentState[1];
		currentState[1] = currentState[5];
		currentState[5] = currentState[2];
		currentState[2] = temp;
	}

}
