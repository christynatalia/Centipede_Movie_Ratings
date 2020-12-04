package user_interface;

import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import giantsweetroll.ImageManager;
import models.Movie;
import models.Review;


public class Rating {
	 
	 //initialization
	DatabaseService ds = new DatabaseService();
	 JFrame frame = new JFrame("Centipede Movie Ratings");
	 
	
	 MovieList ml = new MovieList();
	 JFrame frameMovieList = ml.frame;
	 String moviename = ml.movieNamee;
	 
	 Login lg = new Login();
	 JFrame frameLogin = lg.frame;
	 String usernameReview = lg.usernameLogin;
		
	 float movrating = ds.countAverageMovieRating(ds.getMovieID1(moviename));
	 ImageIcon icon = createImageIcon("/assets/starIcon.png", "star icon");
	 
	 JButton addReview = new JButton("Add New Review");
	 JButton backButton = new JButton("Back");
	 JLabel ratinglabel = new JLabel("Rating");
	 JLabel movielabel = new JLabel(moviename);
	 JLabel descriptionlabel = new JLabel("Description");
	 JLabel starlabel = new JLabel(String.valueOf(movrating));
	 JLabel starIcon = new JLabel(icon);		//Icon for star
	 Font fontTitle = new Font("Arial", Font.BOLD,25);
	 
	//for the Rating title
	 JPanel panel1 = new JPanel();
	//panel 2 is for the table.
	 JPanel panel2 = new JPanel();
	 //panel 3 is for the description, movie name and rating label.
	 JPanel panel3 = new JPanel();
	//panel 4 is to merge the table with the 3 labels.
	 JPanel panel4 = new JPanel();
	 
	 JPanel panel5 = new JPanel();
	 //panel6 for the star and rating.
	 JPanel panel6 = new JPanel();
	 
	 
	 
	 
	 List<Review> rev = ds.getSelectedReviews(moviename);
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
		
		 //close another frame 
	    frameMovieList.dispose();
	    frameLogin.dispose();
		
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
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
	    panel5.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    panel6.setLayout(new FlowLayout(FlowLayout.LEFT));
	    
	    panel6.add(starIcon);
	    panel6.add(starlabel);
	    panel5.add(addReview);
	   	panel5.add(backButton);
	    panel2.add(scrollPane,BorderLayout.CENTER);
	    panel2.add(panel5,BorderLayout.SOUTH);
	    panel1.add(ratinglabel,BorderLayout.NORTH);
	    panel3.add(movielabel);
	    panel3.add(descriptionlabel);
	    panel3.add(panel6);
	    panel4.add(panel3,BorderLayout.NORTH);
	    panel4.add(panel2,BorderLayout.CENTER);
	    
	    panel1.add(panel4,BorderLayout.CENTER);

	    frame.add(panel1);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setSize(500,300);
	    frame.setVisible(true);
	    
	    addReview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new UserReview();
				frame.dispose();
				
			}
	    });
	    
	    backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				MovieList ml = new MovieList();
				ml.userLabel.setText("Welcome," + usernameReview );
				frame.dispose();
				
			}
	    });
		
	}
	
	private ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(ImageManager.scaleImage((new ImageIcon(imgURL, description)).getImage(), 16, 16));
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	private String[][] RatingList(int row, int col, List<Review> rev){
		String[][] Reviews = new String[row][col];
		float userRating = 0;
		
	
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