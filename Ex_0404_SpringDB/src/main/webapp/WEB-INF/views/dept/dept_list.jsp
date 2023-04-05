<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<script>
			function ins(f) {
				let deptno = f.deptno.value;
				let dname = f.dname.value;
				let loc = f.loc.value;
				
				//alert(deptno + " / " + dname + " / " + loc);
				
				if(deptno == "" || dname == "" || loc == ""){
					alert("빈칸 존재");
					return;
				}
				
				/* if(dname.length > 3){
					alert("부서이름은 3글자 이하로 입력하시오");
					return;
				} */
				
				f.action = "insert.do";
				f.method = "post";
				f.submit();
			}
			
			function mod(f){
				let deptno = f.deptno.value;
				let dname = f.dname.value;
				let loc = f.loc.value;
				
				//alert(deptno + " / " + dname + " / " + loc);
				
				if(deptno == "" || dname == "" || loc == ""){
					alert("빈칸 존재 / 수정할 부서 번호를 입력하시오");
					return;
				}
				
				f.action = "modify.do";
				f.method = "post";
				f.submit();
			}
			
			function del(delno) {
				if(!confirm("정말로 삭제하시겠습니까?")){
					return;
				}else{
					location.href = "del.do?delno="+delno;
				}	
			}
		</script>
	</head>
	<body>
		<form>
			<table border="1" align="center">
				<caption>부서목록</caption>
				
				<tr>
					<th width="65">부서번호</th>
					<th width="100">부서이름</th>
					<th width="100">부서위치</th>
					<th width="75">비고</th>
				</tr>
				<c:forEach var="vo" items="${ list }">
					<tr>
						<td>${ vo.deptno }</td>
						<td>${ vo.dname }</td>
						<td>${ vo.loc }</td>
						<td>
							<input type="button" value="삭제" onclick="del(${ vo.deptno });">
						</td>
					</tr>
				</c:forEach>
				
				<tr>
					<td colspan="4">
						<input name="deptno" size="4" placeholder="부서번호">
						<input name="dname" size="9" placeholder="부서이름">
						<input name="loc" size="9" placeholder="부서위치">
						<input type="button" value="추가" style="float: right;" onClick="ins(this.form);">
						<input type="button" value="수정" style="float: right;" onClick="mod(this.form);">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>