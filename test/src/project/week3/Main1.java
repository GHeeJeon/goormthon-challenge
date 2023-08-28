import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int item1 = Integer.parseInt(st.nextToken());
        int item2 = Integer.parseInt(st.nextToken());

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[N] = 0;

        for (int i = N; i >= 0; i--) {
            if (dp[i] != Integer.MAX_VALUE) {
                if (i - item1 >= 0) {
                    dp[i - item1] = Math.min(dp[i - item1], dp[i] + 1);
                }
                if (i - item2 >= 0) {
                    dp[i - item2] = Math.min(dp[i - item2], dp[i] + 1);
                }
            }
        }

        System.out.println(dp[0] != Integer.MAX_VALUE ? dp[0] : -1);
    }
}
