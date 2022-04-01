package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_1991 {
	static int N, index = 1;
	static char[] tree;
	static StringBuilder sb;
	static int lastIdx = 1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		String[] input;
		tree = new char[(int) Math.pow(2, N)];
		tree[1] = 'A';

		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			index = findIndex(input[0].charAt(0));
			if (input[1].charAt(0) != '.') {
				tree[index * 2] = input[1].charAt(0);
				lastIdx = lastIdx < index * 2 ? index * 2 : lastIdx;
			}
			if (input[2].charAt(0) != '.') {
				tree[index * 2 + 1] = input[2].charAt(0);
				lastIdx = lastIdx < index * 2 + 1 ? index * 2 + 1 : lastIdx;
			}

		}

		preOrder(1);
		sb.append("\n");
		inOrder(1);
		sb.append("\n");
		postOrder(1);
		sb.append("\n");
		System.out.println(sb.toString());
	}

	public static void preOrder(int current) {
		if (current > lastIdx)
			return;
		if (tree[current] != '\0') {
			sb.append(tree[current]);
		}
		preOrder(current * 2);
		preOrder(current * 2 + 1);
	}

	public static void inOrder(int current) {
		if (current > lastIdx)
			return;
		inOrder(current * 2);
		if (tree[current] != '\0') {
			sb.append(tree[current]);
		}
		inOrder(current * 2 + 1);

	}

	public static void postOrder(int current) {
		if (current > lastIdx)
			return;
		postOrder(current * 2);
		postOrder(current * 2 + 1);
		if (tree[current] != '\0') {
			sb.append(tree[current]);
		}
	}

	public static int findIndex(char c) {
		int temp = 0;
		for (int i = 1; i <= lastIdx; i++) {

			if (tree[i] == c) {
				temp = i;
				break;
			}
		}
		return temp;
	}

}
