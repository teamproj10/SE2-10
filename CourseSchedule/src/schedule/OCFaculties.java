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

public class OCFaculties implements ActionListener{
	JTextField lastName,firstName,gradSchool,degree,title,daysToTeach,maxLoadFall,maxLoadSpring,maxLoadSummer;
	Service service=new Service();
	JPanel currentContentPane;
	String rowIndexPointer="";
	public OCFaculties(JPanel contentPane,ArrayList tempFacultiesList){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("OC FACULTIES");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList facultyList=null;
		if(tempFacultiesList!=null){
			facultyList=tempFacultiesList;
		}else{
			facultyList=service.allOCMaintainData(service.OCFACULTY);
		} 
		if(facultyList!=null && facultyList.size()!=0){
			int columnsCount=9;
			String facultyColumnNames[] = {"Last Name", "First Name", "Grad School","Degree", "Title", "Days To Teach?","Max Load Fall", "Max Load Spring?", "Max Load Summer"};
			String facultyRowColumnDataValues[][]=new String[facultyList.size()][columnsCount];
			Iterator iterator=facultyList.iterator();
			int poistion=0;
			while(iterator.hasNext()){
				String[] tokens=(String[])iterator.next();
				facultyRowColumnDataValues[poistion][0]=tokens[0];
				facultyRowColumnDataValues[poistion][1]=tokens[1];
				facultyRowColumnDataValues[poistion][2]=tokens[2];
				facultyRowColumnDataValues[poistion][3]=tokens[3];
				facultyRowColumnDataValues[poistion][4]=tokens[4];
				facultyRowColumnDataValues[poistion][5]=tokens[5];
				facultyRowColumnDataValues[poistion][6]=tokens[6];
				facultyRowColumnDataValues[poistion][7]=tokens[7];
				facultyRowColumnDataValues[poistion][8]=tokens[8];
				poistion=poistion+1;
			}
			final JTable facultyTable = new JTable(facultyRowColumnDataValues, facultyColumnNames);
		    ListSelectionModel tableRowSelection = facultyTable.getSelectionModel();
		    tableRowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		    tableRowSelection.addListSelectionListener(new ListSelectionListener(){
		    	public void valueChanged(ListSelectionEvent e) {
		    		rowIndexPointer=""+facultyTable.getSelectedRow();
		    	}
		    });	    
			JScrollPane tableScrollPane = new JScrollPane(facultyTable);
			tableScrollPane.setBounds(100, 200, 550, 250);
			contentPane.add(tableScrollPane, BorderLayout.CENTER);
		}
		
		JButton addFacultyButton = new JButton("Add Faculty");
		addFacultyButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		addFacultyButton.setBounds(700, 200, 200, 40);
		addFacultyButton.addActionListener(this);
		contentPane.add(addFacultyButton);
		
		JButton updateFacultyButton = new JButton("Update Faculty");
		updateFacultyButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		updateFacultyButton.setBounds(700, 260, 200, 40);
		updateFacultyButton.addActionListener(this);
		contentPane.add(updateFacultyButton);
		
		JButton deleteButton = new JButton("Delete Faculty");
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
	
	public void addNewFaculty(JPanel contentPane){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("ADD NEW FACULTY");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Last Name");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 100, 25);
		contentPane.add(headingLabel);
		
		lastName = new JTextField();
		lastName.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		lastName.setBounds(250, 200, 250, 25);
		contentPane.add(lastName);
		
		headingLabel = new JLabel("First Name");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 100, 25);
		contentPane.add(headingLabel);
		
		firstName = new JTextField();
		firstName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		firstName.setBounds(250, 230, 250, 25);
		contentPane.add(firstName);
		
		headingLabel = new JLabel("Grad School");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 125, 25);
		contentPane.add(headingLabel);
		
		gradSchool = new JTextField();
		gradSchool.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		gradSchool.setBounds(250, 260, 250, 25);
		contentPane.add(gradSchool);

