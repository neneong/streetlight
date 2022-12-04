package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Util {
	
	public int getNovelContentLastIndex() {
		int idx = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * FROM(SELECT * FROM novelcontent ORDER BY idx DESC) WHERE ROWNUM=1";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				idx = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		
		return idx;
	}
	
	public int getNovelLastIndex() {
		int idx = 0;
		
		

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * FROM(SELECT * FROM novel ORDER BY id DESC) WHERE ROWNUM=1";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				idx = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		
		return idx;
	}
	
	public int getNovelLastIdx(String id) {
		int idx = 0;
		
		

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * FROM(SELECT * FROM novel ORDER BY idx DESC) WHERE id=? AND ROWNUM=1";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				idx = rs.getInt(2);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		
		return idx;
	}
	
	public int getNovelContentLastContentId() {
		int idx = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * FROM(SELECT * FROM novelcontent ORDER BY content_id DESC) WHERE ROWNUM=1";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				idx = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		
		return idx;
	}
	
	public int getNovelTagLastContentId() {
		int idx = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * FROM(SELECT * FROM tag ORDER BY id DESC) WHERE ROWNUM=1";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				idx = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		
		return idx;
	}
	
	public int getNovelId(String writter, String novelName) {
		
		int idx = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * FROM novel WHERE writter = ? AND novel_name = ?";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writter);
			pstmt.setString(2, novelName);
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				idx = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		
		return idx;
	}
}
