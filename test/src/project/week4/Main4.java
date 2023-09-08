import java.io.*;
import java.util.*;

public class Main {
    private static int totalCities, totalRoads, startCity, endCity;
    private static List<Integer>[] cityConnections;
    private static int[] cityDepth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        totalCities = Integer.parseInt(st.nextToken());
        totalRoads = Integer.parseInt(st.nextToken());
        startCity = Integer.parseInt(st.nextToken());
        endCity = Integer.parseInt(st.nextToken());
        cityConnections = new ArrayList[totalCities + 1];

        for (int i = 0; i < totalRoads; i++) {
            st = new StringTokenizer(br.readLine());
            int city1 = Integer.parseInt(st.nextToken());
            int city2 = Integer.parseInt(st.nextToken());

            if (cityConnections[city1] == null) cityConnections[city1] = new ArrayList<>();
            if (cityConnections[city2] == null) cityConnections[city2] = new ArrayList<>();

            cityConnections[city1].add(city2);
            cityConnections[city2].add(city1);
        }

        for (int i = 1; i <= totalCities; i++) {
            if (startCity == i || endCity == i) {
                System.out.println(-1);
                continue;
            }
            cityDepth = new int[totalCities + 1];
            findAlternativePath(i);
        }
    }

    private static void findAlternativePath(int blockedNode) {
        Queue<Integer> queue = new ArrayDeque<>();
        cityDepth[startCity] = 1;
        queue.add(startCity);

        while (!queue.isEmpty()) {
            int currentCity = queue.poll();
            for (int adjacentCity : cityConnections[currentCity]) {
                if (adjacentCity == endCity) {
                    System.out.println(cityDepth[currentCity] + 1);
                    return;
                } else if (cityDepth[adjacentCity] == 0 && adjacentCity != blockedNode) {
                    cityDepth[adjacentCity] = cityDepth[currentCity] + 1;
                    queue.add(adjacentCity);
                }
            }
        }
        System.out.println(-1);
    }
}
