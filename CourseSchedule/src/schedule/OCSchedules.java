package schedule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class OCSchedules implements ActionListener{
	Service service=new Service();
	JPanel currentContentPane;
	String selectedSemester="2016FA";
	public OCSchedules(){
		
	}
	public OCSchedules(JPanel contentPane,ArrayList generateScheduleList){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("OC SCHEDULE");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		if(generateScheduleList!=null && generateScheduleList.size()!=0){
			int columnsCount=5;
			String scheduleColumnNames[] = {"Term", "Title", "Meeting","Faculty", "Occupy Students"};
			String scheduleRowColumnDataValues[][]=new String[generateScheduleList.size()][columnsCount];
			Iterator iterator=generateScheduleList.iterator();
			int poistion=0;
			while(iterator.hasNext()){
				String[] tokens=(String[])iterator.next();
				scheduleRowColumnDataValues[poistion][0]=tokens[0];
				scheduleRowColumnDataValues[poistion][1]=tokens[1];
				scheduleRowColumnDataValues[poistion][2]=tokens[2];
				scheduleRowColumnDataValues[poistion][3]=tokens[3];
				scheduleRowColumnDataValues[poistion][4]=tokens[4];
				poistion=poistion+1;
			}
			final JTable scheduleTable = new JTable(scheduleRowColumnDataValues, scheduleColumnNames);
		    JScrollPane tableScrollPane = new JScrollPane(scheduleTable);
			tableScrollPane.setBounds(100, 200, 550, 250);
			contentPane.add(tableScrollPane, BorderLayout.CENTER);
		}		
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(700, 380, 200, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void testSchedule(JPanel contentPane,HashMap generateTestScheduleList,String selectedSemester){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("Test Schedule");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		int courses=0;int totalStudentsCount=generateTestScheduleList.size();
		for (Object key : generateTestScheduleList.keySet()) {
			if(((ArrayList)generateTestScheduleList.get(key))!=null && ((ArrayList)generateTestScheduleList.get(key)).size()<10){
				courses++;
			}else if(((ArrayList)generateTestScheduleList.get(key))==null){
				courses++;
			}
		}
		ArrayList generateScheduleList=service.generateSchedule(selectedSemester); 
		headingLabel = new JLabel("Total Student");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 245, 25);
		contentPane.add(headingLabel);
		
		JTextField totalStudents = new JTextField();
		totalStudents.setText(""+totalStudentsCount);
		totalStudents.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		totalStudents.setBounds(350, 200, 50, 25);
		contentPane.add(totalStudents);
		
		headingLabel = new JLabel("Students Required Courses");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 245, 25);
		contentPane.add(headingLabel);
		
		JTextField studentReq = new JTextField();
		studentReq.setText(""+courses);
		studentReq.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		studentReq.setBounds(350, 230, 50, 25);
		contentPane.add(studentReq);
		
		headingLabel = new JLabel("Students with out Required Courses");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 245, 25);
		contentPane.add(headingLabel);
		
		studentReq = new JTextField();
		studentReq.setText(""+(totalStudentsCount-courses));
		studentReq.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		studentReq.setBounds(350, 260, 50, 25);
		contentPane.add(studentReq);
		
		headingLabel = new JLabel("Number of Sections");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 290, 245, 25);
		contentPane.add(headingLabel);
		
		studentReq = new JTextField();
		studentReq.setText(""+generateScheduleList.size());
		studentReq.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		studentReq.setBounds(350, 290, 50, 25);
		contentPane.add(studentReq);
		
		int moreOccupy=0,lessOccupy=0;
		for(int i=0;i<generateScheduleList.size();i++){
			String[] schedule=(String[])generateScheduleList.get(i);
			if(Integer.parseInt(schedule[4].replaceAll("25/", ""))>25){
				moreOccupy++;
			}
			if(Integer.parseInt(schedule[4].replaceAll("25/", ""))<=25){
				lessOccupy++;
			}
		}
		
		headingLabel = new JLabel("Number of Sections above capacity");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 320, 245, 25);
		contentPane.add(headingLabel);
		
		studentReq = new JTextField();
		studentReq.setText(""+moreOccupy);
		studentReq.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		studentReq.setBounds(350, 320, 50, 25);
		contentPane.add(studentReq);
		
		headingLabel = new JLabel("Number of Sections below fill percent");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 350, 245, 25);
		contentPane.add(headingLabel);
		
		studentReq = new JTextField();
		studentReq.setText(""+lessOccupy);
		studentReq.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		studentReq.setBounds(350, 350, 50, 25);
		contentPane.add(studentReq);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(400, 525, 100, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void generateSchedule(JPanel contentPane){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("GENERATE SCHEDULE");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Semester");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		headingLabel.setBounds(175, 225, 100, 30);
		contentPane.add(headingLabel);
		
		String facultyLabels[] = {"2016FA", "2016SU", "2016SP", "2017FA", "2017SU", "2017SP","2018FA", "2018SU", "2018SP","2019FA", "2019SU", "2019SP"};
		final JComboBox facultyComboBox = new JComboBox(facultyLabels);
		facultyComboBox.setMaximumRowCount(5);
		facultyComboBox.setSelectedIndex(0);
		facultyComboBox.setBounds(280, 225, 100, 40);
		facultyComboBox.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 if(facultyComboBox.getSelectedIndex() != -1) {                     
	        		selectedSemester=(String)facultyComboBox.getItemAt(facultyComboBox.getSelectedIndex());
	        	 }
	         }
		});
		contentPane.add(facultyComboBox);
		
		JButton generateButton = new JButton("Generate Schedule");
		generateButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		generateButton.setBounds(150, 400, 200, 40);
		generateButton.addActionListener(this);
		contentPane.add(generateButton);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(375, 400, 100, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void testSchedule(JPanel contentPane){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("TEST SCHEDULE");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Semester");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		headingLabel.setBounds(175, 225, 100, 30);
		contentPane.add(headingLabel);
		
		String facultyLabels[] = {"2016FA", "2016SU", "2016SP", "2017FA", "2017SU", "2017SP","2018FA", "2018SU", "2018SP","2019FA", "2019SU", "2019SP"};
		final JComboBox facultyComboBox = new JComboBox(facultyLabels);
		facultyComboBox.setMaximumRowCount(5);
		facultyComboBox.setSelectedIndex(0);
		facultyComboBox.setBounds(280, 225, 100, 40);
		facultyComboBox.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 if(facultyComboBox.getSelectedIndex() != -1) {                     
	        		selectedSemester=(String)facultyComboBox.getItemAt(facultyComboBox.getSelectedIndex());
	        	 }
	         }
		});
		contentPane.add(facultyComboBox);
		
		JButton testButton = new JButton("Test Schedule");
		testButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		testButton.setBounds(150, 400, 150, 40);
		testButton.addActionListener(this);
		contentPane.add(testButton);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(325, 400, 100, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Generate Schedule")){
			if(selectedSemester.trim().length()!=0){
				ArrayList generateScheduleList=service.generateSchedule(selectedSemester);
				new OCSchedules(currentContentPane,generateScheduleList);
			}else{
				JOptionPane.showMessageDialog(null, "Please select semester.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Test Schedule")){
			HashMap yearWiseStudents=service.yearWiseStudents(selectedSemester,"MSE.ECE","MSE.ENGMGT","MSE.ECE, MSE.ENGMGT");
			testSchedule(currentContentPane,yearWiseStudents,selectedSemester);
		}
		if(e.getActionCommand().equalsIgnoreCase("Exit")){
			System.exit(1);
		}
    }
}
