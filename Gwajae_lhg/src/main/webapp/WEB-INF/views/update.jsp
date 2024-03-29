<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정하기</title>
</head>
<body>
	<h1>게시글 수정하기</h1>
	
	<form action="/update" method="post">
		<p>제목</p>
		<input type="text" name="title" value="${board.boardTitle}">
		
		<p>내용</p>
		<textarea name="content">${board.boardContent}</textarea>
		
		<input name="boardNo" value="${board.boardNo}" type="hidden">
		
		<button>수정하기</button>
		
		
	</form>

</body>
</html>