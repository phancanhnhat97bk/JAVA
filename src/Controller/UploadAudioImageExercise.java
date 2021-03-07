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
 * Servlet implementation class UploadAudioImageExercise
 */
@WebServlet("/UploadAudioImageExercise")
public class UploadAudioImageExercise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadAudioImageExercise() {
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
		String typestr = request.getParameter("type");
		int type = Integer.parseInt(typestr);
		String test = ExercisemanageDAO.UploadAudioImage(conn, request, response);
		
		if(test.equals("success")) {
			RequestDispatcher rd = request.getRequestDispatcher("Listexercisemanage?pageid=1&type="+type);
			rd.forward(request, response);
		}
		else {
			request.setAttribute("msguploadaudioimage", test);
			RequestDispatcher rd = request.getRequestDispatcher("Listexercisemanage?pageid=1&type="+type);
			rd.forward(request, response);
		}
	}

}
