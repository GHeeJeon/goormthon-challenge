import java.io.*;
import java.util.*;

public class CityTraveler {
    // 도시의 수, 도로의 수, 출발 도시, 도착 도시
    private static int numCities, numRoads, startCity, destCity;
    // 도시 연결 정보
    private static List<Integer>[] cityConnections;
    // 각 도시까지의 깊이 (거쳐온 도시의 수)
    private static int[] cityDepth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 변수 초기화
        numCities = Integer.parseInt(st.nextToken());
        numRoads = Integer.parseInt(st.nextToken());
        startCity = Integer.parseInt(st.nextToken());
        destCity = Integer.parseInt(st.nextToken());
        cityConnections = new ArrayList[numCities + 1];

        // 도로 연결 정보 입력
        for (int i = 0; i < numRoads; i++) {
            st = new StringTokenizer(br.readLine());
            int city1 = Integer.parseInt(st.nextToken());
            int city2 = Integer.parseInt(st.nextToken());

            if (cityConnections[city1] == null) cityConnections[city1] = new ArrayList<>();
            if (cityConnections[city2] == null) cityConnections[city2] = new ArrayList<>();

            cityConnections[city1].add(city2);
            cityConnections[city2].add(city1);
        }

        // 각 날짜(도시)에 대해 공사 중일 때의 최단 경로 찾기
        for (int i = 1; i <= numCities; i++) {
            // 만약 출발 도시나 도착 도시에서 공사를 하고 있다면 -1 출력
            if (startCity == i || destCity == i) {
                System.out.println(-1);
                continue;
            }
            cityDepth = new int[numCities + 1];
            findShortestPath(i);
        }
    }

    // 공사 중인 도시를 제외하고 최단 경로 찾기
    private static void findShortestPath(int underConstructionCity) {
        Queue<Integer> queue = new ArrayDeque<>();
        cityDepth[startCity] = 1;
        queue.add(startCity);

        // BFS를 이용한 최단 경로 탐색
        while (!queue.isEmpty()) {
            int currentCity = queue.poll();
            for (int adjacentCity : cityConnections[currentCity]) {
                // 만약 인접 도시가 도착 도시라면 경로 출력
                if (adjacentCity == destCity) {
                    System.out.println(cityDepth[currentCity] + 1);
                    return;
                } 
                // 아직 방문하지 않았고, 공사 중인 도시가 아니라면 큐에 추가
                else if (cityDepth[adjacentCity] == 0 && adjacentCity != underConstructionCity) {
                    cityDepth[adjacentCity] = cityDepth[currentCity] + 1;
                    queue.add(adjacentCity);
                }
            }
        }
        // 도달할 수 없는 경우 -1 출력
        System.out.println(-1);
    }
}
