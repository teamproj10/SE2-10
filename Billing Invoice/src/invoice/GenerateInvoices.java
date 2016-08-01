package invoice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class GenerateInvoices implements ActionListener{
	JTextField day,hours;
	DBServices service=new DBServices();
	JPanel currentContentPane;
	String rowIndexPointer="";
	public GenerateInvoices(JPanel contentPane,User user){
		new Menu(contentPane,user);		
		
		JLabel headingLabel = new JLabel("GENERATE INVOICES");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
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
		
		JButton saveButton = new JButton("Generate PDF");
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		saveButton.setBounds(250, 500, 150, 40);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(425, 500, 100, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Generate PDF")){
			
		}		
		if(e.getActionCommand().equalsIgnoreCase("Exit")){
			System.exit(1);
		}
    }

}
