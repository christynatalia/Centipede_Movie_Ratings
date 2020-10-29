package user_interface;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Register {
	public static void main(String[] args) {  
		JFrame frame=new JFrame("Centipede Movie Ratings");
		frame.setSize(500,300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel1 = new JPanel();
		frame.add(panel1);
		initialize(panel1);
		frame.setVisible(true);	
	}
	
	private static void initialize(JPanel panel1) {
		panel1.setLayout(null);
		
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
		
		//create the Register button
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(337, 214, 150, 50);
		panel1.add(registerButton);
		
	}
}
