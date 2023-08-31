import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] visited;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] arr = new int[N][N];
        visited = new boolean[N][N];
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(visited[i][j] || arr[i][j] == 0){
                    continue;
                }
                result++;
                BFS(i, j, N, arr);
            }
        }
        
        System.out.println(result);
        br.close();
    }

    static void BFS(int r, int c, int N, int[][] arr){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {r, c});
        visited[r][c] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int i=0; i<4; i++){
                int tempR = cur[0] + dy[i];
                int tempC = cur[1] + dx[i];
                
                if(tempR >= 0 && tempR < N && tempC >= 0 && tempC < N && arr[tempR][tempC] == 1 && !visited[tempR][tempC]){
                    visited[tempR][tempC] = true;
                    queue.add(new int[] {tempR, tempC});
                }
            }
        }
    }
}
