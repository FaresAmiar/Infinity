package fr.dauphine.JavaAvance.Main;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.channels.FileChannel;
import java.io.RandomAccessFile;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

import fr.dauphine.JavaAvance.Components.Orientation;
import fr.dauphine.JavaAvance.Components.Piece;
import fr.dauphine.JavaAvance.Components.PieceType;
import fr.dauphine.JavaAvance.GUI.Grid;
import fr.dauphine.JavaAvance.Solve.Generator;

public class GridParser {

    private static final String newLine = System.getProperty("line.separator");
    private Grid inputGrid;
    private static final Charset charset = StandardCharsets.UTF_16;

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

    public static void saveGrid(String grid, String path) throws IOException {
        if (path.equals(""))
            path = "grid.txt";
        File f = new File(path);

        RandomAccessFile stream = new RandomAccessFile(path, "rw");

        FileChannel fc = stream.getChannel();

        FileLock verrou = null;
        verrou = fc.tryLock();

        stream.write(grid.getBytes(charset));
        verrou.release();

        stream.close();
        fc.close();

    }

    public static void main(String[] args) {
        Grid g = new Grid(5, 5);

        Generator.initRandomGrid(g);

        System.out.println(convertGrid(g));
    }

}
