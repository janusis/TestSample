import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CalendarExam {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("년,월 입력 : ");
		String[] day = sc.nextLine().split(",");
		sc.close();
		int year = Integer.parseInt(day[0]);
 		int month = Integer.parseInt(day[1]);
		
 		System.out.println("\t\t   [ "+year+"년 "+month+"월 ]");
 		System.out.println("일\t월\t화\t수\t목\t금\t토");
 		//년, 월을 이용한 calendar 객체 생성
 		GregorianCalendar calendar = new GregorianCalendar(year, month-1,1);
 		
 		//해당 월의 마지막 날 구하기
 		int maxday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
 		//요일 구하기(1:일요일 ~ 7:토요일)
 		int week = calendar.get(Calendar.DAY_OF_WEEK);
 		//시작요일 전까지 공백 출력
 		for(int i=1;i<week;i++) {
 			System.out.print("\t");
 		}
 		//일자 출력
 		for(int x=1; x<=maxday; x++,week++) {
 			if(week%7!=0) {
 				System.out.print(x+"\t");
 			}else {
 				System.out.println(x);
 			}
 		}
 		
 		
	}
}
