import java.io.*;
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String input_time = br.readLine();
		String[] arr = input_time.split("\\s+");
		
		int T = Integer.parseInt(arr[0]);
		int M = Integer.parseInt(arr[1]);
		int merge_time = (T * 60) + M; 

		int sum_time = 0;
		for (int i = 0; i < N; i++) {
			sum_time += Integer.parseInt(br.readLine());
		}

		int sum_merge_time = merge_time + sum_time;
		
		T = sum_merge_time / 60;
		M = sum_merge_time % 60;
		
		if (T >= 24) {
			T %= 24;
		}
		
		System.out.println(T + " " + M);
	}
}