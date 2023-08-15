import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		String[] arr = input.split("\\s+");
		
		double W = Double.parseDouble(arr[0]);
		double R = Double.parseDouble(arr[1]);
		
		System.out.println((int)(W*(1+(R/30))));
	}
}