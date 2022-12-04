package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
	public ArrayList<String> getMemberList() {
		ArrayList<String> list = new ArrayList();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select user_id from member";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return list;
	}

	public int insertMember(String userId, String userpwd, String nick, String birth) {
		int n = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into member values(?,?,?,'default.png',?)";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userpwd);
			pstmt.setString(3, nick);
			pstmt.setString(4, birth);
			
			n = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt);
		}

		return n;
	}

	public int updateMember(String userId, String userPwd) {
		int n = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update member set user_pwd = ? where user_id = ?";
		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userPwd);
			pstmt.setString(2, userId);
			n = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt);
		}
		return n;
	}

	public int deleteMember(String userId) {
		int n = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from member where user_id=?";
		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			n = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt);
		}
		return n;
	}
	
	public boolean getMemberPwd(String id, String pwd) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select user_pwd from member where user_id=?";
		boolean result = false;

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if(pwd.equals(rs.getString("user_pwd")))
					result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return result;
	}
	
	public String getUserProfileImage(String user_id) {
		String profile_image = "";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		conn = JdbcUtil.getConnection();

		try {
			sql = "select user_image from member where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				profile_image = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return profile_image;
		
		
	}
	
	public String getMemberId(String user_id) {
		String member_id = "";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select user_id from member where user_id=?";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				member_id = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return member_id;
	}
	
	public String getMember() {
		String member_name = "";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select nick_name from member where user_id=?";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				member_name = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return member_name;
	}
	
	public String searchMember(String search) {
		String user_id = "";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select user_id from member where nick_name LIKE '%?%'";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user_id = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return user_id;
	}

}
