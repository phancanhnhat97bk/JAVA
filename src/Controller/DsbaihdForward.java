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
import javax.servlet.http.HttpSession;

import BEAN.Guideline;
import BEAN.Member;
import DAO.BaihdDAO;
import DB.DBConnection;

/**
 * Servlet implementation class DsbaihdnguphapForward
 */
@WebServlet("/DsbaihdForward")
public class DsbaihdForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DsbaihdForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 String pageidstr = request.getParameter("pageid");
	 String guidelineroleidstr = request.getParameter("guidelineroleid");
	 HttpSession session = request.getSession(); 
	 Member mem = (Member) session.getAttribute("sessionuser");int guidelineroleid = Integer.parseInt(guidelineroleidstr);
	 //ep kieu String sang int de tinh start
	 int pageid = Integer.parseInt(pageidstr);
	 int count = 4;
	 
	 if(pageid ==1) {
		 
	 }else {
		 pageid = pageid -1;
		 pageid = pageid * count +1;
	 }
	 
	 Connection conn = DBConnection.CreateConnection();
	 List<Guideline> list = BaihdDAO.DisplayGl(request,pageid, count, conn,guidelineroleid,mem);
	
	 
	 int sumrow = BaihdDAO.Countrow(conn,guidelineroleid);
	 int maxpageid =0;
	 if(sumrow%count==0) {
		 maxpageid = (sumrow/count);
	 }else {
		 maxpageid = (sumrow/count)+1;
	 }
	 
	 
	 request.setAttribute("maxpageid", maxpageid);;
	 request.setAttribute("listgl", list);
	 request.setAttribute("numberpage", Integer.parseInt(pageidstr));
	 request.setAttribute("guidelineroleid", guidelineroleid);
	 
	 RequestDispatcher rd = request.getRequestDispatcher("View/Dsbaihd.jsp");
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
