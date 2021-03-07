package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Answeruser;
import BEAN.Exercisequestion;
import DAO.ExerciseDAO;
import DB.DBConnection;

/**
 * Servlet implementation class ExerciseController
 */
@WebServlet("/ExerciseController")
public class ExerciseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExerciseController() {
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
		
		//String exerciseidstr = request.getParameter("exerciseid");
		//int exerciseid = Integer.parseInt(exerciseidstr);
		String typestr = request.getParameter("type");
		int type = Integer.parseInt(typestr);
		String numquestionstr = request.getParameter("numquestion");
		int numquestion = Integer.parseInt(numquestionstr);
		//int countrow = ExerciseDAO.CountrowExercise(conn, exerciseid);
		//List<Exercisequestion> list = ExerciseDAO.GetCorrectAns(request,conn, exerciseid);
		List<Exercisequestion> listcorect =  new ArrayList<Exercisequestion>();
		List<Answeruser> listansweruser = new ArrayList<Answeruser>(); 
		
		for(int i=1 ; i <= numquestion; i++) {
			String exercisequestionidstr = request.getParameter("exercisequestionid["+i+"]");
			int exercisequestionid = Integer.parseInt(exercisequestionidstr);
			Exercisequestion exq  = ExerciseDAO.ExportCorect_id(request, conn, exercisequestionid);
			exq.setNum(i);
			listcorect.add(exq);
			String answeruser = request.getParameter("ans["+i+"]");
			Answeruser au = new Answeruser();
			
			au.setNumber(i);
			au.setAnswer(answeruser);
			
			listansweruser.add(au);
			}
		
		int countCorrect = ExerciseDAO.CountCorrect(listcorect, listansweruser);
		
		request.setAttribute("type", type);
		request.setAttribute("countCorrect", countCorrect);
		request.setAttribute("countrow", numquestion);
		request.setAttribute("listquiz", listcorect);
		request.setAttribute("listansweruser", listansweruser);
		RequestDispatcher rd = request.getRequestDispatcher("View/ResultExercise.jsp");
		rd.forward(request, response);
	}

}
