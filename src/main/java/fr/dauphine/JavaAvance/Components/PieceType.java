package fr.dauphine.JavaAvance.Components;

import java.util.ArrayList;
import java.util.HashMap;
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
        return null;
    }

    public int getNbConnectors() {
        return -1;
    }

    public ArrayList<Orientation> getListOfPossibleOri() {
        return null;
    }

    public Orientation getOrientation(Orientation orientation) {
        return orientation.getOrientation();
    }

    public LinkedList<Orientation> setConnectorsList(Orientation orientation) {
        return null;
    }
}
