<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>delete_form</h1>
<form action="delete_pro.board">
<input type="hidden" name="num" value="${num}">
<table border="1">
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd"></td>
	</tr>
</table>
<input type="submit" value="삭제">
</form>
</body>
</html>