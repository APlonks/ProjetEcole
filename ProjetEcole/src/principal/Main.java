package principal;

import gestion.Scan;
import gestion.ScanException;

/**
 * @author Pinto, Poirier, Planque
 * @version 1.0
 */

/**
 * Class principale d'execution.
 */
public class Main {
	/**
	 * Methode main.
	 * @param args Entre parametrage ligne de commande.
	 * @throws CAException 
	 * @throws ScanException 
	 */
	public static void main(String[] args) throws CAException, ScanException {
		
		UtilMenu.Menu1();
		
		Scan.fermer();
	
	}
	
	

}
