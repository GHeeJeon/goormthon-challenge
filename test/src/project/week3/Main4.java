import java.io.*;
import java.util.*;

class Main {
    static List<List<Integer>> list = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        for(int i=0; i<=N; i++){
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine()," ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.get(s).add(e);
            list.get(e).add(s);
        }
        
        for(int i=0; i<=N; i++){
            Collections.sort(list.get(i));
        }
        
        int[] result = bfs(K, N);
        
        System.out.println(result[0] + " " + result[1]);
    }
    
    static int[] bfs(int K, int N){
        int[] result = new int[2];
        boolean[] visited = new boolean[N+1];
        Queue<Integer> queue = new ArrayDeque<>();
        
        visited[K] = true;
        result[0]++;
        result[1] = K;
        queue.offer(K);
        
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int nxt : list.get(cur)){
                if(!visited[nxt]){
                    visited[nxt] = true;
                    queue.offer(nxt);
                    result[0]++;
                    result[1] = nxt;
                    break;
                }
            }
        }
        
        return result;
    }
}
