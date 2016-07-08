package schedule;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OCMenu implements ActionListener{
	JPanel currentContentPane,child;	
	public OCMenu(final JPanel contentPane){
		contentPane.removeAll();
		
		JLabel headingLabel = new JLabel("Oklahoma Christian University");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		headingLabel.setBounds(250, 35, 650, 45);
		contentPane.add(headingLabel);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		logoutButton.setBounds(850, 75, 100, 35);
		logoutButton.addActionListener(this);
		contentPane.add(logoutButton);
		
		String courseLabels[] = {"Maintain","Courses", "Faculties", "Students", "Degrees", "Semesters","Import"};
	    final JComboBox courseComboBox = new JComboBox(courseLabels);	    
	    courseComboBox.setMaximumRowCount(7);
	    courseComboBox.setSelectedIndex(0);
	    courseComboBox.setBounds(100, 100, 125, 35);
	    courseComboBox.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 if(courseComboBox.getSelectedIndex() != -1) {                     
	        		if(courseComboBox.getItemAt(courseComboBox.getSelectedIndex()).equals("Maintain")){
	        			JOptionPane.showMessageDialog(null, "Please select any one of Maintain Menu option.");
		     		}
	     			if(courseComboBox.getItemAt(courseComboBox.getSelectedIndex()).equals("Courses")){
	     				new OCCourses(contentPane,null);
	     			}
	     			if(courseComboBox.getItemAt(courseComboBox.getSelectedIndex()).equals("Faculties")){
	     				new OCFaculties(contentPane,null);
	     			}
	     			if(courseComboBox.getItemAt(courseComboBox.getSelectedIndex()).equals("Students")){
	     				new OCStudents(contentPane,null);
	     			}
	     			if(courseComboBox.getItemAt(courseComboBox.getSelectedIndex()).equals("Degrees")){
	     				new OCDegrees(contentPane,null);
	     			}
	     			if(courseComboBox.getItemAt(courseComboBox.getSelectedIndex()).equals("Semesters")){
	     				new OCSemesters(contentPane,null);
	     			}
	     			if(courseComboBox.getItemAt(courseComboBox.getSelectedIndex()).equals("Import")){
	     				new OCImports(contentPane);
	     			}
	     		}
	         }
		});	    
	    contentPane.add(courseComboBox);
		
		String degreePlanLabels[] = {"Schedule","Generate", "Test"};
	    final JComboBox degreePlanComboBox = new JComboBox(degreePlanLabels);
	    degreePlanComboBox.setMaximumRowCount(4);
	    degreePlanComboBox.setSelectedIndex(0);
	    degreePlanComboBox.setBounds(230, 100, 100, 35);
	    degreePlanComboBox.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 if(degreePlanComboBox.getSelectedIndex() != -1) { 
	        		if(degreePlanComboBox.getItemAt(degreePlanComboBox.getSelectedIndex()).equals("Schedule")){
	        			JOptionPane.showMessageDialog(null, "Please select any one of Schedule Menu option.");
			     	}
	        		if(degreePlanComboBox.getItemAt(degreePlanComboBox.getSelectedIndex()).equals("Generate")){
	        			new OCSchedules().generateSchedule(contentPane);
		     		}
	     			if(degreePlanComboBox.getItemAt(degreePlanComboBox.getSelectedIndex()).equals("Test")){
	     				new OCSchedules().testSchedule(contentPane);
	     			}
	     		}
	         }
		});	    
	    contentPane.add(degreePlanComboBox);
		
	    String facultyLabels[] = {"Reports","Student-Course", "DegreePlan", "Schedule", "List of Sections"};
		final JComboBox facultyComboBox = new JComboBox(facultyLabels);
		facultyComboBox.setMaximumRowCount(5);
		facultyComboBox.setSelectedIndex(0);
		facultyComboBox.setBounds(335, 100, 125, 35);
		facultyComboBox.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 if(facultyComboBox.getSelectedIndex() != -1) {   
	        		if(facultyComboBox.getItemAt(facultyComboBox.getSelectedIndex()).equals("Reports")){
	        			JOptionPane.showMessageDialog(null, "Please select any one of Reports Menu option.");
				    }
	        		if(facultyComboBox.getItemAt(facultyComboBox.getSelectedIndex()).equals("Student-Course")){
	        			new OCReports().studentCourseReport(contentPane);;
		     		}
	     			if(facultyComboBox.getItemAt(facultyComboBox.getSelectedIndex()).equals("DegreePlan")){
	     				new OCReports().degreePlanReport(contentPane);
	     			}
	     			if(facultyComboBox.getItemAt(facultyComboBox.getSelectedIndex()).equals("Schedule")){
	     				new OCReports().scheduleReport(contentPane);
	     			}
	     			if(facultyComboBox.getItemAt(facultyComboBox.getSelectedIndex()).equals("List of Sections")){
	     				new OCReports().sectionReport(contentPane);
	     			}
	     		}
	         }
		});	    
		contentPane.add(facultyComboBox);
	    
		currentContentPane=contentPane;
		contentPane.repaint();
	}
	public void actionPerformed(ActionEvent e) {
		new OCLogin(currentContentPane);
    } 
}
