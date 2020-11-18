package user_interface;
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
	
	//initialize
	DatabaseService ds = new DatabaseService();
	
	public static String usernameLogin = null;
	JFrame frame = new JFrame("Centipede Movie Ratings");
	Font fontTitle = new Font("Arial", Font.BOLD,40);
	Font font1 = new Font("Arial",Font.PLAIN,18);
	
	JPanel panel1 = new JPanel();
	
	JLabel loginLabel = new JLabel("Login");
	JLabel usernameLabel = new JLabel("username");
	JLabel passwordLabel = new JLabel("password");
	JLabel starLabel = new JLabel("*");
	JLabel starLabel2 = new JLabel("*");
	
	JButton loginButton = new JButton("Login");
	JButton toRegisButton = new JButton("Register");
	
	JTextField passwordText = new JTextField();
	JTextField usernameText = new JTextField();
	
	
	public Login(){
		
		
		frame.setSize(500,300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		

		frame.add(panel1);
		frame.setVisible(true);	
	
		
		
		panel1.setLayout(null);
		
	

		//set the location
		
		loginLabel.setBounds(20, 5, 400, 50);
		usernameLabel.setBounds(20, 70, 150, 20);
		passwordLabel.setBounds(20, 100, 150, 20);
		usernameText.setBounds(200, 70, 200, 20);
		passwordText.setBounds(200, 100, 200, 20);
		starLabel.setBounds(400, 60, 20, 20);
		starLabel2.setBounds(400, 90, 20, 20);
		loginButton.setBounds(337, 214, 150, 50);
		toRegisButton.setBounds(400, 0, 90, 35);
		
		//set the font
		loginLabel.setFont(fontTitle);
		usernameLabel.setFont(font1);
		passwordLabel.setFont(font1);
		starLabel.setFont(font1);
		starLabel2.setFont(font1);
		
		
		panel1.add(loginLabel);
		panel1.add(usernameLabel);
		panel1.add(passwordLabel);
		panel1.add(usernameText);
		panel1.add(passwordText);
		panel1.add(starLabel);
		panel1.add(starLabel2);
		panel1.add(loginButton);
		panel1.add(toRegisButton);

		
	
		//for the color
		starLabel.setForeground(new java.awt.Color(255, 51, 51));
		starLabel2.setForeground(new java.awt.Color(255, 51, 51));
		
		//set the visible
		starLabel.setVisible(false);
		starLabel2.setVisible(false);
		
		
		
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
							usernameLogin = usernameText.getText();
							MovieList mv = new MovieList();
							mv.userLabel.setText("Welcome,  " + usernameText.getText());
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
		
		
		
		
		
		
		toRegisButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new Register();
				frame.dispose();
			}
		});
	}
}