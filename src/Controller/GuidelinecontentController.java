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

@WebServlet("/GuidelinecontentController")
public class GuidelinecontentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GuidelinecontentController() {
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
		Connection conn = DBConnection.CreateConnection();
		String content = request.getParameter("content");
		String guidelineidstr = request.getParameter("guidelineid");
		int guidelineid = Integer.parseInt(guidelineidstr);
		Guideline gl = new Guideline();
		gl.setGuidelineid(guidelineid);
		int guidelineroleid = GuidelinemanageDAO.Retrieveidguidelineroleid(request, conn, gl);
		gl.setContent(content);
		boolean kt = GuidelinemanageDAO.Insertguidelinecontent(request, conn, gl, guidelineid);
		if(kt) {
			RequestDispatcher rd = request.getRequestDispatcher("Listguidelinemanage?pageid=1&guidelineroleid="+guidelineroleid);
			rd.forward(request, response);
		}
		else 
		{
			request.setAttribute("msgguidelinecontent", "Thêm nội dung thất bại");
			request.setAttribute("guidelineid", guidelineid);
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Insertguidelinecontent.jsp");
			rd.forward(request, response);
			
		}
	}

}
