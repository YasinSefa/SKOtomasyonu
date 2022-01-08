package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Manager extends User {
	 
	 Connection con = conn.connDb();
	 Statement st = null;
	 ResultSet rs = null;
	 PreparedStatement preparedStatement = null;
	 
	public Manager(int id, String tcNo, String name, String password, String type) {
		super(id, tcNo, name, password, type);
		// TODO Auto-generated constructor stub
	}
	
	public Manager(){}
	
	public ArrayList<User> getTeacherList() throws SQLException{
		 ArrayList<User> list = new ArrayList<>();
		 User obj;
		 try {
			 st = con.createStatement();
			 rs = st.executeQuery("SELECT * FROM user WHERE TYPE = 'teacher' ");
			 while(rs.next()) {
				 obj = new User(rs.getInt("id"),rs.getString("tcno"),rs.getString("name"),rs.getString("password"),rs.getString("type"));
				 list.add(obj);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;		 
		 }
	
	public boolean addTeacher(String tcno,String password,String name) throws SQLException {
		String query = "INSERT INTO user" + "(tcno,password,name,type) VALUES" + "(?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "teacher");
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
		
		
	}
	
	public boolean deleteTeacher(int id) throws SQLException {
		String query = "DELETE FROM user WHERE id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
		
		
	}
	
	public boolean updateTeacher(int id,String tcno,String password,String name) throws SQLException {
		String query = "UPDATE user SET name=?,tcno=?,password=? WHERE id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, password);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
		
		
	}
	
	public ArrayList<User> getStudentList() throws SQLException{
		 ArrayList<User> list = new ArrayList<>();
		 User obj;
		 try {
			 st = con.createStatement();
			 rs = st.executeQuery("SELECT * FROM user WHERE TYPE = 'student' ");
			 while(rs.next()) {
				 obj = new User(rs.getInt("id"),rs.getString("tcno"),rs.getString("name"),rs.getString("password"),rs.getString("phone_num"),rs.getString("courses"));
				 list.add(obj);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;		 
		 }
	
	public boolean addStudent(String tcno,String password,String name,String phoneNum,String courses) throws SQLException {
		String query = "INSERT INTO user" + "(tcno,password,name,type,phone_num,courses) VALUES" + "(?,?,?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();			
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "student");
			preparedStatement.setString(5, phoneNum);
			preparedStatement.setString(6, courses);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
		
		
	}
	
	public boolean updateStudent(int id,String tcno,String password,String name,String phoneNum,String course) throws SQLException {
		String query = "UPDATE user SET tcno=?,password=?,name=?,phone_num=?,courses=? WHERE id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, phoneNum);
			preparedStatement.setString(5, course);
			preparedStatement.setInt(6, id);		
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
		
		
	}
	
	public boolean deleteStudent(int id) throws SQLException {
		String query = "DELETE FROM user WHERE id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
		
		
	}
	

}
