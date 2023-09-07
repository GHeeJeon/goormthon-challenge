import java.io.*;
import java.util.*;

class Main {
    // 그래프의 간선 정보를 저장하는 리스트
    static List<List<Integer>> list = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 노드 수(N), 간선 수(M), 시작 노드 번호(K) 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        // 그래프 초기화
        for(int i=0; i<=N; i++){
            list.add(new ArrayList<>());
        }
        
        // 간선 정보 입력 받기
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine()," ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.get(s).add(e);
            list.get(e).add(s);
        }
        
        // 작은 번호의 노드부터 방문하기 위해 각 리스트 정렬
        for(int i=0; i<=N; i++){
            Collections.sort(list.get(i));
        }
        
        // BFS를 이용하여 시작 노드(K)부터 방문한 노드의 개수 및 마지막 노드 찾기
        int[] result = bfs(K, N);
        
        // 결과 출력
        System.out.println(result[0] + " " + result[1]);
    }
    
    // BFS로 방문한 노드의 개수 및 마지막 노드 찾는 함수
    static int[] bfs(int K, int N){
        int[] result = new int[2]; // 결과 저장 배열: [방문한 노드 수, 마지막 방문 노드]
        boolean[] visited = new boolean[N+1]; // 방문 여부를 저장하는 배열
        Queue<Integer> queue = new ArrayDeque<>();
        
        // 시작 노드 방문 처리 및 큐에 추가
        visited[K] = true;
        result[0]++;
        result[1] = K;
        queue.offer(K);
        
        // BFS 시작
        while(!queue.isEmpty()){
            int cur = queue.poll();
            // 현재 노드에 연결된 노드 중 방문하지 않은 노드 방문
            for(int next : list.get(cur)){
                if(!visited[next]){
                    visited[next] = true;
                    queue.offer(next);
                    result[0]++;
                    result[1] = next;
                    break;
                }
            }
        }
        
        return result;
    }
}
