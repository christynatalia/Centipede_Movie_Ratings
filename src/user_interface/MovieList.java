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
		
	    //Initialization
	    JFrame frame = new JFrame("SimpleTableDemo");
	    JPanel p = new JPanel();
	    JPanel p2 = new JPanel();
	    JLabel movieNameLabel = new JLabel(" Movie List");
	    JLabel ratingLabel = new JLabel("Rating");
	    Font fontTitle = new Font("Arial", Font.BOLD, 25);
	    final JTable table = new JTable(data, columnNames);
	    
	    //Properties
	    frame.setResizable(false);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
		movieNameLabel.setFont(fontTitle);
		ratingLabel.setFont(fontTitle);
		
	    p.setLayout(new BorderLayout());
	    p2.setLayout(new GridLayout(0,2));
	    
	    table.setTableHeader(null);
		table.setFont(new Font("Arial",Font.PLAIN,16));
		table.setPreferredScrollableViewportSize(new Dimension(500, 220));
	    table.setFillsViewportHeight(true);
	    //make JScrollPane
	    JScrollPane scrollPane = new JScrollPane(table);
	    
	    //Add to panel
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

