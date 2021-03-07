package BEAN;

public class Examination {
	private int examinationid;
	private String examinationname;
	private String examinationimage;
	private int checkquestion;
	private int time;
	private int score;
	private int userid;
	
	public int getExaminationid() {
		return examinationid;
	}
	public void setExaminationid(int examinationid) {
		this.examinationid = examinationid;
	}
	public String getExaminationname() {
		return examinationname;
	}
	public void setExaminationname(String examinationname) {
		this.examinationname = examinationname;
	}
	public String getExaminationimage() {
		return examinationimage;
	}
	public void setExaminationimage(String examinationimage) {
		this.examinationimage = examinationimage;
	}
	public int getCheckquestion() {
		return checkquestion;
	}
	public void setCheckquestion(int checkquestion) {
		this.checkquestion = checkquestion;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
}
