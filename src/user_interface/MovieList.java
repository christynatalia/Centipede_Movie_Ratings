package user_interface;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

public class MovieList extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		
		final JTable table = new JTable(data, columnNames);
		table.setTableHeader(null);
		table.setFont(new Font("Arial",Font.PLAIN,16));
		table.setPreferredScrollableViewportSize(new Dimension(500, 220));
	    table.setFillsViewportHeight(true);
	    
	  //Create the scroll pane and add the table to it.
	    JScrollPane scrollPane = new JScrollPane(table);
	
	    //Add the scroll pane to this panel.
	    add(scrollPane);
	}

	private static void createAndShowGUI() {
	    //Create and set up the window.
	    JFrame frame = new JFrame("SimpleTableDemo");
		frame.setResizable(false);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //set all the text
	    Font fontTitle = new Font("Arial", Font.BOLD, 25);
	    JLabel movieNameLabel = new JLabel(" Movie List");
		movieNameLabel.setFont(fontTitle);
		JLabel ratingLabel = new JLabel("Rating");
		ratingLabel.setFont(fontTitle);
		
		//borderlayout
	    JPanel p = new JPanel();
	    p.setLayout(new BorderLayout());
	    
	    JPanel p2 = new JPanel();
	    p2.setLayout(new GridLayout(0,2));
	    p2.add(movieNameLabel);
	    p2.add(ratingLabel);
	    
	    p.add(p2, BorderLayout.NORTH);
	    MovieList newContentPane = new MovieList();
	    newContentPane.setOpaque(true); //content panes must be opaque
	    p.add(newContentPane, BorderLayout.SOUTH);
	    
	    //Display the window.
	    frame.add(p);
	    frame.pack();
	    frame.setVisible(true);
	}

	public static void main(String[] args) {
	    //Schedule a job for the event-dispatching thread:
	    //creating and showing this application's GUI.
		createAndShowGUI();
	}
}

