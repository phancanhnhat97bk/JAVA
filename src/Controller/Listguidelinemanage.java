package Controller;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.Guideline;
import BEAN.Member;
import DAO.GuidelinemanageDAO;
import DB.DBConnection;

@WebServlet("/Listguidelinemanage")
public class Listguidelinemanage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Listguidelinemanage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 try {
			 String pageidstr = request.getParameter("pageid");
			 String guidelineroleidstr = request.getParameter("guidelineroleid");
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
			 
				
			 
			 //ep kieu String sang int de tinh start
			 int pageid = Integer.parseInt(pageidstr);
			 int guidelineroleid = Integer.parseInt(guidelineroleidstr);
			 int count = 5;
			 
			 if(pageid ==1) {
				 
			 }else {
				 pageid = pageid -1;
				 pageid = pageid * count +1;
			 }
			 
			 Connection conn = DBConnection.CreateConnection();
			 
			 List<Guideline> list = GuidelinemanageDAO.Displaysguidelinemanage(request, pageid, count, conn,guidelineroleid, mem);
			 
			 int sumrow = GuidelinemanageDAO.Countrow(conn,guidelineroleid,mem);
			 int maxpageid =0;
			 if(sumrow%count==0) {
				 maxpageid = (sumrow/count);
			 }else {
				 maxpageid = (sumrow/count)+1;
			 }
			 
			 
			 request.setAttribute("maxpageid", maxpageid);;
			 request.setAttribute("listguidelinemanage", list);
			 request.setAttribute("numberpage", Integer.parseInt(pageidstr));
			 request.setAttribute("guidelineroleid", guidelineroleid);
			 RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Listguidelinemanage.jsp");
			 rd.forward(request, response);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
