package user_interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.Font;

public class MovieList extends JPanel{
	
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
			{"Movie Name 3", "5.0"}
		};
		
		final JTable table = new JTable(data, columnNames);
		table.setTableHeader(null);
		table.setFont(new Font("Arial",Font.PLAIN,18));
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
	    table.setFillsViewportHeight(true);
	    
	  //Create the scroll pane and add the table to it.
	    JScrollPane scrollPane = new JScrollPane(table);
	
	    //Add the scroll pane to this panel.
	    add(scrollPane);
	}

	private static void createAndShowGUI() {
	    //Create and set up the window.
	    JFrame frame = new JFrame("SimpleTableDemo");
		frame.setSize(500, 300);
		frame.setResizable(false);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	    //Create and set up the content pane.
	    MovieList newContentPane = new MovieList();
	    newContentPane.setOpaque(true); //content panes must be opaque
	    frame.setContentPane(newContentPane);
	
	    //Display the window.
	    frame.pack();
	    frame.setVisible(true);
	}

	public static void main(String[] args) {
	    //Schedule a job for the event-dispatching thread:
	    //creating and showing this application's GUI.
		createAndShowGUI();
	}
}

