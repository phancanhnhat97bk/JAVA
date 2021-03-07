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

import BEAN.Exercise;
import DAO.ExerciseDAO;
import DB.DBConnection;

/**
 * Servlet implementation class List_Exercise_Forward
 */
@WebServlet("/List_Exercise_Forward")
public class List_Exercise_Forward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Exercise_Forward() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String pageidstr = request.getParameter("pageid");
		 String typestr = request.getParameter("type");
		 
		 int type = Integer.parseInt(typestr);
		 //ep kieu String sang int de tinh start
		 int pageid = Integer.parseInt(pageidstr);
		 int count = 4;
		 
		 if(pageid ==1) {
			 
		 }else {
			 pageid = pageid -1;
			 pageid = pageid * count +1;
		 }
		 
		 Connection conn = DBConnection.CreateConnection();
		 
		 List<Exercise> list = ExerciseDAO.DisplayListExercise(pageid, count, conn, type);
		 
		 int sumrow = ExerciseDAO.Countrow(conn,type);
		 int maxpageid =0;
		 if(sumrow%count==0) {
			 maxpageid = (sumrow/count);
		 }else {
			 maxpageid = (sumrow/count)+1;
		 }
		 
		 
		 request.setAttribute("maxpageid", maxpageid);;
		 request.setAttribute("listexercise", list);
		 request.setAttribute("numberpage", Integer.parseInt(pageidstr));
		 request.setAttribute("type", type);
		 
		 RequestDispatcher rd = request.getRequestDispatcher("View/List_Exercise.jsp");
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
