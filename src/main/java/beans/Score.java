package beans;

public class Score {
	int stu_id;
	String stu_name;
	double score;
	
	public Score() {
		this.stu_id = 0;
		this.stu_name="";
		this.score = 0;
	}
	
	public Score(int stu_id,String stu_name,double score) {
		this.stu_id = stu_id;
		this.stu_name=stu_name;
		this.score = score;
	}
	public int getStu_id() {
		return stu_id;
	}
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	
}
