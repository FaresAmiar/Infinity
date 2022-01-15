package fr.dauphine.JavaAvance.Main;

import fr.dauphine.JavaAvance.Components.Orientation;
import fr.dauphine.JavaAvance.Components.Piece;
import fr.dauphine.JavaAvance.Components.PieceType;
import fr.dauphine.JavaAvance.GUI.Grid;
import fr.dauphine.JavaAvance.Solve.Generator;

public class GridParser {

    private static final String newLine = System.getProperty("line.separator");
    private Grid inputGrid;

    public static String convertGrid(Grid grid) {
        StringBuilder sb = new StringBuilder();

        sb.append(grid.getHeight() + newLine);
        sb.append(grid.getWidth() + newLine);

        for (Piece[] pieces : grid.getAllPieces()) {
            for (Piece p : pieces) {
                sb.append(PieceType.getValueFromType(p.getType()) + ","
                        + Orientation.getValueFromOri(p.getOrientation()) + " ");
            }
            sb.append(newLine);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Grid g = new Grid(5, 5);

        Generator.initRandomGrid(g);

        System.out.println(convertGrid(g));
    }

}
