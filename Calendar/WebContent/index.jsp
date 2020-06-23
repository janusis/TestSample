<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*"%>
<%
	int year = 2020;
	String month_name = "June";
	int nowpage = 1; 
	String start_date = "";
	String end_date = "";
	String nowyearmonth = "";
	String startmonthdate = "";
	String endmonthdate = "";
	String pageskip = "";
	int weeknumber = 1;
	
	CalendarDAO calendardao = new CalendarDAO();
	List<DateVO> datelist = new ArrayList<DateVO>();
	List<DateVO> weekdatelist = new ArrayList<DateVO>();
	if (request.getParameter("start_date") != null) {
		start_date = request.getParameter("start_date").replace("-","");
	}
	if (request.getParameter("end_date") != null) {
		end_date = request.getParameter("end_date").replace("-","");
		
		if((request.getParameter("startmonthdate") != null)
				&& request.getParameter("endmonthdate") != null){
			startmonthdate = request.getParameter("startmonthdate");
			endmonthdate = request.getParameter("endmonthdate");
			datelist =  calendardao.dateList(startmonthdate, endmonthdate);
			weekdatelist = calendardao.getWeekDate(datelist);
			year = weekdatelist.get(weeknumber-1).getYear();
			month_name = weekdatelist.get(weeknumber-1).getMonth_name();
			weeknumber = weekdatelist.get(weeknumber-1).getStartweeknumber();
		}else if(request.getParameter("startmonthdate") != null && request.getParameter("endmonthdate") == null){
			startmonthdate = request.getParameter("startmonthdate");
			datelist =  calendardao.dateList(startmonthdate, end_date);
			weekdatelist = calendardao.getWeekDate(datelist);
			year = weekdatelist.get(weeknumber-1).getYear();
			month_name = weekdatelist.get(weeknumber-1).getMonth_name();
			weeknumber = weekdatelist.get(weeknumber-1).getStartweeknumber();
		}else if(request.getParameter("startmonthdate") == null && request.getParameter("endmonthdate") != null){
			endmonthdate = request.getParameter("endmonthdate");
			datelist =  calendardao.dateList(start_date, endmonthdate);
			weekdatelist = calendardao.getWeekDate(datelist);
			year = weekdatelist.get(weeknumber-1).getYear();
			month_name = weekdatelist.get(weeknumber-1).getMonth_name();
			weeknumber = weekdatelist.get(weeknumber-1).getStartweeknumber();
		}else if(request.getParameter("startmonthdate") == null && request.getParameter("endmonthdate") == null){
			datelist =  calendardao.dateList(start_date, end_date);
			weekdatelist = calendardao.getWeekDate(datelist);
			year = weekdatelist.get(weeknumber-1).getYear();
			month_name = weekdatelist.get(weeknumber-1).getMonth_name();
			weeknumber = weekdatelist.get(weeknumber-1).getStartweeknumber();
		}
		
		nowyearmonth = start_date.substring(0,6);
		
		if (request.getParameter("nowyearmonth") != null) {
			nowyearmonth = request.getParameter("nowyearmonth");
		}
		if (request.getParameter("nowpage") != null) {
			nowpage = Integer.parseInt(request.getParameter("nowpage"));
		}
		pageskip = calendardao.pageList(nowpage, start_date, end_date, nowyearmonth);
	}else {
		pageskip = calendardao.pageList();
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calendar</title>
</head>
<link href="/css/common.css" rel="stylesheet">
<style>
html, body {
	width: 100%;
	height: 100%;
}
.body-whole {
	
}
.top {
	width: 100%;
}
.top tr td {
	height: 20px;
}
.body-left {
	width: 45%;
	height: 800px;
	float: left;
	padding: 10px;
}
.body-right {
	width: 45%;
	height: 800px;
	float: right;
	padding: 10px;
}
.title {
	text-align: center;
}

.calendar {
	margin: 0 auto;
}
.calendar th {
	background-color: light-grey;
	border: 1px solid #000;
	width: 115px;
	height: 40px;
}
.calendar td {
	border: 1px solid #000;
	width: 115px;
	height: 70px;
	text-align: center;
}
.list {
	margin: 0 auto;
}
.list th, td {
	height: 20px;
	width: 150px;
	border: 1px solid #000;
}
.arrow p{
	padding: 20px;
	text-align: center;
	margin: 0 auto;
}
#button {
	width: 100px;
	float: right;
}
#date {
	text-align: center;
}
</style>
<body>

	<%@ include file="header.jsp"%>

	<div class="body-left">
		<div>
			<form name="search" method="post" action="index.jsp">
				&nbsp;&nbsp; 
				<input type="" value="기간" readOnly size="2"> 
				<input type="date" name="start_date" min="1900-01-01" required pattern="\d{4}-\d{2}-\d{2}"> ~ 
				<input type="date" name="end_date" max="2100-12-31" required pattern="\d{4}-\d{2}-\d{2}">
				<input type="button" value="조회" id="button" onClick="searchDate();">
			</form>
			<div class="title">
				<h1> <%= year %>년 <%= month_name %></h1>
			</div>
			<table class="calendar">
				<tr>
					<th>SUN</th>
					<th>MON</th>
					<th>TUE</th>
					<th>WED</th>
					<th>THU</th>
					<th>FRI</th>
					<th>SAT</th>
				</tr>
				<%
					if(weekdatelist.size()>0){
						for (DateVO vo : weekdatelist) {
				%>
				<tr>
					<td><% if(vo.getSunday()==0){ %> <% }else{ %> <%=vo.getSunday()%> <%} %></td>
					<td><% if(vo.getMonday()==0){ %> <% }else{ %> <%=vo.getMonday()%> <%} %></td>
					<td><% if(vo.getTuesday()==0){ %> <% }else{ %> <%=vo.getTuesday()%> <%} %></td>
					<td><% if(vo.getWednesday()==0){ %> <% }else{ %> <%=vo.getWednesday()%> <%} %></td>
					<td><% if(vo.getThursday()==0){ %> <% }else{ %> <%=vo.getThursday()%> <%} %></td>
					<td><% if(vo.getFriday()==0){ %> <% }else{ %> <%=vo.getFriday()%> <%} %></td>
					<td><% if(vo.getSaturday()==0){ %> <% }else{ %> <%=vo.getSaturday()%> <%} %></td>
	
				</tr>
				<%
						}
						for(int weekcount = weekdatelist.size(); weekcount<5 ; weekcount++){
				%>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<%
						}
					}else {
						for(int x=0; x<5; x++){
				%>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<%
						}
					}
						
				%>
	
			</table>
		</div>
		<div class="arrow">
			<p><%= pageskip %></p>
		</div>
		<div>
			<form name="" method="" action="">
				<p id="date">0000년 00월 00일</p>
				<input type="hidden" name="start_date" value="">
				<input type="hidden" name="end_date" value="">
				<input type="hidden" name="nowpage" value="">
				<input type="hidden" name="nowyearmonth" value="">
				<input type="hidden" name="startmonthdate" value="">
				<input type="hidden" name="endmonthdate" value="">
				<input type="hidden" name="print_list" value="">
				<input type="button" value="적용" onclick="" id="button">
			</form>
		</div>
	</div>



	<div class="body-right">
		<table class="list">
			<tr>
				<th>일자</th>
				<th>요일</th>
				<th>국경일</th>
			</tr>
			<%
				if (true) {
				for (int x = 0; x < 20; x++) {
			%>
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<%
				}
			}
			%>
		</table>
	</div>
</body>
</html>
<script>

	function searchDate() {
		var date_pattern = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/; 
		if(!date_pattern.test(search.start_date.value)){
			alert("시작 날짜를 다시 입력해주세요.");
			return;
		}

		if(!date_pattern.test(search.end_date.value)){
			alert("종료 날짜를 다시 입력해주세요.");
			return;
		}
		var startdate = search.start_date.value.replace("-","");
		var enddate = search.end_date.value.replace("-","");
		if(startdate > enddate){
			alert("날짜 범위가 잘못되었습니다.");
			return;
		}
		alert("조회합니다.");
		search.submit();
	}
	
	
	/*
	function count_ckeck(object) {
		var check = document.getElementsByName("check");

		var checkCount = 0;
		for (var i = 0; i < check.length; i++) {
			if (check[i].checked) {
				var msg = check[i].value;
				alert(msg);

				checkCount++;
			}
		}
		if (checkCount > 2) {
			alert("check NO");
			object.checked = false;
			return false;
		}
	}
	*/
</script>
