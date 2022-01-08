package View;

import java.awt.EventQueue;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Helper.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import Model.Course;
import Model.Manager;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_tcNo;
	private JPasswordField fld_pass;
	private DBConnection conn = new DBConnection();
	private JTable table_fee;
	private DefaultTableModel courseModel = null;
	private Object[] courseData = null;
	Course course = new Course();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public LoginGUI() throws SQLException {
		
		// Course Model
		courseModel = new DefaultTableModel();
		Object[] colCourse = new Object[3];
		colCourse[0] = "Kurs Adý";
		colCourse[1] = "Ücreti";
		colCourse[2] = "Vergi Dahil";
		courseModel.setColumnIdentifiers(colCourse);
		courseData = new Object[3];
		for (int i = 0; i < course.getList().size(); i++) {
			int kursUcreti=Integer.parseInt(course.getList().get(i).getFee());
			int vergi = (kursUcreti*10/100)+kursUcreti;
			courseData[0] = course.getList().get(i).getName();
			courseData[1] = kursUcreti+" "+"TL";
			courseData[2] = vergi+" "+"TL";
			courseModel.addRow(courseData);
		}
		
		
		setResizable(false);
		setTitle("S\u00FCr\u00FCc\u00FC Kursu Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel l_logo = new JLabel(new ImageIcon(getClass().getResource("AxoyL.png")));
		l_logo.setBounds(231, 7, 147, 110);
		w_pane.add(l_logo);
		
		JLabel l_hosgeldiniz = new JLabel("Ho\u015Fgeldiniz");
		l_hosgeldiniz.setBounds(251, 115, 147, 24);
		l_hosgeldiniz.setFont(new Font("Arial", Font.BOLD, 19));
		w_pane.add(l_hosgeldiniz);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 145, 575, 215);
		w_pane.add(w_tabpane);
		
		JPanel w_login = new JPanel();
		w_tabpane.addTab("Üye Giriþi", null, w_login, null);
		w_login.setLayout(null);
		
		JLabel txt_tcNo = new JLabel("T.C. Numaran\u0131z:");
		txt_tcNo.setFont(new Font("Arial", Font.BOLD, 14));
		txt_tcNo.setBounds(10, 28, 218, 24);
		w_login.add(txt_tcNo);
		
		JLabel txt_pass = new JLabel("\u015Eifre:");
		txt_pass.setFont(new Font("Arial", Font.BOLD, 14));
		txt_pass.setBounds(10, 63, 218, 24);
		w_login.add(txt_pass);
		
		fld_tcNo = new JTextField();
		fld_tcNo.setBounds(132, 30, 409, 22);
		w_login.add(fld_tcNo);
		fld_tcNo.setColumns(10);
		
		JButton btn_login = new JButton("Giri\u015F Yap");
		btn_login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(fld_tcNo.getText().length() == 0 || fld_pass.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {					
					try {
						Connection con = conn.connDb();	
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("Select * FROM user");
						while(rs.next()) {
							if(fld_tcNo.getText().equals(rs.getString("tcno")) && fld_pass.getText().equals(rs.getString("password"))){
								Manager m = new Manager();
								m.setId(rs.getInt("id"));
								m.setPassword("password");
								m.setTcno(rs.getString("tcno"));
								m.setName(rs.getString("name"));
								m.setType(rs.getString("type"));
								ManagerGUI mGUI = new ManagerGUI(m);
								mGUI.setVisible(true);
								dispose();
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					}
			}
		});
		btn_login.setFont(new Font("Cambria", Font.PLAIN, 17));
		btn_login.setBounds(184, 108, 212, 51);
		w_login.add(btn_login);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(132, 66, 409, 22);
		w_login.add(fld_pass);
		
		JPanel w_courseFee = new JPanel();
		w_tabpane.addTab("Kurs Ücreti", null, w_courseFee, null);
		w_courseFee.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("S\u00FCr\u00FCc\u00FC Kursu \u00DCcretleri");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_3.setBounds(158, 0, 299, 51);
		w_courseFee.add(lblNewLabel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 550, 134);
		w_courseFee.add(scrollPane);
		
		table_fee = new JTable(courseModel);
		scrollPane.setViewportView(table_fee);
	}
}
