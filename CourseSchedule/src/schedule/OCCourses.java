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
import javax.swing.table.DefaultTableModel;

public class OCCourses implements ActionListener{
	JTextField courseCode,courseName,courseDescription,courseHours,courseCap,offeredFall,offeredSpring,offeredSummer,coursePrereqs,teachers;
	Service service=new Service();
	JPanel currentContentPane;
	String rowIndexPointer="";
	public OCCourses(){
		
	}
	public OCCourses(JPanel contentPane,ArrayList tempCoursesList){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("OC COURSES");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList courseList=null;
		if(tempCoursesList!=null){
			courseList=tempCoursesList;
		}else{
			courseList=service.allOCCoursesORDegreePlan(service.OCCOURSE);
		}
		if(courseList!=null && courseList.size()!=0){
			int columnsCount=10;
			String courseColumnNames[] = {"Course Code", "Course Name", "Course Description","Course Hours", "Course Cap", "Offered Fall?","Offered Spring?", "Offered Summer?", "Course Prereqs (could be list)", "Teachers"};
			String courseRowColumnDataValues[][]=new String[courseList.size()][columnsCount];
			Iterator iterator=courseList.iterator();
			int poistion=0;
			while(iterator.hasNext()){
				String[] tokens=(String[])iterator.next();
				courseRowColumnDataValues[poistion][0]=tokens[0];
				courseRowColumnDataValues[poistion][1]=tokens[1];
				courseRowColumnDataValues[poistion][2]=tokens[2];
				courseRowColumnDataValues[poistion][3]=tokens[3];
				courseRowColumnDataValues[poistion][4]=tokens[4];
				courseRowColumnDataValues[poistion][5]=tokens[5];
				courseRowColumnDataValues[poistion][6]=tokens[6];
				courseRowColumnDataValues[poistion][7]=tokens[7];
				courseRowColumnDataValues[poistion][8]=tokens[8];
				courseRowColumnDataValues[poistion][9]=tokens[9];
				poistion=poistion+1;
			}
			final JTable courseTable = new JTable(courseRowColumnDataValues, courseColumnNames);
		    ListSelectionModel tableRowSelection = courseTable.getSelectionModel();
		    tableRowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		    tableRowSelection.addListSelectionListener(new ListSelectionListener(){
		    	public void valueChanged(ListSelectionEvent e) {
		    		rowIndexPointer=""+courseTable.getSelectedRow();
		    	}
		    });	    
			JScrollPane tableScrollPane = new JScrollPane(courseTable);
			tableScrollPane.setBounds(100, 200, 550, 250);
			contentPane.add(tableScrollPane, BorderLayout.CENTER);
		}		
		
		JButton addCourseButton = new JButton("Add Course");
		addCourseButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		addCourseButton.setBounds(700, 200, 200, 40);
		addCourseButton.addActionListener(this);
		contentPane.add(addCourseButton);
		
		JButton updateCourseButton = new JButton("Update Course");
		updateCourseButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		updateCourseButton.setBounds(700, 260, 200, 40);
		updateCourseButton.addActionListener(this);
		contentPane.add(updateCourseButton);
		
		JButton deleteButton = new JButton("Delete Course");
		deleteButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		deleteButton.setBounds(700, 320, 200, 40);
		deleteButton.addActionListener(this);
		contentPane.add(deleteButton);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(700, 380, 200, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void addNewCours(JPanel contentPane){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("ADD NEW COURSE");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Course Code");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 100, 25);
		contentPane.add(headingLabel);
		
		courseCode = new JTextField();
		courseCode.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		courseCode.setBounds(250, 200, 250, 25);
		contentPane.add(courseCode);
		
		headingLabel = new JLabel("Course Name");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 100, 25);
		contentPane.add(headingLabel);
		
		courseName = new JTextField();
		courseName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		courseName.setBounds(250, 230, 250, 25);
		contentPane.add(courseName);
		
		headingLabel = new JLabel("Course Description");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 125, 25);
		contentPane.add(headingLabel);
		
		courseDescription = new JTextField();
		courseDescription.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		courseDescription.setBounds(250, 260, 250, 25);
		contentPane.add(courseDescription);

		headingLabel = new JLabel("Course Hours");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 290, 100, 25);
		contentPane.add(headingLabel);
		
		courseHours = new JTextField();
		courseHours.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		courseHours.setBounds(250, 290, 250, 25);
		contentPane.add(courseHours);
		
