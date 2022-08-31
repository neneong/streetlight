package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecommendDAO {
	public ArrayList<String> getRecommendNovelId() {
		ArrayList<String> list = new ArrayList();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM(select recommend_id, count(id) counter from recommend GROUP BY recommend_id ORDER BY counter DESC) WHERE ROWNUM<=3";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("recommend_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return list;
	}
	public int getRecommendCount(String novel_id) {
		int n = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Util util = new Util();
		ResultSet rs = null;
		String sql = "SELECT * FROM recommend WHERE recommend_id=?";
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
	public int addRecommend(String user_id, String novel_id) {
		int n = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		Util util = new Util();
		String sql = "insert into recommend values(?,?)";
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
	
	public int deleteRecommend(String user_id, String novel_id) {
		int n = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		Util util = new Util();
		String sql = "delete from recommend where id=? AND recommend_id=?";
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
	
	public boolean checkRecommend(String user_id, String novel_id) {
		boolean n = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		Util util = new Util();
		ResultSet rs = null;
		String sql = "SELECT * FROM recommend WHERE id=? AND recommend_id=?";
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
}
