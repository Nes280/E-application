package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Io {
    public String RTID         = "rtid=";
    public String EID          = "eid=";
    public String RID          = "rid=";

    String        dumpLine;
    String        relationship = "";
    String        node         = "";
    String        bridge       = "";

    List<String>  liste        = new ArrayList<String>();
    PrintWriter   fichierSortie3;
    PrintWriter   fichierSortie2;
    PrintWriter   fichierSortie1;

    public Io() throws IOException {
        System.setProperty( "file.encoding", "UTF-8" );

        // buffer du fichier relationship.csv
        FileWriter fw1 = new FileWriter( "relationship.csv", true );
        BufferedWriter bw1 = new BufferedWriter( fw1 );
        fichierSortie1 = new PrintWriter( bw1 );
        fichierSortie1.print( "identifiant|nom1|nom2|desc\n" );

        // buffer du fichier node.csv
        FileWriter fw2 = new FileWriter( "node.csv", true );
        BufferedWriter bw2 = new BufferedWriter( fw2 );
        fichierSortie2 = new PrintWriter( bw2 );
        fichierSortie2.print( "identifiant|mot|ty|poids\n" );

        // buffer du fichier bridge.csv
        FileWriter fw3 = new FileWriter( "bridge.csv", true );
        BufferedWriter bw3 = new BufferedWriter( fw3 );
        fichierSortie3 = new PrintWriter( bw3 );
        fichierSortie3.print( "identifiant|mot1|mot2|rel|poids\n" );
    }

    public void readAndWrite( String path ) {

        try {
            // Ouverture d'un buffer dans le fichier cible
            InputStream ipsDump = new FileInputStream( path );
            InputStreamReader ipsrDump = new InputStreamReader( ipsDump );
            BufferedReader brDump = new BufferedReader( ipsrDump );

            while ( ( dumpLine = brDump.readLine() ) != null ) {
                if ( dumpLine.startsWith( RID ) ) { // si on lis une arete

                    // alors on supprime ce qui n'est pas utile est on ecrit
                    // dans le fichier bridge.csv
                    fichierSortie3.print( dumpLine.replaceAll( "rid=|n1=|n2=|t=|w=|\"", "" ) + "\n" );

                } else if ( dumpLine.startsWith( EID ) ) {// si on lis un noeud

                    // on supprime ce qui est inutile
                    dumpLine = dumpLine.replaceAll( "eid=|n=|t=|w=|\"|nf=", "" );

                    // on split une ligne dans une liste
                    for ( String retval : dumpLine.split( "[|]" ) ) {
                        liste.add( retval );
                    }

                    if ( liste.size() == 5 ) // si on a 5 elements c'est que on
                                             // a un nf dans cette ligne
                    {
                        // System.out.println("set "+liste.get(4)+" to "+liste);
                        liste.set( 1, liste.get( 4 ) ); // on remplace le code
                                                        // par sa valeur

                    }

                    // on recompose les element de la liste que l'on ecrit dans
                    // le fichier node.csv
                    fichierSortie2.print( liste.get( 0 ) + "|" + liste.get( 1 ) + "|" + liste.get( 2 ) + "|"
                            + liste.get( 3 ) + "\n" );

                    // on efface le contenu de la liste pour eventuellement
                    // traiter une nouvelle ligne
                    liste.clear();

                } else if ( dumpLine.startsWith( RTID ) ) {// si on lis une
                                                           // relation

                    // alors on supprime ce qui n'est pas utile est on ecrit
                    // dans le fichier relationship.csv
                    fichierSortie1.print( dumpLine.replaceAll( "rtid=|name=|info=|\"|nom_etendu=", "" ) + "\n" );

                }
            }

            // le traitement est termine, donc on ferme tout ce qui a ete ouvert
            brDump.close();
            fichierSortie3.close();
            fichierSortie2.close();
            fichierSortie1.close();

        } catch ( Exception e ) {
            System.out.println( e.toString() );
        }
    }
}
