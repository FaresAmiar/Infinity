package fr.dauphine.JavaAvance.Components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
        List<Orientation> empty = Arrays.asList();
        List<Orientation> oneconn = Arrays.asList(Orientation.NORTH, Orientation.EAST, Orientation.SOUTH,
                Orientation.WEST);
        List<Orientation> bar = Arrays.asList(Orientation.NORTH, Orientation.EAST);
        return null;
        /*
         * List<Orientation> ttype = Arrays.asList(Orientation.NORTH,) return this ==
         * VOID ? empty : ( this == ONECONN ? Arrays.asList(Orientation.) )
         */
    }

    public Orientation getOrientation(Orientation orientation) {
        return orientation.getOrientation();
    }

    public LinkedList<Orientation> setConnectorsList(Orientation orientation) {
        return null;
    }

}