		headingLabel = new JLabel("Course Cap");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 320, 100, 25);
		contentPane.add(headingLabel);
		
		courseCap = new JTextField();
		courseCap.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		courseCap.setBounds(250, 320, 250, 25);
		contentPane.add(courseCap);
		
		headingLabel = new JLabel("Offered Fall");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 350, 100, 25);
		contentPane.add(headingLabel);
		
		offeredFall = new JTextField();
		offeredFall.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		offeredFall.setBounds(250, 350, 250, 25);
		contentPane.add(offeredFall);
		
		headingLabel = new JLabel("Offered Spring");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 380, 125, 25);
		contentPane.add(headingLabel);
		
		offeredSpring = new JTextField();
		offeredSpring.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		offeredSpring.setBounds(250, 380, 250, 25);
		contentPane.add(offeredSpring);
		
		headingLabel = new JLabel("Offered Summer");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 410, 120, 25);
		contentPane.add(headingLabel);
		
		offeredSummer = new JTextField();
		offeredSummer.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		offeredSummer.setBounds(250, 410, 250, 25);
		contentPane.add(offeredSummer);

		headingLabel = new JLabel("Course Prereqs");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 440, 100, 25);
		contentPane.add(headingLabel);
		
		coursePrereqs = new JTextField();
		coursePrereqs.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		coursePrereqs.setBounds(250, 440, 250, 25);
		contentPane.add(coursePrereqs);
		
		headingLabel = new JLabel("Teachers");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 470, 100, 25);
		contentPane.add(headingLabel);
		
		teachers = new JTextField();
		teachers.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		teachers.setBounds(250, 470, 250, 25);
		contentPane.add(teachers);
		
		JButton saveButton = new JButton("Save");
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		saveButton.setBounds(250, 525, 100, 40);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(400, 525, 100, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void updateCours(JPanel contentPane,int rowNumber){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("UPDATE COURSE");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList courseList=service.allOCCoursesORDegreePlan(service.OCCOURSE);
		rowIndexPointer=""+rowNumber;
		String[] tokens=(String[])courseList.get(rowNumber);
		
		headingLabel = new JLabel("Course Code");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 100, 25);
		contentPane.add(headingLabel);
		
		courseCode = new JTextField();
		courseCode.setText(tokens[0]);
		courseCode.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		courseCode.setBounds(250, 200, 250, 25);
		contentPane.add(courseCode);
		
		headingLabel = new JLabel("Course Name");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 100, 25);
		contentPane.add(headingLabel);
		
		courseName = new JTextField();
		courseName.setText(tokens[1]);
		courseName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		courseName.setBounds(250, 230, 250, 25);
		contentPane.add(courseName);
		
		headingLabel = new JLabel("Course Description");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 125, 25);
		contentPane.add(headingLabel);
		
		courseDescription = new JTextField();
		courseDescription.setText(tokens[2]);
		courseDescription.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		courseDescription.setBounds(250, 260, 250, 25);
		contentPane.add(courseDescription);

		headingLabel = new JLabel("Course Hours");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 290, 100, 25);
		contentPane.add(headingLabel);
		
		courseHours = new JTextField();
		courseHours.setText(tokens[3]);
		courseHours.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		courseHours.setBounds(250, 290, 250, 25);
		contentPane.add(courseHours);
		
		headingLabel = new JLabel("Course Cap");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 320, 100, 25);
		contentPane.add(headingLabel);
		
		courseCap = new JTextField();
		courseCap.setText(tokens[4]);
		courseCap.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		courseCap.setBounds(250, 320, 250, 25);
		contentPane.add(courseCap);
		
		headingLabel = new JLabel("Offered Fall");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 350, 100, 25);
		contentPane.add(headingLabel);
		
		offeredFall = new JTextField();
		offeredFall.setText(tokens[5]);
		offeredFall.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		offeredFall.setBounds(250, 350, 250, 25);
		contentPane.add(offeredFall);
		
		headingLabel = new JLabel("Offered Spring");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 380, 125, 25);
		contentPane.add(headingLabel);
		
		offeredSpring = new JTextField();
		offeredSpring.setText(tokens[6]);
		offeredSpring.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		offeredSpring.setBounds(250, 380, 250, 25);
		contentPane.add(offeredSpring);
		
		headingLabel = new JLabel("Offered Summer");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 410, 120, 25);
		contentPane.add(headingLabel);
		
		offeredSummer = new JTextField();
		offeredSummer.setText(tokens[7]);
		offeredSummer.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		offeredSummer.setBounds(250, 410, 250, 25);
		contentPane.add(offeredSummer);

		headingLabel = new JLabel("Course Prereqs");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 440, 100, 25);
		contentPane.add(headingLabel);
		
		coursePrereqs = new JTextField();
		coursePrereqs.setText(tokens[8]);
		coursePrereqs.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		coursePrereqs.setBounds(250, 440, 250, 25);
		contentPane.add(coursePrereqs);
		
		headingLabel = new JLabel("Teachers");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 470, 100, 25);
		contentPane.add(headingLabel);
		
		teachers = new JTextField();
		teachers.setText(tokens[9]);
		teachers.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		teachers.setBounds(250, 470, 250, 25);
		contentPane.add(teachers);
		
		JButton saveButton = new JButton("Update");
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		saveButton.setBounds(250, 525, 100, 40);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(400, 525, 100, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Add Course")){
			addNewCours(currentContentPane);
		}
		if(e.getActionCommand().equalsIgnoreCase("Update Course")){
			if(rowIndexPointer.trim().length()!=0){
				updateCours(currentContentPane,Integer.parseInt(rowIndexPointer));
			}else{
				JOptionPane.showMessageDialog(null, "Please select row of the table.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Delete Course")){
			if(rowIndexPointer.trim().length()!=0){
				ArrayList tempList=new ArrayList();
				ArrayList courseList=service.allOCCoursesORDegreePlan(service.OCCOURSE);
				int selectedRowNumber=Integer.parseInt(rowIndexPointer);
				int poistion=0;
				Iterator iterator=courseList.iterator();
				while(iterator.hasNext()){
					String[] tokens=(String[])iterator.next();
					if(poistion!=selectedRowNumber){
						tempList.add(tokens);
					}
					poistion=poistion+1;
					new OCCourses(currentContentPane,tempList);
				}
			}else{
				JOptionPane.showMessageDialog(null, "Please select row of the table.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Save")){
			if(courseCode!=null && courseName!=null && courseDescription!=null && courseHours!=null && courseCap!=null && offeredFall!=null && offeredSpring!=null && offeredSummer!=null && coursePrereqs!=null && teachers!=null){
				if(courseCode.getText().trim().length()!=0 && courseName.getText().trim().length()!=0 && courseDescription.getText().trim().length()!=0 && courseHours.getText().trim().length()!=0 && courseCap.getText().trim().length()!=0 && offeredFall.getText().trim().length()!=0 && offeredSpring.getText().trim().length()!=0 && offeredSummer.getText().trim().length()!=0 && coursePrereqs.getText().trim().length()!=0 && teachers.getText().trim().length()!=0){
					ArrayList courseList=service.allOCCoursesORDegreePlan(service.OCCOURSE);
					String[] tokens=new String[10];
					tokens[0]=courseCode.getText().trim();
					tokens[1]=courseName.getText().trim();
					tokens[2]=courseDescription.getText().trim();
					tokens[3]=courseHours.getText().trim();
					tokens[4]=courseCap.getText().trim();
					tokens[5]=offeredFall.getText().trim();
					tokens[6]=offeredSpring.getText().trim();
					tokens[7]=offeredSummer.getText().trim();
					tokens[8]=coursePrereqs.getText().trim();
					tokens[9]=teachers.getText().trim();
					courseList.add(tokens);
					new OCCourses(currentContentPane,courseList);
				}else{
					JOptionPane.showMessageDialog(null, "Please enter all add new course details.");
				}
			}else{
				JOptionPane.showMessageDialog(null, "Please enter all add new course details.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Update")){
			if(courseCode!=null && courseName!=null && courseDescription!=null && courseHours!=null && courseCap!=null && offeredFall!=null && offeredSpring!=null && offeredSummer!=null && coursePrereqs!=null && teachers!=null){
				if(courseCode.getText().trim().length()!=0 && courseName.getText().trim().length()!=0 && courseDescription.getText().trim().length()!=0 && courseHours.getText().trim().length()!=0 && courseCap.getText().trim().length()!=0 && offeredFall.getText().trim().length()!=0 && offeredSpring.getText().trim().length()!=0 && offeredSummer.getText().trim().length()!=0 && coursePrereqs.getText().trim().length()!=0 && teachers.getText().trim().length()!=0){
					ArrayList tempList=new ArrayList();
					ArrayList courseList=service.allOCCoursesORDegreePlan(service.OCCOURSE);
					int poistion=0;
					int selectedRowNumber=Integer.parseInt(rowIndexPointer);
					Iterator iterator=courseList.iterator();
					while(iterator.hasNext()){
						String[] tokens=(String[])iterator.next();
						if(poistion==selectedRowNumber){
							tokens=new String[10];
							tokens[0]=courseCode.getText().trim();
							tokens[1]=courseName.getText().trim();
							tokens[2]=courseDescription.getText().trim();
							tokens[3]=courseHours.getText().trim();
							tokens[4]=courseCap.getText().trim();
							tokens[5]=offeredFall.getText().trim();
							tokens[6]=offeredSpring.getText().trim();
							tokens[7]=offeredSummer.getText().trim();
							tokens[8]=coursePrereqs.getText().trim();
							tokens[9]=teachers.getText().trim();
							tempList.add(tokens);
						}else{
							tempList.add(tokens);
						}
						poistion=poistion+1;
					}
					
					new OCCourses(currentContentPane,tempList);
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
