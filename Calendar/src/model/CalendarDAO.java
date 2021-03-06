package model;

import java.util.ArrayList;
import java.util.List;

public class CalendarDAO {
	
	//해당 연도에 따른 월별 마지막 날짜
	private static void calculateMonthdate(int year, int[] monthset) {
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) { // 윤년의 조건.
			monthset[1] = 29; // 윤년이면 2월 (monthSet[1])은 29일,
		} else {
			monthset[1] = 28; // 윤년이 아니면 2월은 28일이다.
		}
	}
	
	//마지막 날짜 범위 지정
	private static int setLastDate(int endyear, int endmonth, int enddate, int year, int month, int[] monthset) {
		int maxdate;
		if (year == endyear && month == endmonth) {
			maxdate = enddate;
		} else {
			maxdate = monthset[month - 1];
		}
		return maxdate;
	}

	//전년월 계산
	public String getPreviousYearMonth(int nowpage, String nowyearmonth) {
		String previousyearmonth = nowyearmonth;
		int nowyear = Integer.parseInt(previousyearmonth.substring(0,4));
		int nowmonth = Integer.parseInt(previousyearmonth.substring(4,6));
		if(nowmonth == 01) {
			nowyear--;
			nowmonth = 12;
			previousyearmonth = nowyear + "" + nowmonth;
		}else {
			nowmonth--;
			if(nowmonth<10) {
				previousyearmonth = nowyear + "0" + nowmonth;
			}else {
				previousyearmonth = nowyear + "" + nowmonth;
			}
		}
		return previousyearmonth;
	}
	
	//다음년월 계산
	public String getNextYearMonth(int nowpage, String nowyearmonth) {
		String nextyearmonth = nowyearmonth;
		int nowyear = Integer.parseInt(nextyearmonth.substring(0,4));
		int nowmonth = Integer.parseInt(nextyearmonth.substring(4,6));
		if(nowmonth == 12) {
			nowyear++;
			nowmonth = 01;
			nextyearmonth = nowyear + "0" + nowmonth;
		}else {
			nowmonth++;
			if(nowmonth<10) {
				nextyearmonth = nowyear + "0" + nowmonth;
			}else {
				nextyearmonth = nowyear + "" + nowmonth;
			}
		}
		return nextyearmonth;
	}
	
	//전달 다음달 버튼 출력
	public String pageList(int nowpage, String start_date, String end_date, String nowyearmonth) {
		int startyear = Integer.parseInt(start_date.substring(0, 4));
		int startmonth = Integer.parseInt(start_date.substring(4, 6));
		int endyearmonth = Integer.parseInt(end_date.substring(0, 6));
		int monthset[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		
		String previous_page = "";
		String next_page = "";
		
		int totpage = 0;
		int startyearvalue = startyear;
		int startmonthvalue = startmonth;
		int startyearmonthvalue = 0;
		if(startmonthvalue < 10) {
			startyearmonthvalue = Integer.parseInt(startyear + "0" + startmonth);
		}else {
			startyearmonthvalue = Integer.parseInt(startyear + "" + startmonth);
		}
		while (startyearmonthvalue <= endyearmonth) {
			if (startmonthvalue != 12) {
				startmonthvalue++;
				if (startmonthvalue < 10) {
					startyearmonthvalue = Integer.parseInt(startyearvalue + "0" + startmonthvalue);
				} else {
					startyearmonthvalue = Integer.parseInt(startyearvalue + "" + startmonthvalue);
				}
			} else {
				startyearvalue++;
				startmonthvalue = 1;
				startyearmonthvalue = Integer.parseInt(startyearvalue + "0" + startmonthvalue);
			}
			totpage++;
		}
		
		// Prev 표시 부분
		if (nowpage > 1) {
			String addtag = "";
			String previousyearmonth = getPreviousYearMonth(nowpage, nowyearmonth);
			String previousstartmonthdate = previousyearmonth + "01";
			
			calculateMonthdate(Integer.parseInt(previousyearmonth.substring(0,4)), monthset);
			int date = monthset[Integer.parseInt(previousyearmonth.substring(4,6))-1];
			String previousendmonthdate = "";
			if(date<10) {
				previousendmonthdate = previousyearmonth + "0" + date;
			}else {
				previousendmonthdate = previousyearmonth + date;
			}
			if (nowpage - 1 == 1) {
				addtag = "?start_date=" + start_date + "&end_date=" + end_date 
						+ "&nowpage=" + (nowpage - 1) + "&nowyearmonth=" + previousyearmonth 
						+ "&endmonthdate=" + previousendmonthdate;
			} else {
				addtag = "?start_date=" + start_date + "&end_date=" + end_date 
						+ "&nowpage=" + (nowpage - 1) + "&nowyearmonth=" + previousyearmonth 
						+ "&startmonthdate=" + previousstartmonthdate + "&endmonthdate=" + previousendmonthdate ;
			}
			previous_page = "<a href='index.jsp" + addtag + "'><img src='image/button-left.jpg'></a> ";
		} else {
			previous_page = "<img src='image/button-left.jpg'>";
		}

		// Next 표시부분
		if (nowpage < totpage) {
			String addtag = "";
			String nextyearmonth = getNextYearMonth(nowpage, nowyearmonth);
			
			String nextstartmonthdate = nextyearmonth + "01";
			
			calculateMonthdate(Integer.parseInt(nextyearmonth.substring(0,4)), monthset);
			int date = monthset[Integer.parseInt(nextyearmonth.substring(4,6))-1];
			String nextendmonthdate = "";
			if(date<10) {
				nextendmonthdate = nextyearmonth + "0" + date;
			}else {
				nextendmonthdate = nextyearmonth + date;
			}
			
			if (nowpage + 1 == totpage) {
				addtag = "?start_date=" + start_date + "&end_date=" + end_date 
						+ "&nowpage=" + (nowpage + 1) + "&nowyearmonth=" + nextyearmonth 
						+ "&startmonthdate=" + nextstartmonthdate ;
			} else {
				addtag = "?start_date=" + start_date + "&end_date=" + end_date 
						+ "&nowpage=" + (nowpage + 1) + "&nowyearmonth=" + nextyearmonth 
						+ "&startmonthdate=" + nextstartmonthdate + "&endmonthdate=" + nextendmonthdate ;
			}
			next_page = " <a href='index.jsp" + addtag + "'><img src='image/button-right.jpg'></a>";
		} else {
			next_page = " <img src='image/button-right.jpg'>";
		}
		String outHtml = previous_page + next_page; // Html 문 조합
		return outHtml;
	}
	
	// 처음 출력시 이전 다음 버튼 출력
	public String pageList() {
		return "<img src='image/button-left.jpg'>" + " <img src='image/button-right.jpg'>";
	}
	
	//시작날과 종료날까지의 날짜 계산 후 리스트로 리턴
	public List<DateVO> dateList(String start_date, String end_date) {
		int startyear = Integer.parseInt(start_date.substring(0, 4));
		int startmonth = Integer.parseInt(start_date.substring(4, 6));
		int startdate = Integer.parseInt(start_date.substring(6, 8));
		int endyear = Integer.parseInt(end_date.substring(0, 4));
		int endmonth = Integer.parseInt(end_date.substring(4, 6));
		int enddate = Integer.parseInt(end_date.substring(6, 8));
		
		int monthset[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		
		List<DateVO> datelist = new ArrayList<DateVO>();

		int year = 0, month = 0, date = 0, day = 0, findday = 0, value = 0, maxdate = 0, weeknumber = 0;
		
		year = startyear;
		month = startmonth;
		
		//요일을 구함
		value = (year - 1) * 365 + (year - 1) / 4 - (year - 1) / 100 + (year - 1) / 400;
		for (date = 0; date < month - 1; date++) {
			value += monthset[date];
		}
		findday = value % 7; // 구하고자 하는 달의 시작일(1일)의 요일을 구함.
		day = ((findday + startdate) % 7);

		boolean flag = true;
		while (flag) {
			calculateMonthdate(year, monthset);
			
			maxdate = setLastDate(endyear, endmonth, enddate, year, month, monthset);

			for (date = startdate; date <= maxdate; date++) {
				String setmonth = "";
				String setdate = "";
				if (month < 10) {
					setmonth = "0" + month;
				} else {
					setmonth = "" + month;
				}
				if (date < 10) {
					setdate = "0" + date;
				} else {
					setdate = "" + date;
				}
				DateVO datevo = new DateVO();
				datevo.setSample_date(year + setmonth + setdate);
				datevo.setSample_day(day);
				datevo.setMonth(month);

				weeknumber = (date + (5 - day)) / 7 + 1;
				datevo.setWeeknumber(weeknumber);
				if (setmonth.equals("01")) {
					datevo.setMonth_name("January");
				} else if (setmonth.equals("02")) {
					datevo.setMonth_name("February");
				} else if (setmonth.equals("03")) {
					datevo.setMonth_name("March");
				} else if (setmonth.equals("04")) {
					datevo.setMonth_name("April");
				} else if (setmonth.equals("05")) {
					datevo.setMonth_name("May");
				} else if (setmonth.equals("06")) {
					datevo.setMonth_name("June");
				} else if (setmonth.equals("07")) {
					datevo.setMonth_name("July");
				} else if (setmonth.equals("08")) {
					datevo.setMonth_name("August");
				} else if (setmonth.equals("09")) {
					datevo.setMonth_name("September");
				} else if (setmonth.equals("10")) {
					datevo.setMonth_name("October");
				} else if (setmonth.equals("11")) {
					datevo.setMonth_name("November");
				} else if (setmonth.equals("12")) {
					datevo.setMonth_name("December");
				}
				datevo.setYear(year);

				datelist.add(datevo);
				
				if (day == 6) {
					day = 0;
				} else {
					day++;
				}

				if (date == maxdate) {
					if (month == endmonth) {
						if (year == endyear) {
							flag = false;
							break;
						} else {
							date = 0;
							if (month == 12) {
								month = 1;
								year++;
								calculateMonthdate(year, monthset);
							} else {
								month++;
							}
						}
					} else {
						if (year == endyear) {
							date = 0;
							month++;
						} else {
							date = 0;
							if (month == 12) {
								month = 1;
								year++;
								calculateMonthdate(year, monthset);
							} else {
								month++;
							}
						}
					}
					maxdate = setLastDate(endyear, endmonth, enddate, year, month, monthset);
				}
			}
		}
		return datelist;
	}

	//날짜들을 일주일 단위로 잘라서 리스트에 담아 리턴
	public List<DateVO> getWeekDate(List<DateVO> datelist) {
		List<DateVO> weekdatelist = new ArrayList<DateVO>();
		DateVO datevo = new DateVO();

		int date = 0, day = 0, weeknumber = 0, listcount = 0, startweek = 0;
		boolean flag = true;

		weeknumber = datelist.get(listcount).getWeeknumber();
		
		startweek = 1;
		
		while (startweek != weeknumber) {
			datevo = new DateVO();
			datevo.setYear(datelist.get(listcount).getYear());
			datevo.setMonth(datelist.get(listcount).getMonth());
			datevo.setMonth_name(datelist.get(listcount).getMonth_name());
			
			for (int i = 0; i < 7; i++) {
				datevo.setSunday(0);
				datevo.setMonday(0);
				datevo.setTuesday(0);
				datevo.setWednesday(0);
				datevo.setThursday(0);
				datevo.setFriday(0);
				datevo.setSaturday(0);
			}

			datevo.setWeeknumber(startweek);
			datevo.setStartweeknumber(weeknumber);
			weekdatelist.add(datevo);

			startweek++;
		}

		while (listcount < datelist.size()) {
			if (flag) {
				day = datelist.get(listcount).getSample_day();

				datevo = new DateVO();

				datevo.setYear(datelist.get(listcount).getYear());
				datevo.setMonth(datelist.get(listcount).getMonth());
				datevo.setMonth_name(datelist.get(listcount).getMonth_name());
				datevo.setWeeknumber(datelist.get(listcount).getWeeknumber());

				for (int daycount = day; daycount < 7; daycount++) {
					date = Integer.parseInt((datelist.get(listcount).getSample_date() + "").substring(6, 8));

					switch (datelist.get(listcount).getSample_day()) {
					case 0:
						datevo.setSunday(date);
						break;
					case 1:
						datevo.setMonday(date);
						break;
					case 2:
						datevo.setTuesday(date);
						break;
					case 3:
						datevo.setWednesday(date);
						break;
					case 4:
						datevo.setThursday(date);
						break;
					case 5:
						datevo.setFriday(date);
						break;
					case 6:
						datevo.setSaturday(date);
						break;
					default:
						break;
					}
					if(listcount+1 == datelist.size()) {
						flag = false;
						break;
					}
					if (date > Integer.parseInt((datelist.get(listcount + 1).getSample_date() + "").substring(6, 8))) {
						flag = false;
						break;
					}

					listcount++;

					if (listcount == datelist.size()) {
						break;
					}
				}
				weekdatelist.add(datevo);
			} else {
				break;
			}
		}
		return weekdatelist;
	}
}
