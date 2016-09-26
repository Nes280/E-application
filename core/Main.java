package core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main( String[] args ) {
        logo();
        // on demande a l'utilisateur ou est le fichier
        Scanner path = new Scanner( System.in );
        System.out.println( "Merci de donner le chemin absolu du fichier a traiter." );
        String rep = path.nextLine();

        // on initialise le thread wait pour s'assurer que le travail est en
        // cours
        // Wait w = new Wait();
        // w.start();

        // on lis le fichier
        Io io = new Io();

        io.write( io.relationship, "relationship.csv", false );
        io.write( io.node, "node.csv", false );
        io.write( io.bridge, "bridge.csv", false );

        // io.write("", "dump", false);
        io.read( rep );

        path.close();
        // w.stop();
        System.out.println( "\n\nDone" );

    }

    public static void logo() {
        String logo = "";
        try {

            // Ouverture du fichier logo.txt
            // InputStream ipsDump = new
            // FileInputStream("D:\\Users\\Niels\\Documents\\GitHub\\E-application\\core\\logo.txt");
            InputStream ipsDump = new FileInputStream( "/Users/niels/E-application/core/logo.txt" );
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
