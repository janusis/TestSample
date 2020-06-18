import java.util.Scanner;

public class Calendar {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/*
		 * monthSet은 각 달에 대한 날짜들을 저장한 배열, year은 연도, month는 달, week는 요일, day는 일, i는 반복문을
		 * 사용하기 위한 변수.
		 */
		int year = 0, month = 0, week = 0, day = 0, i = 0;
		int monthSet[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		while (true) {
			System.out.println("원하는 연도를 입력하세요. (0을 누르면 프로그램을 종료합니다.)");
			year = sc.nextInt();
			if (year == 0) {
				System.exit(0);
				break;
			}
			System.out.println("원하는 달을 입력하세요.");
			month = sc.nextInt();
			if (month < 1 || month > 12) {
				System.out.println("1월에서 12월 사이의 달을 입력해주세요.");
				return;
			}
			System.out.println("");

			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) // 윤년의 조건.
				monthSet[1] = 29; // 윤년이면 2월 (monthSet[1])은 29일,
			else
				monthSet[1] = 28; // 윤년이 아니면 2월은 28일이다.

			// 01년 1월 1일부터 계산.
			day = (year - 1) * 365 + (year - 1) / 4 - (year - 1) / 100 + (year - 1) / 400;
			for (i = 0; i < month - 1; i++) {
				day += monthSet[i];
			}
			week = day % 7; // 구하고자 하는 달의 시작일(1일)의 요일을 구함.

			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t\n", "월", "화", "수", "목", "금", "토", "일");
			for (i = 0; i < week; i++) {
				System.out.print("\t"); // 1일이 시작하기 전 공백을 위한 반복문.
			}
			for (i = 1; i <= monthSet[month - 1]; i++) {
				System.out.printf("%d\t", i); // i를 입력한 달의 일수만큼 찍음.
				week++;
				if (week % 7 == 0) // 요일이 일요일이 지나면
					System.out.println(); // 줄을 바꿈.

			}
			if (week % 7 != 0) {
				System.out.println();
			}
		}

	}
}
