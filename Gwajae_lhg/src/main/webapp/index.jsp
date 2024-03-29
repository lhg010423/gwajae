<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>gwajae_login</title>
	
<%--	<link rel="stylesheet" href="/resources/css/main.css"> --%>
</head>
<body>
	<main>
		
		<c:choose>
			<%-- 로그인을 하지 않았다면 --%>
			<c:when test="${empty sessionScope.loginMember}">
				
				<h1>게시판 로그인</h1>
				
				<form action="/login" method="post" class="login-form">
					<div>
						<p>아이디</p>
						<input type="text" name="inputId">
					</div>
					<div>
						<p>패스워드</p>
						<input type="password" name="inputPw">
					</div>
					
					<button>로그인</button>
					
					<a href="/signup" class="signup">회원가입</a>
					
				</form>
			
			</c:when>
			
			
			<%-- 로그인을 했다면 --%>
			<c:otherwise>
				<h1>전체 게시글</h1>
				
				<table>
					<tr>
						<th>게시판번호</th>
						<th>제목</th>
						<th>내용</th>
						<th>작성자</th>
					</tr>
					
					<c:forEach var="bd" items="${boardList}">
						<tr>
							
							<td>${bd.boardNo}</td>
							<td>${bd.boardTitle}</td>
							<td>${bd.boardContent}</td>
							<td>${bd.writer}</td>
							
						</tr>
					</c:forEach>
					
				</table>
				<br>
				<a href="/selectOne">내 게시글 보기</a>
			</c:otherwise>
			
		</c:choose>
	</main>
	
	<c:if test="${not empty sessionScope.message}">
		
		<script>
			alert('${message}');
		</script>
		<c:remove var="message" scope="session"/>
	</c:if>
	
	
</body>
</html>