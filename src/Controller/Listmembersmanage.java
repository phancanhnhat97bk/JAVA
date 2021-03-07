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

import BEAN.Member;
import DAO.MembersmanageDAO;
import DB.DBConnection;

/**
 * Servlet implementation class Listusermanage
 */
@WebServlet("/Listmembersmanage")
public class Listmembersmanage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Listmembersmanage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			 String pageidstr = request.getParameter("pageid");
			 String roleidstr = request.getParameter("roleid");
			 //ep kieu String sang int de tinh start
			 int pageid = Integer.parseInt(pageidstr);
			 int roleid = Integer.parseInt(roleidstr);
			 int count = 5;
			 
			 if(pageid ==1) {
				 
			 }else {
				 pageid = pageid -1;
				 pageid = pageid * count +1;
			 }
			 
			 Connection conn = DBConnection.CreateConnection();
			 
			 List<Member> list = MembersmanageDAO.DisplaysMembermanage(request, pageid, count, conn, roleid);
			 
			 int sumrow = MembersmanageDAO.Countrow(conn, roleid);
			 int maxpageid =0;
			 if(sumrow%count==0) {
				 maxpageid = (sumrow/count);
			 }else {
				 maxpageid = (sumrow/count)+1;
			 }
			 
			 request.setAttribute("roleid", roleid);
			 request.setAttribute("maxpageid", maxpageid);;
			 request.setAttribute("listmembersmanage", list);
			 request.setAttribute("numberpage", Integer.parseInt(pageidstr));
			 RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Listmembersmanage.jsp");
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
