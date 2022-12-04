package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PreferDAO {
	public int addPrefer(String user_id, String novel_id) {
		int n = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		Util util = new Util();
		String sql = "insert into prefer values(?,?)";
		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, novel_id);
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt);
		}

		return n;
	}
	
	public int deletePrefer(String user_id, String novel_id) {
		int n = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		Util util = new Util();
		String sql = "delete from prefer where id=? AND prefer_id=?";
		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, novel_id);
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt);
		}

		return n;
	}
	
	public int getPreferCount(String novel_id) {
		int n = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Util util = new Util();
		ResultSet rs = null;
		String sql = "SELECT * FROM prefer WHERE prefer_id=?";
		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, novel_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				n+=1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt);
		}
		
		return n;
	}
	
	public boolean checkPrefer(String user_id, String novel_id) {
		boolean n = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		Util util = new Util();
		ResultSet rs = null;
		String sql = "SELECT * FROM prefer WHERE id=? AND prefer_id=?";
		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, novel_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				n = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt);
		}
		
		return n;
	}
	
	public ArrayList<String> GetUserPrefer(String user_id){
		ArrayList<String> list = new ArrayList();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select prefer_id from prefer where id=?";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("prefer_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return list;
	}
}
