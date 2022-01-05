package fr.dauphine.JavaAvance.Components;

/**
 * 
 * Orientation of the piece enum
 * 
 */
public enum Orientation {
	NORTH, EAST, SOUTH, WEST;
	/* Implement all the possible orientations and 
	 *  required methods to rotate
	 */

	public static Orientation getOrifromValue(int orientationValue) {
		return null;
	}

	public Orientation getOrientation(){
		return null;
	}

	public int[] getOpposedPieceCoordinates(Piece p) {
		return null;
	}

	public Orientation getOpposedOrientation() {
		return this.getOrientation() == Orientation.NORTH ? Orientation.SOUTH :
				(this.getOrientation() == Orientation.WEST ? Orientation.EAST :
						(this.getOrientation() == Orientation.SOUTH ? Orientation.NORTH :
								(this.getOrientation() == Orientation.EAST ?
										Orientation.WEST : null)));
	}

	public Orientation turn90() {
		return this.getOrientation() == Orientation.NORTH ? Orientation.WEST :
				(this.getOrientation() == Orientation.WEST ? Orientation.SOUTH :
						(this.getOrientation() == Orientation.SOUTH ? Orientation.EAST :
								(this.getOrientation() == Orientation.EAST ?
										Orientation.NORTH : null)));
	}
}
