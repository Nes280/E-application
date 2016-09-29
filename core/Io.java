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
        FileWriter fw1 = new FileWriter( "relationship.csv", true );
        BufferedWriter bw1 = new BufferedWriter( fw1 );
        fichierSortie1 = new PrintWriter( bw1 );

        FileWriter fw2 = new FileWriter( "node.csv", true );
        BufferedWriter bw2 = new BufferedWriter( fw2 );
        fichierSortie2 = new PrintWriter( bw2 );

        FileWriter fw3 = new FileWriter( "bridge.csv", true );
        BufferedWriter bw3 = new BufferedWriter( fw3 );
        fichierSortie3 = new PrintWriter( bw3 );
    }

    public void read( String path ) {

        try {
            // Ouverture de dump
            InputStream ipsDump = new FileInputStream( path );
            InputStreamReader ipsrDump = new InputStreamReader( ipsDump );
            BufferedReader brDump = new BufferedReader( ipsrDump );

            while ( ( dumpLine = brDump.readLine() ) != null ) {
                if ( dumpLine.startsWith( RID ) ) {
                    // write(dumpLine.replaceAll("rid=", "").replaceAll("n1=",
                    // "").replaceAll("n2=", "").replaceAll("t=",
                    // "").replaceAll("w=", "").replaceAll("\"", "") +
                    // "\n","bridge",true);
                    write( dumpLine.replaceAll( "rid=|n1=|n2=|t=|w=|\"", "" ).replaceAll( "[|]", ";" ) + "\n",
                            fichierSortie3 );
                } else if ( dumpLine.startsWith( EID ) ) {
                    // write(dumpLine.replaceAll("eid=", "").replaceAll("n=",
                    // "").replaceAll("t=", "").replaceAll("w=",
                    // "").replaceAll("\"", "") + "\n","node",true);
                    dumpLine = dumpLine.replaceAll( "eid=|n=|t=|w=|\"|nf=", "" );

                    // on split une ligne dans une liste
                    for ( String retval : dumpLine.split( "[|]" ) ) {
                        liste.add( retval );
                        // System.out.println(liste.size() + " ---> "+ retval);
                    }
                    // System.out.println(liste.size() );
                    if ( liste.size() == 5 ) // si on a 5 elements c'est que on
                                             // a un nf dans cette ligne
                    {
                        // System.out.println("set "+liste.get(4)+" to "+liste);
                        liste.set( 1, liste.get( 4 ) ); // on remplace le code
                                                        // par sa valeur

                    }

                    write( liste.get( 0 ) + ";" + liste.get( 1 ) + ";" + liste.get( 2 ) + ";" + liste.get( 3 ) + "\n",
                            fichierSortie2 );

                    liste.clear();
                } else if ( dumpLine.startsWith( RTID ) ) {
                    // write(dumpLine.replaceAll("rtid=",
                    // "").replaceAll("name=", "").replaceAll("info=",
                    // "").replaceAll("\"", "")+ "\n","relationship",true);
                    write( dumpLine.replaceAll( "rtid=|name=|info=|\"", "" ).replaceAll( "[|]", ";" ) + "\n",
                            fichierSortie1 );

                }
                // if (!(dumpLine.startsWith("//")) &&
                // ((dumpLine.startsWith(RTID)) || (dumpLine.startsWith(RID)) ||
                // (dumpLine.startsWith(EID))))
                // write(dumpLine.replaceAll("rid=|n1=|n2=|t=|w=|\"|eid=|n=|rtid=|name=|info=",
                // "")+ "\n","dump",true);
            }

            brDump.close();
            fichierSortie3.close();
            fichierSortie2.close();
            fichierSortie1.close();

        } catch ( Exception e ) {
            System.out.println( e.toString() );
        }
    }

    public void write( String content, PrintWriter p ) {
        try {
            p.print( content );
        } catch ( Exception e ) {
            System.out.println( e.toString() );
        }
    }

}
