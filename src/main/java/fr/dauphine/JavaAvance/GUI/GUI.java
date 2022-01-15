package fr.dauphine.JavaAvance.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
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
public class GUI implements MouseListener{

	
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
	 * And implements also the mouseListener and mouse clicked function
	 * @throws IOException
	 * Author Rayan
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
			this.frame.addMouseListener(this);
			
			
		for (int i = 0; i < grid.getHeight(); i++) {
				for (int j = 0; j < grid.getWidth(); j++) {
					Piece pieceClick = grid.getPiece(i, j);
					System.out.println(pieceClick);
					String pieceType = grid.getPiece(i, j).getType().toString();
					Orientation pieceOrientation = grid.getPiece(i, j).getOrientation();
					JButton btn_i = new JButton();
					btn_i.setIcon(getImageIcon(pieceClick));
					btn_i.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {								
							pieceClick.turn();
							btn_i.setIcon(getImageIcon(pieceClick));
							System.out.println(pieceClick.getOrientation().toString());
						}
						
					});
					
					this.frame.add(btn_i);

				}
			}
	
		

			this.frame.setLocationRelativeTo(null);
			this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.frame.setVisible(true);
			



	}

	/**
	 * Display the correct image from the piece's type and connectors/orientation
	 * 
	 * @param p
	 *            the piece
	 * @return an image icon
	 * author Rayan
	 */
	private ImageIcon getImageIcon(Piece p) {
		ImageIcon resultImage = null;
		
		PieceType typeOfPiece = p.getType();
		LinkedList<Orientation> connectors = p.getConnectors();
				
		
		if(typeOfPiece == PieceType.VOID) {
			
			resultImage = new ImageIcon("");
		
		}
		
		if(typeOfPiece == PieceType.ONECONN) {
			
			if(connectors.get(0) == Orientation.NORTH) {
				resultImage = new ImageIcon("../Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/1.png");
			}
			if(connectors.get(0) == Orientation.EAST) {
				resultImage = new ImageIcon("../Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/2.png");
			}
			if(connectors.get(0) == Orientation.SOUTH) {
				resultImage = new ImageIcon("../Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/3.png");					
			}
			if(connectors.get(0) == Orientation.WEST) {
				resultImage = new ImageIcon("../Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/4.png");				
			}
			

			
		}
		
		if(typeOfPiece == PieceType.BAR) {
			
			if(connectors.get(0) == Orientation.NORTH && connectors.get(1) == Orientation.SOUTH) {
				resultImage = new ImageIcon("../Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/5.png");
			}			

			if(connectors.get(0) == Orientation.WEST && connectors.get(1) == Orientation.EAST) {
				resultImage = new ImageIcon("../Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/6.png");				
			}
			
			
		}
		
		if(typeOfPiece == PieceType.TTYPE) {
			if(connectors.get(0) == Orientation.NORTH && connectors.get(1) == Orientation.EAST && connectors.get(2) == Orientation.WEST) {
				resultImage = new ImageIcon("../Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/7.png");				
			}
			if(connectors.get(0) == Orientation.EAST && connectors.get(1) == Orientation.NORTH && connectors.get(2) == Orientation.SOUTH) {
				resultImage = new ImageIcon("../Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/8.png");
			}
			if(connectors.get(0) == Orientation.SOUTH && connectors.get(1) == Orientation.WEST && connectors.get(2) == Orientation.EAST) {
				resultImage = new ImageIcon("../Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/9.png");
			}
			if(connectors.get(0) == Orientation.WEST && connectors.get(1) == Orientation.NORTH && connectors.get(2) == Orientation.SOUTH) {
				resultImage = new ImageIcon("../Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/10.png");
			}

		}
		
		if(typeOfPiece == PieceType.FOURCONN) {
			resultImage = new ImageIcon("../Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/11.png");
		}
		
		
		if(typeOfPiece == PieceType.LTYPE) {
			
			if(connectors.get(0) == Orientation.NORTH && connectors.get(1) == Orientation.EAST) {
				resultImage = new ImageIcon("../Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/12.png");
			}
			if(connectors.get(0) == Orientation.EAST && connectors.get(1) == Orientation.SOUTH) {
				resultImage = new ImageIcon("../Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/13.png");
			}
			if(connectors.get(0) == Orientation.SOUTH && connectors.get(1) == Orientation.WEST) {
				resultImage = new ImageIcon("../Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/14.png");
			}
			if(connectors.get(0) == Orientation.WEST && connectors.get(1) == Orientation.NORTH) {
				resultImage = new ImageIcon("../Infinity/src/main/resources/fr/dauphine/JavaAvance/icons/io/15.png");
			}

			
		}
		
		return resultImage;
		
		

		
	}

	

	
	
	
	
	public static void main (String[] args) {
		
		Grid grid = new Grid(5,5);
		Generator.initGrid(grid);
		//Generator.initRandomGrid(grid);

		//Generator.initRandomGrid(grid);



		Generator.initializeQueue();

		Generator.recursiveConstruct(grid,0,0);
		
		GUI gui = new GUI(grid);
		

		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("test");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}
}
