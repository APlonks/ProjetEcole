package principal;

import gestion.Scan;
import gestion.ScanException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.StringBuilder;


public class UtilMethodeCA {

	public static Ville random(CA communauteUtilisateur) {
        List<Ville> keyList =
	        new ArrayList<Ville>(communauteUtilisateur.getCommunaute().keySet());
        int size = keyList.size();
        int randIdx = new Random().nextInt(size);
        return keyList.get(randIdx);
	}

	public static int score(CA communauteUtilisateur) {
		int score = 0;;
		for (Ville key : communauteUtilisateur.getCommunaute().keySet()) {
			if (key.getEcole() == true) {
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

	public static String nomAutomatique(int i) {
	    String nom;
	    if (i-- <= 0) nom="";
	    else nom = nomAutomatique(i / 26) + (char) ('A' + i % 26);
	    return nom;
	}

	/**
	 * Mise a jout de accesE.
	 * On ajoute l'acces a une ecole a l'ensemble des voisins d'une ville.
	 * @param voisins de la ville.
	 * @param accesE HashMap d'acces a une ecole.
	 */
	public static void majAccesE(ArrayList<Ville> voisins, HashMap<Ville,Boolean> accesE) {
		for (Ville v : voisins) {
			accesE.put(v,true);
		}
	}

	/**
	 * Creation d'une HashMap accesE qui permet de savoir si les Ville on acces ou non a une ecole.
	 * @param ensembleVille Communaute d'Agglomeration.
	 * @param mode 0 si on initialise toute les ecole a false, sinon on garde celle deja existante.
	 * @return accesE
	 */
	public static HashMap<Ville,Boolean> creationAccesE (CA ensembleVille, int mode) {
		HashMap<Ville,ArrayList<Ville>> communaute = CA.getCommunaute();
		Set<Ville> cle = communaute.keySet();
		HashMap<Ville,Boolean> accesE = new HashMap<Ville,Boolean>();
		/* On commence par ajouter toute les villes a accesE avec false. */
		for (Ville v : cle) {
			accesE.put(v,false);
		}
		if (mode == 0) {
			/* On retire toute les ecole des viles. */
			for (Ville v : voisins) {
				if (v.getEcole()) {
					v.retireEcole();
				}
			}
		} else {
			/* On met a jour l'accessibilite des Ecole. */
			for (Ville v : voisins) {
				if (v.getEcole()) {
					majAccesE(communaute.get(v),accesE);
				}
			}
		}
		return accesE;
	}

	/**
	 * Calcul le nombre de ville voisine qui profiterait de l'ajout d'une ecole.
	 * @param voisins Liste des villes vosines.
	 * @param accesE HashMap d'accessibilite des ecoles pour les villes.
	 * @return Le nombre de nouvelle ville qui ont acces a une ecole.
	 */
	public static int compteNouvAcces(ArrayList<Ville> voisins, HashMap<Ville,Boolean> accesE) {
		int sol++;
		for (Ville v : voisins) {
			if (!accesE.get(c)) {
				sol++;
			}
		}
		return sol;
	}
}
