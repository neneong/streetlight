package dao;

import dao.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class NovelContentDAO {
	public ArrayList<String> getNovelContentNameList(String novel_id) {
		ArrayList<String> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select content_name from novelcontent WHERE id = ? ORDER BY idx";

		conn = JdbcUtil.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, novel_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {	
				list.add(rs.getString("content_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return list;
	}
	
	public ArrayList<String> getNovelContentIdxList(String novel_id) {
		ArrayList<String> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		conn = JdbcUtil.getConnection();
		
		try {
			sql = "select idx from novelcontent WHERE id = ? ORDER BY idx";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, novel_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {	
				list.add(rs.getString("idx"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return list;
	}
	
	public String getNovelTitle(String idx) {
		String title = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from novelcontent WHERE idx = ? ORDER BY idx";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				title = rs.getString("content_name");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return title;
	}
	
	public String getNovelContent(String idx) {
		String content = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from novelcontent WHERE idx = ? ORDER BY idx";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				content = rs.getString("novel_content");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return content;
	}
	
	
	public int getPastNovelContentIdx(String idx, String novel_id) {
		Integer past_idx = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM(SELECT idx, id, LAG(idx) OVER (ORDER BY idx) past_idx, LAG(novel_idx) OVER (ORDER BY idx) past_novel_idx FROM novelContent WHERE id=? ORDER BY novel_idx) WHERE idx=?";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, novel_id);
			pstmt.setString(2, idx);
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				past_idx = rs.getInt("past_idx");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return past_idx;
	}
	
	public int getNextNovelContentIdx(String idx, String novel_id) {
		Integer next_idx = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM(SELECT idx, id, LEAD(idx) OVER (ORDER BY idx) forward_idx, LEAD(novel_idx) OVER (ORDER BY idx) forward_novel_idx FROM novelContent WHERE id=? ORDER BY novel_idx) WHERE idx=?";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, novel_id);
			pstmt.setString(2, idx);
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				next_idx = rs.getInt("forward_idx");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return next_idx;
	}

	public int addNovelContent(String novelId, String title, String content) {
		int n = 0;
		Util util = new Util();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into novelContent values(?,?,?,?,?,(SELECT SYSDATE FROM DUAL))";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, util.getNovelContentLastIndex()+1);
			pstmt.setInt(2, util.getNovelLastIdx(novelId)+1);
			pstmt.setString(3, novelId);
			pstmt.setString(4, title);
			pstmt.setString(5, content);
			
			n = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt);
		}

		return n;
	}
	


	public int deleteNovelContent(int idx) {
		int n = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM novelContent WHERE idx = ?";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			n = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt);
		}

		return n;
	}
	
	public int updateNovelContent(int idx, String title, String content) {
		int n = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE novelContent set content_name = ?, novel_content = TO_CLOB(?) WHERE idx = ?";

		conn = JdbcUtil.getConnection();
		System.out.println(title);
		System.out.println(content);
		System.out.println(idx);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, Integer.toString(idx));
			n = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt);
		}

		return n;
	}
	
	public String getNovelId(String idx) {
		String id = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from novelcontent WHERE idx = ?";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				id = rs.getString("id");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return id;
	}
	
	

}
