package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ExammanageDAO;
import DB.DBConnection;

/**
 * Servlet implementation class Uploadexamimage
 */
@WebServlet("/Uploadexamimage")
public class Uploadexamimage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Uploadexamimage() {
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
		Connection conn = DBConnection.CreateConnection();
		String examinationidstr = request.getParameter("examinationid");
		int examinationid = Integer.parseInt(examinationidstr);
		
		String test = ExammanageDAO.Uploadimageexam(conn, request, response, examinationid);
		
		if(test.equals("success")) {
			RequestDispatcher rd = request.getRequestDispatcher("Listexammanage?pageid=1");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("msguploadimage", test);
			request.setAttribute("examinationid", examinationid);
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Insertexamimage.jsp");
			rd.forward(request, response);
		}
	}

}
