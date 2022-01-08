package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Course {
	DBConnection conn = new DBConnection();	
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	private int id;
	private String name;
	private String fee;

	public Course() {
	}

	public Course(int id, String name, String fee) {
		super();
		this.id = id;
		this.name = name;
		this.fee = fee;
	}

	public ArrayList<Course> getList() throws SQLException {
		ArrayList<Course> list = new ArrayList<>();
		Course obj;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM course");
			while (rs.next()) {
				obj = new Course();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				obj.setFee(rs.getString("fee"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}
		return list;
	}
	
	public Course getCourse(int id) {
		Connection con = conn.connDb();
		Course c = new Course();;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM course WHERE id = " + id);
			while(rs.next()) {
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setFee(rs.getString("fee"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	public boolean addCourse(String name,String string) throws SQLException {
		String query = "INSERT INTO course" + "(name,fee) VALUES" + "(?,?)";
		boolean key = false;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, string);
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
	
	public boolean deleteCourse(int id) throws SQLException {
		String query = "DELETE FROM course WHERE id=?";
		boolean key = false;
		Connection con = conn.connDb();
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
	
	public boolean updateCourse(int id, String name, String fee) throws SQLException {
		String query = "UPDATE course SET name=?,fee=? WHERE id=?";
		boolean key = false;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, fee);		
			preparedStatement.setInt(3, id);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}
}
