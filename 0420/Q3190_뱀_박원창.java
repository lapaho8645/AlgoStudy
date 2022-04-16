package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q3190 {

	static int[] dr = {0, 1, 0, -1}; // 우,하,좌,상
	static int[] dc = {1, 0, -1, 0};
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // board의 크기
		boolean[][] board = new boolean[N+1][N+1];
		boolean[][] snake = new boolean[N+1][N+1];
		int[][] arrowHistory = new int[N+1][N+1];
		snake[1][1] = true;
		arrowHistory[1][1] = 0;
		int K = Integer.parseInt(br.readLine()); // 사과의 개수
		for(int i=0; i<K; i++) {
			String[] temp = br.readLine().split(" ");
			board[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] = true;
		} // board에 사과 채워넣기
		
		int L = Integer.parseInt(br.readLine()); // 방향 변환 횟수
		char[] arrowChange = new char[10001];
		int nowArrow = 0; // 처음엔 오른쪽부터 시작
		int[] head = {1,1}; // snake의 머리 좌표
		int[] tail = {1,1}; // 꼬리 좌표
		for(int i=0; i<L; i++) {
			String[] temp = br.readLine().split(" ");
			arrowChange[Integer.parseInt(temp[0])] = temp[1].charAt(0);
		} // 시간과 방향변환 정보
		
		int time = 0;
		while(true) { // 게임 시작!!
			time++;
			if(!isGo(head, tail, board, snake, arrowHistory, nowArrow)) break;

			// time초가 끝난 뒤에 방향전환이 이루어진다.
			nowArrow = getArrow(time, nowArrow, arrowChange);
			arrowHistory[head[0]][head[1]] = nowArrow;
		}
		System.out.println(time);
	}

	private static boolean isGo(int[] head, int[] tail, boolean[][] board, boolean[][] snake, int[][] arrowHistory, int nowArrow) {
		head[0] += dr[nowArrow];
		head[1] += dc[nowArrow];
		if(!checkBorder(head, tail, snake)) return false; // 경계확인, 충돌확인
		if(!isAppleThere(head, board)) moveTail(tail, snake, arrowHistory); // 사과가 있다면 꼬리는 그대로, 없다면 꼬리도 한칸 움직인다.
		snake[head[0]][head[1]] = true;


		return true;
	}

	private static void moveTail(int[] tail, boolean[][] snake, int[][] arrowHistory) {
		snake[tail[0]][tail[1]] = false; // 움직이기 전의 꼬리의 좌표를 false로 바꿔서 snake가 차지하는 자리를 최신화
		int arrow = arrowHistory[tail[0]][tail[1]];
		tail[0] += dr[arrow];
		tail[1] += dc[arrow];
	}

	private static boolean isAppleThere(int[] head, boolean[][] board) { // 사과가 있다면 true를 반환하고, board의 사과를 지운다.
		int r = head[0];
		int c = head[1];
		if(board[r][c]) {
			board[r][c] = false;
			return true;
		}
		return false;
	}

	private static boolean checkBorder(int[] head, int[] tail, boolean[][] snake) {
		if(head[0]<1 || head[0]>N || head[1]<1 || head[1]>N) return false;
		if(snake[head[0]][head[1]]) return false;
		return true;
	}

	private static int getArrow(int time, int nowArrow, char[] arrowChange) {
//		if(arrowChange[time]=='\0') return nowArrow; // char의 초기값은 Character의 null 값인 \0이다.
		if(arrowChange[time]=='L') { // 왼쪽으로 90도 회전
			nowArrow-=1;
			if(nowArrow<0) nowArrow = 3;
		} else if(arrowChange[time]=='D') { // 오늘쪽으로 90도 회전
			nowArrow=(nowArrow+1)%4;
		}
		return nowArrow;
	}

}
