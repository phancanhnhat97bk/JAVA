package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Member;
import DAO.MembersmanageDAO;
import DB.DBConnection;

/**
 * Servlet implementation class UpdateMember
 */
@WebServlet("/UpdateMember")
public class UpdateMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getCharacterEncoding()==null) {
			request.setCharacterEncoding("UTF-8");
		}
		Connection conn = DBConnection.CreateConnection();
		
		String roleidstr = request.getParameter("roleid");
		String useridstr = request.getParameter("userid");
		String fullname = request.getParameter("fullname");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		int roleid = Integer.parseInt(roleidstr);
		int userid = Integer.parseInt(useridstr);
		
		Member member = new Member();
		member.setFullname(fullname);
		member.setName(name);
		member.setPassword(password);
		member.setRoleid(roleid);
		
		
		
		boolean kt = MembersmanageDAO.UpdateMember(request, conn, member, userid);
		if(kt) {
			RequestDispatcher rd = request.getRequestDispatcher("Listmembersmanage?pageid=1&roleid="+roleid);
			rd.forward(request, response);
		}
		else 
		{
			request.setAttribute("msgupdatemember", "Cập nhật thông tin thất bại");
			RequestDispatcher rd = request.getRequestDispatcher("Listmembersmanage?pageid=1&roleid="+roleid);
			rd.forward(request, response);
			
		}
	}

}
