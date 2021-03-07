package DAO;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Member;


public class MembersmanageDAO {
	public static String md5(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return  sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }
	public static List<Member> DisplaysMembermanage(HttpServletRequest request,int start, int count,Connection conn, int roleid) {
		
		List<Member> list = new ArrayList<Member>();
		PreparedStatement ptmt = null;
		String sql = "select * from user where roleid ="+roleid+" limit "+(start-1)+","+count+"";
		
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.isBeforeFirst()) {
				while (rs.next()) {
					Member mb = new Member();
					int userid = rs.getInt("userid");
					String fullname = rs.getString("fullname");
					String name = rs.getString("name");
					String password  = rs.getString("password");
				
					mb.setUserid(userid);
					mb.setFullname(fullname);
					mb.setName(name);
					mb.setPassword(password);
					 
					list.add(mb);
				}
			}else {
				request.setAttribute("msglistmembermanage","Hiện chưa có thành viên nào" );
			}
			
			
			
			
		} catch (SQLException e) {
			request.setAttribute("msglistmembermanage",e.getMessage() );
		}
		return list;
	}
	
	
	
	public static int Countrow(Connection conn, int roleid) {
		int count = 0;
		String sql = "select count(*) from user where roleid ="+roleid;
		
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
	
	public static void DeleteMember(Connection conn, int userid) {
		try {
			String sql = "delete from user where userid ="+userid;
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.executeUpdate();
			ptmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static int RetrieveidMember(HttpServletRequest request, Connection conn, Member mb) {
		int userid = 0;
		PreparedStatement ptmt = null;
		
		String sql = "select userid from user where name =" +mb.getName();
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				userid = rs.getInt("userid");
			}
			ptmt.close();
			rs.close();
		} catch (SQLException e) {
			request.setAttribute("msgretrieveuserid", e.getMessage());
			//e.printStackTrace();
		}
		return userid;
	}
	
	public static boolean UpdateMember(HttpServletRequest request, Connection conn, Member mb,int userid) {
		PreparedStatement ptmt = null;
		
		String sql ="update user set fullname=?,name=?,password=?   where userid="+userid;
		
		try {
			ptmt = conn.prepareStatement(sql);
			String fullname = mb.getFullname();
			String name = mb.getName();
			String password = MembersmanageDAO.md5(mb.getPassword());
			ptmt.setString(1, fullname);
			ptmt.setString(2, name);
			ptmt.setString(3, password);
			int kt = ptmt.executeUpdate();
			if(kt != 0) {
				return true;
			}
			ptmt.close();
		} catch (SQLException e) {
			request.setAttribute("msgupdatemember", e.getMessage());
		}
		return false;
	}
}
