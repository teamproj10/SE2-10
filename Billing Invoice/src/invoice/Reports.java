package invoice;

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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Reports implements ActionListener{
	DBServices service=new DBServices();
	JPanel currentContentPane;
	String selectedSemester;
	public Reports(){
		
	}
	public void invoiceReport(JPanel contentPane,User user){
		new Menu(contentPane,user);	
		
		JLabel headingLabel = new JLabel("INVOICE REPORT");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		String clientColumnNames[] = {"Client Name", "Project Name", "Invoice Date","Amount"};
		int columnsCount=4;			
		String clientRowColumnDataValues[][]=new String[0][columnsCount];
		
		final JTable clientTable = new JTable(clientRowColumnDataValues, clientColumnNames);
	    ListSelectionModel tableRowSelection = clientTable.getSelectionModel();
	    tableRowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    tableRowSelection.addListSelectionListener(new ListSelectionListener(){
	    	public void valueChanged(ListSelectionEvent e) {
	    		
	    	}
	    });	    
		JScrollPane tableScrollPane = new JScrollPane(clientTable);
		tableScrollPane.setBounds(100, 200, 550, 250);
		contentPane.add(tableScrollPane, BorderLayout.CENTER);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(700, 380, 200, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void projectReport(JPanel contentPane,User user){
		new Menu(contentPane,user);		
		
		JLabel headingLabel = new JLabel("PROJECT REPORT");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		String clientColumnNames[] = {"Column1", "Column2", "Column3", "Column4"};
		int columnsCount=4;			
		String clientRowColumnDataValues[][]=new String[0][columnsCount];
		
		final JTable clientTable = new JTable(clientRowColumnDataValues, clientColumnNames);
	    ListSelectionModel tableRowSelection = clientTable.getSelectionModel();
	    tableRowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    tableRowSelection.addListSelectionListener(new ListSelectionListener(){
	    	public void valueChanged(ListSelectionEvent e) {
	    		
	    	}
	    });	    
		JScrollPane tableScrollPane = new JScrollPane(clientTable);
		tableScrollPane.setBounds(100, 200, 550, 250);
		contentPane.add(tableScrollPane, BorderLayout.CENTER);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(700, 380, 200, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void budgetReport(JPanel contentPane,User user){
		new Menu(contentPane,user);		
		
		JLabel headingLabel = new JLabel("BUDGET REPORT");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		String clientColumnNames[] = {"Column1", "Column2", "Column3", "Column4"};
		int columnsCount=4;			
		String clientRowColumnDataValues[][]=new String[0][columnsCount];
		
		final JTable clientTable = new JTable(clientRowColumnDataValues, clientColumnNames);
	    ListSelectionModel tableRowSelection = clientTable.getSelectionModel();
	    tableRowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    tableRowSelection.addListSelectionListener(new ListSelectionListener(){
	    	public void valueChanged(ListSelectionEvent e) {
	    		
	    	}
	    });	    
		JScrollPane tableScrollPane = new JScrollPane(clientTable);
		tableScrollPane.setBounds(100, 200, 550, 250);
		contentPane.add(tableScrollPane, BorderLayout.CENTER);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(700, 380, 200, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void payrollReport(JPanel contentPane,User user){
		new Menu(contentPane,user);		
		
		JLabel headingLabel = new JLabel("PAYROLL REPORT");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		String clientColumnNames[] = {"Column1", "Column2", "Column3", "Column4"};
		int columnsCount=4;			
		String clientRowColumnDataValues[][]=new String[0][columnsCount];
		
		final JTable clientTable = new JTable(clientRowColumnDataValues, clientColumnNames);
	    ListSelectionModel tableRowSelection = clientTable.getSelectionModel();
	    tableRowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    tableRowSelection.addListSelectionListener(new ListSelectionListener(){
	    	public void valueChanged(ListSelectionEvent e) {
	    		
	    	}
	    });	    
		JScrollPane tableScrollPane = new JScrollPane(clientTable);
		tableScrollPane.setBounds(100, 200, 550, 250);
		contentPane.add(tableScrollPane, BorderLayout.CENTER);
		
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
