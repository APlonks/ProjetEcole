package gestion;

import java.util.Scanner;

/**
 * Classe static et final du scanner.
 * Sera utiliser pour faire les entrers claviers.
 */
public class Scan {
	/**
	 * @param s Scanner general de la class.
	 * @param ligneLu Buffer d'entre general de la class, utilisation de
	 * AnalyseChaine.
	 */
	private static final Scanner s = new Scanner(System.in);
	private static AnalyseChaine ligneLu = new AnalyseChaine(" ");

	/**
	 * Update ligne.
	 * @throws ScanException Exception d'entre clavier.
	 */
	private static void updateLigne() throws ScanException {
		try {
			ligneLu.updateTab(s.nextLine());
		} catch(Exception e) {
			throw new ScanException("Ereur lors de la lecture de l'entrer");
		}
	}

	/**
	 * Ferme le Scanner, on ne peut plus utiliser la class Scan.
	 */
	public static void fermer() {
		s.close();
	}

	/**
	 * Permet de savoir si le buffer de l'entrer utilisateur est vide.
	 * @return true si le buffer est vide, false sinon.
	 */
	public static boolean estVide() {
		return !ligneLu.hasNext();
	}

	/**
	 * Lis le mot suivant du buffer d'entrer.
	 * @throws ScanException Exception d'entre clavier.
	 * @return Lis le premier mot qu'il reste dans le buffer ligne.
	 */
	public static String motSuivant() throws ScanException {
		if (!estVide()) {
			return ligneLu.motSuivant();
		} else {
			throw new ScanException("Entre attendu non presente");
		}
	}

	/**
	 * Realise une nouvelle entrer utilisateur.
	 * @throws ScanException Exception d'entre clavier.
	 * @return Le premier mot de l'entrer realiser.
	 */
	public static String lireMot() throws ScanException {
		updateLigne();
		return motSuivant();
	}

	/**
	 * Lis l'entier suivant dans le buffer d'entrer.
	 * @throws ScanException Exception d'entre clavier.
	 * @return La valeur de l'entier suivant dans le buffer ligne.
	 */
	public static int entierSuivant() throws ScanException {
		Integer valeur;
		/* On verifie qu'il y a un mot suivant puis on essaye de lire
		 * l'entier de ligneLu. */
		if (estVide()) {
			throw new ScanException("Aucune entre disponible");
		} else if ((valeur=ligneLu.intSuivant()) == null) {
			throw new ScanException("L'entre "+ligneLu.motPrecedent()+
				" n'est pas un entier comme attendu");
		} else {
			return valeur;
		}
	}

	/**
	 * Realise une nouvelle entrer utilisateur.
	 * @throws ScanException Exception d'entre clavier.
	 * @return La valeur de l'entier taper par l'utilisateur.
	 */
	public static int lireEntier() throws ScanException {
		int valeur;
		updateLigne();
		return entierSuivant();
	}

	/**
	 * Retourne un string[] contenant les parametrage d'une fonction.
	 * @throws ScanException Exception d'entre clavier.
	 * @return L'entre clavier dans un tableau pour differencier les arguments.
	 */
	public static String[] parametre() throws ScanException {
		if (estVide()) {
			throw new ScanException("Argument attendu non present");
		} else {
			return UtilAnalyseChaine.separationIgnoreVide(motSuivant(),"|");
		}
	}
}