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

public class UserReview {


	private static void initialize() {
		JFrame frame=new JFrame("Centipede Movie Ratings");
		frame.setSize(500,300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		//panel 1 for the text area and also label
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		
		//main one
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		
		//panel for the save button 
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		//fonts
		Font fontTitle = new Font("Arial", Font.BOLD,40);
		Font font1 = new Font("Arial",Font.PLAIN,18);
		
		//create red star labels to kasi tau user belum ngisi
		JLabel starlabel = new JLabel("*");
		JLabel starlabel2 = new JLabel("*");
		starlabel.setForeground(new Color(255,51,51));
		starlabel2.setForeground(new Color(255,51,51));
		starlabel.setBounds(90,20,20,20);
		starlabel2.setBounds(410,20,20,20);
		starlabel.setVisible(false);
		starlabel2.setVisible(false);
		panel1.add(starlabel);	
		panel1.add(starlabel2);
		
		
		//Review label
		JLabel reviewLabel = new JLabel("Review");
		reviewLabel.setBounds(20,20, 150, 20);
		reviewLabel.setFont(font1);
		panel1.add(reviewLabel);
			
		
		Border borderBlack = BorderFactory.createLineBorder(Color.BLACK);
	    
		//Review Text Area
		JTextArea reviewTextArea = new JTextArea();
		reviewTextArea.setBounds(20,50,250,100);
		reviewTextArea.setBorder(borderBlack);
		reviewTextArea.setBorder(BorderFactory.createCompoundBorder(borderBlack,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		panel1.add(reviewTextArea);	

		//Rate label
		JLabel rateLabel = new JLabel("Rate");
		rateLabel.setBounds(360, 20, 150, 20);
		rateLabel.setFont(font1);
		panel1.add(rateLabel);
		
		//Rate Text Field
				JTextField rateTextField = new JTextField();
				rateTextField.setBounds(360,50,100,60);
				rateTextField.setBorder(borderBlack);
				rateTextField.setBorder(BorderFactory.createCompoundBorder(borderBlack,
			            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
				panel1.add(rateTextField);
				
		//Save Button 
		JButton saveButton = new JButton("Save");
		saveButton.setPreferredSize(new Dimension(100,30));
		panel3.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (reviewTextArea.getText().equals("")) {
					starlabel.setVisible(true);	 
				}
				else if (rateTextField.getText().equals("")) {

					starlabel2.setVisible(true);
				}
				
				else { 
				 JOptionPane.showMessageDialog(null, "Data Saved");
				 starlabel.setVisible(false);
				 starlabel2.setVisible(false);
				 reviewTextArea.setText("");
				 rateTextField.setText("");
				}
			}
		});
		
		
		//Current user's review 
		JLabel curUserReview = new JLabel("Current user's review");
		curUserReview.setHorizontalAlignment(JLabel.LEFT);
		
		panel2.add(panel1,BorderLayout.CENTER);
		panel2.add(panel3,BorderLayout.SOUTH);
		panel2.add(curUserReview,BorderLayout.NORTH);
		


		frame.add(panel2);
		frame.setVisible(true);

	}
	public static void main(String[] args) {
	    //Schedule a job for the event-dispatching thread:
	    //creating and showing this application's GUI.
		initialize();
}
}