package generateinvoice;

import invoice.DBServices;
import invoice.User;
import menu.Menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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

import company.Company;

public class GenerateInvoices implements ActionListener{
	JTextField day,hours;
	DBServices service=new DBServices();
	JPanel currentContentPane;
	String rowIndexPointer="";
	User tempuser=null;
	HashMap invoiceMap=null;
	public GenerateInvoices(JPanel contentPane,User user){
		new Menu(contentPane,user);		
		
		JLabel headingLabel = new JLabel("GENERATE INVOICES");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		invoiceMap=service.generateInvoices();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		ArrayList invoiceList=service.getCurrentInvoices(sdf.format(new Date()));
		String clientColumnNames[] = {"Invoice Number", "Client Number", "Project Number", "Invoice Date","Amount"};
		int columnsCount=5;			
		String clientRowColumnDataValues[][]=new String[0][columnsCount];
		if(invoiceList!=null && invoiceList.size()!=0){
			clientRowColumnDataValues=new String[invoiceList.size()][columnsCount];
			int poistion=0;
			for(int i=0;i<invoiceList.size();i++) {
				String[] tokens=(String[])invoiceList.get(i);
				clientRowColumnDataValues[poistion][0]=tokens[0];
				clientRowColumnDataValues[poistion][1]=tokens[1];
				clientRowColumnDataValues[poistion][2]=tokens[2];
				clientRowColumnDataValues[poistion][3]=tokens[3];
				clientRowColumnDataValues[poistion][4]=tokens[4];
				poistion=poistion+1;
			}		
		}
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
		
		JButton saveButton = new JButton("Generate PDF");
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		saveButton.setBounds(250, 500, 150, 40);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(425, 500, 100, 40);
		backButton.addActionListener(this);
		contentPane.add(backButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Generate PDF")){
			System.out.println(invoiceMap);
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			ArrayList invoiceList=service.getCurrentInvoices(sdf.format(new Date()));
			if(invoiceList!=null && invoiceList.size()!=0){
				for(int i=0;i<invoiceList.size();i++) {
					String[] tokens=(String[])invoiceList.get(i);
					String[] client=service.getClient(tokens[1]);
					String projectname=service.getProjectName(tokens[1], tokens[2]);
					String filename=client[1]+"_"+projectname;
					Object[] pair=(Object[])invoiceMap.get(tokens[0]);
					System.out.println("token     "+tokens[0]+"     developer    "+pair);
					GenerateInvoice generateInvoice = new GenerateInvoice(filename,client,tokens,projectname,pair);
				}		
			}
			JOptionPane.showMessageDialog(null, "Generated Invoices successfully on Local Desktop.");
		}		
		if(e.getActionCommand().equalsIgnoreCase("Back")){
			new Company(currentContentPane,tempuser);
		}
    }

}
