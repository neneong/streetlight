package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NovelDAO {
	public ArrayList<String> getNovelList() {
		ArrayList<String> list = new ArrayList();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		conn = JdbcUtil.getConnection();

		try {
			sql = "select title from novel";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return list;
	}
	
	public ArrayList<String> getNovelListByTag(String tagName) {
		ArrayList<String> list = new ArrayList();
		
		TagDAO td = new TagDAO();
		
		String nameId = td.getTagId(tagName);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		conn = JdbcUtil.getConnection();

		try {
			sql = "select id from novel_tag where tag_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nameId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
		

		return list;
	}
	
	public ArrayList<String> getNovelListByTitle(String title) {
		ArrayList<String> list = new ArrayList();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		conn = JdbcUtil.getConnection();

		try {
			sql = "select id from novel where novel_name LIKE '%"+title+"%'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public ArrayList<String> getNovelListByWritter() {
		ArrayList<String> list = new ArrayList();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		conn = JdbcUtil.getConnection();

		try {
			sql = "select title from novel";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return list;
	}
	
	public ArrayList<String> getNovelListByWritter(String writter) {
		ArrayList<String> list = new ArrayList();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select id from novel where writter=?";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writter);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return list;
	}
	
	public ArrayList<String> getPopularNovelId() {
		ArrayList<String> list = new ArrayList();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select id from novel WHERE ROWNUM<=3 ORDER BY prefer_count DESC";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
//	public ArrayList<String> getRecommendNovelId() {
//		ArrayList<String> list = new ArrayList();
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = "select id from novel WHERE ROWNUM<=3 ORDER BY popular DESC";
//
//		conn = JdbcUtil.getConnection();
//
//		try {
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				list.add(rs.getString("id"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JdbcUtil.close(conn, pstmt, rs);
//		}
//		
//		return list;
//	}
	
	public int getNovelId(String id) {
		int idx = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select id from novelcontent WHERE idx=?";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				idx = rs.getInt("id");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return idx;
	}
	
	public String getNovelTitle(String id) {
		String title = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select novel_name from novel WHERE id=?";

		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				title = rs.getString("novel_name");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return title;
	}
	
	public String getNovelIntro(String id) {
		String intro = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		conn = JdbcUtil.getConnection();

		try {
			sql = "select novel_intro from novel WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				intro = rs.getString("novel_intro");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return intro;
	}
	
	public String getNovelWritter(String id) {
		String writter = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		conn = JdbcUtil.getConnection();

		try {
			sql = "select writter from novel WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				writter = rs.getString("writter");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return writter;
	}
	
	public String getNovelImage(String id) {
		String image = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		conn = JdbcUtil.getConnection();

		try {
			sql = "select image from novel WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				image = rs.getString("image");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return image;
	}

	public int addNovel(String userId, String novel_name, String novel_intro, String[] tags) {
		int n = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		Util util = new Util();
		String sql = "insert into novel values(?,?,?,?,?,?,?,?)";
		Integer novelIdx = util.getNovelLastIndex()+1;
		conn = JdbcUtil.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, novelIdx);
			pstmt.setString(2, "default.png");
			pstmt.setString(3, userId);
			pstmt.setString(4, novel_name);
			pstmt.setString(5, novel_intro);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			n = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt);
		}
		
		TagDAO td = new TagDAO();
        NovelTagDAO ntd = new NovelTagDAO();
		ArrayList<String> tag = new ArrayList<>();
        for(int i = 0; i < tags.length;i++){
        	boolean checker = td.checkTag(tags[i]);
        	if(checker == false) {
        		td.addTag(tags[i]);
        	}
        	
        	String tagId = td.getTagId(tags[i]);
        	
        	ntd.addNovelTag(novelIdx.toString(), tagId);

        	
        }

		return n;
	}

	public int updateNovel(String novelIdx, String image, String novel_name, String novel_intro, String[] tags) {
		int n = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = JdbcUtil.getConnection();
		Util util = new Util();
		String sql;
		if(image!=null) {
			sql = "update novel set image=?, novel_name = ?, novel_intro = ? WHERE id=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, image);
				pstmt.setString(2, novel_name);
				pstmt.setString(3, novel_intro);
				pstmt.setString(4, novelIdx);
				n = pstmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(conn, pstmt);
			}
		}else {
			sql = "update novel set novel_name = ?, novel_intro = ? WHERE id=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, novel_name);
				pstmt.setString(2, novel_intro);
				pstmt.setString(3, novelIdx);
				n = pstmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(conn, pstmt);
			}
		}
		
		
		
		
		TagDAO td = new TagDAO();
        NovelTagDAO ntd = new NovelTagDAO();
        ntd.deleteNovelTag(novelIdx);
		ArrayList<String> tag = new ArrayList<>();
        for(int i = 0; i < tags.length;i++){
        	boolean checker = td.checkTag(tags[i]);
        	if(checker == false) {
        		td.addTag(tags[i]);
        	}
        	
        	String tagId = td.getTagId(tags[i]);
        	
        	ntd.addNovelTag(novelIdx.toString(), tagId);

        	
        }

		return n;
	}

	public int deleteNovel(int id) {
		int n = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from Novel where id=?";
		conn = JdbcUtil.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			n = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt);
		}
		return n;
	}
	

	

}
