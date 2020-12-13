package principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

/**
 * Class represantant un groupement de Ville relier entre elle.
 * Communaute d'Agglomeration.
 */
public class CA {
	/**
	 * @param communaute Liste d'adjacence de la CA.
	 */
	private HashMap<Ville,ArrayList<Ville>> communaute;

	/**
	 * Constructeur.
	 */
	public CA() {
		communaute = new HashMap<Ville,ArrayList<Ville>>();
	}

	/**
	 * Getter.
	 */
	public HashMap<Ville,ArrayList<Ville>> getCommunaute() {
		return communaute;
	}

	/**
	 * Regarde si une Ville contient une ecole.
	 * @param lst Liste des villes adjacentes (Lie par une route)
	 * @return true si il existe 'a' dans lst avec 'a' possedant une ecole.
	 * 		false sinon.
	 */
	private boolean contientEcole(ArrayList<Ville> lst) {
		for (Ville a : lst) {
			if (a.getEcole()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Regarde si une Ville a acces a une ecole.
	 * @param agA 1 ville de depart.
	 * @param agB 1 ville adjacente a agA.
	 * @return true si agA contient une ecole ou si une ville adjacence a agA autre que
	 * 		agB contient une ecole.
	 */
	private boolean accesAutreEcole(Ville agA, Ville agB) {
		if (agA.getEcole()) {
			return true;
		} else {
			for (Ville b : communaute.get(agA)) {
				if (b.getEcole() && !b.equals(agB)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Ajoute une ville a la Communaute d'Agglomeration.
	 * @param v Une nouvelle ville
	 */
	public void addVille(Ville v) {
		if (!(communaute.containsKey(v))) {
			/* Ville nom deja presente. */
			communaute.put(v,new ArrayList<Ville>());
		} else {
			/* Affichage pour qu'on pense a l'enlever. */
			System.out.println("Ville deja existante.");
		}
	}

	/**
	 * Ajoute une ville a la Communaute d'Agglomeration.
	 * @param nom Une nouvelle ville
	 */
	public void addVille(String nom) {
		addVille(new Ville(nom));
	}

	/**
	 * Affiche la liste des villes (en ligne).
	 */
	public void affiche() {
		for (Ville k : communaute.keySet()) {
			System.out.print(k+" ");
		}
		System.out.print("\n");
	}

	/**
	 * Affiche la liste de villes possedant une ecole.
	 */
	public void afficheEcole() {
		for (Ville k : communaute.keySet()) {
			if (k.getEcole() == true) {
				System.out.print(k+" ");
			}
		}
		System.out.print("\n");
	}

	/**
	 * Methode permettant de renvoyer le nom de la ville.
	 * @param recherche Un nom de ville
	 * @return Ville recherche si elle existe, null sinon.
	 */
	public Ville getVille(String recherche) {
		for(Ville k : communaute.keySet()) {
			if(k.getNomVille().equals(recherche)) {
				return k;
			}
		}
		return null;
	}

	/**
	 * Ajoute une route entre deux villes (de x vers y et de y vers x ).
	 * @param x La Ville de depart.
	 * @param y La ville d'arrivee. 
	 */
	public void addRoute(String x, String y) {
		Ville depart, arriver;
		if ((depart=getVille(x))==null) {
			System.out.println("La ville "+x+" n'existe pas.");
		} else if ((arriver=getVille(y))==null) {
			System.out.println("La ville "+y+" n'existe pas.");
		} else if (arriver.equals(depart)) {
			System.out.println("Les villes d'arriver et de depart sont les meme. Demande"+
				" incoherente.");
		} else if ((communaute.get(depart).contains(arriver)) &&
			(communaute.get(arriver).contains(depart))) {
			/* On verifie si il existe une route de la ville de depart à la ville d'arriver.
			 * On ne verifie qu'un sens de la route car la liste d'adjacence est symetrique. */
			System.out.println("La route de la ville "+depart+" a la ville "+arriver+
				" existe deja donc on ne peut pas en ajouter une.");
		} else {
			/* Ajoute chaque ville a la liste d'adjacence de l'autre. */
			communaute.get(depart).add(arriver);
			communaute.get(arriver).add(depart);
			System.out.println("Vous avez ajoute une route entre la ville "+x
				+" et la ville "+y+".");
		}
	}

	/**
	 * Supprime une route entre deux villes (de x vers y et de y vers x ).
	 * @param x La ville de depart.
	 * @param y La ville d'arrivee.
	 */
	public void supprimerRoute (String x, String y) {
		Ville depart, arriver;
		if ((depart=getVille(x))==null) {
			System.out.println("La ville "+x+" n'existe pas.");
		} else if ((arriver=getVille(y))==null) {
			System.out.println("La ville "+y+" n'existe pas.");
		} else if (arriver.equals(depart)) {
			System.out.println("Les villes d'arriver et de depart sont les meme. Demande"+
			" incoherente.");
		} else if ((communaute.get(depart).contains(arriver)) &&
			(communaute.get(arriver).contains(depart))) {
			/* Retire chaque ville de la liste d'adjacence de l'autre. */
			communaute.get(depart).remove(arriver);
			communaute.get(arriver).remove(depart);
			System.out.println("Vous avez supprime une route entre la ville "+x+" et la ville "
				+y+".");
		} else {
			/* On verifie si il existe une route de la ville de depart à la ville d'arriver.
			 * On ne verifie qu'un sens de la route car la liste d'adjacence est symetrique. */
			System.out.println("Il n'y a pas de route de la ville "+depart+" a la ville "+arriver
				+" donc elle ne peut pas etre supprimee.");
		}
	}

	/**
	 * Affiche les routes liees a une ville.
	 * @param x la ville choisie.
	 */
	public void afficheRoute(String x) {
		Ville depart;
		if ((depart=getVille(x))==null) {
			System.out.println("La ville "+x+" n'existe pas.");
		} else {
			System.out.println(communaute.get(depart));
		}
	}

	/**
	 * Permet d'afficher la liste d'adjacence.
	 */
	public void afficherHashMap() {
		for(Entry<Ville,ArrayList<Ville>> element : communaute.entrySet()) {
			System.out.println("La ville "+element.getKey()+" est liee aux villes"
				+element.getValue());
		}
		System.out.println();
	}

	/**
	 * Retire une ecole si cela est possible.
	 * @param x une Ville
	 */
	public void retireEcole(String x) {
		Ville ville;
		if ((ville=getVille(x))==null) {
			System.out.println("La ville "+x+" n'existe pas.");
		} else if (!contientEcole(communaute.get(ville))) {
			System.out.println("Aucun voisin de "+x+" ne contient d'ecole.");
		} else {
			/* Parcour des adjacent de ville pur verifier que l'ecole de ville n'est pas la seul
			 * ecole a laquelle ils ont acces. */
			boolean retireOk = true;
			for (Ville b : communaute.get(ville)) {
				if (!accesAutreEcole(b,ville)) {
					System.out.println("L'ecole de "+x+" est la seul ecole a laquelle "+
						b+" a acces.");
					retireOk = false;
				}
			}
			if (retireOk) {
				ville.retireEcole();
			} else {
				System.out.println("On ne retire donc pas l'ecole.");
			}
		}
	}

	/**
	 * On considere une CA vide comme etant non connexe.
	 * @return true si la CA est connexe false sinon.
	 */
	public boolean estConnexe() {
		if (communaute.size() < 1) {
			System.out.println("Il n'y as pas de ville dans la communaute d'agglomeration.");
			return false;
		}
		ArrayList<Ville> ssGrapheConnexe, lstVille;
		ssGrapheConnexe = new ArrayList<Ville>();
		lstVille = new ArrayList<Ville>();
		/* lstVille contient toutes les Villes qui non pas déjà été visité. Elle est initialser
		 * a l'aide de toutes les key de commune.
		 * ssGrapheConnexe correspond a un ensemble des Villes appartenant aux meme sous
		 * graphe connexe. */
		for (Ville k : communaute.keySet()) {
			lstVille.add(k);
		}
		ssGrapheConnexe.add(lstVille.get(0));
		Ville tampon = lstVille.get(0);
		lstVille.remove(tampon);
		/* Tant qu'il y a des Villes dans la liste ssGrapheConnexe, on y ajoute si cela
		 * est possible leurs adjacent que l'on retire de lstVille dans ce cas. */
		while (!ssGrapheConnexe.isEmpty()) {
			tampon = ssGrapheConnexe.get(0);
			ssGrapheConnexe.remove(0);
			for (Ville v : communaute.get(tampon)) {
				if (lstVille.contains(v)) {
					ssGrapheConnexe.add(v);
					lstVille.remove(v);
				}
			}
		}
		if (!lstVille.isEmpty()) {
			System.out.println("Les villes "+tampon+" et "+lstVille.get(0)+" n'appartiennet pas "+
			"la meme communaute d'agglomeration.");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Ajoute une ecole.
	 * @param nomVille Ville ou l'on veut ajouter une ecole.
	 */
	public void addEcole(String nomVille) {
		Ville ville;
		if ((ville=getVille(nomVille)) == null ) {
			System.out.println("La ville " + nomVille + " n'existe pas.");
		} else if (ville.getEcole() == true) {
			System.out.println("La ville " + nomVille + " possede deja une ecole");
		} else {
			ville.addEcole();
		}
	}
	
	/**********************************************************************************/
	
	
	public Ville random(HashMap<Ville,ArrayList<Ville>> communaute) {
		Set<Ville> keySet = communaute.keySet();
        List<Ville> keyList = new ArrayList<>(keySet);
        
        int size = keyList.size();
        int randIdx = new Random().nextInt(size);
        
        return keyList.get(randIdx);

		
	}
	
	public int score(HashMap<Ville,ArrayList<Ville>> communaute) {
		Ville ville = null;
		int score = 0;;
		for (Ville b : communaute.get(ville)) {
			if (contientEcole(communaute.get(ville))) {
				score++;
			}
		}
		return score;
	}
	
	
	
	public HashMap<Ville, ArrayList<Ville>> algoNaif1(HashMap<Ville,ArrayList<Ville>> communaute, int k) {
		Ville ville;
		HashMap<Ville, ArrayList<Ville>> communauteOpti = new HashMap<Ville,ArrayList<Ville>>();
		int i = 0;
		int scoreCourant = score(communaute);
		
		while (i<k) {
			ville = random(communaute);
			if (ville.getEcole() == true) {
				retireEcole(ville.toString());
			}
			else ville.addEcole();
			
			if (score(communaute)<scoreCourant) {
				communauteOpti = communaute;
				i=0;
				scoreCourant = score(communaute);
			}
			else i++;
		}
		return communauteOpti;
	}
}
