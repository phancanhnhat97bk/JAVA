package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Cmtguideline;

public class CommentguidelineDAO {
	public static void InsertCmtguideline(HttpServletRequest request, Connection conn, Cmtguideline cmtguideline) {
		PreparedStatement ptmt = null;
		
		String sql = "insert into cmtguideline (cmtguidelinecontent,guidelineid,userid) value(?,?,?)";
		try {
			ptmt = conn.prepareStatement(sql);
			String cmtguidelinecontent = cmtguideline.getCmtguidelinecontent();
			int guidelineid = cmtguideline.getGuidelineid();
			int userid = cmtguideline.getUserid();
			ptmt.setString(1, cmtguidelinecontent);
			ptmt.setInt(2, guidelineid);
			ptmt.setInt(3, userid);
			ptmt.executeUpdate();
			ptmt.close();
		} catch (SQLException e) {
			//request.setAttribute("msgregister", e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static List<Cmtguideline> DisplayCmtGuideline (Connection conn, int guidelineid){
		List<Cmtguideline> list = new ArrayList<Cmtguideline>();
		
		String sql = "select * from cmtguideline where guidelineid ="+guidelineid;
		
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			while(rs.next()) {
				Cmtguideline cmt = new Cmtguideline();
				int userid = rs.getInt("userid");
				String cmtguidelinecontent = rs.getString("cmtguidelinecontent");
				String name = CommentguidelineDAO.Nameflid(conn, userid);
				String userimage = CommentguidelineDAO.UserImageflid(conn, userid);
				
				cmt.setCmtguidelinecontent(cmtguidelinecontent);
				cmt.setName(name);
				cmt.setUserimage(userimage);
				list.add(cmt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public static String Nameflid(Connection conn, int userid) {
		String name = "";
		String sql = "select name from user where userid ="+userid;
		
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			rs.next();
			name = rs.getString("name");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return name;
	}
	public static String UserImageflid(Connection conn, int userid) {
		String userimage = "";
		String sql = "select userimage from user where userid ="+userid;
		
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			rs.next();
			userimage = rs.getString("userimage");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userimage;
	}
	public static int CountCmt(Connection conn, int guidelineid) {
		int count = 0;
		String sql = "select count(*) from cmtguideline where guidelineid="+guidelineid;
		
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
}
