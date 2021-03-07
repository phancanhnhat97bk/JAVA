package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Examinationquestion;
import DAO.QuizDAO;
import DB.DBConnection;

/**
 * Servlet implementation class QuizFForward
 */
@WebServlet("/QuizForward")
public class QuizForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			 String examinationidstr = request.getParameter("examinationid");
			 int examinationid = Integer.parseInt(examinationidstr);
			 String timestr = request.getParameter("time");
			 int time = Integer.parseInt(timestr);
			 Connection conn = DBConnection.CreateConnection();
			 
			 List<Examinationquestion> list = QuizDAO.Displayquestions(request, conn, examinationid);
			 
			 request.setAttribute("time", time);
			 request.setAttribute("listquiz", list);
			 request.setAttribute("examinationid", examinationid);
			 RequestDispatcher rd = request.getRequestDispatcher("View/Quiz.jsp");
			 rd.forward(request, response);
			 conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
