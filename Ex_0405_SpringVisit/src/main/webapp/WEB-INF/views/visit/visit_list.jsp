<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="/visit/resources/css/visit.css">
		<!-- Ajax사용을 위한 js파일 등록 -->
		<script src="/visit/resources/js/httpRequest.js"></script>
		
		<script>
			function del(f) {
				let pwd = f.pwd.value; //원본 비밀번호
				let c_pwd = f.c_pwd.value; //입력 비밀번호
				
				if(pwd != c_pwd){
					alert("비밀번호 불일치");
					return;
				}
				
				//삭제 확인
				if(!confirm("정말로 삭제하시겠습니까?")){
					return;
				}
				
				//삭제를 위한 url mapping 호출
				var url = "delete.do";
				var param = "idx="+f.idx.value;
				
				sendRequest(url, param, resFn, "get");
			}
			
			function resFn() {
				if(xhr.readyState == 4 && xhr.status == 200){
					
					//data = "no" 또는 "yes"
					var data = xhr.responseText;
					
					if(data == 'no'){
						alert("삭제 실패");
					} else{
						alert("삭제 성공");
					}
					
					location.href="list.do";//전체 목록 갱신
				}
			}
			
			function modify(f) {
				let pwd = f.pwd.value; //원본 비밀번호
				let c_pwd = f.c_pwd.value; //입력 비밀번호
				
				if(pwd != c_pwd){
					alert("비밀번호 불일치");
					return;
				}
				
				f.action="modify_form.do";
				f.method = "post";
				f.submit();
			}
		</script>
	</head>
	<body>
		<div id="main_box">
			<h1>방명록 리스트</h1>
			<div align="right">
				<input type="button" value="글쓰기" onClick="location.href='insert_form.do'">
			</div>
			 
			<c:forEach var="vo" items="${ list }">
				<div class="visit_box">
					<div class="type_content"><pre>${ vo.content }</pre></div>
					<div class="type_name">${ vo.name }(${ vo.ip })</div>
					<div class="type_regdate">작성일 : ${ vo.regdate }</div>

					<form>
						<input type="hidden" name="idx" value="${ vo.idx }">
						<input type="hidden" name="pwd" value="${ vo.pwd }">
						비밀번호 <input type="password" name="c_pwd">
						
						<input type="button" value="수정" onClick="modify(this.form)">
						<input type="button" value="삭제" onClick="del(this.form)">
					</form>
				</div>
			</c:forEach>
		</div>
	</body>
</html>