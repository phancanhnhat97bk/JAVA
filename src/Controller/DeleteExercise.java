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

import DAO.ExercisemanageDAO;
import DB.DBConnection;

/**
 * Servlet implementation class DeleteEexercise
 */
@WebServlet("/DeleteExercise")
public class DeleteExercise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteExercise() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		String exerciseidstr = request.getParameter("exerciseid");
		String typestr = request.getParameter("type");
		int exerciseid = Integer.parseInt(exerciseidstr);
		int type = Integer.parseInt(typestr);
		
		try {
			
			ExercisemanageDAO.DeleteExercisequestion(conn, exerciseid);
			ExercisemanageDAO.DeleteExercise(conn, exerciseid);
			
			RequestDispatcher rd = request.getRequestDispatcher("Listexercisemanage?pageid=1&type="+type);
			rd.forward(request, response);
			
			conn.close();
		} catch (SQLException e) {
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
