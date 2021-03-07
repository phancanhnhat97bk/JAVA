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

import BEAN.Exercisequestion;
import DAO.ExerciseDAO;
import DB.DBConnection;

/**
 * Servlet implementation class ExerciseForward
 */
@WebServlet("/ExerciseForward")
public class ExerciseForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExerciseForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			 //String exerciseidstr = request.getParameter("exerciseid");
			 //int exerciseid = Integer.parseInt(exerciseidstr);
			 String typestr = request.getParameter("type");
			 int type = Integer.parseInt(typestr);
			 String numquestionstr = request.getParameter("numquestion");
			 int numquestion = Integer.parseInt(numquestionstr);
			 
			 Connection conn = DBConnection.CreateConnection();
			 
			 List<Exercisequestion> list = ExerciseDAO.Displayquestions(request, conn, type, numquestion);
			 
			 request.setAttribute("listquiz", list);
			// request.setAttribute("exerciseid", exerciseid);
			 request.setAttribute("type", type);
			 request.setAttribute("numquestion", numquestion);
			 RequestDispatcher rd = request.getRequestDispatcher("View/Exercise.jsp");
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
