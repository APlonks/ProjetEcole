package up.pooa.ppp;
import java.util.Scanner;
import java.lang.StringBuilder;

/**
 * Classe static et final du scanner. Sera utiliser pour faire les entrers claviers.
 * @param s (Scanner) Scanner de la class.
 * @param ligne (StringBuilder) Entrer utilisateur que l'on traite.
 * 		R'ajout d'un espace en fin de ligne pour les traitement.
 */
public class Scan {
	private static final Scanner s = new Scanner(System.in);
	private static StringBuilder ligne = new StringBuilder("");

	/**
	 * Update ligne.
	 */
	private static void updateLigne() throws ScanException {
		ligne.setLength(0);
		try {
			ligne.append(s.nextLine());
		} catch(Exception e) {
			throw new ScanException("Entrer vide.");
		}
		ligne.append(' ');
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
	 * On retire de la ligne jusqu'a un carractere special inclus.
	 * @param key charactere de fin de suppression.
	 */
	private static void deleteTo(char key) {
		while (!estVide() && ligne.charAt(0)!=key) {
			ligne.delete(0,1);
		}
		if (!estVide()) {
			ligne.delete(0,1);
		}
	}
	/**
	 * On retire de ligne se qui est considerer comme la premiere entre utilisateur.
	 */
	private static void deleteFirtInput() {
		deleteTo(' ');
		retireEspc();
	}
	/**
	 * @param c char rechercher.
	 * @return la position du charactere c ou -1.
	 */
	private static int indexChar(char key) {
		if (estVide()) {
			return -1;
		} else {
			/* Parcour le mot jusqu'a trouver le charractere que l'on cherche. */
			for (int pos=0; pos<ligne.length(); pos++) {
				if (ligne.charAt(pos) == key) {
					return pos;
				}
			}
			/* Pas trouver -> n'existe pas. */
			return -1;
		}
	}

	/**
	 * @param str char qui marque la fin du l'entre souhaiter.
	 * @return Le debut du String jusqu'au charactere donner (non inclus).
	 */
	public static String motDelimiter(char str) throws ScanException {
		String retour;
		int pos = indexChar(str);
		if (estVide()) {
			throw new ScanException("Entrer vide.");
		} else if (pos<0 || pos>indexChar(' ')) {
			throw new ScanException("Convension de saisie non respecter.");
		} else {
			retour = ligne.substring(0,pos);
			deleteTo(str);
			return retour;
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
		try {
		updateLigne();
		String retour = motDelimiter(' ');
		retireEspc();
		return retour;
		} catch (ScanException e) {
			throw e;
		}
	}
	/**
	 * @return Lis le premier mot qu'il reste dans le buffer ligne.
	 */
	public static String motSuivant() throws ScanException {
		String retour = motDelimiter(' ');
		retireEspc();
		return retour;
	}
	/**
	 * Realise une nouvelle entrer utilisateur.
	 * @return La valeur de l'entier taper par l'utilisateur.
	 */
	public static int lireEntier() throws ScanException {
		String mot;
		int retour=0;d
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
		} catch (ScanException e) {
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