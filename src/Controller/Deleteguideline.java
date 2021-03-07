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

import DAO.GuidelinemanageDAO;
import DB.DBConnection;

@WebServlet("/Deleteguideline")
public class Deleteguideline extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Deleteguideline() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DBConnection.CreateConnection();
		String guidelineidstr = request.getParameter("guidelineid");
		String guidelineroleidstr = request.getParameter("guidelineroleid");
		int guidelineroleid = Integer.parseInt(guidelineroleidstr);
		int guidelineid = Integer.parseInt(guidelineidstr);
		
		try {
			GuidelinemanageDAO.DeleteCmtguideline(conn, guidelineid);
			GuidelinemanageDAO.DeleteGuideline(conn, guidelineid);
			
			RequestDispatcher rd = request.getRequestDispatcher("Listguidelinemanage?pageid=1&guidelineroleid="+guidelineroleid);
			rd.forward(request, response);
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
