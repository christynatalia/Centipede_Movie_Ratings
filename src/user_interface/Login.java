package user_interface;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login { 
	
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

		// create the login label
		JLabel loginLabel = new JLabel("Login");
		loginLabel.setBounds(20, 5, 400, 50);
		loginLabel.setFont(fontTitle);
		panel1.add(loginLabel);
		
		// create the username label
		JLabel usernameLabel = new JLabel("username");
		usernameLabel.setBounds(20, 70, 150, 20);
		usernameLabel.setFont(font1);
		panel1.add(usernameLabel);
		
		//create the password label 
		JLabel passwordLabel = new JLabel("password");
		passwordLabel.setBounds(20, 100, 150, 20);
		passwordLabel.setFont(font1);
		panel1.add(passwordLabel);
		
		//create the username textfield
		JTextField usernameText = new JTextField();
		usernameText.setBounds(200, 70, 200, 20);
		panel1.add(usernameText);
		
		//create the password textfield
		JTextField passwordText = new JTextField();
		passwordText.setBounds(200, 100, 200, 20);
		panel1.add(passwordText);
		
		//create the login
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(337, 214, 150, 50);
		panel1.add(loginButton);
		
	}
}