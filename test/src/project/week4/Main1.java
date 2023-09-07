import java.io.*;
import java.util.*;

class Main {
    static List<List<Integer>> adjacencyList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());
            adjacencyList.get(startNode).add(endNode);
        }

        boolean[] isVisited = new boolean[N + 1];
        int groupCount = 0;

        for (int i = 1; i <= N; i++) {
            if (isVisited[i]) {
                continue;
            }
            bfs(i, isVisited);
            groupCount++;
        }

        System.out.println(groupCount);
    }

    static void bfs(int start, boolean[] isVisited) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        isVisited[start] = true;

        while (!q.isEmpty()) {
            int currentNode = q.poll();
            for (int nextNode : adjacencyList.get(currentNode)) {
                if (!isVisited[nextNode] && adjacencyList.get(nextNode).contains(currentNode)) {
                    q.offer(nextNode);
                    isVisited[nextNode] = true;
                }
            }
        }
    }
}
