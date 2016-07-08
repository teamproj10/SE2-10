package schedule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class OCStudents implements ActionListener{
	JTextField studentId,courseCode,courseName,semester,grade;
	Service service=new Service();
	JPanel currentContentPane;
	String rowIndexPointer="";
	public OCStudents(JPanel contentPane,ArrayList tempStudentList){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("OC STUDENT COURSES");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList studentList=null;
		if(tempStudentList!=null){
			studentList=tempStudentList;
		}else{
			studentList=service.allOCMaintainData(service.OCSTUDENTCOURSE);
		}
		if(studentList!=null && studentList.size()!=0){
			int columnsCount=5;
			String studentColumnNames[] = {"Student Id", "Course Code", "Course Name","Semester", "Grade"};
			String studentRowColumnDataValues[][]=new String[studentList.size()][columnsCount];
			Iterator iterator=studentList.iterator();
			int poistion=0;
			while(iterator.hasNext()){
				String[] tokens=(String[])iterator.next();
				studentRowColumnDataValues[poistion][0]=tokens[0];
				studentRowColumnDataValues[poistion][1]=tokens[1];
				studentRowColumnDataValues[poistion][2]=tokens[2];
				studentRowColumnDataValues[poistion][3]=tokens[3];
				studentRowColumnDataValues[poistion][4]=tokens[4];
				poistion=poistion+1;
			}
			final JTable studentTable = new JTable(studentRowColumnDataValues, studentColumnNames);
		    ListSelectionModel tableRowSelection = studentTable.getSelectionModel();
		    tableRowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		    tableRowSelection.addListSelectionListener(new ListSelectionListener(){
		    	public void valueChanged(ListSelectionEvent e) {
		    		rowIndexPointer=""+studentTable.getSelectedRow();
		    	}
		    });	    
			JScrollPane tableScrollPane = new JScrollPane(studentTable);
			tableScrollPane.setBounds(100, 200, 550, 250);
			contentPane.add(tableScrollPane, BorderLayout.CENTER);
		}
		
		JButton addStudentButton = new JButton("Add Student");
		addStudentButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		addStudentButton.setBounds(700, 200, 200, 40);
		addStudentButton.addActionListener(this);
		contentPane.add(addStudentButton);
		
		JButton deleteButton = new JButton("Delete Student");
		deleteButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		deleteButton.setBounds(700, 260, 200, 40);
		deleteButton.addActionListener(this);
		contentPane.add(deleteButton);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(700, 320, 200, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void addNewStudent(JPanel contentPane){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("ADD NEW STUDENT COURSE");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Student Id");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 100, 25);
		contentPane.add(headingLabel);
		
		studentId = new JTextField();
		studentId.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		studentId.setBounds(250, 200, 250, 25);
		contentPane.add(studentId);
		
		headingLabel = new JLabel("Course Code");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 100, 25);
		contentPane.add(headingLabel);
		
		courseCode = new JTextField();
		courseCode.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		courseCode.setBounds(250, 230, 250, 25);
		contentPane.add(courseCode);
		
		headingLabel = new JLabel("Course Name");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 100, 25);
		contentPane.add(headingLabel);
		
		courseName = new JTextField();
		courseName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		courseName.setBounds(250, 260, 250, 25);
		contentPane.add(courseName);
		
		headingLabel = new JLabel("Semester");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 290, 125, 25);
		contentPane.add(headingLabel);
		
		semester = new JTextField();
		semester.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		semester.setBounds(250, 290, 250, 25);
		contentPane.add(semester);
		
		headingLabel = new JLabel("Grade");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 320, 125, 25);
		contentPane.add(headingLabel);
		
		grade = new JTextField();
		grade.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		grade.setBounds(250, 320, 250, 25);
		contentPane.add(semester);

		JButton saveButton = new JButton("Save");
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		saveButton.setBounds(250, 400, 100, 40);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(400, 400, 100, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Add Student")){
			addNewStudent(currentContentPane);
		}
		if(e.getActionCommand().equalsIgnoreCase("Delete Student")){
			if(rowIndexPointer.trim().length()!=0){
				ArrayList tempList=new ArrayList();
				ArrayList studentList=service.allOCMaintainData(service.OCSTUDENTCOURSE);
				int selectedRowNumber=Integer.parseInt(rowIndexPointer);
				int poistion=0;
				Iterator iterator=studentList.iterator();
				while(iterator.hasNext()){
					String[] tokens=(String[])iterator.next();
					if(poistion!=selectedRowNumber){
						tempList.add(tokens);
					}
					poistion=poistion+1;					
				}
				new OCStudents(currentContentPane,tempList);
			}else{
				JOptionPane.showMessageDialog(null, "Please select row of the table.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Save")){
			if(studentId!=null && courseCode!=null && courseName!=null && semester!=null && grade!=null){
				if(studentId.getText().trim().length()!=0 && courseCode.getText().trim().length()!=0 && courseName.getText().trim().length()!=0 && semester.getText().trim().length()!=0 && grade.getText().trim().length()!=0){
					ArrayList studentCourseList=service.allOCCoursesORDegreePlan(service.OCSTUDENTCOURSE);
					String[] tokens=new String[10];
					tokens[0]=studentId.getText().trim();
					tokens[1]=courseCode.getText().trim();
					tokens[2]=courseName.getText().trim();
					tokens[3]=semester.getText().trim();
					tokens[4]=grade.getText().trim();
					studentCourseList.add(tokens);
					new OCStudents(currentContentPane,studentCourseList);
				}else{
					JOptionPane.showMessageDialog(null, "Please enter all add new course details.");
				}
			}else{
				JOptionPane.showMessageDialog(null, "Please enter all add new course details.");
			}
		}		
		if(e.getActionCommand().equalsIgnoreCase("Exit")){
			System.exit(1);
		}
    }
}
