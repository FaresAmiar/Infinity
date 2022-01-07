package fr.dauphine.JavaAvance.Solve;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.dauphine.JavaAvance.Components.Orientation;
import fr.dauphine.JavaAvance.Components.Piece;
import fr.dauphine.JavaAvance.Components.PieceType;
import fr.dauphine.JavaAvance.GUI.Grid;

/**
 * Generate a solution, number of connexe composant is not finished
 *
 */

public class Generator {

	private static Grid filledGrid;

	/**
	 * @param output
	 *               file name
	 * @throws IOException
	 *                     - if an I/O error occurs.
	 * @return a File that contains a grid filled with pieces (a level)
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void generateLevel(String fileName, Grid inputGrid) {

		// To be implemented

		System.out.println("Début de la génération de votre niveau");

		for (int i = 0; i < inputGrid.getHeight(); i++) {
			for (int j = 0; j < inputGrid.getWidth(); j++) {
				if (inputGrid.isCorner(i, j)) {
					System.out.println("Coin " + i + " " + j);
					Orientation orientation = Orientation.EAST;
					PieceType typePiece = PieceType.ONECONN;

					Piece piece = new Piece(j, j, typePiece, orientation);
					inputGrid.setPiece(i, j, piece);

				}
				if (inputGrid.isBorderLine(i, j)) {
					System.out.println("Ligne en haut ou en bas " + i + " " + j);
				}
				if (inputGrid.isBorderColumn(i, j)) {
					System.out.println("Colonne Gauche ou droite " + i + " " + j);
				} else if (!inputGrid.isCorner(i, j) && !inputGrid.isBorderLine(i, j)
						&& !inputGrid.isBorderColumn(i, j)) {
					System.out.println("Autre cases " + i + " " + j);
				}
			}

		}

		System.out.println("Fin de la génération de votre niveau : Bonne chance ");
	}

	// implementation fares

	public static void initGrid(Grid grid) {
		for (int i = 0; i < grid.getHeight(); ++i) {
			for (int j = 0; j < grid.getWidth(); ++j) {
				grid.setPiece(i, j, new Piece(i, j));
			}
		}
	}

	public static void recursiveConstruct(Grid inputGrid, int i, int j) {
		Piece p = new Piece(i, j);
		if (inputGrid.allPieceHaveNeighbour())
			return;
		Piece[][] piecesBefore = inputGrid.getAllPieces();
		Random rd = new Random();

		List<PieceType> piecesPossibles = inputGrid.piecePossible(i, j);
		Collections.shuffle(piecesPossibles);
		PieceType pType = piecesPossibles.get(0);

		if (inputGrid.getNumPieces() == 0) {
			inputGrid.setPiece(i, j, p);
		}

	}

	// fin implementation fares

	public static int[] copyGrid(Grid filledGrid, Grid inputGrid, int i, int j) {
		Piece p;
		int hmax = inputGrid.getHeight();
		int wmax = inputGrid.getWidth();

		if (inputGrid.getHeight() != filledGrid.getHeight())
			hmax = filledGrid.getHeight() + i; // we must adjust hmax to have the height of the original grid
		if (inputGrid.getWidth() != filledGrid.getWidth())
			wmax = filledGrid.getWidth() + j;

		int tmpi = 0;// temporary variable to stock the last index
		int tmpj = 0;

		// DEBUG System.out.println("copyGrid : i =" + i + " & j = " + j);
		// DEBUG System.out.println("hmax = " + hmax + " - wmax = " + wmax);
		for (int x = i; x < hmax; x++) {
			for (int y = j; y < wmax; y++) {
				// DEBUG System.out.println("x = " + x + " - y = " + y);
				p = filledGrid.getPiece(x - i, y - j);
				// DEBUG System.out.println("x = " + x + " - y = " +
				// y);System.out.println(p);
				inputGrid.setPiece(x, y, new Piece(x, y, p.getType(), p.getOrientation()));
				// DEBUG System.out.println("x = " + x + " - y = " +
				// y);System.out.println(inputGrid.getPiece(x, y));
				tmpj = y;
			}
			tmpi = x;
		}
		// DEBUGSystem.out.println("tmpi =" + tmpi + " & tmpj = " + tmpj);
		return new int[] { tmpi, tmpj };
	}

	// Implementer par Rayan Test

	public static void main(String args[]) {
		Grid grid = new Grid(5, 5);
		generateLevel("null", grid);
	}
	// Fin implementation Rayan
}