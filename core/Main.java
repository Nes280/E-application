package core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main( String[] args ) throws IOException {
        //Affichage du logo
    	logo();
        
        // on demande a l'utilisateur ou est le fichier
        Scanner path = new Scanner( System.in );
        System.out.println( "Merci de donner le chemin absolu du fichier a traiter." );
        String rep = path.nextLine();
        
        //preparation du calcule de la duree d'execution
        long debut = System.currentTimeMillis();
        
        System.out.print("Execution du programme en cours...");
        
        // on cree un objet Io puis on appel la methode read()
        // qui va lire et envoyer chaques lignes a write()
        //pour l'ecrire dirrectement dans une fichier.
        Io io = new Io();
        io.readAndWrite( rep );
        path.close();
        
        //Affiche la durée d'exécution en secondes
        long temps =  (System.currentTimeMillis()-debut)/1000;
        System.out.print( "\n\nFait en " + temps +"secondes");
    }

    //Affiche un logo aucune utilitee particuliere .. 
    public static void logo() {
        String logo = "";
        try {

            // Ouverture du fichier logo.txt 
        	InputStream ipsDump = new FileInputStream("logo.txt");
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
