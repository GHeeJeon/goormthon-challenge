import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;


class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		while (N >= 1) {
			if (N >= 14) {
				answer = answer + (N / 14);
				N = N % 14;
			}
			else if (N >= 7) {
				answer = answer + (N / 7);
				N = N % 7;
			}
			else {
				answer = answer + N;
				N = 0;
			}
		}
		System.out.println(answer);
	}
}