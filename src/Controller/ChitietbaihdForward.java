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

import BEAN.Cmtguideline;
import BEAN.Guideline;
import DAO.BaihdDAO;
import DAO.CommentguidelineDAO;
import DB.DBConnection;

/**
 * Servlet implementation class ChitietbaihdnguphapForward
 */
@WebServlet("/ChitietbaihdForward")
public class ChitietbaihdForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChitietbaihdForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 Connection conn = DBConnection.CreateConnection();
		 String glidstr = request.getParameter("glid");
		 String guidelineroleidstr = request.getParameter("guidelineroleid");
		 int glid = Integer.parseInt(glidstr);
		 int guidelineroleid = Integer.parseInt(guidelineroleidstr);
		 List<Guideline> list = BaihdDAO.DisplayContent(conn, glid);
		 // Xuat so bình luận
		 
		 int countcmt = CommentguidelineDAO.CountCmt(conn, glid);
		 request.setAttribute("countcmt", countcmt);
		 
		 request.setAttribute("glid", glid);
		 request.setAttribute("listgl", list);
		 request.setAttribute("kitutrongdatabase1","\r\n");
		 request.setAttribute("kitutronghtml1","<br/>");
		 request.setAttribute("guidelineroleid", guidelineroleid);
		 List<Cmtguideline> listcmt = BaihdDAO.DisplayCmtGuideline(conn, glid);
		 request.setAttribute("listcommentguideline", listcmt);
		 
		RequestDispatcher rd = request.getRequestDispatcher("View/Chitietbaihd.jsp");
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
