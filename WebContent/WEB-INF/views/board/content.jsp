<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>	
<script>
$(function(){
	$("#btnUpdate").click(function() {
		location.href = "update_form.board?num=${vo.num}";
	});
	$("#btnDelete").click(function() {
		location.href = "delete_form.board?num=${vo.num}";
	});
	$("#btnReply").click(function() {
		location.href = "reply_form.board?num=${vo.num}";
	});
	$("#btnList").click(function() {
		location.href = "list.board";
	});
});
</script>	
<body>
<h1>글 상세 보기</h1>
<form>
	<table border="1">
		<tr>
			<td>글번호</td>
			<td>${vo.num}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${vo.writer}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${vo.subject}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${vo.content}</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${vo.read_count}</td>
		</tr>
		<tr>
			<td>IP</td>
			<td>${vo.ip}</td>
		</tr>
	</table>
<input type="button" value="수정" id="btnUpdate">
<input type="button" value="삭제" id="btnDelete">
<input type="button" value="목록" id="btnList">
<input type="button" value="답글" id="btnReply">
</form>
</body>
</html>