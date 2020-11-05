package up.pooa.ppp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Class represantant un groupement d'Agglomeration relier entre elle.
 * On utiliserat les listes d'adjacence pour le relier.
 * @param commune Liste d'adjacence des Agglomeration.
 * @param nombresVilles Nombres de villes.
 */
public class Commune {
	private HashMap<Agglomeration,ArrayList<Agglomeration>> commune;
	private int nombresVilles;

	 //Constructeurs.
	public Commune() {
		commune = new HashMap<Agglomeration,ArrayList<Agglomeration>>();
		nombresVilles = 0;
	}
	public Commune(int nombresVilles) {
		commune = new HashMap<Agglomeration,ArrayList<Agglomeration>>();
		this.nombresVilles = nombresVilles;
	}

	/**
	 * @param lst Liste des villes adjacentes (Lie par une route)
	 * @return true si il existe 'a' dans lst avec 'a' possedant une ecole.
	 * 		false sinon.
	 */
	private boolean contientEcole(ArrayList<Agglomeration> lst) {
		for (Agglomeration a : lst) {
			if (a.getEcole()) {
				return true;
			}
		}
		return false;
	}
	/**
	 * @param agA 1 ville de depart.
	 * @param agB 1 ville adjacente a agA.
	 * @return true si agA contient une ecole ou si une ville adjacence a agA autre que
	 * 		agB contient une ecole.
	 */
	private boolean accesAutreEcole(Agglomeration agA, Agglomeration agB) {
		if (agA.getEcole()) {
			return true;
		} else {
			for (Agglomeration b : commune.get(agA)) {
				if (b.getEcole() && !b.equals(agB)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * @param v Une nouvelle ville
	 * Ajoute une ville a l'agglomeration
	 */
	public void addVille(Agglomeration v) {
		if (!(commune.containsKey(v))) {
			/* Ville nom deja presente. */
			commune.put(v,new ArrayList<Agglomeration>());
		} else {
			/* Affichage pour qu'on pense a l'enlever. */
			System.out.println("Ville deja existante.");
		}
	}
	/**
	 * @param nom Une nouvelle ville
	 * Ajoute une ville a l'aglomeration
	 */
	public void addVille(String nom) {
		Agglomeration v = new Agglomeration(nom);
		addVille(v);
	}
	/**
	 * Affiche la liste des villes (en ligne).
	 */
	public void affiche() {
		for (Agglomeration k : commune.keySet()) {
			System.out.print(k+" ");
		}
		System.out.print("\n");
	}
	
	/**
	 * Affiche la liste de villes possedant une ecole.
	 */
	public void afficheEcole() {
		for (Agglomeration k : commune.keySet()) {
			if (k.getEcole() == true) {
				System.out.print(k+" ");
			}
		}
		System.out.print("\n");
	}
	/**
	 * Methode permettant de renvoyer le nom de la ville.
	 * @param recherche Un nom de ville
	 * @return Agglomeration recherche si elle existe, null sinon.
	 */
	public Agglomeration getVille(String recherche) {
		for(Agglomeration k : commune.keySet()) {
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
		Agglomeration depart, arriver;
		if ((depart=getVille(x))==null) {
			System.out.println("La ville "+x+" n'existe pas.");
		} else if ((arriver=getVille(y))==null) {
			System.out.println("La ville "+y+" n'existe pas.");
		} else if (arriver.equals(depart)) {
			System.out.println("Les villes d'arriver et de depart sont les meme. Demande"+
				" incoherente.");
		} else if ((commune.get(depart).contains(arriver)) &&
			(commune.get(arriver).contains(depart))) {
			/* On verifie si il existe une route de la ville de depart à la ville d'arriver.
			 * On ne verifie qu'un sens de la route car la liste d'adjacence est symetrique. */
			System.out.println("La route de la ville "+depart+" a la ville "+arriver+
				" existe deja donc on ne peut pas en ajouter une.");
		} else {
			/* Ajoute chaque ville a la liste d'adjacence de l'autre. */
			commune.get(depart).add(arriver);
			commune.get(arriver).add(depart);
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
		Agglomeration depart, arriver;
		if ((depart=getVille(x))==null) {
			System.out.println("La ville "+x+" n'existe pas.");
		} else if ((arriver=getVille(y))==null) {
			System.out.println("La ville "+y+" n'existe pas.");
		} else if (arriver.equals(depart)) {
			System.out.println("Les villes d'arriver et de depart sont les meme. Demande"+
			" incoherente.");
		} else if ((commune.get(depart).contains(arriver)) &&
			(commune.get(arriver).contains(depart))) {
			/* Retire chaque ville de la liste d'adjacence de l'autre. */
			commune.get(depart).remove(arriver);
			commune.get(arriver).remove(depart);
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
		Agglomeration depart;
		if ((depart=getVille(x))==null) {
			System.out.println("La ville "+x+" n'existe pas.");
		} else {
			System.out.println(commune.get(depart));
		}
	}
	/**
	 * Permet d'afficher la liste d'adjacence.
	 */
	public void afficherHashMap() {
		for(Entry<Agglomeration,ArrayList<Agglomeration>> element : commune.entrySet()) {
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
		Agglomeration ville;
		if ((ville=getVille(x))==null) {
			System.out.println("La ville "+x+" n'existe pas.");
		} else if (!contientEcole(commune.get(ville))) {
			System.out.println("Aucun voisin de "+x+" ne contient d'ecole.");
		} else {
			/* Parcour des adjacent de ville pur verifier que l'ecole de ville n'est pas la seul
			 * ecole a laquelle ils ont acces. */
			boolean retireOk = true;
			for (Agglomeration b : commune.get(ville)) {
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
	 * @return true si commune est connexe false sinon.
	 * On considere une commune vide comme etant non connexe.
	 */
	public boolean estConnexe() {
		if (commune.size() < 1) {
			System.out.println("Il n'y as pas de ville dans la communaute d'agglomeration.");
			return false;
		}
		ArrayList<Agglomeration> ssGrapheConnexe, lstAgglo;
		ssGrapheConnexe = new ArrayList<Agglomeration>();
		lstAgglo = new ArrayList<Agglomeration>();
		/* lstAgglo contient toutes les Villes qui non pas déjà été visité. Elle est initialser
		 * a l'aide de toutes les key de commune.
		 * ssGrapheConnexe correspond a un ensemble d'Agglomeration appartenant aux meme sous
		 * graphe connexe. */
		for (Agglomeration k : commune.keySet()) {
			lstAgglo.add(k);
		}
		ssGrapheConnexe.add(lstAgglo.get(0));
		Agglomeration tampon = lstAgglo.get(0);
		lstAgglo.remove(tampon);
		/* Tant qu'il y a des Agglomeration dans la liste ssGrapheConnexe, on y ajoute si cela
		 * est possible leurs adjacent que l'on retire de lstAgglo dans ce cas. */
		while (!ssGrapheConnexe.isEmpty()) {
			tampon = ssGrapheConnexe.get(0);
			ssGrapheConnexe.remove(0);
			for (Agglomeration v : commune.get(tampon)) {
				if (lstAgglo.contains(v)) {
					ssGrapheConnexe.add(v);
					lstAgglo.remove(v);
				}
			}
		}
		if (!lstAgglo.isEmpty()) {
			System.out.println("Les villes "+tampon+" et "+lstAgglo.get(0)+" n'appartiennet pas "+
			"la meme communaute d'agglomeration.");
			return false;
		} else {
			return true;
		}
	}
}
