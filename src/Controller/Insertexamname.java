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

import BEAN.Examination;
import DAO.ExammanageDAO;
import DB.DBConnection;

/**
 * Servlet implementation class Insertexamname
 */
@WebServlet("/Insertexamname")
public class Insertexamname extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insertexamname() {
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
		String examinationname = request.getParameter("examinationname");
		String timestr = request.getParameter("time");
		String useridstr = request.getParameter("userid");
		int userid = Integer.parseInt(useridstr);
		int time = Integer.parseInt(timestr);
		
		Examination exam = new Examination();
		exam.setExaminationname(examinationname);
		exam.setTime(time);
		exam.setUserid(userid);
		Connection conn = DBConnection.CreateConnection();
		try {
			boolean kt = ExammanageDAO.Insertexamname(request, conn, exam);
			if(kt) {
				int examinationid = ExammanageDAO.Retrieveidexam(request, conn, exam);
				
				ExammanageDAO.Checkquestionexam(request, conn, 0, examinationid);
				
				request.setAttribute("examinationid", examinationid);
				
				RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Insertexamimage.jsp");
				rd.forward(request, response);
			}else {
				request.setAttribute("msglistexammanage", "thêm không thành công");
				RequestDispatcher rd = request.getRequestDispatcher("Listexammanage");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msglistexammanage", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("Listexammanage");
			rd.forward(request, response);
		}
	}

}
