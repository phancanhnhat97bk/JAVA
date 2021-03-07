package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.sql.*;

import BEAN.Cmtguideline;
import BEAN.Guideline;
import BEAN.Member;

public class BaihdDAO {
	public static List<Guideline> DisplayGl(HttpServletRequest request,int start, int count, Connection conn, int guidelineroleid, Member mem){
		List<Guideline> list = new ArrayList<Guideline>();
		String sql = null;
		if(guidelineroleid == 1 || guidelineroleid == 2) {
			sql = "select * from guideline where guidelineroleid ="+guidelineroleid+" limit "+(start-1)+","+count+"";
		}
		else {
			int result = ResultDAO.AverageResult(request, conn, mem);
			if(result <50) {
				sql = "select * from guideline where level ="+1+" limit "+(start-1)+","+count+"";
			}else if(result <70) {
				sql = "select * from guideline where level ="+2+" limit "+(start-1)+","+count+"";
			}else {
				sql = "select * from guideline where level ="+3+" limit "+(start-1)+","+count+"";
			}
		}
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				Guideline gl = new Guideline();
				int guidelineid = rs.getInt("guidelineid");
				String guidelinename = rs.getString("guidelinename");
				String guidelineimage = rs.getString("guidelineimage");
				int level = rs.getInt("level");
				
				gl.setGuidelineid(guidelineid);
				gl.setGuidelinename(guidelinename);
				gl.setGuidelineimage(guidelineimage);
				gl.setLevel(level);
				
				list.add(gl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public static int Countrow(Connection conn,int guidelineroleid) {
		int count = 0;
		String sql;
		if(guidelineroleid == 1 || guidelineroleid ==2) {
			sql = "select count(*) from guideline where guidelineroleid ="+guidelineroleid;
		}
		else {
			sql = "select count(*) from guideline where level ="+1;
		}
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
	public static List<Guideline> DisplayContent(Connection conn, int glid){
		List<Guideline> list = new ArrayList<Guideline>();
		String sql = "select * from guideline where guidelineid ="+glid;
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				Guideline gl = new Guideline();
				int guidelineid = rs.getInt("guidelineid");
				String guidelinename = rs.getString("guidelinename");
				String guidelineimage = rs.getString("guidelineimage");
				String content = rs.getString("content");
				
				
				gl.setGuidelineid(guidelineid);
				gl.setGuidelinename(guidelinename);
				gl.setGuidelineimage(guidelineimage);
				gl.setContent(content);
				
				list.add(gl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
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
}
