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

import BEAN.Exercise;
import DAO.ExercisemanageDAO;
import DB.DBConnection;

/**
 * Servlet implementation class Insertexercisename
 */
@WebServlet("/Insertexercisename")
public class Insertexercisename extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insertexercisename() {
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
		String name = request.getParameter("name");
		String numquestionstr = request.getParameter("numquestion");
		String typestr = request.getParameter("type");
		String useridstr = request.getParameter("userid");
		int numquestion = Integer.parseInt(numquestionstr);
		int type = Integer.parseInt(typestr);
		int userid = Integer.parseInt(useridstr);
		
	
		
		Exercise exercise = new Exercise();
		exercise.setName(name);
		exercise.setType(type);
		exercise.setUserid(userid);
		exercise.setNumquestion(numquestion);
		
		Connection conn = DBConnection.CreateConnection();
		try {
			boolean kt = ExercisemanageDAO.Insertexercisename(request, conn, exercise);
			if(kt) {	
				RequestDispatcher rd = request.getRequestDispatcher("Listexercisemanage?pageid=1&type="+type);
				rd.forward(request, response);
			}else {
				request.setAttribute("msglistexercisemanage", "thêm không thành công");
				RequestDispatcher rd = request.getRequestDispatcher("Listexercisemanage");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msglistexercisemanage", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("Listexercisemanage");
			rd.forward(request, response);
		}
	}

}
