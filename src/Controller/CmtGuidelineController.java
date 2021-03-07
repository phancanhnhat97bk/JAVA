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

import BEAN.Cmtguideline;
import BEAN.Member;
import DAO.CommentguidelineDAO;
import DB.DBConnection;

@WebServlet("/CmtGuidelineController")
public class CmtGuidelineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CmtGuidelineController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getCharacterEncoding()!=null) {
			request.setCharacterEncoding("UTF-8");
		}
		
		try {
			HttpSession session = request.getSession();
			Member mem = (Member) session.getAttribute("sessionuser");
			
			String cmtguidelinecontent = request.getParameter("cmtguidelinecontent");
			String guidelineidstr = request.getParameter("guidelineid");
			
			int guidelineid = Integer.parseInt(guidelineidstr);
			int userid = mem.getUserid();
			
			Connection conn = DBConnection.CreateConnection();
			
			
			Cmtguideline cmtguideline = new Cmtguideline();
			
			cmtguideline.setCmtguidelinecontent(cmtguidelinecontent);
			cmtguideline.setUserid(userid);
			cmtguideline.setGuidelineid(guidelineid);
			
			CommentguidelineDAO.InsertCmtguideline(request, conn, cmtguideline);
			
			List<Cmtguideline> list = CommentguidelineDAO.DisplayCmtGuideline(conn, guidelineid);
			
			request.setAttribute("listcommentguideline", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Listcmtguideline.jsp");
			rd.forward(request, response);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
