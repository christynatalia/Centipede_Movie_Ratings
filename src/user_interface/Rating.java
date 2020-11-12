package user_interface;

import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import database.DatabaseService;
import models.Movie;
import models.Review;


public class Rating{
	 
	 //initialization
	 JFrame frame = new JFrame("Centipede Movie Ratings");
	 JLabel ratinglabel = new JLabel("Rating");
	 
	 MovieList ml = new MovieList();
	 String moviename = ml.movieNamee;
	 JLabel movielabel = new JLabel(moviename);
	 JLabel descriptionlabel = new JLabel("Description");
	 JLabel starlabel = new JLabel("Rating");
	 Font fontTitle = new Font("Arial", Font.BOLD,25);
	 
	//for the Rating title
	 JPanel panel1 = new JPanel();
	//panel 2 is for the table.
	 JPanel panel2 = new JPanel();
	 //panel 3 is for the description, movie name and rating label.
	 JPanel panel3 = new JPanel();
	//panel 4 is to merge the table with the 3 labels.
	 JPanel panel4 = new JPanel();
	 
	 DatabaseService ds = new DatabaseService();
	 List<Review> rev = ds.getAllReviews();
	 int row = rev.size();
	 int col = 3;
	
	 String[][] reviews = RatingList(row, col, rev);
	 String[] columnNames = {"User", "Description", "Rating"};
	 
	 private TableModel tm = new DefaultTableModel(reviews, columnNames){
		 public boolean isCellEditable(int row, int column){
				return false;//This causes all cells to be not editable
			}
		};
		final JTable table = new JTable(tm);
	 
	
	public class WordWrapRenderer extends JTextArea implements TableCellRenderer
	{
	    WordWrapRenderer()
	    {
	        setLineWrap(true);
	        setWrapStyleWord(true);
	    }

	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	    {
	        setText( (value == null) ? "" : value.toString() );
	        setSize(table.getColumnModel().getColumn(column).getWidth(), table.getRowHeight(row));

	        //  Recalculate the preferred height now that the text and renderer width have been set.

	        int preferredHeight = getPreferredSize().height;
	        
	        table.setRowHeight(row, preferredHeight+15);

	        return this;
	    }
	}

	public Rating() {
		
		
		frame.setResizable(false);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
		
		table.setTableHeader(null);
		table.setFont(new Font("Arial",Font.PLAIN,16));
		table.setPreferredScrollableViewportSize(new Dimension(500, 220));
	    table.setFillsViewportHeight(true);
	    
	    for (int i = 0 ; i < 3 ; i++) {
	    table.getColumnModel().getColumn(i).setCellRenderer(new WordWrapRenderer());
	    }
	    
	    
	    
	  //Create the scroll pane and add the table to it.
	    JScrollPane scrollPane = new JScrollPane(table);

	    ratinglabel.setFont(fontTitle);

	    panel1.setLayout(new BorderLayout());
	    panel2.setLayout(new BorderLayout());
	    panel3.setLayout(new GridLayout(0,3));
	    panel4.setLayout(new BorderLayout());
	    
	    
	   
	    panel2.add(scrollPane,BorderLayout.CENTER);
	    panel1.add(ratinglabel,BorderLayout.NORTH);
	    panel3.add(movielabel);
	    panel3.add(descriptionlabel);
	    panel3.add(starlabel);
	    panel4.add(panel3,BorderLayout.NORTH);
	    panel4.add(panel2,BorderLayout.CENTER);
	    
	    panel1.add(panel4,BorderLayout.CENTER);

	    frame.add(panel1);
	    frame.pack();
	    frame.setSize(500,300);
	    frame.setVisible(true);
		
	}
	
	private String[][] RatingList(int row, int col, List<Review> rev){
		String[][] Reviews = new String[row][col];
		float userRating = 0;
		int movieID = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String moviesname = movielabel.getText();
	
		
		//get data from database and insert into lists
		for(int i=0; i<row; i++) {
			Review r = rev.get(i);
			Reviews[i][0] = ds.getUserReviewName(r.getUserID());
			Reviews[i][1] = r.getDesc();
			userRating = r.getUserRating();
			Reviews[i][2] = String.valueOf(userRating);
		}
		return Reviews;	
		}
	
	
	public static void main(String[] args) {
		new Rating();
	}
}