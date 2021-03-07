package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Guideline;
import DAO.GuidelinemanageDAO;
import DB.DBConnection;

@WebServlet("/Uploadguidelineimage")
public class Uploadguidelineimage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Uploadguidelineimage() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		String guidelineidstr = request.getParameter("guidelineid");
		int guidelineid = Integer.parseInt(guidelineidstr);
		Guideline gl = new Guideline();
		gl.setGuidelineid(guidelineid);
		int guidelineroleid = GuidelinemanageDAO.Retrieveidguidelineroleid(request, conn, gl);
		String test = GuidelinemanageDAO.Uploadimageguideline(conn, request, response,guidelineid);
		
		if(test.equals("success")) {
			RequestDispatcher rd = request.getRequestDispatcher("Listguidelinemanage?pageid=1&guidelineroleid="+guidelineroleid);
			rd.forward(request, response);
		}
		else {
			request.setAttribute("msgretrieve", test);
			request.setAttribute("guidelineid", guidelineid);
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Insertguidelineimage.jsp");
			rd.forward(request, response);
		}
	}

}
