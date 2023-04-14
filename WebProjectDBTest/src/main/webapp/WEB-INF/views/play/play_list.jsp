<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<!-- Ajax사용을 위한 js파일 등록 -->
		<script src="/visit/resources/js/httpRequest.js"></script>
		
		
	</head>
	<body>
		<form action="play.do" method="post">
			<input type="submit" value="save">
			<table border="1">
				<tr>
					<td>공연ID</td>
					<td>공연이름</td>
					<td>시작일</td>
					<td>종료일</td>
					<td>공연장</td>
					<td>장르</td>
					<td>포스터경로</td>
					<td>상태</td>
					<td>페이징시퀀스</td>
				</tr>
				
				<c:forEach var="vo" items="${ list }">
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</body>
</html>