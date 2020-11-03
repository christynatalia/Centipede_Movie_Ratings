package user_interface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Rating extends JPanel{
	
	public Rating() {
		
	}
	
	private static void Initialize() {
		JFrame frame = new JFrame("Centipede Movie Ratings");
		frame.setResizable(false);
		
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    
	    Font fontTitle = new Font("Arial", Font.BOLD,25);
	    
	    //Rating label
	    JLabel ratinglabel = new JLabel("Rating");
	    ratinglabel.setFont(fontTitle);
	    
	    JPanel panel1 = new JPanel();
	    panel1.setLayout(new BorderLayout());
	    panel1.add(ratinglabel,BorderLayout.NORTH);
	    
	    frame.add(panel1);
	    frame.pack();
	    frame.setSize(500,300);
	    frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		Initialize();
	}
}
