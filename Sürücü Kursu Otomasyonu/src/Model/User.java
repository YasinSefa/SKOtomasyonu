package Model;

import Helper.DBConnection;

public class User {
	private int id;
	String tcNo,name,password,type,phoneNum,courses;
	DBConnection conn = new DBConnection();

	public User(int id, String tcNo, String name, String password, String type) {
		this.id = id;
		this.tcNo = tcNo;
		this.name = name;
		this.password = password;
		this.type = type;
	}
	
	public User(int id, String tcNo, String name, String password, String phoneNum,String courses) {
		this.id = id;
		this.tcNo = tcNo;
		this.name = name;
		this.password = password;
		this.phoneNum = phoneNum;
		this.courses = courses;
	}
	
	public User() {
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTcno() {
		return tcNo;
	}
	public void setTcno(String tcno) {
		this.tcNo = tcno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}
}
