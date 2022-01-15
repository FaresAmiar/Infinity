package fr.dauphine.JavaAvance.Components;

/**
 * 
 * Orientation of the piece enum
 * 
 */
public enum Orientation {
	NORTH, EAST, SOUTH, WEST;
	/*
	 * Implement all the possible orientations and required methods to rotate
	 */

	/**
	 * Return the orientation from value
	 * 
	 * @param orientationValue
	 * @return
	 */
	public static Orientation getOrifromValue(int orientationValue) {
		return orientationValue == 0 ? NORTH
				: (orientationValue == 1 ? EAST
						: (orientationValue == 2 ? SOUTH : (orientationValue == 3 ? WEST : null)));
	}

	/**
	 * Return the value from orientation
	 * 
	 * @param orientation
	 * @return
	 */
	public static int getValueFromOri(Orientation orientation) {
		return orientation == NORTH ? 0
				: (orientation == EAST ? 1
						: (orientation == SOUTH ? 2
								: (orientation == WEST ? 3
										: null)));
	}

	/**
	 * Return orientation
	 * 
	 * @return
	 */
	public Orientation getOrientation() {
		return this;
	}

	/**
	 * Return the coordinates of the piece in the opposed orientation
	 * 
	 * @param p
	 * @return
	 */
	public int[] getOpposedPieceCoordinates(Piece p) {
		int[] coordinates = new int[2];

		Orientation oppOri = this.getOpposedOrientation();

		switch (oppOri) {
			case NORTH:
				coordinates[0] = p.getPosX() - 1;
				coordinates[1] = p.getPosY();
				break;

			case EAST:
				coordinates[0] = p.getPosX();
				coordinates[1] = p.getPosY() + 1;
				break;
			case SOUTH:
				coordinates[0] = p.getPosX() + 1;
				coordinates[1] = p.getPosY();
				break;
			case WEST:
				coordinates[0] = p.getPosX();
				coordinates[1] = p.getPosY() - 1;
		}
		return coordinates;
	}

	/**
	 * Return opposed orientation of this
	 * 
	 * @return
	 */
	public Orientation getOpposedOrientation() {
		return this.getOrientation() == Orientation.NORTH ? Orientation.SOUTH
				: (this.getOrientation() == Orientation.WEST ? Orientation.EAST
						: (this.getOrientation() == Orientation.SOUTH ? Orientation.NORTH
								: (this.getOrientation() == Orientation.EAST ? Orientation.WEST : null)));
	}

	/**
	 * Turn the orientation to 90° degrees hour sens
	 * 
	 * @return
	 */
	public Orientation turn90() {
		return this.getOrientation() == Orientation.NORTH ? Orientation.EAST
				: (this.getOrientation() == Orientation.WEST ? Orientation.NORTH
						: (this.getOrientation() == Orientation.SOUTH ? Orientation.WEST
								: (this.getOrientation() == Orientation.EAST ? Orientation.SOUTH : null)));
	}

}
