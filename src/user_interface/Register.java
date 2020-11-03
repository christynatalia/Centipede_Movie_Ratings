package user_interface;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.DatabaseService;
import models.User;

public class Register {
	
	static JFrame frame;
	
	public static void main(String[] args) {  
		frame = new JFrame("Centipede Movie Ratings");
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
		DatabaseService ds = new DatabaseService();
		
		Font fontTitle = new Font("Arial", Font.BOLD,40);
		Font font1 = new Font("Arial",Font.PLAIN,18);

		// create the register label
		JLabel registerLabel = new JLabel("Register");
		registerLabel.setBounds(20, 5, 400, 50);
		registerLabel.setFont(fontTitle);
		panel1.add(registerLabel);
		
		// create the first name label
		JLabel firstNameLabel = new JLabel("first name");
		firstNameLabel.setBounds(20, 70, 150, 20);
		firstNameLabel.setFont(font1);
		panel1.add(firstNameLabel);
		
		//create the last name label 
		JLabel lastNameLabel = new JLabel("last name");
		lastNameLabel.setBounds(20, 100, 150, 20);
		lastNameLabel.setFont(font1);
		panel1.add(lastNameLabel);
		
		// create the username label
		JLabel usernameLabel = new JLabel("username");
		usernameLabel.setBounds(20, 130, 150, 20);
		usernameLabel.setFont(font1);
		panel1.add(usernameLabel);
				
		//create the password label 
		JLabel passwordLabel = new JLabel("password");
		passwordLabel.setBounds(20, 160, 150, 20);
		passwordLabel.setFont(font1);
		panel1.add(passwordLabel);
		
		//create the username textfield
		JTextField firstNameText = new JTextField();
		firstNameText.setBounds(200, 70, 200, 20);
		panel1.add(firstNameText);
				
		//create the password textfield
		JTextField lastNameText = new JTextField();
		lastNameText.setBounds(200, 100, 200, 20);
		panel1.add(lastNameText);
		
		//create the username textfield
		JTextField usernameText = new JTextField();
		usernameText.setBounds(200, 130, 200, 20);
		panel1.add(usernameText);
		
		//create the password textfield
		JTextField passwordText = new JTextField();
		passwordText.setBounds(200, 160, 200, 20);
		panel1.add(passwordText);
		
		//create red star labels to kasi tau user belum ngisi
		JLabel starLabel = new JLabel("*");
		JLabel starLabel2 = new JLabel("*");
		JLabel starLabel3 = new JLabel("*");
		JLabel starLabel4 = new JLabel("*");
		starLabel.setBounds(400, 60, 20, 20);
		starLabel2.setBounds(400, 90, 20, 20);
		starLabel3.setBounds(400, 120, 20, 20);
		starLabel4.setBounds(400, 150, 20, 20);
		starLabel.setForeground(new java.awt.Color(255, 51, 51));
		starLabel2.setForeground(new java.awt.Color(255, 51, 51));
		starLabel3.setForeground(new java.awt.Color(255, 51, 51));
		starLabel4.setForeground(new java.awt.Color(255, 51, 51));
		panel1.add(starLabel);
		panel1.add(starLabel2);
		panel1.add(starLabel3);
		panel1.add(starLabel4);
		starLabel.setVisible(false);
		starLabel2.setVisible(false);
		starLabel3.setVisible(false);
		starLabel4.setVisible(false);
		
		//create the Register button
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(337, 214, 150, 50);
		panel1.add(registerButton);
		registerButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(firstNameText.getText().equals("")) {
					starLabel.setVisible(true);
				}
				if(lastNameText.getText().equals("")) {
					starLabel2.setVisible(true);
				}
				if(passwordText.getText().equals("")) {
					starLabel3.setVisible(true);
				}
				if(usernameText.getText().equals("")) {
					starLabel4.setVisible(true);
				}
				else {
					ds.insert(new User(0, firstNameText.getText(), lastNameText.getText(), usernameText.getText(), passwordText.getText()));
					JOptionPane.showMessageDialog(null, "User Successfully Registered");
					firstNameText.setText("");
					lastNameText.setText("");
					usernameText.setText("");
					passwordText.setText("");
					starLabel.setVisible(false);
					starLabel2.setVisible(false);
					starLabel3.setVisible(false);
					starLabel4.setVisible(false);
				}
			}
		});
		
	}
}
