<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>답글 달기</h1>
<form action="reply_pro.board">
	<input type="hidden" name="ref" value="${vo.ref}">
	<input type="hidden" name="re_step" value="${vo.re_step}">
	<input type="hidden" name="re_level" value="${vo.re_level}">
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
			<td><input type="text" name="subject" value="[Re]${vo.subject}"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content">${vo.content}</textarea></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd" required></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="완료"/></td>
		</tr>
	</table>
</form>
</body>
</html>