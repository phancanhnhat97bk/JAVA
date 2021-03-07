package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GrammarguidelinecontentForward
 */
@WebServlet("/GuidelinecontentForward")
public class GuidelinecontentForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuidelinecontentForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String guidelineid = request.getParameter("guidelineid");
		String guidelineroleidstr = request.getParameter("guidelineroleid");
		int guidelineroleid = Integer.parseInt(guidelineroleidstr);
		request.setAttribute("guidelineid", guidelineid);
		request.setAttribute("guidelineroleid", guidelineroleid);
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Insertguidelinecontent.jsp");
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
