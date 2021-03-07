package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Answeruser;
import BEAN.Exercise;
import BEAN.Exercisequestion;

public class ExerciseDAO {
	public static List<Exercise> DisplayListExercise(int start, int count, Connection conn, int typ){
		List<Exercise> list = new ArrayList<Exercise>();
		String sql = "select * from exercise where type ="+typ+" limit "+(start-1)+","+count+"";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				Exercise exercise = new Exercise();
				int exerciseid = rs.getInt("exerciseid");
				String name = rs.getString("name");
				int type = rs.getInt("type");
				int numquestion = rs.getInt("numquestion");
				
				
				exercise.setExerciseid(exerciseid);
				exercise.setName(name);
				exercise.setType(type);
				exercise.setNumquestion(numquestion);
				
				list.add(exercise);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static int Countrow(Connection conn, int type) {
		int count = 0;
		String sql = "select count(*) from exercise where type="+type;
		
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
	
	
	public static List<Exercisequestion> Displayquestions(HttpServletRequest request,Connection conn,int type, int numquestion) {
		List<Exercisequestion> list = new ArrayList<Exercisequestion>();
		PreparedStatement ptmt = null;
		String sql = "SELECT * FROM exercisequestion where type="+type+" ORDER BY RAND() LIMIT "+numquestion;
		
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			int count=1;
			if(rs.isBeforeFirst()) {
				while (rs.next()) {
					Exercisequestion ex = new Exercisequestion();
					int exercisequestionid = rs.getInt("exercisequestionid");
					String image = rs.getString("image");
					String audiogg = rs.getString("audiogg");
					String audiomp3 = rs.getString("audiomp3");
					String paragraph = rs.getString("paragraph");
					String question = rs.getString("question");
					String option1 = rs.getString("option1");
					String option2 = rs.getString("option2");
					String option3 = rs.getString("option3");
					String option4 = rs.getString("option4");
					String correctanswer = rs.getString("correctanswer");
					
					ex.setExercisequestionid(exercisequestionid);
					ex.setNum(count);
					ex.setImage(image);
					ex.setAudiogg(audiogg);
					ex.setAudiomp3(audiomp3);
					ex.setParagraph(paragraph);
					ex.setQuestion(question);
					ex.setOption1(option1);
					ex.setOption2(option2);
					ex.setOption3(option3);
					ex.setOption4(option4);
					ex.setCorrectanswer(correctanswer);
					
					list.add(ex);
					count++;
				}
			}else {
				request.setAttribute("msgquiz","Không có câu hỏi nào" );
			}
			
			
		} catch (SQLException e) {
			request.setAttribute("msglistexercisemanage",e.getMessage() );
		}
		return list;
	}
	
	public static int CountrowExercise(Connection conn, int exerciseid) {
		int count = 0;
		String sql = "select count(*) from exercisequestion where exerciseid="+exerciseid;
		
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
	
	
	public static List<Exercisequestion> GetCorrectAns(HttpServletRequest request,Connection conn,int exerciseid) {
		
		List<Exercisequestion> list = new ArrayList<Exercisequestion>();
		PreparedStatement ptmt = null;
		String sql = "select * from exercisequestion where exerciseid ="+exerciseid;
		
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.isBeforeFirst()) {
				while (rs.next()) {
					Exercisequestion exq = new Exercisequestion();
					int num = rs.getInt("num");
					String image = rs.getString("image");
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
					exq.setImage(image);
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
			request.setAttribute("msglistexercisemanage",e.getMessage() );
		}
		return list;
	}

	public static int CountCorrect(List<Exercisequestion> list , List<Answeruser> listansweruser ) {
		int count =0;
		for(int i=0;i<list.size();i++) {
			Exercisequestion ex = list.get(i);
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
		
	
	public static Exercisequestion ExportCorect_id(HttpServletRequest request,Connection conn, int exercisequestionid) {
		PreparedStatement ptmt = null;
		Exercisequestion exq = new Exercisequestion();
		
		String sql = "select * from exercisequestion where exercisequestionid="+exercisequestionid;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				
				String image = rs.getString("image");
				String audiogg  = rs.getString("audiogg");
				String audiomp3 = rs.getString("audiomp3");
				String paragraph = rs.getString("paragraph");
				String question = rs.getString("question");
				String option1 = rs.getString("option1");
				String option2 = rs.getString("option2");
				String option3 = rs.getString("option3");
				String option4 = rs.getString("option4");
				String correctanswer = rs.getString("correctanswer");
				
				
				exq.setImage(image);
				exq.setAudiogg(audiogg);
				exq.setAudiomp3(audiomp3);
				exq.setParagraph(paragraph);
				exq.setQuestion(question);
				exq.setOption1(option1);
				exq.setOption2(option2);
				exq.setOption3(option3);
				exq.setOption4(option4);
				exq.setCorrectanswer(correctanswer);
			}
			ptmt.close();
			rs.close();
		} catch (SQLException e) {
			request.setAttribute("msgcorrectanswer", e.getMessage());
			//e.printStackTrace();
		}
		
		return exq;
	}
}
