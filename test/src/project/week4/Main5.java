import java.io.*;
import java.util.*;

public class Main {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int size = Integer.parseInt(st.nextToken());
        int threshold = Integer.parseInt(st.nextToken());
        int operations = Integer.parseInt(st.nextToken());
        char[][] grid = new char[size][size];

        for (int a = 0; a < size; a++) {
            String line = br.readLine();
            Arrays.fill(grid[a], '.');
            for (int b = 0; b < size; b++) {
                grid[a][b] = line.charAt(b);
            }
        }

        for (int a = 0; a < operations; a++) {
            st = new StringTokenizer(br.readLine(), " ");
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            char direction = st.nextToken().charAt(0);
            grid[row][col] = direction;

            bfs(grid, row, col, direction, threshold, size);
        }

        for (int a = 0; a < size; a++) {
            for (int b = 0; b < size; b++) {
                System.out.print(grid[a][b]);
            }
            System.out.println();
        }

        br.close();
    }

    static void bfs(char[][] grid, int row, int col, char direction, int threshold, int size) {
        Queue<int[]> queue = new ArrayDeque<>();
        List<int[]> cells = new ArrayList<>();
        cells.add(new int[]{row, col});
        boolean[][] checked = new boolean[size][size];
        queue.offer(new int[]{row, col});
        checked[row][col] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newRow = current[0] + dy[i];
                int newCol = current[1] + dx[i];

                if (newRow >= 0 && newCol >= 0 && newRow < size && newCol < size
                        && !checked[newRow][newCol] && grid[newRow][newCol] == direction) {
                    checked[newRow][newCol] = true;
                    cells.add(new int[]{newRow, newCol});
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        if (cells.size() >= threshold) {
            for (int[] current : cells) {
                grid[current[0]][current[1]] = '.';
            }
        }
    }
}
