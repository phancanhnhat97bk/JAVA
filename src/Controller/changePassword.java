package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.Member;
import DAO.ProfileUserDAO;
import DB.DBConnection;

/**
 * Servlet implementation class changePassword
 */
@WebServlet("/changePassword")
public class changePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getCharacterEncoding()==null) {
			request.setCharacterEncoding("UTF-8");
		}
		Connection conn = DBConnection.CreateConnection();
		String oldpassword = request.getParameter("oldpassword");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		
		HttpSession session = request.getSession();
		Member mem = null;
		mem = (Member) session.getAttribute("sessionuser");
		
		if(mem.getPassword().equals(oldpassword) && password.equals(confirm_password)) {
			mem.setPassword(password);
			ProfileUserDAO.UpdateMember(request, conn, mem);
			session.setAttribute("sessionuser", mem);
			request.setAttribute("msgchangePass", "Đổi mật khẩu thành công");
			RequestDispatcher rd = request.getRequestDispatcher("ProfileUserForward");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("msgchangePass", "Đổi mật khẩu không thành công");
			RequestDispatcher rd = request.getRequestDispatcher("ProfileUserForward");
			rd.forward(request, response);
		}
	}

}
