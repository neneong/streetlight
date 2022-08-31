package dao;

import java.sql.*;

public class JdbcUtil {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		}catch(Exception e){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "hr");
			}catch(Exception i){
				i.printStackTrace();
				System.out.println("DB 연결 오류");
			}
		}
		return conn;
	}
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(SQLException e) {
				System.out.println("pstmt.close() 실패");
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
				System.out.println("conn.close() 실패");
				e.printStackTrace();
			}
		}
	}
	
	
	public static void close(Connection close, PreparedStatement pstmt, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException e) {
				System.out.println("rs.close 실패");
				e.printStackTrace();
			}
	
		}

	}
}
