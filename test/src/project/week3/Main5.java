import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        Pair[] fruits = new Pair[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long price = Long.parseLong(st.nextToken());
            long fullness = Long.parseLong(st.nextToken());

            fruits[i] = new Pair(fullness / price, price);
        }

        Arrays.sort(fruits);

        long totalFullness = 0;
        for (int i = N - 1; i >= 0; i--) {
            long cnt = Math.min(K, fruits[i].second);
            totalFullness += fruits[i].first * cnt;
            K -= cnt;
            if (K == 0) {
                break;
            }
        }

        System.out.println(totalFullness);
    }

    static class Pair implements Comparable<Pair> {
        long first;
        long second;

        public Pair(long first, long second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            return Long.compare(this.first, o.first);
        }
    }
}
