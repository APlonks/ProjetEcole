package gestion;

import principal.Ville;
import principal.CA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class EnregistrementCA {
	/**
	 * Seul fonction accessible pour l'enregistrement d'un fichier.
	 * Demande a l'utilisateur d'entrer le chemin de ce dernier.
	 * Si il exist lui demande s'il veut l'ecraser.
	 * Appele par la suite une fonction pour enregister les valeurs.
	 * @param CA la HashMap de la Cammunaute d'Agglomeration que l'on veut sauvegarder.
	 * @return 1 si il y a eut une ereur dans l'ouverture du fichier. 0 sinon.
	 */
	public static int enregister(CA communaute) {
		String nomFichier = new String("");
		int choix=0;
		do {
			/* Tant que l'utilisateur n'as pas fait de choix on lui demande. */
				nomFichier = choixNom();
				choix = 1;
				File testExist = new File(nomFichier);
				if (testExist.exists()) {
					/* Si le fichier exist ou lui demande de confirmer sont choix. */
					choix = ecraser();
				}
		} while (choix==0);
		/* On ouvre le fichier et on appele la fonciton qui ecrit a l'interieur de ce dernier. */
		try {
			ecritureCA(communaute,new PrintWriter(new BufferedWriter(new FileWriter(nomFichier))));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 1;
		}
		return 0;
	}

	/**
	 * Recupere le nom du fichier choix par l'utilisateur.
	 * @return nom du fichier choisie.
	 */
	private static String choixNom() {
		String nomFichier=null;
		int choix = 0;
		try {
			if (!Scan.estVide()) {
				nomFichier = Scan.motSuivant();
				if (extension(nomFichier)) {
					return nomFichier;
				} else {
					System.out.println("Extension de fichier incorrect, '.ca' attendu");
				}
			}
		} catch (ScanException e) {
			System.out.println(e.getMessage());
		}
		do {
			try {
				System.out.print("Nom du fichier : ");
				nomFichier = Scan.lireMot();
				if (extension(nomFichier)) {
					choix = 1;
				} else {
					System.out.println("Extension de fichier incorrect, '.ca' attendu");
				}
			} catch (ScanException e) {
				System.out.println(e.getMessage());
			}
		} while (choix == 0);
		return nomFichier;
	}

	/**
	 * Demande a L'utilisateur si il veut ecraser le fichier cite.
	 * @return 1 si oui 0 si non.
	 */
	private static int ecraser() {
		String nomFichier = new String("");
		int choix = 0;
		System.out.print("Ce fichier existe deja, l'ecraser : ");
		do {
			try {
				/* Verifie le retour. */
				switch (Scan.lireMot()) {
					case "oui":
					case "o":
						choix = 1;
						break;
					case "non":
					case "n":
						choix = -1;
						break;
					default :
						System.out.println("Reponse incorecte.");
						break;
				}
			} catch (ScanException e) {
				System.out.println(e.getMessage());
			}
		} while (choix==0);
		if (choix == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Verifie que le fichier donner contient l'extension '.txt'.
	 * @param String nom du fichier.
	 * @return true Si '.txt' false finon.
	 */
	private static boolean extension(String nomFichier) {
		if (nomFichier.length() < 3) {
			return false;
		} else if (nomFichier.substring(nomFichier.length()-3).compareTo(".ca") != 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Ecriture a l'interieur du PrintWriter de la CA.
	 * @param CA Communaute d'Agglomeration que l'on veut enregistrer.
	 * @param ecriture PrintWriter qui permet d'ecrire.
	 */
	private static void ecritureCA(CA communaute,PrintWriter ecriture) {
		HashMap<Ville,ArrayList<Ville>> listeAdjacence = communaute.getCommunaute();
		Set<Ville> cle = listeAdjacence.keySet();
		/* On ecrit d'abord toute les villes de notre CA. */
		for (Ville v : cle) {
			ecriture.println("ville("+v.getNomVille()+").");
		}
		/* On ecrit ensuite les routes.
		 * Comme il y a une symetrie pour les routes, on utilise compareTo entre deux
		 * String pour selectionner un ordre. */
		for (Ville vDepart : cle) {
			for (Ville vArriver : listeAdjacence.get(vDepart)) {
				if (vDepart.compareTo(vArriver) > 0) {
					ecriture.println("route("+vDepart.getNomVille()+","
						+vArriver.getNomVille()+").");
				}
			}
		}
		/* On note ensuite la presence des ecoles. */
		for (Ville v : cle) {
			if (v.getEcole()) {
				ecriture.println("ecole("+v.getNomVille()+").");
			}
		}
		/* On ferme le PrintWriter pour que l'ecriture soit effective. */
		if (ecriture != null) {
			ecriture.close();
		}
	}
}