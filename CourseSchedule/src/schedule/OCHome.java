package schedule;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class OCHome extends JFrame {
	private JPanel contentPane;
	private JFrame frame;
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try{
					OCHome frame = new OCHome();
					frame.setVisible(true);					
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}
 
	public OCHome(){
		String current = "";
		setTitle("Oklahoma Christian University");
		final List<Image> icons = new ArrayList<Image>();
	    try{
	    	current = new java.io.File( "." ).getCanonicalPath();
	    	icons.add(ImageIO.read(new File(current+"\\src\\schedule\\ocu.jpg")));
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		setIconImages(icons);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 1000, 650);
		contentPane = new OCPanel(current+"\\src\\schedule\\panelimage.jpg"); //new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		new OCLogin(contentPane);
	}
}
