<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="servlet4_board.BoardDto" %>
<%@ page import="servlet4_board.BoardDao" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 게시글 보기 </title>
<style>
	table {
		text-align : center;
	}
</style>
</head>
<body>

	<!-- 게시글 보기 -->
	<table width="500" cellpadding="0" cellspacing="0" border="1" >
	
		<tr>
		  <td width="40%" > 번호 </td>
		  <td width="60%" > ${ board_dto.board_idx }</td>
		</tr>
		
		<tr>
		  <td width="40%" > 조회수 </td>
		  <td width="60%" > ${ board_dto.board_hit }</td>
		</tr>
		
		<tr>
		  <td width="40%" > 이름 </td>
		  <td width="60%" > ${ board_dto.board_name }</td>
		</tr>
	
		<tr>
		  <td width="40%" > 제목 </td>
		  <td width="60%" > ${ board_dto.board_title }</td>
		</tr>
		
		<tr>
		  <td width="40%" > 글 내용 </td>
		  <td width="60%" > ${ board_dto.board_content }</td>
		</tr>
		
		<tr>
		  <td colspan="2">
		    <a href="modify.do"><input type="button" value="수정"/></a> &nbsp; &nbsp;
			<a href="list.do"><input type="button" value="목록"/></a>
		  </td>
		</tr>
		
	</table>
	
	<!-- 댓글 작성  -->
	<form action="reply.do" method="post">
	  <input type="hidden" name="reply_board_idx" value=" ${ board_dto.board_idx }" />
	  
	  <table width="500" cellpadding="0" cellspacing="0" boarder="1">
	  	<tr>
	  	  <td colspan="2">
	  	  	<label> 댓글 </label> <textarea name="reply_content" cols="50" rows="2"></textarea>
	  	  	  <br>
	  	  	<label> 별명 </label> <input type="text" name="reply_name" value="" />
	  	  	<input type="submit" value="댓글" />
	  	  </td>
	  	</tr>
	  </table>
	  
	</form>
	<br>
	
	<table width="500" cellpadding="0" cellspacing="0" boarder="1" >
	  <tr>
	  	<th> 별명 </th>
	  	<th> 내용 </th>
	  	<th> 날짜 </th>
		<th> 삭제 </th>
	  </tr>
	  
	  <c:forEach var="reply_dto" items=" ${ reply_list }">
	    <tr>
	      <td> ${ reply_dto.reply_name }  </td>
	      <td> ${ reply_dto.reply_content }  </td>
	      <td> ${ reply_dto.reply_date }  </td>
	      <td> 
	      	<a href="reply_delete.do?reply_idx = ${ reply_dto.reply_idx } & reply_board_idx = ${ reply_dto.reply_board_idx }" >
	      	<input type="submit" value="삭제" /></a>
	      </td>
	    </tr>
	  </c:forEach>
	  
	</table>

</body>
</html>