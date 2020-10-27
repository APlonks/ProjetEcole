import java.util.Scanner;

/**
 * Classe static et final du scanner. Serat utiliser pour faie les entrers claviers.
 * @param s Scanner de la class.
 */
public class Scan {
	private static final Scanner s = new Scanner(System.in);
	/**
	 * @return Scanner.next();
	 */
	public static String lireString() {
		return s.next();
	}
	/**
	 * @return Scanner.nextInt();
	 */
	public static int lireInt() {
		return s.nextInt();
	}
	/**
	 * @return Scanner.nextLine();
	 */
	public static String lireLigne() {
		return s.nextLine();
	}
	/**
	 * Si System.in n'est pas vide, on affiche a l'utilisateur que ces valeurs seront ignorer. */
	public static void ignoreSiExist() {
		if (s.hasNext()) {
			System.out.println("L'entrer "+s.nextLine()+" est ignorer.");
		}
	}
	/**
	 * Scanner.close();
	 */
	public static void fermer() {
	s.close();
	}
}