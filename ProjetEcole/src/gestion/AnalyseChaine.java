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
		tabMot = UtilAnalyseChaine.splitIgnoreVoid(chaine,regex);
		lecture = 0;
	}

	public void debug() {
		System.out.println("Taille : "+tabMot.length);
		for (int i=0; i<tabMot.length; i++) {
			System.out.println('.'+tabMot[i]+'.');
		}
	}
}