package DAO;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import BEAN.Member;

public class LoginDAO {
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
	public static boolean Authenticationmember(HttpServletRequest request, Connection conn, Member mem) {
		PreparedStatement ptmt = null;
		
		
		String sql = "select *from user";
		
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				String password = rs.getString("password");
				if(name.equals(mem.getName()) && password.equals(LoginDAO.md5(mem.getPassword()))){
					return true;
				}
			}
			ptmt.close();
			rs.close();
		} catch (SQLException e) {
			request.setAttribute("msglogin", e.getMessage());
			//e.printStackTrace();
		}
		return false;
	}
	
	
	public static int Authorizationmember(HttpServletRequest request, Connection conn, Member mem) {
		PreparedStatement ptmt = null;
		
		String name = mem.getName();
		String password = LoginDAO.md5(mem.getPassword());
		String sql = "select roleid from user "+" where name = '"+name+"' AND password = '"+password+"';";
		int roleid = 0;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				roleid = rs.getInt("roleid");
			}
			ptmt.close();
			rs.close();
		} catch (SQLException e) {
			request.setAttribute("msglogin", e.getMessage());
			//e.printStackTrace();
		}
		return roleid;
	}
	public static String Exportnameimage(HttpServletRequest request,Connection conn, Member mem) {
		PreparedStatement ptmt = null;
		String name = mem.getName();
		String password = LoginDAO.md5(mem.getPassword());
		String sql = "select userimage from user "+" where name = '"+name+"' AND password = '"+password+"';";
		String nameimage = "";
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				nameimage = rs.getString("userimage");
			}
			ptmt.close();
			rs.close();
		} catch (SQLException e) {
			request.setAttribute("msglogin", e.getMessage());
			//e.printStackTrace();
		}
		return nameimage;
	}
	
	public static int Exportmemberid(HttpServletRequest request,Connection conn, Member mem) {
		PreparedStatement ptmt = null;
		String name = mem.getName();
		String password = LoginDAO.md5(mem.getPassword());
		String sql = "select userid from user "+" where name = '"+name+"' AND password = '"+password+"';";
		int userid = 0;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				userid = rs.getInt("userid");
			}
			ptmt.close();
			rs.close();
		} catch (SQLException e) {
			request.setAttribute("msglogin", e.getMessage());
			//e.printStackTrace();
		}
		return userid;
	}
	
	public static int Exportroleid(HttpServletRequest request,Connection conn, Member mem) {
		PreparedStatement ptmt = null;
		String name = mem.getName();
		String password = LoginDAO.md5(mem.getPassword());
		String sql = "select roleid from user "+" where name = '"+name+"' AND password = '"+password+"';";
		int roleid = 0;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				roleid = rs.getInt("roleid");
			}
			ptmt.close();
			rs.close();
		} catch (SQLException e) {
			request.setAttribute("msglogin", e.getMessage());
			//e.printStackTrace();
		}
		return roleid;
	}
}
