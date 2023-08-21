import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int max = Integer.MIN_VALUE;
        int max_idx = -1;
        int sum = 0;
        int[] tastes = new int[N];
        
        for (int i = 0; i < N; i++) {
            tastes[i] = Integer.parseInt(st.nextToken());
            sum += tastes[i];
            
            if (tastes[i] > max) {
                max = tastes[i];
                max_idx = i;
            }
        }

        for (int i = 1; i < max_idx; i++) {
            if (tastes[i] < tastes[i - 1]) {
                System.out.println("0");
                return;
            }
        }

        for (int i = N - 2; i > max_idx; i--) {
            if (tastes[i] < tastes[i + 1]) {
                System.out.println("0");
                return;
            }
        }

        System.out.println(sum);
    }
}
