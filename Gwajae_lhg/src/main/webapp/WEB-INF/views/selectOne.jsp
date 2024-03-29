<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>내 게시판 보기</title>
</head>
<body>
	
	<h1>내 게시글 모음</h1>
	<table>
		<tr>
			<th>게시글번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>작성자ID</th>
		</tr>
		
	
		<c:forEach var="bl" items="${boardList}">
			<tr>
				
				<td>${bl.boardNo}</td>
				<td>${bl.boardTitle}</td>
				<td>${bl.boardContent}</td>
				<%--
				<td><a href="boardView?boardNo=${bo.boardNo}">${bo.boardTitle}</a></td>
				--%>
				<td>${bl.writer}</td>
				<td><a href="/update?boardNo=${bl.boardNo}">수정</a></td>
				<td><a href="/delete?boardNo=${bl.boardNo}">삭제</a></td>
			</tr>
			
		</c:forEach>
	
	</table>
	
	<br>
	<a href="/firstView">처음으로</a>
	


</body>
</html>