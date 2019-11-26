<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>글쓰기</h1>
<form action="write_pro.board" method="post" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer" required></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="subject"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content"></textarea></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd" required></td>
		</tr>
		<tr>
			<td>이미지</td>
			<td><input type="file" name="file1"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="완료"/></td>
		</tr>
	</table>
</form>
</body>
</html>