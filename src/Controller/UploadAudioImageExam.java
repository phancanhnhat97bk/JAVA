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
 * Servlet implementation class UploadAudioImageExam
 */
@WebServlet("/UploadAudioImageExam")
public class UploadAudioImageExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadAudioImageExam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		
		
		
		
		
		String test = ExammanageDAO.UploadAudioImage(conn, request, response);
		
		if(test.equals("success")) {
			RequestDispatcher rd = request.getRequestDispatcher("Listexammanage?pageid=1");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("msguploadaudioimage", test);
			RequestDispatcher rd = request.getRequestDispatcher("Listexammanage?pageid=1");
			rd.forward(request, response);
		}
	}

}
