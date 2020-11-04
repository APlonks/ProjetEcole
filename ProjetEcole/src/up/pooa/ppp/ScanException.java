package up.pooa.ppp;
/**
 * Exception de la class Scan.
 * @param ereur Message d'ereur.
 */
public class ScanException extends Exception {
	private String ereur;

	/**
	 * Constructeur.
	 */
	public ScanException(String msg) {
		super(msg);
	}
}