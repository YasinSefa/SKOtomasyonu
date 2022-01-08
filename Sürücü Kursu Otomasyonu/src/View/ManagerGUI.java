package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.Course;
import Model.Lesson;
import Model.Manager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Helper.*;
import javax.swing.JComboBox;

public class ManagerGUI extends JFrame {

	Course course = new Course();
	Lesson lesson = new Lesson();
	private JPanel contentPane;
	static Manager manager = new Manager();
	private JTextField fld_tName;
	private JTextField fld_tTc;
	private JTextField fld_tPass;
	private JTextField fld_tID;
	private JTable table_T;
	private DefaultTableModel teacherModel = null;
	private Object[] teacherData = null;
	private JTable table_course;
	private JTextField fld_courseName;
	private DefaultTableModel courseModel = null;
	private Object[] courseData = null;
	private JTextField fld_courseFee;
	private JPopupMenu courseMenu;
	private JTextField fld_lessonTime;
	private JTextField fld_nameS;
	private JTextField fld_tcS;
	private JTextField fld_numberS;
	private JTextField fld_IdS;
	private JTextField fld_passS;
	private JTable table_student;
	private DefaultTableModel studentModel = null;
	private Object[] studentData = null;
	private DefaultTableModel lessonModel = null;
	private Object[] lessonData = null;
	private JTable table_lesson;
	private JTextField fld_lessonId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerGUI frame = new ManagerGUI(manager);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public ManagerGUI(Manager manager) throws SQLException {

		// Teacher Model
		teacherModel = new DefaultTableModel();
		Object[] colTeacherName = new Object[4];
		colTeacherName[0] = "ID";
		colTeacherName[1] = "Ad Soyad";
		colTeacherName[2] = "TC NO";
		colTeacherName[3] = "Þifre";
		teacherModel.setColumnIdentifiers(colTeacherName);
		teacherData = new Object[4];
		for (int i = 0; i < manager.getTeacherList().size(); i++) {
			teacherData[0] = manager.getTeacherList().get(i).getId();
			teacherData[1] = manager.getTeacherList().get(i).getName();
			teacherData[2] = manager.getTeacherList().get(i).getTcno();
			teacherData[3] = manager.getTeacherList().get(i).getPassword();
			teacherModel.addRow(teacherData);
		}

		// Course Model
		courseModel = new DefaultTableModel();
		Object[] colCourse = new Object[3];
		colCourse[0] = "ID";
		colCourse[1] = "Kurs Adý";
		colCourse[2] = "Ücreti";
		courseModel.setColumnIdentifiers(colCourse);
		courseData = new Object[3];
		for (int i = 0; i < course.getList().size(); i++) {
			courseData[0] = course.getList().get(i).getId();
			courseData[1] = course.getList().get(i).getName();
			courseData[2] = course.getList().get(i).getFee();
			courseModel.addRow(courseData);
		}

		// Student Model
		studentModel = new DefaultTableModel();
		Object[] colStudent = new Object[6];
		colStudent[0] = "ID";
		colStudent[1] = "TC No";
		colStudent[2] = "Þifre";
		colStudent[3] = "Ýsim";
		colStudent[4] = "Telefon Numarasý";
		colStudent[5] = "Kursu";

		studentModel.setColumnIdentifiers(colStudent);
		studentData = new Object[6];
		for (int i = 0; i < manager.getStudentList().size(); i++) {
			studentData[0] = manager.getStudentList().get(i).getId();
			studentData[1] = manager.getStudentList().get(i).getName();
			studentData[2] = manager.getStudentList().get(i).getPassword();
			studentData[3] = manager.getStudentList().get(i).getName();
			studentData[4] = manager.getStudentList().get(i).getPhoneNum();
			studentData[5] = manager.getStudentList().get(i).getCourses();
			studentModel.addRow(studentData);
		}

		// Lesson Model
		lessonModel = new DefaultTableModel();
		Object[] colLesson = new Object[4];
		colLesson[0] = "ID";
		colLesson[1] = "Kurs Vakti";
		colLesson[2] = "Kurs Hocasý";
		colLesson[3] = "Kurs Öðrencisi";

		lessonModel.setColumnIdentifiers(colLesson);
		lessonData = new Object[4];
		for (int i = 0; i < lesson.getLessonList().size(); i++) {
			lessonData[0] = lesson.getLessonList().get(i).getId();
			lessonData[1] = lesson.getLessonList().get(i).getLessonTime();
			lessonData[2] = lesson.getLessonList().get(i).getLessonTeacher();
			lessonData[3] = lesson.getLessonList().get(i).getLessonStudent();
			lessonModel.addRow(lessonData);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_welcome = new JLabel("Ho\u015Fgeldiniz, Say\u0131n" + " " + manager.getName());
		lbl_welcome.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_welcome.setBounds(10, 11, 211, 33);
		contentPane.add(lbl_welcome);

		JButton btn_exit = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btn_exit.setFont(new Font("Arial", Font.BOLD, 12));
		btn_exit.setBounds(635, 18, 89, 23);
		contentPane.add(btn_exit);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 113, 714, 337);
		contentPane.add(w_tab);

		JPanel w_manager = new JPanel();
		w_tab.addTab("Öðretmenler", null, w_manager, null);
		w_manager.setLayout(null);

		JLabel labelName = new JLabel("Ad Soyad");
		labelName.setFont(new Font("Arial", Font.BOLD, 11));
		labelName.setBounds(559, 11, 58, 30);
		w_manager.add(labelName);

		JLabel lblTcNo = new JLabel("T.C. No");
		lblTcNo.setFont(new Font("Arial", Font.BOLD, 11));
		lblTcNo.setBounds(559, 68, 58, 30);
		w_manager.add(lblTcNo);

		JLabel lblPass = new JLabel("\u015Eifre");
		lblPass.setFont(new Font("Arial", Font.BOLD, 11));
		lblPass.setBounds(559, 125, 58, 30);
		w_manager.add(lblPass);

		fld_tName = new JTextField();
		fld_tName.setBounds(558, 37, 125, 20);
		w_manager.add(fld_tName);
		fld_tName.setColumns(10);

		fld_tTc = new JTextField();
		fld_tTc.setColumns(10);
		fld_tTc.setBounds(559, 94, 125, 20);
		w_manager.add(fld_tTc);

		fld_tPass = new JTextField();
		fld_tPass.setColumns(10);
		fld_tPass.setBounds(559, 154, 125, 20);
		w_manager.add(fld_tPass);

		JButton btn_tAdd = new JButton("Ekle");
		btn_tAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_tName.getText().length() == 0 || fld_tPass.getText().length() == 0
						|| fld_tTc.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = manager.addTeacher(fld_tTc.getText(), fld_tPass.getText(),
								fld_tName.getText());
						if (control) {
							Helper.showMsg("success");
							fld_tName.setText(null);
							fld_tTc.setText(null);
							fld_tPass.setText(null);
							updateTeacherModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		btn_tAdd.setBounds(569, 185, 89, 23);
		w_manager.add(btn_tAdd);

		JLabel lblKullancId = new JLabel("Kullan\u0131c\u0131 ID");
		lblKullancId.setFont(new Font("Arial", Font.BOLD, 11));
		lblKullancId.setBounds(559, 219, 58, 30);
		w_manager.add(lblKullancId);

		fld_tID = new JTextField();
		fld_tID.setColumns(10);
		fld_tID.setBounds(558, 246, 125, 20);
		w_manager.add(fld_tID);

		JButton btn_tDelete = new JButton("Sil");
		btn_tDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fld_tID.getText().length() == 0) {
					Helper.showMsg("Lütfen geçerli öðretmen belirleyiniz!");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_tID.getText());
						try {
							boolean control = manager.deleteTeacher(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_tID.setText(null);
								updateTeacherModel();
							}
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}

				}
			}
		});
		btn_tDelete.setBounds(569, 275, 89, 23);
		w_manager.add(btn_tDelete);

		JScrollPane w_tScroll = new JScrollPane();
		w_tScroll.setBounds(10, 11, 536, 287);
		w_manager.add(w_tScroll);

		table_T = new JTable(teacherModel);
		w_tScroll.setViewportView(table_T);

		table_T.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_tID.setText(table_T.getValueAt(table_T.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

		table_T.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(table_T.getValueAt(table_T.getSelectedRow(), 0).toString());
					String selectName = table_T.getValueAt(table_T.getSelectedRow(), 1).toString();
					String selectTcno = table_T.getValueAt(table_T.getSelectedRow(), 2).toString();
					String selectPass = table_T.getValueAt(table_T.getSelectedRow(), 3).toString();

					try {
						boolean control = manager.updateTeacher(selectID, selectTcno, selectPass, selectName);
						if (control) {
							Helper.showMsg("success");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		JPanel w_course = new JPanel();
		w_course.setBackground(Color.WHITE);
		w_tab.addTab("Kurslar", null, w_course, null);
		w_course.setLayout(null);

		JScrollPane w_scrollCourse = new JScrollPane();
		w_scrollCourse.setBounds(10, 11, 253, 287);
		w_course.add(w_scrollCourse);

		courseMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		courseMenu.add(updateMenu);
		courseMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_course.getValueAt(table_course.getSelectedRow(), 0).toString());
				Course selectCourse = course.getCourse(selID);
				UpdateCourseGUI updateGUI = new UpdateCourseGUI(selectCourse);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateCourseModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				});
			}
		});

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_course.getValueAt(table_course.getSelectedRow(), 0).toString());
					try {
						if (course.deleteCourse(selID)) {
							Helper.showMsg("success");
							updateCourseModel();
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

		table_course = new JTable(courseModel);
		table_course.setComponentPopupMenu(courseMenu);
		table_course.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_course.rowAtPoint(point);
				table_course.setRowSelectionInterval(selectedRow, selectedRow);

			}
		});
		w_scrollCourse.setViewportView(table_course);

		JLabel lbl_courseName = new JLabel("Kurs \u0130smi");
		lbl_courseName.setFont(new Font("Arial", Font.BOLD, 11));
		lbl_courseName.setBounds(287, 11, 58, 30);
		w_course.add(lbl_courseName);

		fld_courseName = new JTextField();
		fld_courseName.setColumns(10);
		fld_courseName.setBounds(286, 37, 125, 20);
		w_course.add(fld_courseName);

		JButton btn_courseAdd = new JButton("Ekle");
		btn_courseAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_courseName.getText().length() == 0 || fld_courseFee.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (course.addCourse(fld_courseName.getText(), fld_courseFee.getText())) {
							Helper.showMsg("success");
							fld_courseName.setText(null);
							fld_courseFee.setText(null);
							updateCourseModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_courseAdd.setBounds(297, 120, 89, 23);
		w_course.add(btn_courseAdd);

		fld_courseFee = new JTextField();
		fld_courseFee.setColumns(10);
		fld_courseFee.setBounds(287, 89, 125, 20);
		w_course.add(fld_courseFee);

		JLabel lbl_courseFee = new JLabel("Kurs \u00DCcreti");
		lbl_courseFee.setFont(new Font("Arial", Font.BOLD, 11));
		lbl_courseFee.setBounds(288, 63, 89, 30);
		w_course.add(lbl_courseFee);

		JPanel w_students = new JPanel();
		w_tab.addTab("Öðrenciler", null, w_students, null);
		w_students.setLayout(null);

		JScrollPane w_scrollStudent = new JScrollPane();
		w_scrollStudent.setBounds(10, 11, 439, 287);
		w_students.add(w_scrollStudent);

		table_student = new JTable(studentModel);
		w_scrollStudent.setViewportView(table_student);
		table_student.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_IdS.setText(table_student.getValueAt(table_student.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

		table_student.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_student.getValueAt(table_student.getSelectedRow(), 0).toString());
					String selectName = table_student.getValueAt(table_student.getSelectedRow(), 1).toString();
					String selectTcno = table_student.getValueAt(table_student.getSelectedRow(), 2).toString();
					String selectPass = table_student.getValueAt(table_student.getSelectedRow(), 3).toString();
					String selectPhoneNum = table_student.getValueAt(table_student.getSelectedRow(), 4).toString();
					String selectCourse = table_student.getValueAt(table_student.getSelectedRow(), 5).toString();

					try {
						boolean control = manager.updateStudent(selectID, selectTcno, selectPass, selectName,
								selectPhoneNum, selectCourse);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		JLabel lbl_studentName = new JLabel("Ad Soyad");
		lbl_studentName.setFont(new Font("Arial", Font.BOLD, 11));
		lbl_studentName.setBounds(462, 11, 58, 30);
		w_students.add(lbl_studentName);

		fld_nameS = new JTextField();
		fld_nameS.setColumns(10);
		fld_nameS.setBounds(461, 37, 125, 20);
		w_students.add(fld_nameS);

		JLabel lbl_studentTc = new JLabel("T.C. No");
		lbl_studentTc.setFont(new Font("Arial", Font.BOLD, 11));
		lbl_studentTc.setBounds(462, 68, 58, 30);
		w_students.add(lbl_studentTc);

		fld_tcS = new JTextField();
		fld_tcS.setColumns(10);
		fld_tcS.setBounds(462, 94, 125, 20);
		w_students.add(fld_tcS);

		JLabel lbl_studentPass = new JLabel("\u015Eifre");
		lbl_studentPass.setFont(new Font("Arial", Font.BOLD, 11));
		lbl_studentPass.setBounds(597, 68, 58, 30);
		w_students.add(lbl_studentPass);

		fld_numberS = new JTextField();
		fld_numberS.setColumns(10);
		fld_numberS.setBounds(459, 153, 125, 20);
		w_students.add(fld_numberS);

		JComboBox select_course = new JComboBox();
		select_course.setBounds(596, 37, 103, 20);
		for (int i = 0; i < course.getList().size(); i++) {
			select_course.addItem(new Item(course.getList().get(i).getId(), course.getList().get(i).getName()));
			updateCourseModel();
		}
		select_course.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
		});

		w_students.add(select_course);

		JButton btn_addS = new JButton("Ekle");
		btn_addS.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fld_nameS.getText().length() == 0 || fld_tcS.getText().length() == 0
						|| fld_passS.getText().length() == 0 || fld_numberS.getText().length() == 0) {
					Helper.showMsg("Boþluklarý doldurunuz!");
				} else {
					Item courseItem = (Item) select_course.getSelectedItem();
					try {
						boolean control = manager.addStudent(fld_nameS.getText(), fld_tcS.getText(),
								fld_passS.getText(), fld_numberS.getText(), courseItem.getValue());
						if (control) {
							Helper.showMsg("success");
							fld_nameS.setText(null);
							fld_tcS.setText(null);
							fld_passS.setText(null);
							fld_numberS.setText(null);
							select_course.setSelectedItem(null);
							updateStudentModel();
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btn_addS.setBounds(472, 185, 89, 23);
		w_students.add(btn_addS);

		JLabel lbl_studentId = new JLabel("Kullan\u0131c\u0131 ID");
		lbl_studentId.setFont(new Font("Arial", Font.BOLD, 11));
		lbl_studentId.setBounds(462, 219, 58, 30);
		w_students.add(lbl_studentId);

		fld_IdS = new JTextField();
		fld_IdS.setColumns(10);
		fld_IdS.setBounds(461, 246, 125, 20);
		w_students.add(fld_IdS);

		JButton btn_deleteS = new JButton("Sil");
		btn_deleteS.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fld_IdS.getText().length() == 0) {
					Helper.showMsg("Öðrenci seçiniz");
				} else {
					int selectID = Integer.parseInt(fld_IdS.getText());
					try {
						if (Helper.confirm("sure")) {
							boolean control = manager.deleteStudent(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_IdS.setText(null);
								updateStudentModel();
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		});
		btn_deleteS.setBounds(472, 275, 89, 23);
		w_students.add(btn_deleteS);

		JLabel lbl_studentCourse = new JLabel("Alaca\u011F\u0131 Kurs");
		lbl_studentCourse.setFont(new Font("Arial", Font.BOLD, 11));
		lbl_studentCourse.setBounds(596, 11, 103, 30);
		w_students.add(lbl_studentCourse);

		JLabel lbl_studentName_1 = new JLabel("Telefon Numaras\u0131");
		lbl_studentName_1.setFont(new Font("Arial", Font.BOLD, 11));
		lbl_studentName_1.setBounds(459, 125, 102, 30);
		w_students.add(lbl_studentName_1);

		fld_passS = new JTextField();
		fld_passS.setColumns(10);
		fld_passS.setBounds(597, 94, 103, 20);
		w_students.add(fld_passS);

		JPanel w_lesson = new JPanel();
		w_tab.addTab("Dersler", null, w_lesson, null);
		w_lesson.setLayout(null);

		fld_lessonTime = new JTextField();
		fld_lessonTime.setColumns(10);
		fld_lessonTime.setBounds(574, 34, 125, 20);
		w_lesson.add(fld_lessonTime);

		JComboBox select_lessonT = new JComboBox();
		for (int i = 0; i < manager.getTeacherList().size(); i++) {
			select_lessonT.addItem(
					new Item(manager.getTeacherList().get(i).getId(), manager.getTeacherList().get(i).getName()));
		}
		select_lessonT.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
		});
		select_lessonT.setBounds(574, 85, 125, 20);
		w_lesson.add(select_lessonT);

		JComboBox select_lessonS = new JComboBox();
		for (int i = 0; i < manager.getStudentList().size(); i++) {
			select_lessonS.addItem(
					new Item(manager.getStudentList().get(i).getId(), manager.getStudentList().get(i).getName()));
		}
		select_lessonS.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
		});
		select_lessonS.setBounds(574, 131, 125, 20);
		w_lesson.add(select_lessonS);

		JScrollPane w_scrollLessonAdd = new JScrollPane();
		w_scrollLessonAdd.setBounds(10, 11, 554, 287);
		w_lesson.add(w_scrollLessonAdd);

		table_lesson = new JTable(lessonModel);
		w_scrollLessonAdd.setViewportView(table_lesson);
		table_lesson.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_lessonId.setText(table_lesson.getValueAt(table_lesson.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

		table_lesson.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_lesson.getValueAt(table_lesson.getSelectedRow(), 0).toString());
					String selectLessonTime = table_lesson.getValueAt(table_lesson.getSelectedRow(), 1).toString();
					String selectLessonTeacher = table_lesson.getValueAt(table_lesson.getSelectedRow(), 2).toString();
					String selectLessonStudent = table_lesson.getValueAt(table_lesson.getSelectedRow(), 3).toString();
					try {
						boolean control = lesson.updateLesson(selectID, selectLessonTime, selectLessonTeacher,
								selectLessonStudent);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		JLabel lbl_courseTime = new JLabel("Ders Saati");
		lbl_courseTime.setFont(new Font("Arial", Font.BOLD, 11));
		lbl_courseTime.setBounds(574, 11, 58, 30);
		w_lesson.add(lbl_courseTime);

		JLabel lbl_courseTeacher = new JLabel("Ders \u00D6\u011Fretmeni");
		lbl_courseTeacher.setFont(new Font("Arial", Font.BOLD, 11));
		lbl_courseTeacher.setBounds(575, 60, 89, 30);
		w_lesson.add(lbl_courseTeacher);

		JButton btn_addLesson = new JButton("Ekle");
		btn_addLesson.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fld_lessonTime.getText().length() == 0) {
					Helper.showMsg("Error");
				} else {
					Item lessonTItem = (Item) select_lessonT.getSelectedItem();
					Item lessonSItem = (Item) select_lessonS.getSelectedItem();
					try {
						boolean control = lesson.addLesson(fld_lessonTime.getText(), lessonTItem.getValue(),
								lessonSItem.getValue());
						if (control) {
							try {
								Helper.showMsg("success");
								fld_lessonTime.setText(null);
								select_lessonT.setSelectedItem(null);
								select_lessonS.setSelectedItem(null);
								updateLessonModel();
								updateTeacherModel();
								updateStudentModel();
							} catch (Exception e2) {
								e2.getStackTrace();
							}

						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btn_addLesson.setBounds(585, 162, 89, 23);
		w_lesson.add(btn_addLesson);

		JLabel lbl_courseStudent = new JLabel("\u00D6\u011Frenci");
		lbl_courseStudent.setFont(new Font("Arial", Font.BOLD, 11));
		lbl_courseStudent.setBounds(575, 106, 89, 30);
		w_lesson.add(lbl_courseStudent);

		JLabel lbl_lessonId = new JLabel("Kullan\u0131c\u0131 ID");
		lbl_lessonId.setFont(new Font("Arial", Font.BOLD, 11));
		lbl_lessonId.setBounds(575, 207, 58, 30);
		w_lesson.add(lbl_lessonId);

		fld_lessonId = new JTextField();
		fld_lessonId.setColumns(10);
		fld_lessonId.setBounds(574, 234, 125, 20);
		w_lesson.add(fld_lessonId);

		JButton btn_deleteL = new JButton("Sil");
		btn_deleteL.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fld_lessonId.getText().length() == 0) {
					Helper.showMsg("Lütfen geçerli ders seçiniz!");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_lessonId.getText());
						try {
							boolean control = lesson.deleteLesson(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_lessonId.setText(null);
								updateLessonModel();
							}
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}

				}
			}
		});
		btn_deleteL.setBounds(585, 263, 89, 23);
		w_lesson.add(btn_deleteL);

	}

	public void updateTeacherModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_T.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < manager.getTeacherList().size(); i++) {
			teacherData[0] = manager.getTeacherList().get(i).getId();
			teacherData[1] = manager.getTeacherList().get(i).getName();
			teacherData[2] = manager.getTeacherList().get(i).getTcno();
			teacherData[3] = manager.getTeacherList().get(i).getPassword();
			teacherModel.addRow(teacherData);
		}
	}

	public void updateCourseModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_course.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < course.getList().size(); i++) {
			courseData[0] = course.getList().get(i).getId();
			courseData[1] = course.getList().get(i).getName();
			courseData[2] = course.getList().get(i).getFee();
			courseModel.addRow(courseData);
		}
	}

	public void updateStudentModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_student.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < manager.getStudentList().size(); i++) {
			studentData[0] = manager.getStudentList().get(i).getId();
			studentData[1] = manager.getStudentList().get(i).getName();
			studentData[2] = manager.getStudentList().get(i).getTcno();
			studentData[3] = manager.getStudentList().get(i).getPassword();
			studentData[4] = manager.getStudentList().get(i).getPhoneNum();
			studentData[5] = manager.getStudentList().get(i).getCourses();
			studentModel.addRow(studentData);
		}
	}

	public void updateLessonModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_lesson.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < lesson.getLessonList().size(); i++) {
			lessonData[0] = lesson.getLessonList().get(i).getId();
			lessonData[1] = lesson.getLessonList().get(i).getLessonTime();
			lessonData[2] = lesson.getLessonList().get(i).getLessonTeacher();
			lessonData[3] = lesson.getLessonList().get(i).getLessonStudent();
			lessonModel.addRow(lessonData);
		}
	}
}
