package DAO;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import BEAN.Member;

public class RegisterDAO {
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
	public static boolean InsertAcount(HttpServletRequest request, Connection conn, Member mem) {
		PreparedStatement ptmt = null;
		
		String sql = "insert into user (fullname,name,password,userimage,roleid) value(?,?,?,?,?)";
		
		try {
			ptmt = conn.prepareStatement(sql);
			String fullname = mem.getFullname();
			String name = mem.getName();
			String password = RegisterDAO.md5(mem.getPassword());
			String userimage = mem.getUserimage();
			int roleid = mem.getRoleid();
			ptmt.setString(1, fullname);
			ptmt.setString(2, name);
			ptmt.setString(3, password);
			ptmt.setString(4, userimage);
			ptmt.setInt(5, roleid);
			int kt = ptmt.executeUpdate();
			if(kt != 0) {
				return true;
			}
			ptmt.close();
		} catch (SQLException e) {
			request.setAttribute("msgregister", e.getMessage());
			//e.printStackTrace();
		}
		return false;
	}
}
