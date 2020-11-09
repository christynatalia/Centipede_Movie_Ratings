package user_interface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

import database.DatabaseService;


public class Rating extends JPanel{
	 DatabaseService ds = new DatabaseService();
	
	
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
	
	
	private static final long serialVersionUID = 1L;
	public Rating() {
		
		String[] ratingCol = {"User", "Description", "Rating"};
		
		String[][] ratingData = {
			{"User 1", "A bit boring and i don't recommend this movie", "2.1"},
			{"User 2","OK","2.5"},
			{"User 3", "I love this movie","5.0"},
			{"User 4", "I love this movie","5.0"},
			{"User 5", "I love this movie","5.0"},
			{"User 1", "A bit boring and i don't recommend this movie", "2.1"},
			{"User 2","OK","2.5"},
			{"User 3", "I love this movie","5.0"},
			{"User 4", "I love this movie","5.0"},
			{"User 5", "I love this movie","5.0"},
			{"User 12", "Don't watch this movie.","0.0"},
			{"User 1", "A bit boring and i don't recommend this movie", "2.1"},
			{"User 2","OK","2.5"},
			{"User 3", "I love this movie","5.0"},
			{"User 4", "I love this movie","5.0"},
			{"User 5", "I love this movie","5.0"},
			{"User 1", "A bit boring and i don't recommend this movie", "2.1"}
			
			
			};
		
		
		final JTable table = new JTable(ratingData, ratingCol);
		table.setTableHeader(null);
		table.setFont(new Font("Arial",Font.PLAIN,16));
		table.setPreferredScrollableViewportSize(new Dimension(500, 220));
	    table.setFillsViewportHeight(true);
	    table.getColumnModel().getColumn(1).setCellRenderer(new WordWrapRenderer());
	    table.setCellSelectionEnabled(false);

	    
	    
	  //Create the scroll pane and add the table to it.
	    JScrollPane scrollPane = new JScrollPane(table);
	
	    //Add the scroll pane to this panel.
	    add(scrollPane);
		
	}
	
	

	

	
	
	private static void Initialize() {
		JFrame frame = new JFrame("Centipede Movie Ratings");
		frame.setResizable(false);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    
	    Font fontTitle = new Font("Arial", Font.BOLD,25);
	    
	    //Rating label
	    JLabel ratinglabel = new JLabel("Rating");
	    ratinglabel.setFont(fontTitle);
	    
	    //Movie label
	    JLabel movielabel = new JLabel("Movie");
	    
	    //Description label
	    JLabel descriptionlabel = new JLabel("Description");
	    
	    //Movie label
	    JLabel starlabel = new JLabel("Rating");
	 
	    
	    
	    //for the Rating title
	    JPanel panel1 = new JPanel();
	    panel1.setLayout(new BorderLayout());
	    panel1.add(ratinglabel,BorderLayout.NORTH);
	    
	    //panel 2 is for the table.
	    JPanel panel2 = new JPanel();
	    panel2.setLayout(new BorderLayout());
	    Rating newRatingList = new Rating();
	    panel2.add(newRatingList,BorderLayout.SOUTH);
	    //panel1.add(panel2);
	    
	    
	    //panel 3 is for the description, movie name and rating label.
	    JPanel panel3 = new JPanel();
	    panel3.setLayout(new GridLayout(0,3));
	    panel3.add(movielabel);
	    panel3.add(descriptionlabel);
	    panel3.add(starlabel);
	    

	    
	    //panel 4 is to merge the table with the 3 labels.
	    JPanel panel4 = new JPanel();
	    panel4.setLayout(new BorderLayout());
	    panel4.add(panel3,BorderLayout.NORTH);
	    panel4.add(panel2,BorderLayout.SOUTH);
	    
	    panel1.add(panel4,BorderLayout.SOUTH);

	    frame.add(panel1);
	    frame.pack();
	    frame.setSize(500,300);
	    frame.setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		Initialize();
	}
}
