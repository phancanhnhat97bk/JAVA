package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ExercisemanageDAO;
import DB.DBConnection;

/**
 * Servlet implementation class Uploadexercisequestion
 */
@WebServlet("/Uploadexercisequestion")
public class Uploadexercisequestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Uploadexercisequestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String exerciseidstr = request.getParameter("exerciseid");
		int exerciseid = Integer.parseInt(exerciseidstr);
		String typestr = request.getParameter("type");
		int type = Integer.parseInt(typestr);
		
		request.setAttribute("type", type);
		request.setAttribute("exerciseid", exerciseid);
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Insertexercisequestion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		String exerciseidstr = request.getParameter("exerciseid");
		int exerciseid = Integer.parseInt(exerciseidstr);
		String typestr = request.getParameter("type");
		int type = Integer.parseInt(typestr);
		
		
		
		String test = ExercisemanageDAO.Uploadquestionexercise(conn, request, response, exerciseid,type);
		
		if(test.equals("success")) {
			RequestDispatcher rd = request.getRequestDispatcher("Listexercisemanage?pageid=1&type="+type);
			rd.forward(request, response);
		}
		else {
			request.setAttribute("msguploadquestion", test);
			request.setAttribute("exerciseid", exerciseid);
			request.setAttribute("type", type);
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Insertexercisequestion.jsp");
			rd.forward(request, response);
		}
	}

}
