package fr.dauphine.JavaAvance.Solve;

import fr.dauphine.JavaAvance.GUI.Grid;
import fr.dauphine.JavaAvance.Main.GridParser;

import java.io.IOException;

public class Checker {

    /**
     * Retourne la grille construite
     * 
     * @param inputFile
     */
    public static Grid buildGrid(String inputFile) throws IOException {
        return GridParser.convertFile(inputFile);
    }

    /**
     * Vérifie si la grille est résolue
     * 
     * @param inputFile
     * @throws IOException
     */
    public static boolean check(String inputFile) throws IOException {
        return Checker.buildGrid(inputFile).allPiecesAreFixed();
    }

    /**
     * Affiche si la grille est résolue ou non
     * 
     * @param inputFile : le fichier a verifier
     * @throws IOException
     */
    public static void checker(String inputFile) throws IOException {
        if (check(inputFile))
            System.out.println("SOLVED:true");
        else
            System.out.println("SOLVED:false");
    }

}
