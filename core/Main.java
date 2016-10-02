package core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main( String[] args ) throws IOException {
        System.setProperty( "file.encoding", "UTF-8" );
        // Affichage du logo
        logo();

        // on demande a l'utilisateur ou est le fichier
        Scanner path = new Scanner( System.in );
        System.out.println( "Merci de donner le chemin absolu du fichier a traiter." );
        String rep = path.nextLine();

        // Scanner pathDef = new Scanner( System.in );
        // System.out.println( "Merci de donner le chemin absolu du fichier de
        // definition." );
        // String repDef = path.nextLine();

        // preparation du calcule de la duree d'execution
        long debut = System.currentTimeMillis();

        System.out.print( "Execution du programme en cours..." );

        // on cree un objet Io puis on appel la methode read()
        // qui va lire et envoyer chaques lignes a write()
        // pour l'ecrire dirrectement dans une fichier.
        Io io = new Io();
        io.readAndWrite( rep );
        path.close();

        System.out.print( "  OK" );

        // Affiche la duree d'execution en secondes
        long temps = ( System.currentTimeMillis() - debut ) / 1000;
        int minutes = (int) ( temps / 60 );
        int secondes = (int) ( temps % 60 );
        System.out.print( "\nFait en " + minutes + " minute(s) et " + secondes + " seconde(s).\n" );
    }

    // Affiche un logo aucune utilitee particuliere ..
    public static void logo() {
        String logo = "";
        try {

            // Ouverture du fichier logo.txt
            InputStream ipsDump = new FileInputStream( "logo.txt" );
            InputStreamReader ipsrDump = new InputStreamReader( ipsDump );
            BufferedReader brDump = new BufferedReader( ipsrDump );

            while ( ( logo = brDump.readLine() ) != null ) {

                System.out.println( logo );
            }

            brDump.close();

        } catch ( Exception e ) {
            System.out.println( e.toString() );
        }
    }
}
