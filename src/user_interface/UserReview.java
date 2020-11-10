package user_interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.sql.Connection;
import java.sql.PreparedStatement;

import database.DatabaseService;
import models.Review;

public class UserReview {
	
	//Initialize the component
		DatabaseService ds = new DatabaseService();
	
		JFrame frame=new JFrame("Centipede Movie Ratings");
		//panel 1 for the text area and also label
		JPanel panel1 = new JPanel();
		//main one
		JPanel panel2 = new JPanel();
		//panel for the save button 
		JPanel panel3 = new JPanel();
		//Review label
		JLabel reviewLabel = new JLabel("Review");
		//Rate label
		JLabel rateLabel = new JLabel("Rate");
		//create red star labels to kasi tau user belum ngisi
		JLabel starlabel = new JLabel("*");
		JLabel starlabel2 = new JLabel("*");
		//Current user's review 
		JLabel curUserReview = new JLabel("User's review");
		
		//Save Button 
		JButton saveButton = new JButton("Save");
		
		
		//Rate Text Field
		JTextField rateTextField = new JTextField();
		//Review Text Area
		JTextArea reviewTextArea = new JTextArea();
		//fonts
		Font fontTitle = new Font("Arial", Font.BOLD,40);
		Font font1 = new Font("Arial",Font.PLAIN,18);
		
		//Border
		Border borderBlack = BorderFactory.createLineBorder(Color.BLACK);


	public UserReview() {
		
		frame.setSize(500,300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		
		panel1.setLayout(null);
		panel2.setLayout(new BorderLayout());
		panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		reviewLabel.setFont(font1);
		rateLabel.setFont(font1);
		
		
		starlabel.setForeground(new Color(255,51,51));
		starlabel2.setForeground(new Color(255,51,51));
		
		saveButton.setPreferredSize(new Dimension(100,30));
		
		reviewLabel.setBounds(20,20, 150, 20);
		starlabel.setBounds(90,20,20,20);
		starlabel2.setBounds(410,20,20,20);
		reviewTextArea.setBounds(20,50,250,100);
		rateLabel.setBounds(360, 20, 150, 20);
		rateTextField.setBounds(360,50,100,60);
		
		reviewTextArea.setBorder(borderBlack);
		reviewTextArea.setBorder(BorderFactory.createCompoundBorder(borderBlack,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		rateTextField.setBorder(borderBlack);
		rateTextField.setBorder(BorderFactory.createCompoundBorder(borderBlack,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		
		starlabel.setVisible(false);
		starlabel2.setVisible(false);
		
		
		panel1.add(starlabel);	
		panel1.add(starlabel2);
		panel1.add(reviewLabel);
		panel1.add(reviewTextArea);	
		panel1.add(rateLabel);
		panel1.add(rateTextField);
		panel3.add(saveButton);
		panel2.add(panel1,BorderLayout.CENTER);
		panel2.add(panel3,BorderLayout.SOUTH);
		panel2.add(curUserReview,BorderLayout.NORTH);
		
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				starlabel.setVisible(false);
				starlabel2.setVisible(false);
				if (reviewTextArea.getText().equals("")) {
					starlabel.setVisible(true);	 
				}
				else if (rateTextField.getText().equals("")) {

					starlabel2.setVisible(true);
				}
				
				else { 
				 Connection con = ds.getConnection();
				 PreparedStatement ps;
				 
				 
				 JOptionPane.showMessageDialog(null, "Data Saved");
				 starlabel.setVisible(false);
				 starlabel2.setVisible(false);
				 reviewTextArea.setText("");
				 rateTextField.setText("");
				}
			}
		});
	

		frame.add(panel2);
		frame.setVisible(true);

	}
	

	public static void main(String[] args) {
	    //Schedule a job for the event-dispatching thread:
	    //creating and showing this application's GUI.
		new UserReview();
}
}