package gestion;

/**
 * Exception de la class LectureCA.
 * @see Exception
 */
public class LectureException extends Exception {
	/**
	 * @param ligne Ligne de l'ereur. -1 si le champs n'est pas initialiser.
	 */
	private int ligne;

	/**
	 * Getter.
	 */
	public int getLigne() {
		return ligne;
	}

	/**
	 * Constructeur.
	 * @param msg Message d'ereur.
	 */
	public LectureException(String msg) {
		super(msg);
		ligne = -1;
	}

	/**
	 * Constructeur
	 * @param msg Message d'ereur.
	 * @param ligne Ligne de l'ereur.
	 */
	public LectureException(String msg, int ligne) {
		super(msg);
		this.ligne = ligne;
	}
}