import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1107 {
	static int  N, length, pressNo, min;
	static boolean [] buttonNo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		if(N==0) {
			length = 1;
				
		}else {
			length = (int) (Math.log10(N) +1); // N의 길이	
		}
		
		pressNo = 0;
		int M = Integer.parseInt(br.readLine());
		if(M != 0) {
			buttonNo = new boolean[10]; // 0~9
			String[] input = br.readLine().split(" ");
			for (int i = 0; i < M; i++) {
				buttonNo[Integer.parseInt(input[i])] = true; // 고장난 버튼은 true로
			}
		}
		
		min = Math.abs(N - 100);
		
		if(M == 0) {		//모든 버튼을 누를 수 있을 때
			min = min > length ?length : min;
			System.out.println(min);
		}else if(N == 100) {			//버튼을 누를 필요가 없을 때
			System.out.println(0);
		}else {
			recur(0,0);				//가능한 모든 버튼을 눌러보기 0~999999 	//999999까지 눌러보는 이유는 0~8이 모두 망가지고 9만 누를 수 있을 때 6자리 채널을 찾기 위해서
			System.out.println(min);
		}
		

	}
	
	public static void recur(int idx, int pressNo) {
		for(int i = 0; i < 10; i++) {
			if(!buttonNo[i]) {
				int newPressNo = (int) (pressNo + i * Math.pow(10, idx));
				min = min > Math.abs(newPressNo - N) + idx+1? Math.abs(newPressNo - N)+idx+1 : min;				//idx+1은 새로운 버튼을 누르기 위해 
				if(idx < 5) {
					recur(idx+1, newPressNo);
				}
			}
		}
	}
}
