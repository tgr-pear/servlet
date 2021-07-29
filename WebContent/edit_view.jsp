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
<title> 게시글 작성 </title>
<style>
	* {
		margin 0 auto;
		padding 0 auto;
		overflow hidden;
	}
	
	td {
    	padding: 5px;
	}
</style>
</head>
<body>
	<form action="edit.do" method="post">
	
	<table width="500" cellpadding="0" cellspacing="0" border="1" >
		<tr>
		  <td> 이름 </td>
		  <td><input type="text" name="board_name" value="" size="50" /></td>
		</tr>
		
		<tr>
		  <td> 제목 </td>
		  <td><input type="text" name="board_title" value="" size="50" /></td>
		</tr>
		
		<tr>
		  <td> 내용 </td>
		  <td><textarea rows="10" cols="50" name="board_content" ></textarea></td>
		</tr>
	
		<tr>
		  <td colspan="2">
		    <input type="submit" value="쓰기"/> &nbsp;&nbsp;
			<a href="list.do"><input type="button" value="목록"/></a>
		  </td>
		</tr>
	</table>
	
	</form>
</body>
</html>