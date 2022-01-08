package fr.dauphine.JavaAvance.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import fr.dauphine.JavaAvance.Components.Orientation;
import fr.dauphine.JavaAvance.Components.Piece;
import fr.dauphine.JavaAvance.Components.PieceType;
import fr.dauphine.JavaAvance.Solve.Checker;
import fr.dauphine.JavaAvance.Solve.Generator;

/**
 * This class handles the GUI
 * 
 *
 */
public class GUI {

	
	private JFrame frame;

	/**
	 * 
	 * @param inputFile
	 *            String from IO
	 * @throws IOException
	 *             if there is a problem with the gui
	 */
	public static void startGUI(String inputFile) throws NullPointerException {
		// We have to check that the grid is generated before to launch the GUI
		// construction
		Runnable task = new Runnable() {
			public void run() {

				try {
					Grid grid = Checker.buildGrid(inputFile);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							GUI window;
							window = new GUI(grid);
							window.frame.setVisible(true);
						}
					});
				} catch (IOException e) {
					throw new NullPointerException("Error with input file");
				}

			}
		};
		new Thread(task).start();

	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public GUI(Grid grid) {

		initialize(grid);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize(Grid grid) {
		
		// To implement:
		// creating frame, labels
		// Implementing method mouse clicked of interface MouseListener.
		
			GridLayout gridLayout = new GridLayout(grid.getHeight(), grid.getWidth());
			this.frame = new JFrame();
			this.frame.setTitle("InfinityLoop Game Farès_Rayan");
			this.frame.setLayout(gridLayout);
			this.frame.setSize(500, 500);
			
			
			
			for (int i = 0; i < grid.getHeight(); i++) {
				for (int j = 0; j < grid.getWidth(); j++) {
					String pieceType = grid.getPiece(i, j).getType().toString();
					JButton btn_i = new JButton(pieceType);
					btn_i.setIcon(new ImageIcon("../Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/1.png"));
					this.frame.add(btn_i);

				}
			}
			
/*			for (int i = 0; i < grid.getHeight()*grid.getWidth(); i++) {
				JButton btn_i = new JButton();
				this.frame.add(btn_i);
			}
*/	
			this.frame.setLocationRelativeTo(null);
			this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.frame.setVisible(true);
			



	}

	/**
	 * Display the correct image from the piece's type and orientation
	 * 
	 * @param p
	 *            the piece
	 * @return an image icon
	 */
	private ImageIcon getImageIcon(Piece p) {
		//To be implemented
		//A finir
		ImageIcon resultImage = null;
		PieceType typeOfPiece = p.getType();
		Orientation orientation = p.getOrientation();
		
		if(typeOfPiece.toString() == "VOID") {
			
		}
		
		switch(typeOfPiece) {
		
		case VOID :
			resultImage = new ImageIcon("/Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/background.png");
		case ONECONN:
			resultImage = new ImageIcon("/Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/11.png");
		case BAR:
			resultImage = new ImageIcon("/Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/11.png");
		case TTYPE:
			resultImage = new ImageIcon("/Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/11.png");
		case FOURCONN:
			resultImage = new ImageIcon("/Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/11.png");
		case LTYPE:
			resultImage = new ImageIcon("/Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/11.png");

		}
		
		return resultImage;
	}

	
	public static void main (String[] args) {
		
		Grid grid = new Grid(5,5);
		Generator.initGrid(grid);
		GUI gui = new GUI(grid);
		
		
		
		
	}
}
