package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.Member;
import DAO.LoginDAO;
import DB.DBConnection;

import java.sql.*;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
	
		Member mem = new Member();
		mem.setName(name);
		mem.setPassword(password);
		int userid = LoginDAO.Exportmemberid(request, conn, mem);
		int roleid = LoginDAO.Exportroleid(request, conn, mem);
		String nameimage = LoginDAO.Exportnameimage(request, conn, mem);
		mem.setUserid(userid);
		mem.setRoleid(roleid);
		
		boolean authentication = LoginDAO.Authenticationmember(request, conn, mem);
		if(authentication) {
			int authentization = LoginDAO.Authorizationmember(request, conn, mem);
			if(authentization==2) {
				HttpSession session = request.getSession(true);
				session.setAttribute("sessionuser", mem);
				session.setAttribute("sessionname", name);
				session.setAttribute("sessionimage", nameimage);
				RequestDispatcher rd = request.getRequestDispatcher("HomeForward");
				rd.forward(request, response);
			}
			if(authentization==3) {
				HttpSession session = request.getSession(true);
				session.setAttribute("sessioneditor", mem);
				session.setAttribute("sessionname", name);
				RequestDispatcher rd = request.getRequestDispatcher("AdminForward");
				rd.forward(request, response);
			}
			if(authentization==1) {
				HttpSession session = request.getSession(true);
				session.setAttribute("sessionadmin", mem);
				session.setAttribute("sessionname", name);
				RequestDispatcher rd = request.getRequestDispatcher("AdminForward");
				rd.forward(request, response);
			}
			
		}else {
			request.setAttribute("msgLogin", "Tên hoặc mật khẩu sai");
			RequestDispatcher rd = request.getRequestDispatcher("LoginForward");
			rd.forward(request, response);
		}
	}	

}
