package fr.dauphine.JavaAvance.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import fr.dauphine.JavaAvance.Components.Orientation;
import fr.dauphine.JavaAvance.Components.Piece;
import fr.dauphine.JavaAvance.Components.PieceType;
import fr.dauphine.JavaAvance.Solve.Checker;

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
		
		switch(typeOfPiece) {
		
		case VOID :
			resultImage = new ImageIcon("");
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

}
