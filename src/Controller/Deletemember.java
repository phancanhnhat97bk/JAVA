package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MembersmanageDAO;
import DB.DBConnection;

/**
 * Servlet implementation class Deletemember
 */
@WebServlet("/Deletemember")
public class Deletemember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deletemember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		String useridstr = request.getParameter("userid");
		int userid = Integer.parseInt(useridstr);
		String roleidstr = request.getParameter("roleid");
		int roleid = Integer.parseInt(roleidstr);
		
		try {
			MembersmanageDAO.DeleteMember(conn, userid);
			
			RequestDispatcher rd = request.getRequestDispatcher("Listmembersmanage?pageid=1&roleid="+roleid);
			rd.forward(request, response);
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
