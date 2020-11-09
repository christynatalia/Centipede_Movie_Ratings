package user_interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import database.DatabaseService;
import models.Movie;

public class MovieList{
	
	//Initialization
    JFrame frame = new JFrame("SimpleTableDemo");
    JPanel p = new JPanel();
    JPanel p2 = new JPanel();
    JLabel movieNameLabel = new JLabel("Movie List");
    JLabel ratingLabel = new JLabel("Rating");
    JLabel userLabel = new JLabel("Welcome, User");
    Font fontTitle = new Font("Arial", Font.BOLD, 25);
    
    DatabaseService ds = new DatabaseService();

	public MovieList(){
		//data for table
		List<Movie> mov = ds.getAllMovies();
		
		int row = mov.size();
		int col = 3;
		float movieRating = 0;
		
		String[][] movies = new String[row][col];
		
		//get data from database and insert into lists
		for(int i=0; i<row; i++) {
			Movie m = mov.get(i);
			movies[i][0] = m.getName();
			
			movieRating = ds.countAverageMovieRating(m.getID());
			movies[i][1] = String.valueOf(movieRating);
		}
		
		/*
		for(int i = 0; i<row; i++)
		{
		    for(int j = 0; j<col; j++)
		    {
		        System.out.print(movies[i][j]);
		    }
		}
		*/
		
		String[] columnNames = {"Movie Title", "Rating", ""};
		
		//Initialization table
		final JTable table = new JTable(movies, columnNames);
		
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
	    table.setRowHeight(20);
	    table.getColumnModel().getColumn(0).setPreferredWidth(250);
	    table.getColumnModel().getColumn(1).setPreferredWidth(200);
	    table.getColumnModel().getColumn(2).setPreferredWidth(50);
	    
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

