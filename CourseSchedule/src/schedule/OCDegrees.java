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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class OCDegrees implements ActionListener{
	JTextField degreeCode,gradSchool,degreeName,forecast;
	Service service=new Service();
	JPanel currentContentPane;
	String rowIndexPointer="";
	public OCDegrees(){
		
	}
	public OCDegrees(JPanel contentPane,ArrayList tempDegreeList){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("OC DEGREES");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList degreeList=null;
		if(tempDegreeList!=null){
			degreeList=tempDegreeList;
		}else{
			degreeList=service.allOCMaintainData(service.OCSDEGREE);
		}		
		if(degreeList!=null && degreeList.size()!=0){
			int columnsCount=4;
			String degreeColumnNames[] = {"Degree Code", "Grad School", "Degree Name", "Forecast"};
			String degreeRowColumnDataValues[][]=new String[degreeList.size()][columnsCount];
			Iterator iterator=degreeList.iterator();
			int poistion=0;
			while(iterator.hasNext()){
				String[] tokens=(String[])iterator.next();
				degreeRowColumnDataValues[poistion][0]=tokens[0];
				degreeRowColumnDataValues[poistion][1]=tokens[1];
				degreeRowColumnDataValues[poistion][2]=tokens[2];
				degreeRowColumnDataValues[poistion][3]=tokens[3];
				poistion=poistion+1;
			}			
			
			final JTable degreeTable = new JTable(degreeRowColumnDataValues, degreeColumnNames);
		    ListSelectionModel tableRowSelection = degreeTable.getSelectionModel();
		    tableRowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		    tableRowSelection.addListSelectionListener(new ListSelectionListener(){
		    	public void valueChanged(ListSelectionEvent e) {
		    		rowIndexPointer=""+degreeTable.getSelectedRow();
		    	}
		    });	    
			JScrollPane tableScrollPane = new JScrollPane(degreeTable);
			tableScrollPane.setBounds(100, 200, 550, 250);
			contentPane.add(tableScrollPane, BorderLayout.CENTER);
		}
		
		JButton addDegreeButton = new JButton("Add Degree");
		addDegreeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		addDegreeButton.setBounds(700, 200, 200, 40);
		addDegreeButton.addActionListener(this);
		contentPane.add(addDegreeButton);
		
		JButton updateDegreeButton = new JButton("Update Degree");
		updateDegreeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		updateDegreeButton.setBounds(700, 260, 200, 40);
		updateDegreeButton.addActionListener(this);
		contentPane.add(updateDegreeButton);
		
		JButton viewCoursesButton = new JButton("View Courses");
		viewCoursesButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		viewCoursesButton.setBounds(700, 320, 200, 40);
		viewCoursesButton.addActionListener(this);
		contentPane.add(viewCoursesButton);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(700, 380, 200, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void OCDegreeCourses(JPanel contentPane,String degreeCode){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("OC DEGREE COURSES");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList degreePlanList=service.allOCCoursesORDegreePlan(service.OCSDEGREEPLAN);
		if(degreePlanList!=null && degreePlanList.size()!=0){
			ArrayList tempDegreePlanList=new ArrayList();
			Iterator iterator=degreePlanList.iterator();
			while(iterator.hasNext()){
				String[] tokens=(String[])iterator.next();
				if(tokens[0].equalsIgnoreCase(degreeCode)){
					tempDegreePlanList.add(tokens);
				}
			}
			if(tempDegreePlanList!=null && tempDegreePlanList.size()!=0){
				int columnsCount=5;
				String degreePlanColumnNames[] = {"Degree Code", "Description", "Hours", "Type", "Courses"};
				String degreePlanRowColumnDataValues[][]=new String[tempDegreePlanList.size()][columnsCount];
				iterator=tempDegreePlanList.iterator();
				int poistion=0;
				while(iterator.hasNext()){
					String[] tokens=(String[])iterator.next();
					degreePlanRowColumnDataValues[poistion][0]=tokens[0];
					degreePlanRowColumnDataValues[poistion][1]=tokens[1];
					degreePlanRowColumnDataValues[poistion][2]=tokens[2];
					degreePlanRowColumnDataValues[poistion][3]=tokens[3];
					degreePlanRowColumnDataValues[poistion][4]=tokens[4];
					poistion=poistion+1;
				}
				final JTable courseTable = new JTable(degreePlanRowColumnDataValues, degreePlanColumnNames);
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
		}
		
		JButton updateCourseButton = new JButton("Back");
		updateCourseButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		updateCourseButton.setBounds(700, 260, 200, 40);
		updateCourseButton.addActionListener(this);
		contentPane.add(updateCourseButton);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(700, 380, 200, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void addNewDegree(JPanel contentPane){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("ADD NEW DEGREE");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Degree Code");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 100, 25);
		contentPane.add(headingLabel);
		
		degreeCode = new JTextField();
		degreeCode.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		degreeCode.setBounds(250, 200, 250, 25);
		contentPane.add(degreeCode);
		
		headingLabel = new JLabel("Grad School");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 100, 25);
		contentPane.add(headingLabel);
		
		gradSchool = new JTextField();
		gradSchool.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		gradSchool.setBounds(250, 230, 250, 25);
		contentPane.add(gradSchool);
		
		headingLabel = new JLabel("Degree Name");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 125, 25);
		contentPane.add(headingLabel);
		
		degreeName = new JTextField();
		degreeName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		degreeName.setBounds(250, 260, 250, 25);
		contentPane.add(degreeName);

		headingLabel = new JLabel("Forecast");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 290, 100, 25);
		contentPane.add(headingLabel);
		
		forecast = new JTextField();
		forecast.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		forecast.setBounds(250, 290, 250, 25);
		contentPane.add(forecast);
		
		JButton saveButton = new JButton("Save");
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		saveButton.setBounds(250, 350, 100, 40);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(400, 350, 100, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void updatedegree(JPanel contentPane,int rowNumber){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("UPDATE DEGREE");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList degreeList=service.allOCMaintainData(service.OCSDEGREE);
		rowIndexPointer=""+rowNumber;
		String[] tokens=(String[])degreeList.get(rowNumber);
		
		headingLabel = new JLabel("Degree Code");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 100, 25);
		contentPane.add(headingLabel);
		
		degreeCode = new JTextField();
		degreeCode.setText(tokens[0]);
		degreeCode.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		degreeCode.setBounds(250, 200, 250, 25);
		contentPane.add(degreeCode);
		
		headingLabel = new JLabel("Grad School");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 100, 25);
		contentPane.add(headingLabel);
		
		gradSchool = new JTextField();
		gradSchool.setText(tokens[1]);
		gradSchool.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		gradSchool.setBounds(250, 230, 250, 25);
		contentPane.add(gradSchool);
		
		headingLabel = new JLabel("Degree Name");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 125, 25);
		contentPane.add(headingLabel);
		
		degreeName = new JTextField();
		degreeName.setText(tokens[2]);
		degreeName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		degreeName.setBounds(250, 260, 250, 25);
		contentPane.add(degreeName);

		headingLabel = new JLabel("Forecast");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 290, 100, 25);
		contentPane.add(headingLabel);
		
		forecast = new JTextField();
		forecast.setText(tokens[3]);
		forecast.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		forecast.setBounds(250, 290, 250, 25);
		contentPane.add(forecast);
		
		JButton saveButton = new JButton("Update");
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		saveButton.setBounds(250, 350, 100, 40);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(400, 350, 100, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Add Degree")){
			addNewDegree(currentContentPane);
		}
		if(e.getActionCommand().equalsIgnoreCase("Update Degree")){
			if(rowIndexPointer.trim().length()!=0){
				updatedegree(currentContentPane,Integer.parseInt(rowIndexPointer));
			}else{
				JOptionPane.showMessageDialog(null, "Please select row of the table.");
			}			
		}
		if(e.getActionCommand().equalsIgnoreCase("View Courses")){
			if(rowIndexPointer.trim().length()!=0){
				ArrayList degreeList=service.allOCCoursesORDegreePlan(service.OCSDEGREE);
				String[] tokens=(String[])degreeList.get(Integer.parseInt(rowIndexPointer));
				OCDegreeCourses(currentContentPane,tokens[0]);
			}else{
				JOptionPane.showMessageDialog(null, "Please select row of the table.");
			}			
		}
		if(e.getActionCommand().equalsIgnoreCase("Save")){
			if(degreeCode!=null && gradSchool!=null && degreeName!=null && forecast!=null){
				if(degreeCode.getText().trim().length()!=0 && gradSchool.getText().trim().length()!=0 && degreeName.getText().trim().length()!=0 && forecast.getText().trim().length()!=0){
					ArrayList degreeList=service.allOCCoursesORDegreePlan(service.OCSDEGREE);
					String[] tokens=new String[10];
					tokens[0]=degreeCode.getText().trim();
					tokens[1]=gradSchool.getText().trim();
					tokens[2]=degreeName.getText().trim();
					tokens[3]=forecast.getText().trim();
					degreeList.add(tokens);
					new OCDegrees(currentContentPane,degreeList);
				}else{
					JOptionPane.showMessageDialog(null, "Please enter all add new course details.");
				}
			}else{
				JOptionPane.showMessageDialog(null, "Please enter all add new course details.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Update")){
			if(degreeCode!=null && gradSchool!=null && degreeName!=null && forecast!=null){
				if(degreeCode.getText().trim().length()!=0 && gradSchool.getText().trim().length()!=0 && degreeName.getText().trim().length()!=0 && forecast.getText().trim().length()!=0){
					ArrayList tempList=new ArrayList();
					ArrayList degreeList=service.allOCCoursesORDegreePlan(service.OCSDEGREE);
					int poistion=0;
					int selectedRowNumber=Integer.parseInt(rowIndexPointer);
					Iterator iterator=degreeList.iterator();
					while(iterator.hasNext()){
						String[] tokens=(String[])iterator.next();
						if(poistion==selectedRowNumber){
							tokens=new String[10];
							tokens[0]=degreeCode.getText().trim();
							tokens[1]=gradSchool.getText().trim();
							tokens[2]=degreeName.getText().trim();
							tokens[3]=forecast.getText().trim();
							tempList.add(tokens);
						}else{
							tempList.add(tokens);
						}
						poistion=poistion+1;
					}					
					new OCDegrees(currentContentPane,tempList);
				}else{
					JOptionPane.showMessageDialog(null, "Please enter all add new course details.");
				}
			}else{
				JOptionPane.showMessageDialog(null, "Please enter all add new course details.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Back")){
			new OCDegrees(currentContentPane,null);
		}
		if(e.getActionCommand().equalsIgnoreCase("Exit")){
			System.exit(1);
		}
    }
}
