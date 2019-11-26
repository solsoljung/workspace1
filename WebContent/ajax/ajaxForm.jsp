<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$("#btn").click(function(){
			$.getJSON("ajaxSend.jsp", {}, function(receiveData){
				console.log(receiveData);
 				$.each(receiveData, function(){
 					var name = this.name;
 					var age = this.age;
 						console.log(name);
 						console.log(age);
 						var td = "<td>" + name + "</td>";
 							td += "<td>" + age + "</td>";
 						$("#tbl tr").append(td);
 				});
			});
		});
	});
</script>
<title>Insert title here</title>
</head>
<body>
<input type="button" value="클릭" id="btn">
<table border="1" id="tbl">
	<tr></tr>
</table>
</body>
</html>