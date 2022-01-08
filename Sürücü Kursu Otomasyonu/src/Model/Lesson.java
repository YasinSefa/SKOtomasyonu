package Model;

import java.sql.*;
import java.util.ArrayList;

import Helper.DBConnection;

public class Lesson {
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	
	private int id;
	private String lessonTime;
	private String lessonTeacher;
	private String lessonStudent;
	
	
	public Lesson() {}
	
	public Lesson(int id, String lessonTime, String lessonTeacher, String lessonStudent) {
		super();
		this.id = id;
		this.lessonTime = lessonTime;
		this.lessonTeacher = lessonTeacher;
		this.lessonStudent = lessonStudent;
	}
	
	public ArrayList<Lesson> getLessonList() throws SQLException{
		ArrayList<Lesson> list = new ArrayList<>();
		Lesson obj;
		Connection con = conn.connDb();
		try {		
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM lesson");
			while(rs.next()) {
				obj = new Lesson();
				obj.setId(rs.getInt("id"));
				obj.setLessonTime(rs.getString("lesson_time"));
				obj.setLessonTeacher(rs.getString("lesson_teacher"));
				obj.setLessonStudent(rs.getString("lesson_student"));
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
	
	
	public boolean addLesson(String lessonTime,String lessonTeacher,String lessonStudent) throws SQLException {
		Connection con = conn.connDb();
		String query = "INSERT INTO lesson" + "(lesson_time,lesson_teacher,lesson_student) VALUES" + "(?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();			
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, lessonTime);
			preparedStatement.setString(2, lessonTeacher);
			preparedStatement.setString(3, lessonStudent);
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
	
	public boolean deleteLesson(int id) throws SQLException {
		Connection con = conn.connDb();
		String query = "DELETE FROM lesson WHERE id=?";
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
	
	public boolean updateLesson(int id,String lessonTime,String lessonTeacher,String lessonStudent) throws SQLException {
		Connection con = conn.connDb();
		String query = "UPDATE lesson SET lesson_time=?,lesson_teacher=?,lesson_student=? WHERE id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, lessonTime);
			preparedStatement.setString(2, lessonTeacher);
			preparedStatement.setString(3, lessonStudent);
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLessonTime() {
		return lessonTime;
	}
	public void setLessonTime(String lessonTime) {
		this.lessonTime = lessonTime;
	}
	public String getLessonTeacher() {
		return lessonTeacher;
	}
	public void setLessonTeacher(String lessonTeacher) {
		this.lessonTeacher = lessonTeacher;
	}
	public String getLessonStudent() {
		return lessonStudent;
	}
	public void setLessonStudent(String lessonStudent) {
		this.lessonStudent = lessonStudent;
	}
	
	
}
