package core;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// on demande à l'utilisateur où est le fichier
		Scanner path = new Scanner(System.in);
		System.out.println("Merci de donner le chemin absolu du fichier à traiter.");
		String rep = path.nextLine();
		
		// on initialise le thread wait pour s'assurer que le travail est en cours 
		//Wait w = new Wait();
		//w.start();
		
		//on lis le fichier
		Io io = new Io();
		io.write(io.relationship, "relationship", false);
		io.write(io.node, "node", false);
		io.write(io.bridge, "bridge", false);
		io.read(rep);
		
		path.close();
		//w.stop();
		System.out.println("\n\nDone");

	}

}
