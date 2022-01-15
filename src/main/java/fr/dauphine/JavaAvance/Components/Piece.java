package fr.dauphine.JavaAvance.Components;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Handling of pieces with general functions
 */
public class Piece {
	private int posX;// j
	private int posY;// i
	private PieceType type; 
	private Orientation orientation;
	private LinkedList<Orientation> connectors; //connectors of the piece
	private ArrayList<Orientation> possibleOrientations; 
	private int badNeighbors; //neighbors which are not adapted to this piece / orientation

	private boolean isFixed;

	public Piece(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.type = PieceType.VOID;
		this.orientation = type.getOrientation(Orientation.NORTH);
		this.connectors = type.setConnectorsList(Orientation.NORTH);
		this.isFixed = false; // Is there any orientation for the piece
		this.possibleOrientations = type.getListOfPossibleOri();
	}

	public Piece(int posX, int posY, PieceType type, Orientation orientation) {
		this.posX = posX;
		this.posY = posY;
		this.type = type;
		this.orientation = type.getOrientation(orientation);
		this.connectors = type.setConnectorsList(orientation);
		this.isFixed = false;
		this.possibleOrientations = type.getListOfPossibleOri();
	}

	public Piece(int posX, int posY, int typeValue, int orientationValue) {
		this.posX = posX;
		this.posY = posY;
		this.type = PieceType.getTypefromValue(typeValue);
		this.orientation = type.getOrientation(Orientation.getOrifromValue(orientationValue));
		this.connectors = type.setConnectorsList(Orientation.getOrifromValue(orientationValue));
		this.isFixed = false;
		this.possibleOrientations = type.getListOfPossibleOri();
	}

	public Piece(Piece p) {
		posX = p.posX;
		posY = p.posY;
		type = p.getType();
		orientation = p.getOrientation();
		connectors = p.getConnectors();
		isFixed = false;
		possibleOrientations = p.getPossibleOrientations();
	}

	/**
	 * Get bad neighbors number
	 * @return
	 */
	public int getBadNeighbors() { return badNeighbors; }

	/**
	 * Set bad neighbors value
	 * @param badNeighbors
	 */
	public void setBadNeighbors(int badNeighbors) { this.badNeighbors = badNeighbors; }

	/**
	 * Reset the number of bad neighbors
	 */
	public void resetBadNeighbors() { this.badNeighbors = 0; }

	
	/** 
	 * @param possibleOrientations
	 */
	public void setPossibleOrientations(ArrayList<Orientation> possibleOrientations) {
		this.possibleOrientations = possibleOrientations;
	}

	
	/** 
	 * @return ArrayList<Orientation>
	 */
	public ArrayList<Orientation> getPossibleOrientations() {
		return this.possibleOrientations;
	}

	
	/** Get the orientation possible inverted
	 * @return LinkedList<Orientation>
	 */
	public LinkedList<Orientation> getInvPossibleOrientation() {
		LinkedList<Orientation> invPossibleOrientations = new LinkedList<>();
		for (Orientation ori : this.getPossibleOrientations()) {
			invPossibleOrientations.addFirst(ori);
		}
		return invPossibleOrientations;
	}

	
	/** Delete ori from possible orientations
	 * @param ori
	 */
	public void deleteFromPossibleOrientation(Orientation ori) {
		if (this.possibleOrientations.contains(ori)) {
			this.possibleOrientations.remove(ori);
		}
	}

	
	/** 
	 * @param isFixed
	 */
	public void setFixed(boolean isFixed) {
		this.isFixed = isFixed;
	}

	
	/** 
	 * @return boolean
	 */
	public boolean isFixed() {
		return isFixed;
	}

	
	/** 
	 * @param posX
	 * @return int
	 */
	public int getPosX() { // get j
		return posX;
	}

	
	/** 
	 * @param posX
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	
	/** 
	 * @param posY
	 * @return int
	 */
	public int getPosY() { // get i
		return posY;
	}

	
	/** 
	 * @param posY
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	
	/** 
	 * @return PieceType
	 */
	public PieceType getType() {
		return type;
	}

	
	/** set type 
	 * @param type
	 */
	public void setType(PieceType type) {
		this.type = type;
	}

	
	/** Set orientation of the piece and adapt connectors
	 * @param orientationValue
	 */
	public void setOrientation(int orientationValue) {
		this.orientation = type.getOrientation(Orientation.getOrifromValue(orientationValue));
		this.connectors = type.setConnectorsList(this.orientation);
	}

	
	/** Return orientation value
	 * @return Orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}
	
	
	/** Set the orientation of the piece and adapt connectors
	 * @param orientation
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
		this.connectors = type.setConnectorsList(this.orientation);
	}

	

	
	/** Return all connectors
	 * @return LinkedList<Orientation>
	 */
	public LinkedList<Orientation> getConnectors() {
		return connectors;
	}

	
	/** Return if it has a top connector
	 * @return boolean
	 */
	public boolean hasTopConnector() {
		for (Orientation ori : this.getConnectors()) {
			if (ori == Orientation.NORTH) {
				return true;
			}
		}
		return false;
	}

	
	/** Return if it has a right connector
	 * @return boolean
	 */
	public boolean hasRightConnector() {
		for (Orientation ori : this.getConnectors()) {
			if (ori == Orientation.EAST) {
				return true;
			}
		}
		return false;
	}

	
	/** Return if it has a bottom connector
	 * @return boolean
	 */
	public boolean hasBottomConnector() {
		for (Orientation ori : this.getConnectors()) {
			if (ori == Orientation.SOUTH) {
				return true;
			}
		}
		return false;
	}

	
	/** Return if it has a left connector
	 * @return boolean
	 */
	public boolean hasLeftConnector() {
		for (Orientation ori : this.getConnectors()) {
			if (ori == Orientation.WEST) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Turn the piece 90° on the right and redefine the connectors's position
	 */
	public void turn() {
		this.orientation = type.getOrientation(orientation.turn90());
		this.connectors = type.setConnectorsList(orientation);
	}

	
	/** 
	 * @return String
	 */
	@Override
	public String toString() {
		String s = "[" + this.getPosX() + ", " + this.getPosY() + "] " + this.getType() + " ";
		for (Orientation ori : this.getConnectors()) {
			s += " " + ori.toString();
		}
		s += " Orientation / " + this.getOrientation();
		return s;
	}

}
