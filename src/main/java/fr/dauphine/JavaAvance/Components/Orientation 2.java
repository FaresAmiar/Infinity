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

	// f
	public static Orientation getOrifromValue(int orientationValue) {
		return orientationValue == 0 ? NORTH
				: (orientationValue == 1 ? EAST
						: (orientationValue == 2 ? SOUTH : (orientationValue == 3 ? WEST : null)));
	}

	// f
	public Orientation getOrientation() {
		return this;
	}

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

	// f
	public Orientation getOpposedOrientation() {
		return this.getOrientation() == Orientation.NORTH ? Orientation.SOUTH
				: (this.getOrientation() == Orientation.WEST ? Orientation.EAST
						: (this.getOrientation() == Orientation.SOUTH ? Orientation.NORTH
								: (this.getOrientation() == Orientation.EAST ? Orientation.WEST : null)));
	}

	// f
	public Orientation turn90() {
		return this.getOrientation() == Orientation.NORTH ? Orientation.EAST
				: (this.getOrientation() == Orientation.WEST ? Orientation.NORTH
						: (this.getOrientation() == Orientation.SOUTH ? Orientation.WEST
								: (this.getOrientation() == Orientation.EAST ? Orientation.SOUTH : null)));
	}
}
