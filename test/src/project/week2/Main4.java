import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String[][] board = new String[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().split(" ");
        }

        int[][] bomb = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            bomb[i][0] = Integer.parseInt(st.nextToken()) - 1; // 0-based index
            bomb[i][1] = Integer.parseInt(st.nextToken()) - 1; // 0-based index
        }

        int[][] check = new int[N][N];

        int[] dx = {0, 1, 0, -1, 0}; // {0, 0} 추가
        int[] dy = {1, 0, -1, 0, 0}; // {0, 0} 추가

        for (int[] b : bomb) {
            int r = b[0];
            int c = b[1];

            for (int m = 0; m < 5; m++) {
                int nx = r + dx[m];
                int ny = c + dy[m];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !board[nx][ny].equals("#")) {
                    if (board[nx][ny].equals("0")) check[nx][ny]++;
                    if (board[nx][ny].equals("@")) check[nx][ny] += 2;
                }
            }
        }

        int max = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (check[i][j] > max) {
                    max = check[i][j];
                }
            }
        }

        System.out.println(max);
    }
}