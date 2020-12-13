package gestion;

/**
 * Class qui vas servir a analyse une ligne de texte.
 */
public class AnalyseChaine {
	/**
	 * @param tabMot Mot dans la chaine qui ont ete separer selon regex.
	 * @param regex Character separateur dans la phrase d'origine.
	 * @param lecture Position du dernier mot dans le tableau utiliser.
	 */
	private String[] tabMot;
	private String regex;
	private int lecture;

	/**
	 * Constructeur.
	 */
	public AnalyseChaine(String regex) {
		this.regex = regex;
		this.tabMot = new String[0];
		this.lecture = 0;
	}

	/**
	 * Mise a jour du tableau avec une nouvelle chaine de character.
	 * @param chaine que l'on vas analyser
	 */
	public void updateTab(String chaine) {
		tabMot = UtilAnalyseChaine.separationIgnoreVide(chaine,regex);
		lecture = 0;
	}

	/**
	 * Verifie si il y a encore quelque chose a lire dans le tableau.
	 * @return true si lecture plus petit tabMot.length, false sinon.
	 */
	public boolean hasNext() {
		return lecture < tabMot.length;
	}

	/**
	 * Lecture du String suivant dans le tableau.
	 * @return tabMot[lecture] s'il existe, null sinon.
	 */
	public String motSuivant() {
		if (hasNext()) {
			return tabMot[lecture++];
		} else {
			return null;
		}
	}

	/**
	 * Lecture sous forme d'un entier du String suivant.
	 * @return L'entier compris dans le mots suivant, null si cela n'est
	 * pas possible.
	 */
	public Integer intSuivant() {
		String mot = motSuivant();
		if (mot == null) {
			return null;
		} else {
			return UtilAnalyseChaine.stringEnInt(mot);
		}
	}

	/**
	 * @return String precedent lorsque cela est possible, null sinon.
	 */
	public String motPrecedent() {
		if (lecture > 0) {
			lecture--;
			return motSuivant();
		} else {
			return null;
		}
	}
}