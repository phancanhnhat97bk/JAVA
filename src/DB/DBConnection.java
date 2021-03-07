package DB;

import java.sql.*;

public class DBConnection {
	public static Connection CreateConnection() {
		Connection conn = null;
		String url ="jdbc:mysql://localhost/toeiconline?useUnicode=true&amp;characterEncoding=utf8";
		String username ="root";
		String password ="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// create connection
			conn = DriverManager.getConnection(url,username,password);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
