<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<!-- Ajax사용을 위한 js파일 등록 -->
		<script src="/visit/resources/js/httpRequest.js"></script>
		
		<script>
			function send(f) {
				let idx = f.idx.value;
				let name = f.name.value.trim();
				let content = f.content.value.trim();
				let pwd = f.pwd.value.trim();
				let photo = f.photo.value();
				
				//유효성 체크
				if( name == '' || content == '' || pwd == ''){
					alert("빈칸이 존재합니다");
					return;
				}
				
				var url = "modify.do";
				var param = "idx="+idx+"&name="+name+"&content="+encodeURIComponent(content)+"&pwd="+pwd+"&photo="+photo;
				//alert("전송");
				sendRequest(url, param, resFn, "post");
			}
			
			function resFn() {
				if(xhr.readyState == 4 && xhr.status == 200){
					
					//data = "no" 또는 "yes"
					var data = xhr.responseText;
					
					if(data == 'no'){
						alert("수정 실패");
					} else{
						alert("수정 성공");
					}
					
					location.href="list.do";//전체 목록 갱신
				}
			}
		</script>
	</head>
	<body>
		<form method="post" enctype="multipart/form-data">
			<input type="hidden" name="idx" value="${vo.idx}">
			<table border="1" align="center">
				<caption>내용 수정</caption>
				
				<tr>
					<th>작성자</th>
					<td><input name="name" value="${ vo.name }"></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="5" cols="50" name="content" style="resize:none;" wrap="on">${ vo.content }</textarea>
					</td>
				</tr>
				
				<tr>
					<th>파일첨부</th>
					<td><input type="file" name="photo"></td>
				</tr>
				
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd" value="${ vo.pwd }"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="수정" onClick="send(this.form);">
						<input type="button" value="목록으로" onClick="location.href='list.do'">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>