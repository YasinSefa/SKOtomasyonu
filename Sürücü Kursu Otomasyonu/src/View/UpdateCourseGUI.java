package View;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.*;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateCourseGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_courseName;
	private JTextField fld_courseFee;
	private static Course course;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCourseGUI frame = new UpdateCourseGUI(course);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateCourseGUI(Course course) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_courseName = new JLabel("Kurs \u0130smi");
		lbl_courseName.setFont(new Font("Arial", Font.BOLD, 11));
		lbl_courseName.setBounds(11, 11, 58, 30);
		contentPane.add(lbl_courseName);
		
		fld_courseName = new JTextField();
		fld_courseName.setColumns(10);
		fld_courseName.setBounds(10, 37, 125, 20);
		fld_courseName.setText(course.getName());
		contentPane.add(fld_courseName);
		
		JLabel lbl_courseFee = new JLabel("Kurs \u00DCcreti");
		lbl_courseFee.setFont(new Font("Arial", Font.BOLD, 11));
		lbl_courseFee.setBounds(12, 63, 89, 30);
		contentPane.add(lbl_courseFee);
		
		fld_courseFee = new JTextField();
		fld_courseFee.setColumns(10);
		fld_courseFee.setBounds(11, 89, 125, 20);
		fld_courseFee.setText(course.getFee());
		contentPane.add(fld_courseFee);
		
		JButton btn_updateCourse = new JButton("D\u00FCzenle");
		btn_updateCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					try {
						course.updateCourse(course.getId(), fld_courseName.getText(), fld_courseFee.getText());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_updateCourse.setBounds(11, 120, 124, 23);
		contentPane.add(btn_updateCourse);
	}

}