		headingLabel = new JLabel("Degree");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 290, 100, 25);
		contentPane.add(headingLabel);
		
		degree = new JTextField();
		degree.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		degree.setBounds(250, 290, 250, 25);
		contentPane.add(degree);
		
		headingLabel = new JLabel("Title");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 320, 100, 25);
		contentPane.add(headingLabel);
		
		title = new JTextField();
		title.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		title.setBounds(250, 320, 250, 25);
		contentPane.add(title);
		
		headingLabel = new JLabel("Days To Teach");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 350, 120, 25);
		contentPane.add(headingLabel);
		
		daysToTeach = new JTextField();
		daysToTeach.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		daysToTeach.setBounds(250, 350, 250, 25);
		contentPane.add(daysToTeach);
		
		headingLabel = new JLabel("Max Load Fall");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 380, 125, 25);
		contentPane.add(headingLabel);
		
		maxLoadFall = new JTextField();
		maxLoadFall.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		maxLoadFall.setBounds(250, 380, 250, 25);
		contentPane.add(maxLoadFall);
		
		headingLabel = new JLabel("Max Load Spring");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 410, 125, 25);
		contentPane.add(headingLabel);
		
		maxLoadSpring = new JTextField();
		maxLoadSpring.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		maxLoadSpring.setBounds(250, 410, 250, 25);
		contentPane.add(maxLoadSpring);

		headingLabel = new JLabel("Max Load Summer");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 440, 120, 25);
		contentPane.add(headingLabel);
		
		maxLoadSummer = new JTextField();
		maxLoadSummer.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		maxLoadSummer.setBounds(250, 440, 250, 25);
		contentPane.add(maxLoadSummer);
		
		JButton saveButton = new JButton("Save");
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		saveButton.setBounds(250, 475, 100, 40);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(400, 475, 100, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void updateFaculty(JPanel contentPane,int rowNumber){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("UPDATE FACULTY");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList facultyList=service.allOCCoursesORDegreePlan(service.OCFACULTY);
		rowIndexPointer=""+rowNumber;
		String[] tokens=(String[])facultyList.get(rowNumber);
		
		headingLabel = new JLabel("Last Name");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 100, 25);
		contentPane.add(headingLabel);
		
		lastName = new JTextField();
		lastName.setText(tokens[0]);
		lastName.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		lastName.setBounds(250, 200, 250, 25);
		contentPane.add(lastName);
		
		headingLabel = new JLabel("First Name");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 100, 25);
		contentPane.add(headingLabel);
		
		firstName = new JTextField();
		firstName.setText(tokens[1]);
		firstName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		firstName.setBounds(250, 230, 250, 25);
		contentPane.add(firstName);
		
		headingLabel = new JLabel("Grad School");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 125, 25);
		contentPane.add(headingLabel);
		
		gradSchool = new JTextField();
		gradSchool.setText(tokens[2]);
		gradSchool.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		gradSchool.setBounds(250, 260, 250, 25);
		contentPane.add(gradSchool);

		headingLabel = new JLabel("Degree");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 290, 100, 25);
		contentPane.add(headingLabel);
		
		degree = new JTextField();
		degree.setText(tokens[3]);
		degree.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		degree.setBounds(250, 290, 250, 25);
		contentPane.add(degree);
		
		headingLabel = new JLabel("Title");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 320, 100, 25);
		contentPane.add(headingLabel);
		
		title = new JTextField();
		title.setText(tokens[4]);
		title.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		title.setBounds(250, 320, 250, 25);
		contentPane.add(title);
		
		headingLabel = new JLabel("Days To Teach");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 350, 120, 25);
		contentPane.add(headingLabel);
		
		daysToTeach = new JTextField();
		daysToTeach.setText(tokens[5]);
		daysToTeach.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		daysToTeach.setBounds(250, 350, 250, 25);
		contentPane.add(daysToTeach);
		
		headingLabel = new JLabel("Max Load Fall");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 380, 125, 25);
		contentPane.add(headingLabel);
		
		maxLoadFall = new JTextField();
		maxLoadFall.setText(tokens[6]);
		maxLoadFall.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		maxLoadFall.setBounds(250, 380, 250, 25);
		contentPane.add(maxLoadFall);
		
		headingLabel = new JLabel("Max Load Spring");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 410, 125, 25);
		contentPane.add(headingLabel);
		
		maxLoadSpring = new JTextField();
		maxLoadSpring.setText(tokens[7]);
		maxLoadSpring.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		maxLoadSpring.setBounds(250, 410, 250, 25);
		contentPane.add(maxLoadSpring);

		headingLabel = new JLabel("Max Load Summer");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 440, 120, 25);
		contentPane.add(headingLabel);
		
		maxLoadSummer = new JTextField();
		maxLoadSummer.setText(tokens[8]);
		maxLoadSummer.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		maxLoadSummer.setBounds(250, 440, 250, 25);
		contentPane.add(maxLoadSummer);
		
		JButton saveButton = new JButton("Update");
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		saveButton.setBounds(250, 475, 100, 40);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(400, 475, 100, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Add Faculty")){
			addNewFaculty(currentContentPane);
		}
		if(e.getActionCommand().equalsIgnoreCase("Update Faculty")){
			if(rowIndexPointer.trim().length()!=0){
				updateFaculty(currentContentPane,Integer.parseInt(rowIndexPointer));
			}else{
				JOptionPane.showMessageDialog(null, "Please select row of the table.");
			}			
		}
		if(e.getActionCommand().equalsIgnoreCase("Delete Faculty")){
			if(rowIndexPointer.trim().length()!=0){
				ArrayList tempList=new ArrayList();
				ArrayList facultyList=service.allOCCoursesORDegreePlan(service.OCFACULTY);
				int selectedRowNumber=Integer.parseInt(rowIndexPointer);
				int poistion=0;
				Iterator iterator=facultyList.iterator();
				while(iterator.hasNext()){
					String[] tokens=(String[])iterator.next();
					if(poistion!=selectedRowNumber){
						tempList.add(tokens);
					}
					poistion=poistion+1;
					new OCFaculties(currentContentPane,tempList);
				}
			}else{
				JOptionPane.showMessageDialog(null, "Please select row of the table.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Save")){			
			if(lastName!=null && firstName!=null && gradSchool!=null && degree!=null && title!=null && daysToTeach!=null && maxLoadFall!=null && maxLoadSummer!=null && maxLoadSpring!=null){
				if(lastName.getText().trim().length()!=0 && firstName.getText().trim().length()!=0 && gradSchool.getText().trim().length()!=0 && degree.getText().trim().length()!=0 && title.getText().trim().length()!=0 && daysToTeach.getText().trim().length()!=0 && maxLoadFall.getText().trim().length()!=0 && maxLoadSummer.getText().trim().length()!=0 && maxLoadSpring.getText().trim().length()!=0){
					ArrayList facultyList=service.allOCCoursesORDegreePlan(service.OCFACULTY);
					String[] tokens=new String[9];
					tokens[0]=lastName.getText().trim();
					tokens[1]=firstName.getText().trim();
					tokens[2]=gradSchool.getText().trim();
					tokens[3]=degree.getText().trim();
					tokens[4]=title.getText().trim();
					tokens[5]=daysToTeach.getText().trim();
					tokens[6]=maxLoadFall.getText().trim();
					tokens[7]=maxLoadSummer.getText().trim();
					tokens[8]=maxLoadSpring.getText().trim();
					facultyList.add(tokens);
					new OCFaculties(currentContentPane,facultyList);
				}else{
					JOptionPane.showMessageDialog(null, "Please enter all add new course details.");
				}
			}else{
				JOptionPane.showMessageDialog(null, "Please enter all add new course details.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Update")){
			if(lastName!=null && firstName!=null && gradSchool!=null && degree!=null && title!=null && daysToTeach!=null && maxLoadFall!=null && maxLoadSummer!=null && maxLoadSpring!=null){
				if(lastName.getText().trim().length()!=0 && firstName.getText().trim().length()!=0 && gradSchool.getText().trim().length()!=0 && degree.getText().trim().length()!=0 && title.getText().trim().length()!=0 && daysToTeach.getText().trim().length()!=0 && maxLoadFall.getText().trim().length()!=0 && maxLoadSummer.getText().trim().length()!=0 && maxLoadSpring.getText().trim().length()!=0){
					ArrayList tempList=new ArrayList();
					ArrayList facultyList=service.allOCCoursesORDegreePlan(service.OCFACULTY);
					int poistion=0;
					int selectedRowNumber=Integer.parseInt(rowIndexPointer);
					Iterator iterator=facultyList.iterator();
					while(iterator.hasNext()){
						String[] tokens=(String[])iterator.next();
						if(poistion==selectedRowNumber){
							tokens=new String[9];
							tokens[0]=lastName.getText().trim();
							tokens[1]=firstName.getText().trim();
							tokens[2]=gradSchool.getText().trim();
							tokens[3]=degree.getText().trim();
							tokens[4]=title.getText().trim();
							tokens[5]=daysToTeach.getText().trim();
							tokens[6]=maxLoadFall.getText().trim();
							tokens[7]=maxLoadSummer.getText().trim();
							tokens[8]=maxLoadSpring.getText().trim();
							tempList.add(tokens);
						}else{
							tempList.add(tokens);
						}
						poistion=poistion+1;
					}
					
					new OCFaculties(currentContentPane,tempList);
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
