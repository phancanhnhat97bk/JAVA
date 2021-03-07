package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Member;
import BEAN.Result;

public class ResultDAO {
public static List<Result> DisplaysResult(HttpServletRequest request,Connection conn,Member mem) {
		
		List<Result> list = new ArrayList<Result>();
		PreparedStatement ptmt = null;
		String sql = "select * from result where userid="+mem.getUserid();
		
		
		
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.isBeforeFirst()) {
				while (rs.next()) {
					Result result = new Result();
					int resultid = rs.getInt("resultid");
					int score = rs.getInt("score");
					int examinationid = rs.getInt("examinationid");
					String examinationname = ResultDAO.Exportnameexam(request, conn, examinationid);
					
					result.setResultid(resultid);
					result.setScore(score);
					result.setExaminationid(examinationid);
					result.setExaminationname(examinationname);
					
					list.add(result);
				}
			}else {
				request.setAttribute("msglistresult","Bạn chưa thi thử" );
			}
			
			
			
			
		} catch (SQLException e) {
			request.setAttribute("msglistresult",e.getMessage() );
		}
		return list;
	}


	public static String Exportnameexam(HttpServletRequest request,Connection conn,int examinationid) {
		PreparedStatement ptmt = null;
	
		String sql = "select examinationname from examination where examinationid="+examinationid;
		String examinationname = "";
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				examinationname = rs.getString("examinationname");
			}
			ptmt.close();
			rs.close();
		} catch (SQLException e) {
			request.setAttribute("msglistresult", e.getMessage());
			//e.printStackTrace();
		}
		return examinationname;
	}
	
	
public static int AverageResult(HttpServletRequest request,Connection conn,Member mem) {
		int Sumscore = 0;
		int count = 0;
		PreparedStatement ptmt = null;
		String sql = "select * from result where userid="+mem.getUserid();
		
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.isBeforeFirst()) {
				while (rs.next()) {
					int score = rs.getInt("score");
					Sumscore += score;
					count++;
					if(count==3) {
						break;
					}
				}
			}else {
				request.setAttribute("msglistresult","Bạn chưa thi thử" );
			}
			
		} catch (SQLException e) {
			request.setAttribute("msglistresult",e.getMessage() );
		}
		return Sumscore/count;
	}
	
}
