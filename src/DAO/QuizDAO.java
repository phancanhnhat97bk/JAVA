package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Answeruser;
import BEAN.Examination;
import BEAN.Examinationquestion;
import BEAN.Result;

public class QuizDAO {

	
	public static List<Examination> DisplayEx(int start, int count, Connection conn){
		List<Examination> list = new ArrayList<Examination>();
		String sql = "select * from examination limit "+(start-1)+","+count+"";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				Examination ex = new Examination();
				int examinationid = rs.getInt("examinationid");
				String examinationname = rs.getString("examinationname");
				String examinationimage = rs.getString("examinationimage");
				int time = rs.getInt("time");
				
				ex.setExaminationid(examinationid);
				ex.setExaminationname(examinationname);
				ex.setExaminationimage(examinationimage);
				ex.setTime(time);
				
				list.add(ex);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static int Countrow(Connection conn) {
		int count = 0;
		String sql = "select count(*) from examination";
		
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	public static List<Examinationquestion> Displayquestions(HttpServletRequest request,Connection conn,int examinationid) {
		List<Examinationquestion> list = new ArrayList<Examinationquestion>();
		PreparedStatement ptmt = null;
		String sql = "select * from examinationquestion where examinationid ="+examinationid;
		
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.isBeforeFirst()) {
				while (rs.next()) {
					Examinationquestion exq = new Examinationquestion();
					int num = rs.getInt("num");
					String imagequestion = rs.getString("imagequestion");
					String audiogg  = rs.getString("audiogg");
					String audiomp3 = rs.getString("audiomp3");
					String paragraph = rs.getString("paragraph");
					String question = rs.getString("question");
					String option1 = rs.getString("option1");
					String option2 = rs.getString("option2");
					String option3 = rs.getString("option3");
					String option4 = rs.getString("option4");
					String correctanswer = rs.getString("correctanswer");
					
					exq.setNum(num);
					exq.setImagequestion(imagequestion);
					exq.setAudiogg(audiogg);
					exq.setAudiomp3(audiomp3);
					exq.setParagraph(paragraph);
					exq.setQuestion(question);
					exq.setOption1(option1);
					exq.setOption2(option2);
					exq.setOption3(option3);
					exq.setOption4(option4);
					exq.setCorrectanswer(correctanswer);
					
			
					
					list.add(exq);
				}
			}else {
				request.setAttribute("msgquiz","Không có câu hỏi nào" );
			}
			
			
		} catch (SQLException e) {
			request.setAttribute("msglistexammanage",e.getMessage() );
		}
		return list;
	}
	
	
	public static int Countrow(Connection conn, int examinationid) {
		int count = 0;
		String sql = "select count(*) from examinationquestion where examinationid="+examinationid;
		
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	
	
	public static List<Examinationquestion> GetCorrectAns(HttpServletRequest request,Connection conn,int examinationid) {
		
		List<Examinationquestion> list = new ArrayList<Examinationquestion>();
		PreparedStatement ptmt = null;
		String sql = "select * from examinationquestion where examinationid ="+examinationid;
		
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.isBeforeFirst()) {
				while (rs.next()) {
					Examinationquestion exq = new Examinationquestion();
					int num = rs.getInt("num");
					String imagequestion = rs.getString("imagequestion");
					String audiogg  = rs.getString("audiogg");
					String audiomp3 = rs.getString("audiomp3");
					String paragraph = rs.getString("paragraph");
					String question = rs.getString("question");
					String option1 = rs.getString("option1");
					String option2 = rs.getString("option2");
					String option3 = rs.getString("option3");
					String option4 = rs.getString("option4");
					String correctanswer = rs.getString("correctanswer");
					
					exq.setNum(num);
					exq.setImagequestion(imagequestion);
					exq.setAudiogg(audiogg);
					exq.setAudiomp3(audiomp3);
					exq.setParagraph(paragraph);
					exq.setQuestion(question);
					exq.setOption1(option1);
					exq.setOption2(option2);
					exq.setOption3(option3);
					exq.setOption4(option4);
					exq.setCorrectanswer(correctanswer);
					
			
					
					list.add(exq);
				}
			}else {
				request.setAttribute("msgquiz","Không có câu hỏi nào" );
			}
			
			
		} catch (SQLException e) {
			request.setAttribute("msglistexammanage",e.getMessage() );
		}
		return list;
	}
	
	public static int CountCorrect(List<Examinationquestion> list , List<Answeruser> listansweruser ) {
		int count =0;
		for(int i=0;i<list.size();i++) {
			Examinationquestion ex = list.get(i);
			for(int j=0;j<listansweruser.size();j++) {
				Answeruser ans = listansweruser.get(j);
				if(ex.getNum() == ans.getNumber() && ex.getCorrectanswer().equals(ans.getAnswer()) ) {
					count++;
					break;
				}
			}
		}
		return count;
	}

		public static void InsertScore(HttpServletRequest request, Connection conn, Result result) {
			PreparedStatement ptmt = null;
			
			String sql = "insert into result (score,examinationid,userid) value(?,?,?)";
			try {
				ptmt = conn.prepareStatement(sql);
				int score = result.getScore();
				int examinationid = result.getExaminationid();
				int userid = result.getUserid();
				
				ptmt.setInt(1, score);
				ptmt.setInt(2, examinationid);
				ptmt.setInt(3, userid);
				ptmt.executeUpdate();
				ptmt.close();
			} catch (SQLException e) {
				//request.setAttribute("msgregister", e.getMessage());
				e.printStackTrace();
			}
		}
		
	
	
}
