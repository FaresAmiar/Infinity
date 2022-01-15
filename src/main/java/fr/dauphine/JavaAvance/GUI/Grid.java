package fr.dauphine.JavaAvance.GUI;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import fr.dauphine.JavaAvance.Components.Orientation;
import fr.dauphine.JavaAvance.Components.Piece;
import fr.dauphine.JavaAvance.Components.PieceType;

/**
 * Grid handler and peces'functions which depends of the grid
 * 
 *
 */
public class Grid {
	private int width; // j
	private int height; // i
	private int nbcc = -1;
	private Piece[][] pieces;

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		pieces = new Piece[height][width];
	}

	// Constructor with specified number of connected component
	public Grid(int width, int height, int nbcc) {
		this.width = width;
		this.height = height;
		this.nbcc = nbcc;
		pieces = new Piece[height][width];
	}

	
	/** 
	 * @return int
	 */
	public int getWidth() {
		return width;
	}

	
	/** 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	
	/** 
	 * @return int
	 */
	public int getHeight() {
		return height;
	}

	
	/** 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	
	/** 
	 * @return Integer
	 */
	public Integer getNbcc() {
		return nbcc;
	}

	
	/** 
	 * @param nbcc
	 */
	public void setNbcc(int nbcc) {
		this.nbcc = nbcc;
	}

	
	/** 
	 * @param line
	 * @param column
	 * @return Piece
	 */
	public Piece getPiece(int line, int column) {
		return this.pieces[line][column];
	}

	
	/** 
	 * @param line
	 * @param column
	 * @param piece
	 */
	public void setPiece(int line, int column, Piece piece) {
		this.pieces[line][column] = piece;
	}

	
	/** 
	 * @return Piece[][]
	 */
	public Piece[][] getAllPieces() {
		return pieces;
	}

	
	/** 
	 * @param p
	 * @return List<Orientation>
	 */
	//f
	public List<Orientation> getConnectorsEmpty(Piece p) {
		List<Orientation> connEmpty = new ArrayList<Orientation>();
		if(p.hasLeftConnector() && this.leftNeighbor(p) != null && this.leftNeighbor(p).getType() == PieceType.VOID)
			connEmpty.add(Orientation.WEST);
		if(p.hasRightConnector() && rightNeighbor(p) != null && this.rightNeighbor(p).getType() == PieceType.VOID)
			connEmpty.add(Orientation.EAST);
		if(p.hasTopConnector() && topNeighbor(p) != null && this.topNeighbor(p).getType() == PieceType.VOID)
			connEmpty.add(Orientation.NORTH);
		if(p.hasBottomConnector() && bottomNeighbor(p) != null && this.bottomNeighbor(p).getType() == PieceType.VOID)
			connEmpty.add(Orientation.SOUTH);

		return connEmpty;

	}

	
	/** 
	 * Vérifie les cases vides adjacentes d'une pièce
	 * @param p : piece
	 * @return List<Orientation>
	 */
	//f
	public List<Orientation> getFreeAdjacentCell(Piece p) {
		int i = p.getPosX(), j = p.getPosY();
		List<Orientation> oris = new ArrayList<Orientation>();

		if(i == 0) 
			if(j == 0) 
				oris.addAll(0, Arrays.asList(Orientation.EAST, Orientation.SOUTH));
			else if(j == this.getWidth() - 1)
				oris.addAll(0, Arrays.asList(Orientation.WEST, Orientation.SOUTH));
			else
				oris.addAll(0, Arrays.asList(Orientation.WEST,Orientation.EAST, Orientation.SOUTH));
		else if(i == this.getHeight() - 1)
			if(j == 0)
				oris.addAll(0, Arrays.asList(Orientation.NORTH, Orientation.EAST));
			else if(j == this.getWidth() - 1)
				oris.addAll(0, Arrays.asList(Orientation.WEST, Orientation.NORTH));
		else
			if(j == 0)
				oris.addAll(0, Arrays.asList(Orientation.NORTH, Orientation.EAST, Orientation.SOUTH));
			else if(j == this.getWidth() - 1)
				oris.addAll(0, Arrays.asList(Orientation.EAST, Orientation.NORTH, Orientation.SOUTH));
			else 
				oris.addAll(0, Arrays.asList(Orientation.NORTH, Orientation.EAST, Orientation.SOUTH, Orientation.WEST));

		if(this.leftNeighbor(p) != null && this.leftNeighbor(p).getType() != PieceType.VOID || !p.hasLeftConnector())
			oris.remove(Orientation.WEST);

		if(this.rightNeighbor(p) != null && this.rightNeighbor(p).getType() != PieceType.VOID || !p.hasRightConnector())
			oris.remove(Orientation.EAST);

		if(this.topNeighbor(p) != null && this.topNeighbor(p).getType() != PieceType.VOID || !p.hasTopConnector())
			oris.remove(Orientation.NORTH);

		if(this.bottomNeighbor(p) != null && this.bottomNeighbor(p).getType() != PieceType.VOID || !p.hasBottomConnector())
			oris.remove(Orientation.SOUTH);

		return oris;
	}

	
	/** 
	 * Retourne les coordonnées des cases sur lequel un connecteur vide va pointer
	 * @param p : pièce
	 * @param orientation : prochaine orientation choisie (connecteur)
	 * @return Integer[][]
	 */
	public Integer[][] getVoidConnectorsCoords(Piece p, Orientation orientation){
		/*if(p.getType() != PieceType.TTYPE || p.getType() != PieceType.FOURCONN)
			throw new IllegalArgumentException("The piece type must be either TTYPE or FOURCONN");*/
		Integer[][] coords = null;
		int i = 0;
		
		if(PieceType.VOID == p.getType())
			return null;
		
		//Si un connecteur vide autre que celui a etendre est présent 
		List<Orientation> connects = getConnectorsEmpty(p);
		connects.remove(orientation);

		

		//Pour le FOURCONN, si on choisi un connecteur à étendre, 2 seront vides et en attente
/*		if(p.getType() == PieceType.FOURCONN) { 
			coords = new Integer[][2];
		}*/

		//Pour le TTYPE, si on choisi un connecteur à étendre, 1 sera vide et en attente
		/*if(p.getType() == PieceType.TTYPE)
			coords = new Integer[1][2];*/

		coords = new Integer[connects.size()][2];

		for(Orientation o : connects) {
				coords[i++] = Arrays.stream(orientationToCoordinates(o, p.getPosX(), p.getPosY())).boxed().toArray(Integer[]::new);

		}
		return coords;
	}

	
	/** 
	 * Transforme une orientation en des coordonnées à partir de i et j
	 * @param ori
	 * @param i
	 * @param j
	 * @return int[]
	 */
	public static int[] orientationToCoordinates(Orientation ori, int i, int j) {
		int[] coords = new int[2];
		switch (ori) {
			case NORTH:
				coords[0] = i - 1;
				break;
			case SOUTH:
				coords[0] = i + 1;
				break;
			case EAST:
				coords[1] = j + 1;
				break;
			case WEST: 
				coords[1] = j - 1;
		}

		return coords;
	}


	
	/** 
	 * Retourne le type de piece possible pour les coins
	 * @return List<PieceType>
	 */
	public List<PieceType> getPieceTypeCorner() {
		return new ArrayList<PieceType>(Arrays.asList(PieceType.ONECONN, PieceType.LTYPE));
	}

	
	/** 
	 * Retourne la liste de piece possible pour la case i,j donnée
	 * @param i
	 * @param j
	 * @return List<PieceType>
	 */
	public List<PieceType> piecePossible(int i, int j) {

		//On crée une liste avec toutes les possibilités qu'on réduira au fil du temps
		List<PieceType> possible = new ArrayList<PieceType>(
			Arrays.asList(PieceType.ONECONN, PieceType.LTYPE, PieceType.BAR, PieceType.FOURCONN,
					PieceType.TTYPE));

		//Liste des piecestype avec un ordre particulier, sans le 0 (VOID)
		LinkedList<Integer> pTypesTest = new LinkedList<Integer>(Arrays.asList(4, 3, 2, 5, 1));
		
		//Si on est dans un coin : seulement 2 types possibles : LTYPE et ONECONN
		if (this.isCorner(i, j))
			possible.removeAll(new ArrayList<>(Arrays.asList(PieceType.BAR, PieceType.FOURCONN, PieceType.TTYPE)));
		
		//En bordure, on a pas le droit a FOURCONN car un connecteur sera forcement outside
			else if(this.isBorderColumn(i, j) || this.isBorderLine(i, j))
			possible.remove(PieceType.FOURCONN);

		//Si la piece n'est pas entourée par des voisins, on retourne directement toute la liste
		if(numberOfNeibours(new Piece(i,j)) == 0)
			return possible;
		
		Piece p, piece;
		Orientation orientation;
		Map<PieceType,List<Integer>> badNeighborsForPiece = new HashMap<PieceType,List<Integer>>(); //cette map va permettre pour chaque orientation de chaque type de piece de savoir le nombre voisins gênants 
		List<Integer> bdNeis = null;
		PieceType pt = null;
		Set<Entry<Piece, Orientation>> neighbors;
		Map<Piece,Orientation> neighs;

		for(Integer inte : pTypesTest){
			for(int k = 0; k < 3; ++k) {
				bdNeis = new ArrayList<Integer>();
				pt = PieceType.getTypefromValue((inte));
				p = new Piece(i,j,pt, Orientation.getOrifromValue(k));
				neighs = getNeighbourWithDirection(p);
				neighbors = getNeighbourWithDirection(p).entrySet();

				for(Map.Entry<Piece, Orientation> e : neighbors){
					piece = e.getKey();
					orientation = e.getValue();

					if(piece.getType() != PieceType.VOID && (!hasConnector(piece, orientation.getOpposedOrientation())
					|| !hasConnector(p, orientation) || connectorsOutSide(p).size() != 0))
						p.setBadNeighbors(p.getBadNeighbors() + 1);
					
				}
				bdNeis.add(p.getBadNeighbors());
			}
			badNeighborsForPiece.put(pt,bdNeis);
		}

		//On va trier les listes pour chaque piece et retenir le plus petit nbBadNeighbors
		//Ensuite on prend la/les PieceType avec le plus petit et si plusieurs on les ajoutes dans la liste finale

		Set<Entry<PieceType, List<Integer>>> typeAndBadNei = badNeighborsForPiece.entrySet();
		int minVoisPerType;
		Map<Integer, PieceType> minBadNeisForType = new TreeMap<Integer, PieceType>((Comparator<Integer>) (o1, o2) -> o1.compareTo(o2));
	
		for(Map.Entry<PieceType, List<Integer>> entry : typeAndBadNei) {
			pt = entry.getKey();
			bdNeis = entry.getValue();

			Collections.sort(bdNeis);
			//Pour chaque PieceType, on prend le minBadNeis qui va concourrir pour rester dans les minimums de la liste
			minVoisPerType = (Integer) bdNeis.get(0);
			minBadNeisForType.put(minVoisPerType, pt);
			
		}

		int min = Integer.MAX_VALUE;

		PieceType last;
		int k = 0;
		

		for(Map.Entry<Integer, PieceType> entry : minBadNeisForType.entrySet()) {
			if(k == 0) {
				min = entry.getKey();
				++k;
				continue;
			}
			//Toutes les PieceType qui sont strictement supérieures au minimum sont supprimés
			if(entry.getKey() > min) {
				possible.remove(entry.getValue());
			}
			++k;
		}

		
		return possible;

	}

	
	/** 
	 * Trouve les connecteurs qui pointent hors de la grid
	 * @param p : piece
	 * @return List<Orientation>
	 */
	//f
	public List<Orientation> connectorsOutSide(Piece p) {
		List<Orientation> connectors = new ArrayList<Orientation>();
		if(p.getPosX() == 0 && p.hasTopConnector())
			connectors.add(Orientation.NORTH);

		if(p.getPosX() == getHeight() - 1 && p.hasBottomConnector())
			connectors.add(Orientation.SOUTH);

		if(p.getPosY() == 0 && p.hasLeftConnector())
			connectors.add(Orientation.WEST);

		if(p.getPosY() == getWidth() - 1 && p.hasRightConnector())
			connectors.add(Orientation.EAST);

		return connectors;

	}

	
	/** 
	 * Retourne si p a un connecteur dans l'orientation o
	 * @param p : piece
	 * @param o : orientation
	 * @return boolean
	 */
	//f
	public boolean hasConnector(Piece p, Orientation o) {
		if(o == Orientation.WEST && p.hasLeftConnector())
			return true;
		if(o == Orientation.EAST && p.hasRightConnector())
			return true;
		if(o == Orientation.NORTH && p.hasTopConnector())
			return true;
		if(o == Orientation.SOUTH && p.hasBottomConnector())
			return true;
		return false;	
	}

	
	/** 
	 * Permet d'obtenir les voisins avec leur direction relative a p
	 * @param p : piece
	 * @return Map<Piece, Orientation>
	 */
	//f
	public Map<Piece,Orientation> getNeighbourWithDirection(Piece p) {
		Map<Piece, Orientation> m = new HashMap();

		//Regarde pour tous les voisins leur position
		for(Piece pp : listOfNeighbours(p)) {
			if(p.getPosX() - pp.getPosX() > 0)
				m.put(pp, Orientation.NORTH);
			else if(p.getPosX() - pp.getPosX() < 0)
				m.put(pp, Orientation.SOUTH);
			if(p.getPosY() - pp.getPosY() > 0)
				m.put(pp, Orientation.WEST);
			else if(p.getPosY() - pp.getPosY() > 0)
				m.put(pp, Orientation.EAST);
		}

		return m;

	}

	
	/** 
	 * Avoir le nombre de piece totales
	 * @return int
	 */
	// f
	public int getNumPieces() {
		return pieces.length * pieces[0].length;
	}

	/**
	 * Check if a case is a corner
	 * 
	 * @param line
	 * @param column
	 * @return true if the case is a corner
	 */
	public boolean isCorner(int line, int column) {
		if (line == 0) {
			if (column == 0)
				return true;
			if (column == this.getWidth() - 1)
				return true;
			return false;
		} else if (line == this.getHeight() - 1) {
			if (column == 0)
				return true;
			if (column == this.getWidth() - 1)
				return true;
			return false;
		} else {
			return false;
		}
	}

	/**
	 * Check if a case is member of the first or the last line
	 * 
	 * @param line
	 * @param column
	 * @return true if the case is a corner
	 */
	public boolean isBorderLine(int line, int column) {
		if (line == 0 && column > 0 && column < this.getWidth() - 1) {
			return true;
		} else if (line == this.getHeight() - 1 && column > 0 && column < this.getWidth() - 1) {
			return true;
		}
		return false;

	}

	/**
	 * Check if a case is member of the first or the last column
	 * 
	 * @param line
	 * @param column
	 * @return true if the case is a corner
	 */
	public boolean isBorderColumn(int line, int column) {
		if (column == 0 && line > 0 && line < this.getHeight() - 1) {
			return true;
		} else if (column == this.getWidth() - 1 && line > 0 && line < this.getHeight() - 1) {
			return true;
		}
		return false;

	}

	/**
	 * Check if a piece has a neighbour for its connectors for one orientation
	 * 
	 * @param p
	 *          piece
	 * @return true if there is a neighbour for all connectors
	 */
	public boolean hasNeighbour(Piece p) {
		for (Orientation ori : p.getConnectors()) {
			int oppPieceY = ori.getOpposedPieceCoordinates(p)[0];// i
			int oppPieceX = ori.getOpposedPieceCoordinates(p)[1];// j
			try {
				if (this.getPiece(oppPieceY, oppPieceX).getType() == PieceType.VOID) {
					return false;
				}

			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}
		}
		return true;

	}

	/**
	 * Check if a piece has a fixed neighbor for each one of its connecotrs
	 * 
	 * @param p
	 *          the piece
	 * @return true if there is a fixed piece for each connector
	 */
	public boolean hasFixedNeighbour(Piece p) {
		boolean bool = false;
		for (Orientation ori : p.getConnectors()) {
			bool = false;
			int oppPieceY = ori.getOpposedPieceCoordinates(p)[0];// i
			int oppPieceX = ori.getOpposedPieceCoordinates(p)[1];// j
			try {
				Piece neigh = this.getPiece(oppPieceY, oppPieceX);
				if (neigh.getType() == PieceType.VOID || !neigh.isFixed()) {
					return false;
				}
				if (neigh.isFixed()) {
					for (Orientation oriOppPiece : neigh.getConnectors()) {
						if (ori == oriOppPiece.getOpposedOrientation()) {
							bool = true;
						}
					}
					if (!bool) {
						return false;
					}

				}
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}
		}
		return bool;
	}

	/**
	 * Check if a piece has a at least one fixed neighbor
	 * 
	 * @param p
	 *          the piece
	 * @return true if there is a fixed piece for each connector
	 */
	public boolean hasAtLeast1FixedNeighbour(Piece p) {
		for (Orientation ori : p.getConnectors()) {
			int oppPieceY = ori.getOpposedPieceCoordinates(p)[0];// i
			int oppPieceX = ori.getOpposedPieceCoordinates(p)[1];// j
			try {
				Piece neigh = this.getPiece(oppPieceY, oppPieceX);
				if (neigh.isFixed()) {
					for (Orientation oriOppPiece : neigh.getConnectors()) {
						if (ori == oriOppPiece.getOpposedOrientation()) {
							return true;
						}
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * list of neighbors
	 * 
	 * @param p
	 *          the piece
	 * @return the list of neighbors
	 */
	public ArrayList<Piece> listOfNeighbours(Piece p) {
		ArrayList<Piece> lp = new ArrayList<>();
		for (Orientation ori : p.getConnectors()) {
			int oppPieceY = ori.getOpposedPieceCoordinates(p)[0];// i
			int oppPieceX = ori.getOpposedPieceCoordinates(p)[1];// j

			if (oppPieceY >= 0 && oppPieceY < this.getHeight() && oppPieceX >= 0 && oppPieceX < this.width) {
				if (this.getPiece(oppPieceY, oppPieceX).getType() != PieceType.VOID) {
					lp.add(this.getPiece(oppPieceY, oppPieceX));
				}
			}

		}
		return lp;
	}

	/**
	 * this function returns the number of neighbors
	 * 
	 * @param p
	 * @return the number of neighbors
	 */
	public int numberOfNeibours(Piece p) {
		int X = p.getPosX();
		int Y = p.getPosY();
		int count = 0;
		if (Y < this.getHeight() - 1 && getPiece(Y + 1, X).getType() != PieceType.VOID)
			count++;
		if (X < this.getWidth() - 1 && getPiece(Y, X + 1).getType() != PieceType.VOID)
			count++;
		if (Y > 0 && getPiece(Y - 1, X).getType() != PieceType.VOID)
			count++;
		if (X > 0 && getPiece(Y, X - 1).getType() != PieceType.VOID)
			count++;
		return count;
	}

	/**
	 * this function returns the number of fixed neighbors
	 * 
	 * @param p
	 * @return the number of neighbors
	 */
	public int numberOfFixedNeibours(Piece p) {
		int X = p.getPosX();
		int Y = p.getPosY();
		int count = 0;

		if (Y < this.getHeight() - 1 && getPiece(X + 1, Y).getType() != PieceType.VOID && getPiece(X + 1, Y).isFixed())
			count++;
		if (X < this.getWidth() - 1 && getPiece(X, Y + 1).getType() != PieceType.VOID && getPiece(X, Y + 1).isFixed())
			count++;
		if (Y > 0 && getPiece(X - 1, Y).getType() != PieceType.VOID && getPiece(X - 1, Y).isFixed())
			count++;
		if (X > 0 && getPiece(X, Y - 1).getType() != PieceType.VOID && getPiece(X, Y - 1).isFixed())
			count++;
		return count;
	}

	
	/** 
	 * Vérifie si toutes les pieces sont fixées
	 * @return boolean
	 */
	//f
	public boolean allPiecesAreFixed() {
		for(Piece[] ligne : this.getAllPieces())
			for(Piece p : ligne)
				if(!this.hasFixedNeighbour(p))
					return false;
		return true;
	} 

	/**
	 * Check if all pieces have neighbors even if we don't know the orientation
	 * 
	 * @param p
	 * @return false if a piece has no neighbor
	 */
	public boolean allPieceHaveNeighbour() {

		for (Piece[] ligne : this.getAllPieces()) {
			for (Piece p : ligne) {

				if (p.getType() != PieceType.VOID) {
					if (p.getType().getNbConnectors() > numberOfNeibours(p)) {
						return false;
					}
				}

			}
		}
		return true;

	}

	/**
	 * Return the next piece of the current piece
	 * 
	 * @param p
	 *          the current piece
	 * @return the piece or null if p is the last piece
	 */
	public Piece getNextPiece(Piece p) {
		int i = p.getPosX();
		int j = p.getPosY();
		if (j < this.getWidth() - 1) {
			p = this.getPiece(i, j + 1);
		} else {
			if (i < this.getHeight() - 1) {
				p = this.getPiece(i + 1, 0);
			} else {
				return null;
			}

		}
		return p;
	}

	/**
	 * Return the next piece of the current piece right2left and bottom2top
	 * 
	 * @param p
	 *          the current piece
	 * @return the piece or null if p is the last piece
	 */
	public Piece getNextPieceInv(Piece p) {

		int i = p.getPosX();
		int j = p.getPosY();
		if (j > 0) {
			p = this.getPiece(i, j - 1);
		} else {
			if (i > 0) {
				p = this.getPiece(i - 1, this.getWidth() - 1);
			} else {
				return null;
			}

		}

		return p;

	}

	/**
	 * Check if a piece is connected
	 * 
	 * @param line
	 * @param column
	 * @return true if a connector of a piece is connected
	 */
	public boolean isConnected(Piece p, Orientation ori) {
		int oppPieceY = ori.getOpposedPieceCoordinates(p)[0];// i
		int oppPieceX = ori.getOpposedPieceCoordinates(p)[1];// j
		if (p.getType() == PieceType.VOID)
			return true;
		try {
			for (Orientation oppConnector : this.getPiece(oppPieceY, oppPieceX).getConnectors()) {
				if (oppConnector == ori.getOpposedOrientation()) {
					return true;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return false;
	}

	/**
	 * Check if a piece is totally connected
	 * 
	 * @param line
	 * @param column
	 * @return true if a connector of a piece is connected
	 */
	public boolean isTotallyConnected(Piece p) {
		if (p.getType() != PieceType.VOID) {
			for (Orientation connector : p.getConnectors()) {
				if (!this.isConnected(p, connector)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Check if a piece position is valid
	 * 
	 * @param line
	 * @param column
	 * @return true if a connector of a piece is connected
	 */
	public boolean isValidOrientation(int line, int column) {
//a corriger
		Piece tn = this.topNeighbor(this.getPiece(line, column));
		Piece ln = this.leftNeighbor(this.getPiece(line, column));
		Piece rn = this.rightNeighbor(this.getPiece(line, column));
		Piece bn = this.bottomNeighbor(this.getPiece(line, column));

		if (this.getPiece(line, column).getType() != PieceType.VOID) {
			if (line == 0) {
				if (column == 0) {
					if (this.getPiece(line, column).hasLeftConnector())
						return false;
				} else if (column == this.getWidth() - 1) {
					if (this.getPiece(line, column).hasRightConnector())
						return false;
				}
				if (this.getPiece(line, column).hasTopConnector())
					return false;
				if (!this.getPiece(line, column).hasRightConnector() && rn != null && rn.getType() != PieceType.VOID && rn.hasLeftConnector())
					return false;
				if (this.getPiece(line, column).hasRightConnector() && rn != null && rn.getType() != PieceType.VOID && !rn.hasLeftConnector())
					return false;
				if (!this.getPiece(line, column).hasBottomConnector() && bn != null && bn.getType() != PieceType.VOID && bn.hasTopConnector())
					return false;
				if (this.getPiece(line, column).hasBottomConnector() && bn != null && bn.getType() != PieceType.VOID && !bn.hasTopConnector())
					return false;

			} else if (line > 0 && line < this.getHeight() - 1) {
				if (column == 0) {
					if (this.getPiece(line, column).hasLeftConnector())
						return false;

				} else if (column == this.getWidth() - 1) {
					if (this.getPiece(line, column).hasRightConnector())
						return false;
				}

				if (!this.getPiece(line, column).hasRightConnector() && rn != null && rn.getType() != PieceType.VOID && rn.hasLeftConnector())
					return false;
				if (this.getPiece(line, column).hasRightConnector() && rn != null && rn.getType() != PieceType.VOID && !rn.hasLeftConnector())
					return false;
				if (!this.getPiece(line, column).hasBottomConnector() && bn != null && bn.getType() != PieceType.VOID && bn.hasTopConnector())
					return false;
				if (this.getPiece(line, column).hasBottomConnector() && bn != null && bn.getType() != PieceType.VOID && !bn.hasTopConnector())
					return false;

			} else if (line == this.getHeight() - 1) {
				if (column == 0) {
					if (this.getPiece(line, column).hasLeftConnector())
						return false;
				} else if (column == this.getWidth() - 1) {
					if (this.getPiece(line, column).hasRightConnector())
						return false;
				}
				if (this.getPiece(line, column).hasBottomConnector())
					return false;
				if (!this.getPiece(line, column).hasRightConnector() && rn != null && rn.getType() != PieceType.VOID && rn.hasLeftConnector())
					return false;
				if (this.getPiece(line, column).hasRightConnector() && rn != null && rn.getType() != PieceType.VOID && !rn.hasLeftConnector())
					return false;

			}
			if (this.getPiece(line, column).hasLeftConnector() && ln == null)
				return false;
			if (this.getPiece(line, column).hasTopConnector() && tn == null)
				return false;
			if (this.getPiece(line, column).hasRightConnector() && rn == null)
				return false;
			if (this.getPiece(line, column).hasBottomConnector() && bn == null)
				return false;
		}

		return true;
	}

	/**
	 * Find the left neighbor
	 * 
	 * @param p
	 * @return the neighbor or null if no neighbor
	 */
	public Piece leftNeighbor(Piece p) {

		if (p.getPosY() > 0) {
			if (this.getPiece(p.getPosX(), p.getPosY() - 1) != null) {
				return this.getPiece(p.getPosX(), p.getPosY() - 1);
			}
		}
		return null;
	}

	/**
	 * Find the top neighbor
	 * 
	 * @param p
	 * @return the neighbor or null if no neighbor
	 */
	public Piece topNeighbor(Piece p) {

		if (p.getPosX() > 0) {
			if (this.getPiece(p.getPosX() - 1, p.getPosY())!= null) {
				return this.getPiece(p.getPosX() - 1, p.getPosY());
			}
		}
		return null;
	}

	/**
	 * Find the right neighbor
	 * 
	 * @param p
	 * @return the neighbor or null if no neighbor
	 */
	public Piece rightNeighbor(Piece p) {
		if (p.getPosY() < this.getWidth() - 1) 
			if (this.getPiece(p.getPosX(), p.getPosY() + 1) != null) 
				return this.getPiece(p.getPosX(), p.getPosY() + 1);
		return null;
	}

	/**
	 * Find the bottom neighbor
	 * 
	 * @param p
	 * @return the neighbor or null if no neighbor
	 */
	public Piece bottomNeighbor(Piece p) {

		if (p.getPosX() < this.getHeight() - 1) {
			if (this.getPiece(p.getPosX() + 1, p.getPosY()) != null) {
				return this.getPiece(p.getPosX() + 1, p.getPosY());
			}	
		}
		return null;
	}

	
	/** 
	 * @return String
	 */
	@Override
	public String toString() {

		String s = "";
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				s += DisplayUnicode.getUnicodeOfPiece(pieces[i][j].getType(), pieces[i][j].getOrientation());
			}
			s += "\n";
		}
		return s;
	}

	
	public void displayGrid(){
		StringBuilder sb = new StringBuilder();
		Piece p;
		for(int i = 0; i<this.getHeight(); i++) {
			System.out.println("");
			for(int j = 0; j<this.getWidth(); j++) {
				p = this.getPiece(i,j);
				if(p.hasLeftConnector())
					sb.append("L");
				if(p.hasRightConnector())
					sb.append("R");
				if(p.hasTopConnector())
					sb.append("U");
				if(p.hasBottomConnector())
					sb.append("D");
					
				System.out.print("| "+p.getType()+" " + sb.toString() +" |");
				
				sb = new StringBuilder();
			}
		}
		
	}

}
