package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Examination;
import DAO.QuizDAO;
import DB.DBConnection;

/**
 * Servlet implementation class ListQuizForward
 */
@WebServlet("/ListQuizForward")
public class ListQuizForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListQuizForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String pageidstr = request.getParameter("pageid");
		 
		 //ep kieu String sang int de tinh start
		 int pageid = Integer.parseInt(pageidstr);
		 int count = 4;
		 
		 if(pageid ==1) {
			 
		 }else {
			 pageid = pageid -1;
			 pageid = pageid * count +1;
		 }
		 
		 Connection conn = DBConnection.CreateConnection();
		 
		 List<Examination> list = QuizDAO.DisplayEx(pageid, count, conn);
		 
		 int sumrow = QuizDAO.Countrow(conn);
		 int maxpageid =0;
		 if(sumrow%count==0) {
			 maxpageid = (sumrow/count);
		 }else {
			 maxpageid = (sumrow/count)+1;
		 }
		 
		 
		 request.setAttribute("maxpageid", maxpageid);;
		 request.setAttribute("listexamination", list);
		 request.setAttribute("numberpage", Integer.parseInt(pageidstr));
		 
		 RequestDispatcher rd = request.getRequestDispatcher("View/ListQuiz.jsp");
		 rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
