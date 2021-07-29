package servlet4_board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class BoardDao {
	
	 // 게시글 목록 가져오기 - 완성
	 public static ArrayList<BoardDto> board_list() {
	  ArrayList<BoardDto> board_list = new ArrayList<BoardDto>();
	  
	  Connection conn = null; // DB 연결 클래스 
	  ResultSet rs = null; // 검색 결과를 담는 클래스
	  Statement stmt = null; // 쿼리(SQL)를 전송하는 클래스
	  // PreparedStatement pstmt = null; // 매개변수 입력을 고려한 State 클래스
	  
	  try {
		  conn = DBConnection.getConnection(); // DB 연결 객체
		  stmt = conn.createStatement(); // 쿼리문 객체
		  
		  String query = "SELECT * FROM board ORDER BY board_date DESC"; // 작성시간 기준으로 내림차순 정렬
		  rs = stmt.executeQuery( query ); // 쿼리문 실행 
		  
		  // next() 다음값이 있는지 true / false 리턴 
		  while( rs.next() ) { 
			  int board_idx = rs.getInt( "board_idx" );
			  String board_name = rs.getString( "board_name"); 
			  String board_title = rs.getString( "board_title" );
			  String board_content = rs.getString( "board_content" );
			  Date board_date = rs.getDate( "board_date"); 
			  int board_hit = rs.getInt( "board_hit" );
			  
			  System.out.println( "board_idx : " + board_idx );
			  System.out.println( "board_title : " + board_title ); 
			  System.out.println( "board_content : " + board_content );
			  
			  BoardDto board_dto = new BoardDto( board_idx, board_name, board_title,
				  							board_content, board_date, board_hit ); 
			  board_list.add( board_dto );
		  }
	  }
	  catch( Exception e ) {	 }
		  
	  return board_list; 
	  }
	 
	 // 게시글 작성 - 완성
	 public static int edit(String board_name, String board_title, String board_content ) {	
		 Connection conn = null; // DB연결 클래스 
		 PreparedStatement pstmt = null; // 매개변수 입력을 고려한 State 클래스
		 
		 try {
			 conn = DBConnection.getConnection(); // DB 연결 객체
			 
			 String query = "INSERT INTO board( board_idx, board_name, board_title, board_content, board_date ) "
			 		+ "VALUES ( board_seq.nextval, ?, ?, ?, sysdate )"; // SQL 문자열
			 pstmt = conn.prepareStatement( query );
			 pstmt.setString( 1, board_name );
			 pstmt.setString( 2, board_title );
			 pstmt.setString( 3, board_content );
			 
			 int result = pstmt.executeUpdate(); // insert, update, delete 쿼리문에서 사용 / ( query ) ; X 
			 System.out.println( "insert result : " + result );			 
			 
		 } catch(Exception e) {
			 e.printStackTrace();
		 }

		 return 1; // 성공 
	 }
	 
	 // 조회수 올리기 
	 public static void hit( String board_idx ) {
		 Connection conn = null; // DB연결 클래스 
		 PreparedStatement pstmt = null; // 매개변수 입력을 고려한 State 클래스

		 try {
			 conn = DBConnection.getConnection(); // DB 연결 객체
			 
			 String query = "UPDATE board SET board_hit = board_hit + 1 WHERE board_idx=?"; // SQL 문자열
			 pstmt = conn.prepareStatement( query );
			 pstmt.setInt( 1, Integer.parseInt( board_idx ) );
			 
			 int result = pstmt.executeUpdate(); // insert, update, delete 쿼리문에서 사용 / ( query ) ; X 
			 System.out.println( "hit result : " + result );
			 
		 } catch( Exception e ) {
			 e.printStackTrace();
		 }
	 }
	 
	 // 게시물 보기  - 게시물 보기 시 %20 찍힘
	 public static BoardDto content_view( String board_idx ) {
	    BoardDto board_dto = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	    	conn = DBConnection.getConnection();
	    	String query = "SELECT * FROM board WHERE board_idx = ?"; // '%20' 공백 나옴 ;  trim() ,,?
	    	  System.out.println( query );
	    	pstmt = conn.prepareStatement( query );
	    	pstmt.setInt(1, Integer.parseInt( board_idx ) );
	    	rs = pstmt.executeQuery();
	    	
	      while ( rs.next() ) {
	        int board_idx_int = rs.getInt( "board_idx" );
	        String board_name = rs.getString( "board_name" );
	        String board_title = rs.getString( "board_title" );
	        String board_content = rs.getString( "board_content" );
	        Date board_date = rs.getTimestamp( "board_date" );
	        int board_hit = rs.getInt( "board_hit" );
	        board_dto = new BoardDto( board_idx_int, board_name, board_title, 
	        							board_content, board_date, board_hit );
	        
	      } 
	    } catch ( Exception e ) {	
	    	e.printStackTrace();
	    }
	    return board_dto;
	  }
	 
	 // 댓글 보기
	 public static ArrayList<ReplyDto> reply_list( String board_idx ) {
		  ArrayList<ReplyDto> reply_list = new ArrayList<>();
		  
		  Connection conn = null; // DB 연결 클래스 
		  ResultSet rs = null; // 검색 결과를 담는 클래스
		  PreparedStatement pstmt = null; // 매개변수 입력을 고려한 State 클래스
		  
		    System.out.println( "reply_list board_idx : " + board_idx );
		  
		  try {
			  conn = DBConnection.getConnection(); // DB 연결 객체
			  
			  String query = "SELECT * FROM reply WHERE reply_board_idx = ?"
			  						+ "ORDER BY reply_date DESC"; 			// 게시글마다 작성시간 기준으로 내림차순 정렬
			  pstmt = conn.prepareStatement( query ); // 쿼리문 객체
			  pstmt.setInt( 1, Integer.parseInt( board_idx ) );
			  rs = pstmt.executeQuery(); // 쿼리문 실행 
			  
			  // next() 다음값이 있는지 true / false 리턴 
			  while( rs.next() ) { 
				  int reply_idx = rs.getInt( "reply_idx" );
				  String reply_name = rs.getString( "reply_name");
				  String reply_content = rs.getString( "reply_content" );
				  Date reply_date = rs.getDate( "reply_date"); 
				  int reply_board_hit = rs.getInt( "reply_board_hit" );
				  
				  System.out.println( "reply_idx : " + reply_idx );
				  System.out.println( "reply_name : " + reply_name ); 
				  System.out.println( "reply_content : " + reply_content );
				  
				  ReplyDto reply_dto = new ReplyDto( reply_idx, reply_name, reply_content,
						  										reply_date, reply_board_hit ); 
				  reply_list.add( reply_dto );
			  }
		  } catch( Exception e ) {	 }
			  
		  return reply_list; 
		  }
	 
}
