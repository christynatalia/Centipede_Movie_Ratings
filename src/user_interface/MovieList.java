package user_interface;

import java.awt.BorderLayout; 
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
    public static String movieNamee = null;
    
    DatabaseService ds = new DatabaseService();
    List<Movie> mov = ds.getAllMovies();
	int row = mov.size();
	int col = 2;
	
	
	String[][] movies = makeMovieList(row, col, mov);
	String[] columnNames = {"Movie Title", "Rating"};
	
	private TableModel model = new DefaultTableModel(movies, columnNames){
		public boolean isCellEditable(int row, int column){
			return false;//This causes all cells to be not editable
		}
	};
	final JTable table = new JTable(model);

	public MovieList(){
		
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
	    
	    mouseClicked(table);
	    
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
	
	public void mouseClicked(JTable table1) {
		table1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	if (!event.getValueIsAdjusting()) {
	            // do some actions here, for example
	            // print first column value from selected row
	           movieNamee = table1.getValueAt(table1.getSelectedRow(), 0).toString();
	           System.out.println(movieNamee);
	           Rating rt = new Rating();
	           rt.movielabel.setText(movieNamee);
	           frame.dispose();
	        	}
	        }
	    });
    }
	
	private String[][] makeMovieList(int row, int col, List<Movie> mov){
		String[][] movies = new String[row][col];
		float movieRating = 0;
		
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
		
		return movies;
	}
	
	public static void main (String[] args) {
		new MovieList();
	}
}

