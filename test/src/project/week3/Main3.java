import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] town = new int[M][M];
        
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++){
                town[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited = new boolean[M][M];
        int[] complexCnt = new int[31];

        for(int y=0; y<M; y++){
            for(int x=0; x<M; x++){
                if(visited[y][x]){
                    continue;
                }
                complexCnt[town[y][x]] += search(y, x, town, visited, M, K);
            }
        }

        int max = 0;
        int result = 0;

        for(int i=1; i<31; i++){
            if(complexCnt[i] == 0){
                continue;
            }
            if(complexCnt[i] >= max){
                max = complexCnt[i];
                result = i;
            }
        }

        System.out.println(result);
        br.close();
    }

    static int search(int y, int x, int[][] town, boolean[][] visited, int M, int K){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{y, x});
        visited[y][x] = true;
        int cnt = 1;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int i=0; i<4; i++){
                int tempY = cur[0] + dy[i];
                int tempX = cur[1] + dx[i];

                if(tempY >= 0 && tempY < M && tempX >= 0 && tempX < M && !visited[tempY][tempX] && town[tempY][tempX] == town[y][x]){
                    queue.offer(new int[]{tempY, tempX});
                    visited[tempY][tempX] = true;
                    cnt++;
                }
            }
        }

        if(cnt < K){
            return 0;
        } else {
            return 1;
        }
    }

}
