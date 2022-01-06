package fr.dauphine.JavaAvance.Components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 
 * Type of the piece enum
 * 
 */
public enum PieceType {
    VOID, ONECONN, BAR, TTYPE, FOURCONN, LTYPE;
    // Each Type has a number of connectors and a specific value

    public static PieceType getTypefromValue(int typeValue) {
        return typeValue == 0 ? PieceType.VOID
                : (typeValue == 1 ? PieceType.ONECONN
                        : (typeValue == 2 ? PieceType.BAR
                                : (typeValue == 3 ? PieceType.TTYPE
                                        : (typeValue == 4 ? PieceType.FOURCONN
                                                : (typeValue == 5 ? PieceType.LTYPE : null)))));
    }

    public int getNbConnectors() {
        return this == VOID ? 0
                : (this == ONECONN ? 1
                        : (this == BAR ? 2 : (this == TTYPE ? 3 : (this == FOURCONN ? 4 : (this == LTYPE ? 2 : -1)))));

    }

    public ArrayList<Orientation> getListOfPossibleOri() {
        ArrayList<Orientation> empty = (ArrayList<Orientation>) Arrays.asList(Orientation.NORTH);
        ArrayList<Orientation> oneconn = (ArrayList<Orientation>) Arrays.asList(Orientation.NORTH, Orientation.EAST,
                Orientation.SOUTH, Orientation.WEST);
        ArrayList<Orientation> bar = (ArrayList<Orientation>) Arrays.asList(Orientation.NORTH, Orientation.EAST);
        ArrayList<Orientation> ttype = (ArrayList<Orientation>) Arrays.asList(Orientation.NORTH, Orientation.EAST,
                Orientation.SOUTH, Orientation.WEST);
        ArrayList<Orientation> fourconn = (ArrayList<Orientation>) Arrays.asList(Orientation.NORTH);
        ArrayList<Orientation> ltype = (ArrayList<Orientation>) Arrays.asList(Orientation.NORTH, Orientation.EAST,
                Orientation.SOUTH, Orientation.WEST);

        return this == VOID ? empty
                : (this == ONECONN ? oneconn
                        : (this == BAR ? bar
                                : (this == TTYPE ? ttype
                                        : (this == FOURCONN ? fourconn : (this == LTYPE ? ltype : null)))));

        /*
         * List<Orientation> ttype = Arrays.asList(Orientation.NORTH,) return this ==
         * VOID ? empty : ( this == ONECONN ? Arrays.asList(Orientation.) )
         */
    }

    public Orientation getOrientation(Orientation orientation) {
        return orientation.getOrientation();
    }

    public LinkedList<Orientation> setConnectorsList(Orientation orientation) {
        if (this == VOID)
            return (LinkedList<Orientation>) Arrays.asList(Orientation.NORTH);
        if (this == ONECONN && orientation == Orientation.NORTH)
            return (LinkedList<Orientation>) Arrays.asList(Orientation.NORTH);
        if (this == ONECONN && orientation == Orientation.EAST)
            return (LinkedList<Orientation>) Arrays.asList(Orientation.EAST);
        if (this == ONECONN && orientation == Orientation.SOUTH)
            return (LinkedList<Orientation>) Arrays.asList(Orientation.SOUTH);
        if (this == ONECONN && orientation == Orientation.WEST)
            return (LinkedList<Orientation>) Arrays.asList(Orientation.WEST);
        if (this == BAR && (orientation == Orientation.WEST || orientation == Orientation.EAST))
            return (LinkedList<Orientation>) Arrays.asList(Orientation.WEST, Orientation.EAST);
        if (this == BAR && (orientation == Orientation.NORTH || orientation == Orientation.SOUTH))
            return (LinkedList<Orientation>) Arrays.asList(Orientation.NORTH, Orientation.SOUTH);
        // if(this == TTYPE && ()

        return null;
    }

}
