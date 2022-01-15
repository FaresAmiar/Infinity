import java.io.IOException;

import org.junit.jupiter.api.Test;

import fr.dauphine.JavaAvance.Components.Orientation;
import fr.dauphine.JavaAvance.Components.Piece;
import fr.dauphine.JavaAvance.Components.PieceType;
import fr.dauphine.JavaAvance.GUI.Grid;
import fr.dauphine.JavaAvance.Main.GridParser;
import fr.dauphine.JavaAvance.Solve.Checker;

public class CheckerTest {

    @Test
    void testChecker() {
        Grid g = new Grid(1,2);
        
        g.setPiece(0,0,new Piece(0,0,PieceType.getTypefromValue(1),Orientation.getOrifromValue(1)));// Oneconn
        g.setPiece(0,1, new Piece(0,1,PieceType.getTypefromValue(1),Orientation.getOrifromValue(3)));//oneconn

        
        try {
            GridParser.saveGrid(GridParser.convertGrid(g), "testChecker");
            assert(Checker.check("testChecker"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
