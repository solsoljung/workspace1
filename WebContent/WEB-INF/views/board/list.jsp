<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>							
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>	
<script>
$(function(){
	function getList(nowPage){
		$("input[name=nowPage]").val(nowPage);
		$("#searchForm").submit();
	}
	$(".paging").click(function(e){
		e.preventDefault();
		var page = $(this).attr("href");
		getList(page);
	});
	$("select[name=nLine]").change(function(){
		var line = (this).val();
		location.href="";    //여기하는 중
	});
});
</script>
<h1>글목록</h1>
<form action="list.board" id="searchForm">
	<input type="hidden" name="nowPage" value="${pagingDto.nowPage}">
<select name="searchType">
		<option value="subject"
		<c:if test="${searchDto.searchType == 'subject'}">
		selected
		</c:if>
		>제목</option>
		<option value="content"
		<c:if test="${searchDto.searchType == 'content'}">
		selected
		</c:if>
		>내용</option>
		<option value="writer"
		<c:if test="${searchDto.searchType == 'writer'}">
		selected
		</c:if>
		>작성자</option>
</select>
<input type="text" name="keyword" value="${searchDto.keyword}">
<input type="submit" value="검색">
<select name="nLine">
	<option value="10">10줄씩 보기</option>
	<option value="15">15줄씩 보기</option>
	<option value="20">20줄씩 보기</option>
</select>
</form>
	<table border="1">
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
			<td>IP</td>
		</tr>
		<c:forEach items="${list}" var="vo">
		<tr>
			<td>${vo.num}</td>
			<td><a href="content.board?num=${vo.num}">${vo.subject}</a></td>
			<td>${vo.writer}</td>
			<td>${vo.reg_date}</td>
			<td>${vo.read_count}</td>
			<td>${vo.ip}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="6">
				<c:if test="${pagingDto.startPage != 1}">
					<a class="paging" href="${pagingDto.startPage - 1}">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${pagingDto.startPage}" end="${pagingDto.endPage}">
					<a class="paging" href="${i}">${i}</a>
				</c:forEach>
				<c:if test="${pagingDto.endPage != pagingDto.totalPage}">
					<a class="paging" href="${pagingDto.endPage + 1}">[다음]</a>
				</c:if>
					
			</td>
		</tr>
		
	</table>
<a href="write_form.board">글쓰기</a>
</body>
</html>