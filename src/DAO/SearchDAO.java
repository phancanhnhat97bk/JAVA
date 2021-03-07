package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Guideline;

public class SearchDAO {
	public static List<Guideline> DisplayResultGl(HttpServletRequest request, Connection conn, String name){
	
		List<Guideline> listgmgl = new ArrayList<Guideline>();
		String sql = "select * from guideline where guidelinename like '%"+name+"%'";
		
		PreparedStatement ptmt;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			if(!rs.isBeforeFirst()) {
				request.setAttribute("ketqua", "Không có kết quả");
			}
			else {
				while(rs.next()) {
					Guideline gl = new Guideline();
					int guidelineid = rs.getInt("guidelineid");
					String guidelinename = rs.getString("guidelinename");
					String guidelineimage = rs.getString("guidelineimage");
					int guidelineroleid = rs.getInt("guidelineroleid");
					
					gl.setGuidelineid(guidelineid);
					gl.setGuidelinename(guidelinename);
					gl.setGuidelineimage(guidelineimage);
					gl.setGuidelineroleid(guidelineroleid);
					listgmgl.add(gl);
				}
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("ketqua", e.getMessage());
		}
		
		
		return listgmgl;
	
	}
	
}
