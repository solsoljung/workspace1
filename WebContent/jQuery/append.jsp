<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>								
<script>
$(document).ready(function(){
	var tr = "<td>데이터</td>"
	$("#btnAppend").click(function(){
		$("#tblAppend tr").append(tr);
	});
});
</script>
<title>Insert title here</title>
</head>
<body>
<form>
	<table id="tblAppend">
		<tr></tr>
	</table>
</form>
<input type="button" value="클릭" id="btnAppend"/>
</body>
</html>