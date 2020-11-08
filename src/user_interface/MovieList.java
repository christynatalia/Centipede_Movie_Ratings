package user_interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MovieList{
	
	//Initialization
    JFrame frame = new JFrame("SimpleTableDemo");
    JPanel p = new JPanel();
    JPanel p2 = new JPanel();
    JLabel movieNameLabel = new JLabel("Movie List");
    JLabel ratingLabel = new JLabel("Rating");
    JLabel userLabel = new JLabel("Welcome, User");
    Font fontTitle = new Font("Arial", Font.BOLD, 25);

	public MovieList(){
		
		String[] columnNames = {"Movie Title", "Rating"};
		
		String[][] data = {
			{"Movie Name 1", "4.0"},
			{"Movie Name 2", "4.5"},
			{"Movie Name 3", "5.0"},
			{"Movie Name 1", "4.0"},
			{"Movie Name 2", "4.5"},
			{"Movie Name 3", "5.0"},
			{"Movie Name 1", "4.0"},
			{"Movie Name 2", "4.5"},
			{"Movie Name 3", "5.0"},
			{"Movie Name 1", "4.0"},
			{"Movie Name 2", "4.5"},
			{"Movie Name 3", "5.0"},
			{"Movie Name 1", "4.0"},
			{"Movie Name 2", "4.5"},
			{"Movie Name 3", "5.0"},
			{"Movie Name 1", "4.0"},
			{"Movie Name 2", "4.5"},
			{"Movie Name 3", "5.0"}
		};
		
		//Initialization table
		final JTable table = new JTable(data, columnNames);
		
	    //Properties
	    frame.setResizable(false);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    userLabel.setFont(new Font("Arial",Font.BOLD,14));
		movieNameLabel.setFont(fontTitle);
		ratingLabel.setFont(fontTitle);
		
	    p.setLayout(new BorderLayout());
	    p2.setLayout(new GridLayout(2,2));
	    
	    table.setTableHeader(null);
		table.setFont(new Font("Arial",Font.PLAIN,14));
		table.setPreferredScrollableViewportSize(new Dimension(500, 220));
	    table.setFillsViewportHeight(true);
	    //make JScrollPane
	    JScrollPane scrollPane = new JScrollPane(table);
	    
	    //Add to panel
	    p2.add(userLabel);
	    p2.add(new JLabel(""));
	    p2.add(movieNameLabel);
	    p2.add(ratingLabel);
	    p.add(p2, BorderLayout.NORTH);
	    p.add(scrollPane, BorderLayout.CENTER);
	    
	    //Display the window.
	    frame.add(p);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
	
	public static void main (String[] args) {
		new MovieList();
	}
}

