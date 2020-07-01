package book.connect;

import java.sql.*;

public class ConnectionClose {

	/*
	 * public static Connection getConnection(String dsn) { Connection conn = null;
	 * 
	 * try { if (dsn.equals("mysql")) {
	 * 
	 * }else if(dsn.equals("oracle")) {
	 * Class.forName("oracle.jdbc.driver.OracleDriver"); conn =
	 * DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##yoon",
	 * "oracle");
	 * 
	 * } } catch (Exception e) { e.printStackTrace(); } finally { return conn; } }
	 * 
	 * 
	 * 
	 * public static Connection getConnection(String dsn, String user, String pwd) {
	 * Connection conn = null;
	 * 
	 * 
	 * try { if (dsn.equals("mysql")) {
	 * 
	 * }else if(dsn.equals("oracle")) {
	 * Class.forName("oracle.jdbc.driver.OracleDriver"); conn =
	 * DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",user,pwd);
	 * 
	 * } } catch (Exception e) { e.printStackTrace(); } finally { return conn; } }
	 */
	
	public static void close(Connection conn) {
		try {
			
			if (conn != null) conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void close(Statement stmt) {
		try {
			if (stmt != null) stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void close(PreparedStatement pstmt) {
		try {
			if (pstmt != null) pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void close(ResultSet rs) {
		try {
			if (rs != null) rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
