import java.util.List;

import model.DateVO;

public class PageIndex {

	// 페이지
	public static String pageList(int nowpage, String start_date, String end_date) {
		String previous_page = "";
		String next_page = "";

		String start = start_date.replace("-", "");
		String end = end_date.replace("-", "");
		int startyearmonth = Integer.parseInt(start.substring(0, 6));
		int startyear = Integer.parseInt(start.substring(0, 4));
		int startmonth = Integer.parseInt(start.substring(4, 6));
		int endyearmonth = Integer.parseInt(end.substring(0, 6));

		int totpage = 0;

		while (startyearmonth <= endyearmonth) {
			if (startmonth != 12) {
				startmonth++;
				if (startmonth < 10) {
					startyearmonth = Integer.parseInt(startyear + "0" + startmonth);
				} else {
					startyearmonth = Integer.parseInt(startyear + "" + startmonth);
				}

			} else {
				startyear++;
				startmonth = 1;
				startyearmonth = Integer.parseInt(startyear + "0" + startmonth);
			}
			totpage++;
		}

		// Prev 표시 부분
		if (nowpage > 1) {
			String addtag = "";
			if (nowpage - 1 == 1) {
				addtag = "start_date=" + start_date + "&";
			} else {
				addtag = "";
			}
			previous_page = "<li><a href='" + "?page=" + (nowpage - 1) + "'>이전</a></li> ";
		} else {
			previous_page = "<li><a>이전</a></li> ";
		}

		// Next 표시부분
		if (nowpage < totpage) {
			String addtag = "";
			if (nowpage + 1 == totpage) {

			} else {

			}

			next_page = " <li><a href='" + "?page=" + (nowpage + 1) + "'>다음</a></li>";
		} else {
			next_page = " <li><a>다음</a></li>";
		}

		String outHtml = previous_page + next_page; // Html 문 조합
		return outHtml;
	}

	// 전체 페이지수 구하기
	public int getPageCount(String start_date, String end_date) {
		String start = start_date.replace("-", "");
		String end = end_date.replace("-", "");
		int startyearmonth = Integer.parseInt(start.substring(0, 6));
		int startyear = Integer.parseInt(start.substring(0, 4));
		int startmonth = Integer.parseInt(start.substring(4, 6));
		int endyearmonth = Integer.parseInt(end.substring(0, 6));

		int totpage = 0;

		while (startyearmonth <= endyearmonth) {
			if (startmonth != 12) {
				startmonth++;
				if (startmonth < 10) {
					startyearmonth = Integer.parseInt(startyear + "0" + startmonth);
				} else {
					startyearmonth = Integer.parseInt(startyear + "" + startmonth);
				}

			} else {
				startyear++;
				startmonth = 1;
				startyearmonth = Integer.parseInt(startyear + "0" + startmonth);
			}
			totpage++;
		}

		return totpage;
	}

}
