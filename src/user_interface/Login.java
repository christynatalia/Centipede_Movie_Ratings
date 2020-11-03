package user_interface;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.DatabaseService;

public class Login { 
	
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
		DatabaseService ds = new DatabaseService();
		
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
		
		//create * labels to inform the user that they haven't filled a textfield yet
		JLabel starLabel = new JLabel("*");
		JLabel starLabel2 = new JLabel("*");
		starLabel.setBounds(400, 60, 20, 20);
		starLabel2.setBounds(400, 90, 20, 20);
		starLabel.setFont(font1);
		starLabel2.setFont(font1);
		starLabel.setForeground(new java.awt.Color(255, 51, 51));
		starLabel2.setForeground(new java.awt.Color(255, 51, 51));
		panel1.add(starLabel);
		panel1.add(starLabel2);
		starLabel.setVisible(false);
		starLabel2.setVisible(false);
		
		//create the login button
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(337, 214, 150, 50);
		panel1.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if(usernameText.getText().equals("")) {
					starLabel.setVisible(true);
				}
				if(passwordText.getText().equals("")) {
					starLabel2.setVisible(true);
				}
				else {
					Connection con = ds.getConnection();
					PreparedStatement ps;
					
					try {
						ps = con.prepareStatement("SELECT * FROM User WHERE username = ? and password = ?");
						ps.setString(1, usernameText.getText());
						ps.setString(2, passwordText.getText());
						
						ResultSet rs = ps.executeQuery();
						
						if(rs.next()) {
							MovieList movieList = new MovieList();
							frame.dispose();
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Error Logging in", 2);
						}
					}
					catch(SQLException ex){
						System.err.println(ex.getMessage());
					}
					
				}
				
			}
		});
		
	}
}