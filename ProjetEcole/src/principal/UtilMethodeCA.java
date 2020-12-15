package principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import gestion.Scan;
import gestion.ScanException;



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
}
