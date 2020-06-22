<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*"%>
<%
	String start_date = "20200620";
	String end_date = "20200715";
	int year = 0;
	String month_name = "";
	int nowpage = 1;
	String pageskip = "";
	
	CalendarDAO calendardao = new CalendarDAO();
	List<DateVO> datelist = new ArrayList<DateVO>();
	List<DateVO> weekdatelist = new ArrayList<DateVO>();
	if (request.getParameter("nowpage") != null) {
		nowpage = Integer.parseInt(request.getParameter("nowpage"));
	}
	if (request.getParameter("start_date") != null) {
		start_date = request.getParameter("start_date");
	}
	if (request.getParameter("end_date") != null) {
		end_date = request.getParameter("end_date");
		datelist =  calendardao.dateList(start_date, end_date);
		weekdatelist = calendardao.getWeekDate(datelist);
		year = weekdatelist.get(nowpage-1).getYear();
		month_name = weekdatelist.get(nowpage-1).getMonth_name();
	}
	
	datelist = calendardao.dateList(start_date, end_date);
	weekdatelist = calendardao.getWeekDate(datelist);
	year = weekdatelist.get(nowpage-1).getYear();
	month_name = weekdatelist.get(nowpage-1).getMonth_name();
	pageskip = calendardao.pageList(nowpage, start_date, end_date);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calendar</title>
</head>
<link href="/css/common.css" rel="stylesheet">

<body>

	<%@ include file="header.jsp"%>

	<div class="body-left">
		<div>
			<form name="search" method="post" action="index.jsp">
				&nbsp;&nbsp; 
				<input type="" value="기간" readOnly> 
				<input type="date" name="start_date"> ~ 
				<input type="date" name="end_date">&nbsp;&nbsp;
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
					int i = 0;
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
			<table>
				<tr>
					<td><p><%= pageskip %></p></td>
				</tr>
			</table>
		</div>
		<div>
			<form name="" method="" action="">
				<span id="selected_start_date"/>
				<span id="selected_end_date"/>
				<p id="date">0000년 00월 00일</p>
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
		alert("submit");
		search.submit();
	}

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
</script>
