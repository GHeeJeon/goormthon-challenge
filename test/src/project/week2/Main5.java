import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] queryCount;
    static char[][] queryCommand;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        String[] goormStart = br.readLine().split(" ");
        int goormY = Integer.parseInt(goormStart[0]) - 1;
        int goormX = Integer.parseInt(goormStart[1]) - 1;

        String[] playerStart = br.readLine().split(" ");
        int playerY = Integer.parseInt(playerStart[0]) - 1;
        int playerX = Integer.parseInt(playerStart[1]) - 1;

        queryCount = new int[N][N];
        queryCommand = new char[N][N];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                String command = line[j];
                queryCount[i][j] = Integer.parseInt(command.substring(0, command.length() - 1));
                queryCommand[i][j] = command.charAt(command.length() - 1);
            }
        }

        solution(goormY, goormX, playerY, playerX);
    }

    public static int change(int index) {
        if (index < 0) {
            return N + index % N;
        }
        if (index >= N) {
            return index % N;
        }
        return index;
    }

    public static int[] move(int Y, int X, HashSet<String> visited) {
        int[][] queries = {
            {0, -1}, // L
            {0, 1},  // R
            {-1, 0}, // U
            {1, 0}   // D
        };
        char[] commandList = {'L', 'R', 'U', 'D'};
        int moveCount = queryCount[Y][X];
        char direction = queryCommand[Y][X];
        int idx = new String(commandList).indexOf(direction);

        for (int i = 0; i < moveCount; i++) {
            Y = change(Y + queries[idx][0]);
            X = change(X + queries[idx][1]);
            String pos = Y + "," + X;

            if (visited.contains(pos)) {
                return new int[]{Y, X, -1}; // -1 indicates the end
            } else {
                visited.add(pos);
            }
        }

        return new int[]{Y, X, 0};
    }

    public static void solution(int goormY, int goormX, int playerY, int playerX) {
        HashSet<String> goormVisited = new HashSet<>();
        HashSet<String> playerVisited = new HashSet<>();
        goormVisited.add(goormY + "," + goormX);
        playerVisited.add(playerY + "," + playerX);

        int[] result;
        while (true) {
            result = move(goormY, goormX, goormVisited);
            if (result[2] == -1) break;
            goormY = result[0];
            goormX = result[1];
        }

        while (true) {
            result = move(playerY, playerX, playerVisited);
            if (result[2] == -1) break;
            playerY = result[0];
            playerX = result[1];
        }

        if (goormVisited.size() > playerVisited.size()) {
            System.out.println("goorm " + goormVisited.size());
        } else {
            System.out.println("player " + playerVisited.size());
        }
    }
}
