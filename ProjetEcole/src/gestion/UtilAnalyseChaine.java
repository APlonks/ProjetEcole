package gestion;

import java.util.ArrayList;
import java.lang.CharSequence;

/**
 * Class utilitaire pour AnalyseChaine.
 */
public class UtilAnalyseChaine {

	/**
	 * Regarde si un character est compris dans un String.
	 * @param chaine ou l'on cherche le character.
	 * @param charracter que l'on cherche.
	 */
	private static boolean contains(String chaine, char character) {
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
	public static String[] splitIgnoreVoid(String chaine, String regex) {
		ArrayList<String> extraction = new ArrayList<String>();
		int debut=0;
		/* Parcour de chaine a la recherche de character de regex. */
		for (int j=0; j<chaine.length(); j++) {
			if (contains(regex,chaine.charAt(j))) {
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
}