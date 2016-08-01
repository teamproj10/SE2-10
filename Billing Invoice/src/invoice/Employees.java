package invoice;

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

public class Employees implements ActionListener{
	JTextField name,title,billrate,role;
	DBServices service=new DBServices();
	JPanel currentContentPane;
	String rowIndexPointer="";
	User tempUser=null;
	public Employees(JPanel contentPane,ArrayList tempemployeeList,User user){
		new Menu(contentPane,user);		
		tempUser=user;
		JLabel headingLabel = new JLabel("VIEW EMPLOYEES");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList employeeList=service.getAllEmployees();
		int columnsCount=4;
		String employeeColumnNames[] = {"Name", "Title", "Bill Rate","Role"};
		String employeeRowColumnDataValues[][]=new String[0][columnsCount];
		if(employeeList!=null && employeeList.size()!=0){
			employeeRowColumnDataValues=new String[employeeList.size()][columnsCount];
			Iterator iterator=employeeList.iterator();
			int poistion=0;
			while(iterator.hasNext()){
				String[] tokens=(String[])iterator.next();
				employeeRowColumnDataValues[poistion][0]=tokens[0];
				employeeRowColumnDataValues[poistion][1]=tokens[1];
				employeeRowColumnDataValues[poistion][2]=tokens[2];
				employeeRowColumnDataValues[poistion][3]=tokens[3];
				poistion=poistion+1;
			}			
		}
		final JTable employeeTable = new JTable(employeeRowColumnDataValues, employeeColumnNames);
	    ListSelectionModel tableRowSelection = employeeTable.getSelectionModel();
	    tableRowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    tableRowSelection.addListSelectionListener(new ListSelectionListener(){
	    	public void valueChanged(ListSelectionEvent e) {
	    		rowIndexPointer=""+employeeTable.getSelectedRow();
	    	}
	    });	    
		JScrollPane tableScrollPane = new JScrollPane(employeeTable);
		tableScrollPane.setBounds(100, 200, 550, 250);
		contentPane.add(tableScrollPane, BorderLayout.CENTER);
		
		if(user!=null && user.getUserrole().equalsIgnoreCase("Accountant")){
			JButton addemployeeButton = new JButton("Add Employee");
			addemployeeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
			addemployeeButton.setBounds(700, 200, 200, 40);
			addemployeeButton.addActionListener(this);
			contentPane.add(addemployeeButton);
		}
		
		JButton updateemployeeButton = new JButton("Update Employee");
		updateemployeeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		updateemployeeButton.setBounds(700, 260, 200, 40);
		updateemployeeButton.addActionListener(this);
		contentPane.add(updateemployeeButton);
		
		JButton deleteButton = new JButton("InActive Employees");
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
	
	public void addNewEmployee(JPanel contentPane,User user){
		new Menu(contentPane,user);		
		
		JLabel headingLabel = new JLabel("ADD NEW EMPLOYEE");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Name");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 100, 25);
		contentPane.add(headingLabel);
		
		name = new JTextField();
		name.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		name.setBounds(250, 200, 250, 25);
		contentPane.add(name);
		
		headingLabel = new JLabel("Title");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 100, 25);
		contentPane.add(headingLabel);
		
		title = new JTextField();
		title.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		title.setBounds(250, 230, 250, 25);
		contentPane.add(title);
		
		headingLabel = new JLabel("Bill Rate");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 100, 25);
		contentPane.add(headingLabel);
		
		billrate = new JTextField();
		billrate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		billrate.setBounds(250, 260, 250, 25);
		contentPane.add(billrate);
		
		headingLabel = new JLabel("Role");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 290, 125, 25);
		contentPane.add(headingLabel);
		
		role = new JTextField();
		role.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		role.setBounds(250, 290, 250, 25);
		contentPane.add(role);
		
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
		if(e.getActionCommand().equalsIgnoreCase("Add Employee")){
			addNewEmployee(currentContentPane,tempUser);
		}
		if(e.getActionCommand().equalsIgnoreCase("InActive Employee")){
			if(rowIndexPointer.trim().length()!=0){
				//new Employees(currentContentPane,tempList);
			}else{
				JOptionPane.showMessageDialog(null, "Please select row of the table.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Save")){
			if(name!=null && title!=null && billrate!=null && role!=null){
				if(name.getText().trim().length()!=0 && title.getText().trim().length()!=0 && billrate.getText().trim().length()!=0 && role.getText().trim().length()!=0){
					//new Employees(currentContentPane,employeeCourseList);
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
