package user_interface;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class UserReview {
	public static void main(String[] args) {  
		JFrame frame=new JFrame("Centipede Movie Ratings");
		frame.setSize(500,300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JPanel panel1 = new JPanel();
		frame.add(panel1);
		initialize(panel1);
		frame.setVisible(true);	
	}
	
	private static void initialize(JPanel panel1) {
		panel1.setLayout(null);
		
		Font fontTitle = new Font("Arial", Font.BOLD,40);
		Font font1 = new Font("Arial",Font.PLAIN,18);
		
		//Review label
		JLabel reviewLabel = new JLabel("Review");
		reviewLabel.setBounds(20, 70, 150, 20);
		reviewLabel.setFont(font1);
		panel1.add(reviewLabel);
		
		
		Border borderBlack = BorderFactory.createLineBorder(Color.BLACK);
	    
		//Review Text Area
		JTextArea reviewTextArea = new JTextArea();
		reviewTextArea.setBounds(20,100,250,100);
		reviewTextArea.setBorder(borderBlack);
		reviewTextArea.setBorder(BorderFactory.createCompoundBorder(borderBlack,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		panel1.add(reviewTextArea);	

		//Rate label
		JLabel rateLabel = new JLabel("Rate");
		rateLabel.setBounds(360, 100, 150, 20);
		rateLabel.setFont(font1);
		panel1.add(rateLabel);
		
		//Rate Text Area
				JTextArea rateTextArea = new JTextArea();
				rateTextArea.setBounds(360,130,100,60);
				rateTextArea.setBorder(borderBlack);
				rateTextArea.setBorder(BorderFactory.createCompoundBorder(borderBlack,
			            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
				panel1.add(rateTextArea);	

		

	}
}