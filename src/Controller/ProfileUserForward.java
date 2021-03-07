package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.Member;
import BEAN.Result;
import DAO.ResultDAO;
import DB.DBConnection;

/**
 * Servlet implementation class ProfileUserForward
 */
@WebServlet("/ProfileUserForward")
public class ProfileUserForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileUserForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member mem = (Member) session.getAttribute("sessionuser");
		Connection conn = DBConnection.CreateConnection();
		List<Result> list = ResultDAO.DisplaysResult(request, conn, mem);
		
		request.setAttribute("listresult", list);
		 //Xuất ra một list bao gồm các đối tượng result theo userid 
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Profile.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
