package invoice;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu implements ActionListener{
	JPanel currentContentPane,child;	
	public Menu(final JPanel contentPane,final User user){
		contentPane.removeAll();
		
		JLabel headingLabel = new JLabel("");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		headingLabel.setBounds(250, 35, 650, 45);
		contentPane.add(headingLabel);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		logoutButton.setBounds(850, 75, 100, 35);
		logoutButton.addActionListener(this);
		contentPane.add(logoutButton);
		if(user!=null && user.getUserrole().equalsIgnoreCase("Accountant")){
			String maintainLabels[] = {"EMPLOYEE", "CLIENT", "PROJECT"};
		    final JComboBox maintainComboBox = new JComboBox(maintainLabels);	    
		    maintainComboBox.setMaximumRowCount(7);
		    maintainComboBox.setSelectedIndex(0);
		    maintainComboBox.setBounds(100, 100, 125, 35);
		    maintainComboBox.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 if(maintainComboBox.getSelectedIndex() != -1) {                     
		        		/*if(maintainComboBox.getItemAt(maintainComboBox.getSelectedIndex()).equals("Maintain")){
	//	        			JOptionPane.showMessageDialog(null, "Please select any one of Maintain Menu option.");
			     //		}*/
		     			if(maintainComboBox.getItemAt(maintainComboBox.getSelectedIndex()).equals("EMPLOYEE")){
		     				new Employees(contentPane,null,user);
		     			}
		     			if(maintainComboBox.getItemAt(maintainComboBox.getSelectedIndex()).equals("CLIENT")){
		     				new Clients(contentPane,null,user);
		     			}
		     			if(maintainComboBox.getItemAt(maintainComboBox.getSelectedIndex()).equals("PROJECT")){
		     				new Projects(contentPane,null,user);
		     			}
		     		}
		         }
			});	    
		    contentPane.add(maintainComboBox);
		
		    JButton addemployeeButton = new JButton("Generate Invoices");
			addemployeeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
			addemployeeButton.setBounds(230, 100, 200, 35);
			addemployeeButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 new GenerateInvoices(contentPane,user);
		         }
			});
			contentPane.add(addemployeeButton);
		    
			String facultyLabels[] = {"Invoice Report", "Project Report", "Budget Report", "Payroll Report"};
			final JComboBox facultyComboBox = new JComboBox(facultyLabels);
			facultyComboBox.setMaximumRowCount(5);
			facultyComboBox.setSelectedIndex(0);
			facultyComboBox.setBounds(450, 100, 100, 35);
			facultyComboBox.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 if(facultyComboBox.getSelectedIndex() != -1) {   
		        		/*if(facultyComboBox.getItemAt(facultyComboBox.getSelectedIndex()).equals("Reports")){
		        			JOptionPane.showMessageDialog(null, "Please select any one of Reports Menu option.");
					    }*/
		        		if(facultyComboBox.getItemAt(facultyComboBox.getSelectedIndex()).equals("Invoice Report")){
		        			new Reports().invoiceReport(contentPane,user);
			     		}
		     			if(facultyComboBox.getItemAt(facultyComboBox.getSelectedIndex()).equals("Project Report")){
		     				new Reports().projectReport(contentPane,user);
		     			}
		     			if(facultyComboBox.getItemAt(facultyComboBox.getSelectedIndex()).equals("Budget Report")){
		     				new Reports().budgetReport(contentPane,user);
		     			}
		     			if(facultyComboBox.getItemAt(facultyComboBox.getSelectedIndex()).equals("Payroll Report")){
		     				new Reports().payrollReport(contentPane,user);
		     			}
		     		}
		         }
			});	    
			contentPane.add(facultyComboBox);
		}
		
		if(user!=null && user.getUserrole().equalsIgnoreCase("Project Manager")){
			String maintainLabels[] = {"EMPLOYEE", "PROJECT"};
		    final JComboBox maintainComboBox = new JComboBox(maintainLabels);	    
		    maintainComboBox.setMaximumRowCount(7);
		    maintainComboBox.setSelectedIndex(0);
		    maintainComboBox.setBounds(100, 100, 125, 35);
		    maintainComboBox.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 if(maintainComboBox.getSelectedIndex() != -1) {                     
		        	/*	if(maintainComboBox.getItemAt(maintainComboBox.getSelectedIndex()).equals("Maintain")){
		        			JOptionPane.showMessageDialog(null, "Please select any one of Maintain Menu option.");
			     		}*/
		     			if(maintainComboBox.getItemAt(maintainComboBox.getSelectedIndex()).equals("EMPLOYEE")){
		     				new Employees(contentPane,null,user);
		     			}
		     			if(maintainComboBox.getItemAt(maintainComboBox.getSelectedIndex()).equals("PROJECT")){
		     				new Projects(contentPane,null,user);
		     			}
		     		}
		         }
			});	    
		    contentPane.add(maintainComboBox);
		
			String reportLabels[] = {"Project Report", "Budget Report"};
			final JComboBox reportComboBox = new JComboBox(reportLabels);
			reportComboBox.setMaximumRowCount(5);
			reportComboBox.setSelectedIndex(0);
			reportComboBox.setBounds(230, 100, 100, 35);
			reportComboBox.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 if(reportComboBox.getSelectedIndex() != -1) {   
		        	/*	if(reportComboBox.getItemAt(reportComboBox.getSelectedIndex()).equals("Reports")){
		        			JOptionPane.showMessageDialog(null, "Please select any one of Reports Menu option.");
					    }*/
		        		if(reportComboBox.getItemAt(reportComboBox.getSelectedIndex()).equals("Project Report")){
		     				new Reports().projectReport(contentPane,user);
		     			}
		     			if(reportComboBox.getItemAt(reportComboBox.getSelectedIndex()).equals("Budget Report")){
		     				new Reports().budgetReport(contentPane,user);
		     			}		     			
		     		}
		         }
			});	    
			contentPane.add(reportComboBox);
		}
		
		if(user!=null && user.getUserrole().equalsIgnoreCase("Developer")){
			JButton addemployeeButton = new JButton("Developer Work Time");
			addemployeeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
			addemployeeButton.setBounds(230, 100, 200, 35);
			addemployeeButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 new EmployeeHours().addEmployeeHours(contentPane,user);
		         }
			});
			contentPane.add(addemployeeButton);
		}
		
		currentContentPane=contentPane;
		contentPane.repaint();
	}
	public void actionPerformed(ActionEvent e) {
		new Login(currentContentPane);
    } 
}
