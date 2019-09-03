package beans;

public class Stu {

	int id;
	String name;
	String password;
	
	
	public Stu() {
		this.id = 0;
		this.name = "";
		this.password = "";
	}
	
	public Stu(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String username) {
		this.name = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
