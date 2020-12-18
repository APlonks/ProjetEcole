package gestion;

import java.util.ArrayList;

/**
 * Class utilitaire pour AnalyseChaine.
 */
public class UtilAnalyseChaine {

	/**
	 * Regarde si un character est compris dans un String.
	 * @param chaine ou l'on cherche le character.
	 * @param charracter que l'on cherche.
	 */
	private static boolean contient(String chaine, char character) {
		for (int i=0; i<chaine.length(); i++) {
			if (chaine.charAt(i) == character) {
				return true;
			}
		}
		return false;
	}

	/**
	 * On separe un String en un tableau de String selon les character de regex.
	 * On ne creer pas de String vide.
	 * @return Un tableau de string (non vide) separer selon regex.
	 * @param chaine que l'on veut separer.
	 * @param regex character qui delimite les mots.
	 */
	public static String[] separationIgnoreVide(String chaine, String regex) {
		ArrayList<String> extraction = new ArrayList<String>();
		int debut=0;
		/* Parcour de chaine a la recherche de character de regex. */
		for (int j=0; j<chaine.length(); j++) {
			if (contient(regex,chaine.charAt(j))) {
				/* On ajoute le mot trouve aux tableau s'il est non vide. */
				if (j-debut < 1) {
					debut = j+1;
				} else {
					extraction.add(chaine.substring(debut,j));
					debut=j+1;
				}
			}
		}
		if (chaine.length()-debut > 0) {
			extraction.add(chaine.substring(debut,chaine.length()));
		}
		/* On transforme l'ArrayList<String> en String[]. */
		String[] tableau = new String[extraction.size()];
		return extraction.toArray(tableau);
	}

	/**
	 * Transforme une chaine de character en entier lorsque cela est possible
	 * renvoye null sinon.
	 * @param chaine a transformer en entier.
	 * @return Le nombre chaine ou null.
	 */
	public static Integer stringEnInt(String chaine) {
		Integer retour;
		if (chaine.isEmpty()) {
			/* On verifie si notre chaine est vide. */
			return null;
		} else if (chaine.charAt(0)=='-' && chaine.length()>1) {
			/* Si la chaine comme par le signe - et a plus de 1 character.
			 * On retourne l'oppose du resultat pour la sous-chaine a partir
			 * du second character de la chaine. */
			if ((retour=stringEnInt(chaine.substring(1))) == null) {
				return null;
			} else {
				return -retour;
			}
		} else {
			/* Si on n'as pas de signe -, on parcour la chaine.
			 * Si on croise autre chose qu'un chiffre on retourne null.
			 * Sinon on calcul la solution */
			int sol=0;
			for (int i=0; i<chaine.length(); i++) {
				if(!Character.isDigit(chaine.charAt(i))) {
					return null;
				} else {
					sol = sol*10+chaine.charAt(i)-'0';
				}
			}
			return sol;
		}
	}
}