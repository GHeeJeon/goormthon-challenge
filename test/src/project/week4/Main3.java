import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 격자의 크기와 그릴 선의 개수를 입력받는다.
        int gridSize = scanner.nextInt();
        int numOfLines = scanner.nextInt();
        scanner.nextLine();  // 다음 라인으로 넘어가기 위한 코드

        // 각각의 세로선과 가로선을 저장할 2차원 배열 초기화
        long[][] verticalLines = new long[gridSize+1][gridSize+1];
        long[][] horizontalLines = new long[gridSize+1][gridSize+1];

        // 교차점의 개수를 저장할 변수 초기화
        long intersectionCount = 0;

        // 선의 개수만큼 반복하여 선의 정보를 입력받고 처리
        for (int s = 0; s < numOfLines; s++) {
            int startY = scanner.nextInt();
            int startX = scanner.nextInt();
            String direction = scanner.next();
            scanner.nextLine();  // 다음 라인으로 넘어가기 위한 코드

            // 방향에 따른 처리
            if (direction.equals("D")) {
                for (int i = startY; i <= gridSize; i++) {
                    intersectionCount += horizontalLines[i][startX];  // 교차점 계산
                    verticalLines[i][startX]++;   // 세로선 갱신
                }
            } else if (direction.equals("U")) {
                for (int i = 1; i <= startY; i++) {
                    intersectionCount += horizontalLines[i][startX];  // 교차점 계산
                    verticalLines[i][startX]++;   // 세로선 갱신
                }
            } else if (direction.equals("R")) {
                for (int i = startX; i <= gridSize; i++) {
                    intersectionCount += verticalLines[startY][i];    // 교차점 계산
                    horizontalLines[startY][i]++; // 가로선 갱신
                }
            } else if (direction.equals("L")) {
                for (int i = 1; i <= startX; i++) {
                    intersectionCount += verticalLines[startY][i];    // 교차점 계산
                    horizontalLines[startY][i]++; // 가로선 갱신
                }
            }
        }

        // 교차점의 개수 출력
        System.out.println(intersectionCount);
    }
}
