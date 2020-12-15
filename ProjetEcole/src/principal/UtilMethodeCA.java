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

	public static Ville random(HashMap<Ville,ArrayList<Ville>> communaute) {
		Set<Ville> keySet = communaute.keySet();
        List<Ville> keyList = new ArrayList<>(keySet);
        
        int size = keyList.size();
        int randIdx = new Random().nextInt(size);
        
        return keyList.get(randIdx);

		
	}
	
	public static int score(HashMap<Ville,ArrayList<Ville>> communaute) {
		Ville ville = null;
		int score = 0;;
		for (Ville v : communaute.get(ville)) {
			if (v.getEcole() == true) {
				score++;
			}
		}
		return score;
	}

	public static String nomVilleUtilisateur() {
		String nomVille = null;
		System.out.println("Veuillez donner un nom a la prochaine ville : ");
		try {
			nomVille = Scan.lireMot();
		} catch (ScanException e) {
			System.out.println(e.getMessage());
		}
		
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
}
