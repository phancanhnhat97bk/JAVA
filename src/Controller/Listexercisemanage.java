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
import javax.servlet.http.HttpSession;

import BEAN.Exercise;
import BEAN.Member;
import DAO.ExercisemanageDAO;
import DB.DBConnection;

/**
 * Servlet implementation class Listexercisemanage
 */
@WebServlet("/Listexercisemanage")
public class Listexercisemanage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Listexercisemanage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			 String pageidstr = request.getParameter("pageid");
			 String typestr = request.getParameter("type");
			 //ep kieu String sang int de tinh start
			 int pageid = Integer.parseInt(pageidstr);
			 int type = Integer.parseInt(typestr);
			 
			 HttpSession session = request.getSession();
			 Member mem = null;
			 if(session.getAttribute("sessionadmin")!=null) {
				 mem = (Member) session.getAttribute("sessionadmin");
				 int userid = mem.getUserid();
				 request.setAttribute("userid", userid);
			 }
			 if(session.getAttribute("sessioneditor")!=null) {
				 mem = (Member) session.getAttribute("sessioneditor");
				 int userid = mem.getUserid();
				 request.setAttribute("userid", userid);
			 }

			 int count = 5;
			 
			 if(pageid ==1) {
				 
			 }else {
				 pageid = pageid -1;
				 pageid = pageid * count +1;
			 }
			 
			 Connection conn = DBConnection.CreateConnection();
			 
			 List<Exercise> list = ExercisemanageDAO.Displaysexercisemanage(request, pageid, count, conn, type, mem);
			 
			 int sumrow = ExercisemanageDAO.Countrow(conn, type,mem);
			 int maxpageid =0;
			 if(sumrow%count==0) {
				 maxpageid = (sumrow/count);
			 }else {
				 maxpageid = (sumrow/count)+1;
			 }
			 
			 
			 request.setAttribute("maxpageid", maxpageid);
			 request.setAttribute("type", type);
			 request.setAttribute("listexercisemanage", list);
			 request.setAttribute("numberpage", Integer.parseInt(pageidstr));
			 RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Listexercisemanage.jsp");
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
