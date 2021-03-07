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
import BEAN.Slidebanner;
import DAO.HOMEDAO;
import DB.DBConnection;

import java.sql.*;
import java.util.*;

@WebServlet("/HomeForward")
public class HomeForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public HomeForward() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		List<Slidebanner> list = HOMEDAO.DisplaySlidebanner(conn);
		request.setAttribute("listslidebanner", list);
		HttpSession session = request.getSession();
		if(session.getAttribute("sessionuser") != null) {
		Member mem = (Member) session.getAttribute("sessionuser");
		String name = mem.getName();
		int roleid = mem.getRoleid();
		int userid = mem.getUserid();
		
		request.setAttribute("name", name);
		request.setAttribute("roleid", roleid);
		request.setAttribute("userid", userid);
		}
		RequestDispatcher rd = request.getRequestDispatcher("View/Home.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
