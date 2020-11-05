package up.pooa.ppp;
import java.util.Scanner;
import java.lang.StringBuilder;

/**
 * Classe static et final du scanner. Sera utiliser pour faire les entrers claviers.
 * @param s (Scanner) Scanner de la class.
 * @param ligne (StringBuilder) Entrer utilisateur que l'on traite.
 */
public class Scan {
	private static final Scanner s = new Scanner(System.in);
	private static StringBuilder ligne = new StringBuilder("");

	/**
	 * Update ligne.
	 */
	private static void updateLigne() throws ScanException, java.util.InputMismatchException {
		ligne.setLength(0);
		try {
			ligne.append(s.nextLine());
		} catch(Exception e) {
			throw new ScanException("Entrer vide.");
		}
		retireEspc();
	}
	/**
	 * Retire les espace en debut de ligne.
	 */
	private static void retireEspc() {
		while (!estVide() && ligne.charAt(0)==' ') {
			ligne.delete(0,1);
		}
	}
	/**
	 * On retire de ligne se qui est considerer comme la premiere entre utilisateur.
	 */
	private static void deleteFirtInput() {
		while (!estVide() && ligne.charAt(0)!=' ') {
			ligne.delete(0,1);
		}
		retireEspc();
	}

	/**
	 * Ferme le Scanner, on ne peut plus utiliser la class Scan.
	 */
	public static void fermer() {
		s.close();
	}
	/**
	 * Permet de savoir si le buffer de l'entrer utilisateur est vide.
	 */
	public static boolean estVide() {
		if (ligne.length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Realise une nouvelle entrer utilisateur.
	 * @return Le premier mot de l'entrer realiser.
	 */
	public static String lireMot() throws ScanException {
		/* Mise a jour de l'entrer utilisateur. */
		updateLigne();
		String retour;
		int fin = ligne.indexOf(" ");
		if (estVide()) {
			throw new ScanException("Entrer vide.");
		} else if (fin != -1) {
			/* Il y a un espace dans la ligne. */
			retour = ligne.subSequence(0,fin).toString();
		} else {
			/* On retourne l'entrer entiere qui est constituer d'un seul mot. */
			retour = ligne.toString();
		}
		/* Retire l'entrer que l'on vient de retourner. */
		deleteFirtInput();
		return retour;
	}
	/**
	 * @return Lis le premier mot qu'il reste dans le buffer ligne.
	 */
	public static String motSuivant() throws ScanException {
		if (estVide()) {
			/* Ereur, il n'y as plus rien a lire. */
			throw new ScanException("Plus rien a lire.");
		}
		String retour;
		int fin = ligne.indexOf(" ");
		if (fin != -1) {
			retour = ligne.subSequence(0,fin).toString();
		} else {
			retour = ligne.toString();
		}
		/* Retire de la ligne se que l'on vient vas retourner. */
		deleteFirtInput();
		return retour;
	}
	/**
	 * Realise une nouvelle entrer utilisateur.
	 * @return La valeur de l'entier taper par l'utilisateur.
	 */
	public static int lireEntier() throws ScanException {
		String mot;
		int retour=0;
		char lettre;
		try {
			/* Utilise lireMot pour obtenir la premiere entrer utilisateur. */
			mot = lireMot();
		} catch (ScanException e) {
			/* On récupére une ereur dans la fonction lireMot(), on l'a transmet. */
			throw e;
		}
		/* Lettre de l'entier taper.
		 * Si jamais un des symbole du mot n'est pas un chiffre, on retourne une ereur.
		 */
		for (int i=0; i<mot.length(); i++) {
			lettre = mot.charAt(i);
			if (lettre<'0' || lettre>'9') {
				throw new ScanException("L'entrer n'est pas un entier.");
			} else {
				retour = retour*10+lettre-'0';
			}
		}
		return retour;
	}
	/**
	 * @return La valeur de l'entier suivant dans le buffer ligne.
	 */
	public static int entierSuivant() throws ScanException {
		String mot;
		int retour=0;
		char lettre;
		try {
			/* Utilise motSuivant pour obtenir l'entrer utilisateur suivante. */
			mot = motSuivant();
		} catch (ScanException e) {
			/* On récupére une ereur dans la fonction entierSuivant(), on l'a transmet. */
			throw e;
		}
		/* Lettre de l'entier taper.
		 * Si jamais un des symbole du mot n'est pas un chiffre, on retourne une ereur.
		 */
		for (int i=0; i<mot.length(); i++) {
			lettre = mot.charAt(i);
			if (lettre<'0' || lettre>'9') {
				throw new ScanException("L'entrer n'est pas un entier.");
			} else {
				retour = retour*10+lettre-'0';
			}
		}
		return retour;
	}
	/**
	 * Pose une question a l'utilisateur jusqu'a que ce dernier donne une reponse correcte.
	 * N'est pas optimal surtout si le nombre de mot cle est consequant.
	 * @param question Question poser a l'utilisateur.
	 * @param rA Reponse accepter, permet d'etre sure qu'il y a aux moins une reponse.
	 * @param ...a Ensemble des mot cles accepter.
	 * @return String mot cle reconnue.
	 */
	public static String questionReponse(String question, String rA, String ... a) {
		System.out.print(question);
		String reponse;
		/* Lis l'entrer utilisateur. */
		try {
			reponse = lireMot();
		} catch (Exception e) {
			/* Enonce l'ereur de l'utilisateur et attend une nouvelle reponse. */
			System.out.println(e.getMessage());
			return questionReponse(question,rA,a);
		}
		/* Recherche d'un match. */
		if (reponse.equals(rA)) {
			return rA;
		}
		for (String i : a) {
			if (reponse.equals(i)) {
				return reponse;
			}
		}
		/* Aucun match, annonce que l'entrer est invalide.
		 * Demande une nouvelle reponse. */
		System.out.println("Aucune reponse valide.");
		return questionReponse(question,rA,a);
	}
}