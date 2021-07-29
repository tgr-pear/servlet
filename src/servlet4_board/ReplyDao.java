package servlet4_board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplyDao {

	// 댓글 추가
	public static void reply( String reply_name, String reply_content, String reply_board_idx ) {
	    Connection conn = null; // DB연결 클래스 
	    PreparedStatement pstmt = null; // 매개변수 입력을 고려한 State 클래스
	    
	    try {
	    	conn = DBConnection.getConnection(); // DB 연결 객체
	    	
	    	String query = "INSERT INTO reply( reply_idx, reply_name, reply_content, reply_date, reply_board_idx )"
	    			+ "VALUES ( reply_board_seq.nextval, ?, ?, sysdate, ? )";
	    	pstmt = conn.prepareStatement( query );
	    	pstmt.setString( 1, reply_name );
	    	pstmt.setString( 2, reply_content );
	    	pstmt.setInt( 3, Integer.parseInt( reply_board_idx ) );

	    	int result = pstmt.executeUpdate(); // insert, update, delete 쿼리문에서 사용 / ( query ) ; X 
	    	System.out.println( "insert result : " + result );
	    	
	    } catch( Exception e ) {
	    	e.printStackTrace();
	    }
	}
	
	// 댓글 삭제
	public static void reply_delete( String reply_idx ) {
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
	    	conn = DBConnection.getConnection();
	    	String query = "DELETE FROM reply WHERE reply_idx=?";
	    	pstmt = conn.prepareStatement( query );
	    	pstmt.setInt( 1, Integer.parseInt( reply_idx ) );
	    	printSqlStatement( pstmt, query );

	    	int result = pstmt.executeUpdate();
	    	System.out.println( "INSERT result : " + result );
	    	
	    } catch( Exception e ) {
	    	e.printStackTrace();
	    }
	}

	
	public static void printSqlStatement(PreparedStatement preparedStatement, String sql) throws SQLException {
	    String[] sqlArrya = new String[preparedStatement.getParameterMetaData().getParameterCount()];
	    try {
	      Pattern pattern = Pattern.compile("\\?");
	      Matcher matcher = pattern.matcher(sql);
	      StringBuffer sb = new StringBuffer();
	      
	      int indx = 1;
	      while( matcher.find() )
	        matcher.appendReplacement(sb, String.valueOf(sqlArrya[indx])); 
	      matcher.appendTail(sb);
	      System.out.println("Executing Query [" + sb.toString() + "] with Database[" + "] ...");
	    
	    } catch( Exception e ) {
	      System.out.println("Executing Query [" + sql + "] with Database[" + "] ...");
	    } 
	  }
	
}
