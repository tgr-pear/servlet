<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="servlet4_board.BoardDto" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="servlet4_board.BoardDao" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 메인 게시판 </title>
<style>
	* {
		margin 0 auto;
		padding 0 auto;
		overflow hidden;
	}
	
	td, h1 {
		text-align : center;
	}
</style>
<script>
	/* 웹브라우저가 back키 누르면 페이지 재로딩
		if( window.name == "reloader" ) {
		window.name = "";
		location.reload(); // 한번만 재로딩
	}
	
	window.onbeforeunload = function() {
		window.name = "reloader";
	}
	*/
	
</script>
</head>
<body>

<h1> 게시판 글 목록 </h1> 
	
<table width="500" cellpadding="0" cellspacing="0" border="1" >
	<tr>
	  <th> 번호 </th>	
	  <th> 이름 </th>
	  <th> 제목 </th>
	  <th> 날짜 </th>
	  <th> 조회수 </th>
	</tr>
	
	<c:forEach var="board_dto" items="${ board_list }">
	  <tr>
	    <td> ${ board_dto.board_idx }</td>
	    <td> ${ board_dto.board_name }</td>
	    <td><a href="content_view.do?board_idx = ${ board_dto.board_idx }" >${ board_dto.board_title }</a></td>
	    <fmt:formatDate value="${ board_dto.board_date }" pattern = "yyyy-MM-dd HH:mm:ss" var = "board_date" />
	    <td> ${ board_dto.board_date }</td>
	    <td> ${ board_dto.board_hit }</td>
	  </tr>
	</c:forEach>
	
	<tr>
		<td colspan="5"><a href="edit_view.do">글 작성</a></td>
	</tr>
</table>	
	
</body>
</html>