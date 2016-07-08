package schedule;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OCImports implements ActionListener{
	Service service=new Service();
	JPanel currentContentPane;
	String filePaths="";
	JFileChooser multipleFileChooser = new JFileChooser();
	public OCImports(){
		
	}
	public OCImports(JPanel contentPane){
		new OCMenu(contentPane);		
		
		JLabel headingLabel = new JLabel("OC IMPORT FILES");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Upload File");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(100, 200, 100, 25);
		contentPane.add(headingLabel);
		
		final JTextField textField = new JTextField();
		textField.setBounds(250, 200, 200, 25);
		contentPane.add(textField);	
		
		final JButton button = new JButton("Browse");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				JFileChooser multipleFileChooser = new JFileChooser();				
				multipleFileChooser.setMultiSelectionEnabled(true);
				int value = multipleFileChooser.showOpenDialog(null);
				if(value==JFileChooser.APPROVE_OPTION){					 
					try{
						File[] file=multipleFileChooser.getSelectedFiles();
						for(int i=0;i<file.length;i++){  
							filePaths+=file[i].toString()+"\n";				              				              
						}
						textField.setText(filePaths);
					}catch (Exception ex){
						JOptionPane.showMessageDialog(button, "Please select test data files");
					}
				}
			}
		});
		button.setBounds(500,200,200,40);
		contentPane.add(button);
		
		JButton uploadFileButton = new JButton("Upload File Data");
		uploadFileButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		uploadFileButton.setBounds(200, 380, 200, 40);
		uploadFileButton.addActionListener(this);
		contentPane.add(uploadFileButton);
		
		JButton quitButton = new JButton("Exit");
		quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		quitButton.setBounds(425, 380, 200, 40);
		quitButton.addActionListener(this);
		contentPane.add(quitButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Upload File Data")){
			File[] file=multipleFileChooser.getSelectedFiles();
			for(int i=0;i<file.length;i++){
				
			}
			JOptionPane.showMessageDialog(null, "Upload Data Successfully");
		}
		if(e.getActionCommand().equalsIgnoreCase("Exit")){
			System.exit(1);
		}
    }
}