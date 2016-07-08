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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class OCReports implements ActionListener{
	Service service=new Service();
	JPanel currentContentPane;
	String selectedSemester;
	public OCReports(){
		
	}
	public void studentCourseReport(JPanel contentPane){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("OC STUDENT COURSE REPORT");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		HashMap studentDumpMap=service.studentDumpMap();
		HashMap studentCourseDumpMap=service.studentCourseDumpMap();
		if(studentDumpMap!=null && studentCourseDumpMap!=null && studentCourseDumpMap.size()!=0){
			int columnsCount=4;
			String studentColumnNames[] = {"Student Id","Degree Code" ,"Studied Coursed", "Futur Study Courese"};
			String studentRowColumnDataValues[][]=new String[studentCourseDumpMap.size()][columnsCount];
			Iterator itr2 = studentCourseDumpMap.keySet().iterator();
			int poistion=0;
			int totalCourses=10;
			while(itr2.hasNext()){
			    String key = (String)itr2.next();
			    studentRowColumnDataValues[poistion][0]=key;
				studentRowColumnDataValues[poistion][1]=(String)studentDumpMap.get(key);
				if((ArrayList)studentCourseDumpMap.get(key)!=null){
					studentRowColumnDataValues[poistion][2]=""+((ArrayList)studentCourseDumpMap.get(key)).size();
				}else{
					studentRowColumnDataValues[poistion][2]="0";
				}
				if((ArrayList)studentCourseDumpMap.get(key)==null){
					studentRowColumnDataValues[poistion][3]="10";
				}else if(((ArrayList)studentCourseDumpMap.get(key)).size()>10){
					studentRowColumnDataValues[poistion][3]=""+((ArrayList)studentCourseDumpMap.get(key)).size();
				}else{
					studentRowColumnDataValues[poistion][3]=""+(totalCourses-((ArrayList)studentCourseDumpMap.get(key)).size());
				}
				poistion=poistion+1;
			}
			final JTable studentTable = new JTable(studentRowColumnDataValues, studentColumnNames);
		    JScrollPane tableScrollPane = new JScrollPane(studentTable);
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
	
	public void degreePlanReport(JPanel contentPane){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("OC DEGREE PLAN REPORT");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		HashMap degreeStudentsMap=service.studentDegreeDumpMap();
		HashMap degreeForecastMap=service.degreeForecastMap();
		if(degreeForecastMap!=null && degreeStudentsMap!=null && degreeStudentsMap.size()!=0){
			int columnsCount=3;
			String degreeColumnNames[] = {"Degree Code","Current Students" ,"Future Students"};
			String degreeRowColumnDataValues[][]=new String[degreeStudentsMap.size()][columnsCount];
			Iterator itr2 = degreeStudentsMap.keySet().iterator();
			int poistion=0;
			int totalCourses=10;
			while(itr2.hasNext()){
			    String key = (String)itr2.next();
			    degreeRowColumnDataValues[poistion][0]=key;
			    degreeRowColumnDataValues[poistion][1]=""+((ArrayList)degreeStudentsMap.get(key)).size();
			    if(degreeForecastMap.get(key)!=null){
			    	degreeRowColumnDataValues[poistion][2]=""+degreeForecastMap.get(key);
			    }else{
			    	degreeRowColumnDataValues[poistion][2]="0";
			    }
			    poistion=poistion+1;
			}
			final JTable degreeTable = new JTable(degreeRowColumnDataValues, degreeColumnNames);
		    JScrollPane tableScrollPane = new JScrollPane(degreeTable);
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
	
	public void scheduleReport(JPanel contentPane){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("OC SCHEDULE REPORT");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList generateScheduleList=service.generateSchedule("2016FA");
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
	
	public void sectionReport(JPanel contentPane){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("OC SECTIONS REPORT");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList generateScheduleList=service.generateSchedule("2016FA");
		if(generateScheduleList!=null && generateScheduleList.size()!=0){
			int columnsCount=3;
			String sectionColumnNames[] = { "Course", "Faculty", "Number of Students"};
			String sectionRowColumnDataValues[][]=new String[generateScheduleList.size()][columnsCount];
			Iterator iterator=generateScheduleList.iterator();
			int poistion=0;
			while(iterator.hasNext()){
				String[] tokens=(String[])iterator.next();
				sectionRowColumnDataValues[poistion][0]=tokens[1];
				sectionRowColumnDataValues[poistion][1]=tokens[3];
				sectionRowColumnDataValues[poistion][2]=tokens[4];
				poistion=poistion+1;
			}
			final JTable sectionTable = new JTable(sectionRowColumnDataValues, sectionColumnNames);
			JScrollPane tableScrollPane = new JScrollPane(sectionTable);
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
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Exit")){
			System.exit(1);
		}
    }
}
