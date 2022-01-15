import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.dauphine.JavaAvance.Components.Orientation;
import fr.dauphine.JavaAvance.Components.Piece;
import fr.dauphine.JavaAvance.Components.PieceType;
import fr.dauphine.JavaAvance.GUI.Grid;
 
class GridTest {
 
    /*@Test
    void testGetConnectorsEmpty() {
        Grid g = new Grid(2,2);
        Piece p1 = new Piece(0,0,PieceType.LTYPE,Orientation.NORTH);
        Piece p2 = new Piece(1,0,PieceType.ONECONN,Orientation.NORTH);

        //assertTrue(p1.hasRightConnector());

        g.setPiece(0,0,p1);
        g.setPiece(1,0,p2);

        g.displayGrid();

        assertEquals(g.getConnectorsEmpty(p1).size(), 1);
        assertTrue(g.getConnectorsEmpty(p2).isEmpty());



    }*/

    @Test
    void testgetVoidConnectorsCoords() {
        Orientation o = Orientation.NORTH;
        Piece p = new Piece(0,0,PieceType.LTYPE,o);


        Piece p2 = new Piece(1,0,PieceType.TTYPE,o);

        Grid g = new Grid(3,3);

        g.setPiece(0,0,p);
        g.setPiece(1,0,p2);

        Integer[][] tab1 = g.getVoidConnectorsCoords(p2,Orientation.EAST), tab2 = new Integer[][]{{2,0}};
        for(Integer[] i : tab1)
            for(Integer ii : i)
                System.out.println(ii + "  ,");
        assertTrue(tab1[0][0] == 2 && tab1[0][1] == 0);
    }

    
}