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

public class OCSemesters implements ActionListener{
	JTextField semester,startDate,endDate;
	Service service=new Service();
	JPanel currentContentPane;
	String rowIndexPointer="";
	public OCSemesters(JPanel contentPane,ArrayList tempSemesterList){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("OC SEMESTERS");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList semesterList=null;
		if(tempSemesterList!=null){
			semesterList=tempSemesterList;
		}else{
			semesterList=service.allOCMaintainData(service.OCSEMESTER);
		}
		if(semesterList!=null && semesterList.size()!=0){
			int columnsCount=3;
			String semesterColumnNames[] = {"Semester", "Start Date", "End Date"};
			String semesterRowColumnDataValues[][]=new String[semesterList.size()][columnsCount];
			Iterator iterator=semesterList.iterator();
			int poistion=0;
			while(iterator.hasNext()){
				String[] tokens=(String[])iterator.next();
				semesterRowColumnDataValues[poistion][0]=tokens[0];
				semesterRowColumnDataValues[poistion][1]=tokens[1];
				semesterRowColumnDataValues[poistion][2]=tokens[2];
				poistion=poistion+1;
			}
			final JTable semesterTable = new JTable(semesterRowColumnDataValues, semesterColumnNames);
		    ListSelectionModel tableRowSelection = semesterTable.getSelectionModel();
		    tableRowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		    tableRowSelection.addListSelectionListener(new ListSelectionListener(){
		    	public void valueChanged(ListSelectionEvent e) {
		    		rowIndexPointer=""+semesterTable.getSelectedRow();
		    	}
		    });	    
			JScrollPane tableScrollPane = new JScrollPane(semesterTable);
			tableScrollPane.setBounds(100, 200, 550, 250);
			contentPane.add(tableScrollPane, BorderLayout.CENTER);
		}
				
		JButton addSemesterButton = new JButton("Add Semester");
		addSemesterButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		addSemesterButton.setBounds(700, 200, 200, 40);
		addSemesterButton.addActionListener(this);
		contentPane.add(addSemesterButton);
		
		JButton deleteButton = new JButton("Delete Semester");
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
	
	public void addNewSemester(JPanel contentPane){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("ADD NEW SEMESTER");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Semester");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 100, 25);
		contentPane.add(headingLabel);
		
		semester = new JTextField();
		semester.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		semester.setBounds(250, 200, 250, 25);
		contentPane.add(semester);
		
		headingLabel = new JLabel("Start Date");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 100, 25);
		contentPane.add(headingLabel);
		
		startDate = new JTextField();
		startDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		startDate.setBounds(250, 230, 250, 25);
		contentPane.add(startDate);
		
		headingLabel = new JLabel("End Date");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 125, 25);
		contentPane.add(headingLabel);
		
		endDate = new JTextField();
		endDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		endDate.setBounds(250, 260, 250, 25);
		contentPane.add(endDate);

		JButton saveButton = new JButton("Save");
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		saveButton.setBounds(250, 300, 100, 40);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(400, 300, 100, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Add Semester")){
			addNewSemester(currentContentPane);
		}
		if(e.getActionCommand().equalsIgnoreCase("Delete Semester")){
			if(rowIndexPointer.trim().length()!=0){
				ArrayList tempList=new ArrayList();
				ArrayList semesterList=service.allOCCoursesORDegreePlan(service.OCSEMESTER);
				int selectedRowNumber=Integer.parseInt(rowIndexPointer);
				int poistion=0;
				Iterator iterator=semesterList.iterator();
				while(iterator.hasNext()){
					String[] tokens=(String[])iterator.next();
					if(poistion!=selectedRowNumber){
						tempList.add(tokens);
					}
					poistion=poistion+1;
					new OCSemesters(currentContentPane,tempList);
				}
			}else{
				JOptionPane.showMessageDialog(null, "Please select row of the table.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Save")){
			if(semester!=null && startDate!=null && endDate!=null){
				if(semester.getText().trim().length()!=0 && startDate.getText().trim().length()!=0 && endDate.getText().trim().length()!=0){
					ArrayList semesterList=service.allOCCoursesORDegreePlan(service.OCSEMESTER);
					String[] tokens=new String[10];
					tokens[0]=semester.getText().trim();
					tokens[1]=startDate.getText().trim();
					tokens[2]=endDate.getText().trim();
					semesterList.add(tokens);
					new OCSemesters(currentContentPane,semesterList);
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
