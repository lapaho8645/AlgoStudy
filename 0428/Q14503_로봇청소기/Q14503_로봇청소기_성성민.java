package beakjoon.samsung_sw_workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
    북0
서3  +  동1
    남2

[내 기준 왼쪽 방향]   [내 기준 뒤]
북0 -> 서3          북0 -> 남2
서3 -> 남2          서3 -> 동1
남2 -> 동1          남2 -> 북0
동1 -> 북0          동1 -> 서2
(a+3)%4
*/

public class B14503_로봇청소기_h1 {

    static int N,M,arr[][], di[]={-1,0,1,0}, dj[]={0,1,0,-1}, ans; // 북동남서

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);

        str = br.readLine().split(" ");
        int row = Integer.parseInt(str[0]);
        int col = Integer.parseInt(str[1]);
        int dis = Integer.parseInt(str[2]);

        arr = new int[N][M];
        for(int i=0; i<N; i++){
            str = br.readLine().split(" ");
            for(int j=0; j<M; j++){
                arr[i][j]=Integer.parseInt(str[j]);
            }
        }
        clean(row,col,dis);
        System.out.println(ans);
    }

    public static void clean(int r, int c, int d){

        if(arr[r][c]==0) {
            arr[r][c]=2; // 빈 칸이면 청소
            ans++;
        } else if (arr[r][c]==1) return;

        int nd=d;
        for(int b=0; b<4; b++){
            nd=(nd+3)%4;
            int ni=r+di[nd];
            int nj=c+dj[nd];

            if(arr[ni][nj]==0){
                clean(ni,nj,nd);
                return;
            }
        }
        // 움직이긴 방향이 (벽 || 청소끝) 일 때 후진
        clean(r-di[d],c-dj[d],d);
        return;
    } // clean end
}
