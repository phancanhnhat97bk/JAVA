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
import javax.servlet.http.HttpSession;

import BEAN.Answeruser;
import BEAN.Examinationquestion;
import BEAN.Member;
import BEAN.Result;
import DAO.QuizDAO;
import DB.DBConnection;

/**
 * Servlet implementation class QuizController
 */
@WebServlet("/QuizController")
public class QuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizController() {
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
		HttpSession session = request.getSession();
		Member mem = (Member) session.getAttribute("sessionuser");
		String examinationidstr = request.getParameter("examinationid");
		int examinationid = Integer.parseInt(examinationidstr);
		int userid = mem.getUserid();
		
		int countrow = QuizDAO.Countrow(conn, examinationid);
		List<Examinationquestion> list = QuizDAO.GetCorrectAns(request,conn, examinationid);
		List<Answeruser> listansweruser = new ArrayList<Answeruser>(); 
		
		for(int i=1 ; i <= countrow; i++) {
			String answeruser = request.getParameter("ans["+i+"]");
			Answeruser au = new Answeruser();
			
			au.setNumber(i);
			au.setAnswer(answeruser);
			
			listansweruser.add(au);
			}
		
		int countCorrect = QuizDAO.CountCorrect(list, listansweruser);
		int score = (int)Math.round(countCorrect*100/countrow);
		
		Result result = new Result();
		result.setScore(score);
		result.setExaminationid(examinationid);
		result.setUserid(userid);
		
		QuizDAO.InsertScore(request, conn, result);
		
		
		request.setAttribute("countCorrect", countCorrect);
		request.setAttribute("countrow", countrow);
		request.setAttribute("score", score);
		request.setAttribute("listquiz", list);
		request.setAttribute("listansweruser", listansweruser);
		RequestDispatcher rd = request.getRequestDispatcher("View/Result.jsp");
		rd.forward(request, response);
	}

}
