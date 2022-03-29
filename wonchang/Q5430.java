package com.ssafy.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// 백준
// AC
public class Q5430 {
	
	static int start;
	static int end;
	static boolean dir;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			String orders = br.readLine();
			int n = Integer.parseInt(br.readLine());
			String arrT = br.readLine(); 
			String arrT2 = arrT.substring(1, arrT.length()-1); // [] 지우기
			if(arrT2.length()==0) {
				// 빈 배열
				if(orders.contains("D")) {
					sb.append("error").append("\n");
				}else {
					sb.append("[]").append("\n");
				}
				continue;
			}
			String[] arr = arrT2.split(",");
			start = 0;
			end = n-1;
			dir = false;
			for(int i=0; i<orders.length(); i++) {
				char order = orders.charAt(i);
				switch(order) {
				case 'R': R(); break;
				case 'D': D(); break;
				}
			}
			if(start>end+1) { sb.append("error").append("\n"); continue; } //error
			if(start>end) { sb.append("[]").append("\n"); continue; } // 빈문자열의 경우
			sb.append("[");
			if(!dir) { // 정방향으로 읽는다.
				while(start<=end) { sb.append(arr[start++]).append(","); }
			}else { // 역방향으로 읽는다.
				while(start<=end) { sb.append(arr[end--]).append(","); }
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("]").append("\n");
		}
		System.out.println(sb);
	}

	private static void D() {
		if(!dir) { // 정방향
			start++;
		}else {
			end--;
		}
	}

	private static void R() {
		dir = !dir;
	}
}
