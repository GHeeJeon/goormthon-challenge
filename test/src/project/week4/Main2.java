import java.io.*;
import java.util.*;

class Main {
    static List<List<Integer>> computerConnections = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfComputers = Integer.parseInt(st.nextToken());
        int numOfLines = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= numOfComputers; i++) {
            computerConnections.add(new ArrayList<>());
        }

        for (int i = 0; i < numOfLines; i++) {
            st = new StringTokenizer(br.readLine());
            int computerA = Integer.parseInt(st.nextToken());
            int computerB = Integer.parseInt(st.nextToken());
            computerConnections.get(computerA).add(computerB);
            computerConnections.get(computerB).add(computerA);
        }

        boolean[] isPartOfComponent = new boolean[numOfComputers + 1];
        List<Integer> densestComponent = new ArrayList<>();
        double maxDensity = -1;

        for (int i = 1; i <= numOfComputers; i++) {
            if (!isPartOfComponent[i]) {
                List<Integer> currentComponent = BFS(i, isPartOfComponent);
                int lineCount = 0;
                for (int computer : currentComponent) {
                    lineCount += computerConnections.get(computer).size();
                }
                lineCount /= 2;  // 각 연결은 두 번 카운트되므로 2로 나눈다.
                double currentDensity = (double) lineCount / currentComponent.size();
                
                if (currentDensity > maxDensity || 
                    (currentDensity == maxDensity && currentComponent.size() < densestComponent.size()) || 
                    (currentDensity == maxDensity && currentComponent.size() == densestComponent.size() && currentComponent.get(0) < densestComponent.get(0))) {
                    maxDensity = currentDensity;
                    densestComponent = currentComponent;
                }
            }
        }

        Collections.sort(densestComponent);
        for (int computer : densestComponent) {
            System.out.print(computer + " ");
        }
    }

    static List<Integer> BFS(int startComputer, boolean[] isPartOfComponent) {
        Queue<Integer> q = new ArrayDeque<>();
        List<Integer> currentComponent = new ArrayList<>();
        q.add(startComputer);
        isPartOfComponent[startComputer] = true;

        while (!q.isEmpty()) {
            int currentComputer = q.poll();
            currentComponent.add(currentComputer);
            for (int nextComputer : computerConnections.get(currentComputer)) {
                if (!isPartOfComponent[nextComputer]) {
                    isPartOfComponent[nextComputer] = true;
                    q.add(nextComputer);
                }
            }
        }
        return currentComponent;
    }
}
