package principal;

import gestion.Scan;
import gestion.ScanException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.lang.StringBuilder;


public class UtilMethodeCA {

	public static Ville random(CA communauteUtilisateur) {
		communauteUtilisateur.getCommunaute();
		Set<Ville> keySet = communauteUtilisateur.keySet();
        List<Ville> keyList = new ArrayList<>(keySet);
        
        int size = keyList.size();
        int randIdx = new Random().nextInt(size);
        
        return keyList.get(randIdx);

		
	}
	
	public static int score(CA communauteUtilisateur) {
		communauteUtilisateur.getCommunaute();
		int score = 0;;
		for (Ville key : communauteUtilisateur.keySet()) {
			if (key.getEcole() == true) {
				score++;
			}
		}
		return score;
	}

	public static String nomVilleUtilisateur() throws ScanException {
		String nomVille = null;
		System.out.println("Veuillez donner un nom a la prochaine ville : ");
		nomVille = Scan.lireMot();
		
		return nomVille;
	}

	/** Fonction temporaire que fournie un nom automatiquement.
	 * @param num Numero permettant de generer le nom.
	 * @return Le numero en String.
	 */
	public static String nomAuto(int num) {
		StringBuilder nom = new StringBuilder("");
		while (num != 0) {
			nom.append(num%10);
			num /= 10;
		}
		return nom.reverse().toString();
	}
		
	public static String nomAutomatique(int i) {
	    String nom;
	    if (i-- <= 0) nom="";
	    else nom = nomAutomatique(i / 26) + (char) ('A' + i % 26);
	    return nom;
	}

	
}
