package servlet4_board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			
			String id = "ezen";
			String pw = "1234";
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
				// 고정 xe:express버전 / orcl:일반버전
			
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
			conn = DriverManager.getConnection( url, id, pw );
			
			System.out.println( "DB가 연결되었습니다." );
			
		} catch( ClassNotFoundException cnfe ) {
			System.out.println( "DB 연결 실패(ClassNotFoundException) : " + cnfe.toString() );
		} catch( SQLException sqle ) {
			System.out.println( "DB 연결 실패(SQLException) : " + sqle.toString() );
		}catch( Exception e ) {
			System.out.println( "Unknown error" );
			e.printStackTrace();
		}
		return conn;
	}

}
