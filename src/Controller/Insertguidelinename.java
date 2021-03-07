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

import BEAN.Guideline;
import DAO.GuidelinemanageDAO;
import DB.DBConnection;

@WebServlet("/Insertguidelinename")
public class Insertguidelinename extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Insertguidelinename() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getCharacterEncoding()==null) {
			request.setCharacterEncoding("UTF-8");
		}
		String guidelineroleidstr = request.getParameter("guidelineroleid");
		String useridstr = request.getParameter("userid");
		String guidelinename = request.getParameter("guidelinename");
		String levelstr = request.getParameter("level");
		int userid = Integer.parseInt(useridstr);
		int guidelineroleid = Integer.parseInt(guidelineroleidstr);
		int level = Integer.parseInt(levelstr);
		Guideline gl = new Guideline();
		gl.setGuidelinename(guidelinename);
		gl.setGuidelineroleid(guidelineroleid);
		gl.setUserid(userid);
		gl.setLevel(level);
		Connection conn = DBConnection.CreateConnection();
		try {
			boolean kt = GuidelinemanageDAO.Insertguidelinename(request, conn, gl);
			if(kt) {
				int guidelineid = GuidelinemanageDAO.Retrieveidguideline(request, conn, gl);
				
				request.setAttribute("guidelineid", guidelineid);
				request.setAttribute("guidelineroleid", guidelineroleid);
				
				RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Insertguidelineimage.jsp");
				rd.forward(request, response);
			}else {
				request.setAttribute("msglistguidelinemanage", "thêm không thành công");
				RequestDispatcher rd = request.getRequestDispatcher("Listguidelinemanage");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msglistguidelinemanage", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("Listguidelinemanage");
			rd.forward(request, response);
		}
	}

}
