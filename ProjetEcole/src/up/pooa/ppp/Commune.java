package up.pooa.ppp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
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
	 * Cette methode verifie si les villes entrees par l'utilisateur existent ou non.
	 * @param x La Ville de depart
	 * @param y La ville d'arrivee
	 * @return true si les 2 villes existes, false sinon.
	 */
	public boolean verifieVilleExiste(String x, String y) {
		boolean existe =true;
		Agglomeration depart=getVille(x);//On recupere la ville de depart.
		Agglomeration arrivee = getVille(y);//On recupere la ville d'arrivee.
		if(!commune.containsKey(depart)) { //On verifie si la ville de depart existe.
			System.out.println("La ville "+x+" n'existe pas.");
			existe = false;
		}
		if(!commune.containsKey(arrivee)) {//On verifie si la la ville d'arrivee existe.
			System.out.println("La ville "+y+" n'existe pas.");
			existe = false;
		}
		System.out.println("Avant l'ajout ou le retrait d'une route : ");	//Ces trois lignes
		afficherHashMap();												//sont ici pour check les ArrayList 
		System.out.println("--------------------\n");						//lors des test
		return(existe);
	}
	
	/**
	 * Ajoute une route entre deux villes (de x vers y et de y vers x ).
	 * @param x La Ville de depart.
	 * @param y La ville d'arrivee. 
	 */
	public void addRoute(String x, String y) {
		Agglomeration depart=getVille(x);//On recupere la ville de depart.
		Agglomeration arrivee = getVille(y);//On recupere la ville d'arrivee.
		if((commune.get(depart).contains(arrivee))&&(commune.get(arrivee).contains(depart))) //On verifie si il existe une route de la ville de depart à la ville d'arriver.il me semble que l'on peut tester seulement dans un sens
			System.out.println("La route de la ville "+depart+" a la ville "+arrivee+" existe deja donc on ne peut pas en ajouter une.");
		else {
			commune.get(depart).add(arrivee);
			commune.get(arrivee).add(depart);
			System.out.println("Vous avez ajoute une route entre la ville "+x+" et la ville "+y+".");
		}
		afficherHashMap();//Ici pour check les ArrayList lors des test
	}
	
	/**
	 * Supprime une route entre deux villes (de x vers y et de y vers x ).
	 * @param x La ville de depart.
	 * @param y La ville d'arrivee.
	 */
	public void supprimerRoute (String x, String y) {
		Agglomeration depart=getVille(x);//On recupere la ville de depart.
		Agglomeration arrivee = getVille(y);//On recupere la ville d'arrivee.
		if(!(commune.get(depart).contains(arrivee))||!(commune.get(arrivee).contains(depart)))	//Dans le cas ou il n'existe pas de route.il me semble que l'on peut tester seulement dans un sens
			System.out.println("Il n'y a pas de route de la ville "+depart+" a la ville "+arrivee+" donc elle ne peut pas etre supprimee.");
		else {
			commune.get(depart).remove(arrivee);//Supprime la ville d'arrivee de l'ArrayList de la ville de depart, c'est a dire qu'on supprime la route de la ville de depart vers la route d'arrivee
			commune.get(arrivee).remove(depart);//Supprime la ville de depart de l'ArrayList de la ville d'arrivee, c'est a dire qu'on supprime la route de la ville d'arrivee vers la route de depart
			System.out.print("Vous avez supprime une route entre la ville "+x+" et la ville "+y+".\n");
		}
		afficherHashMap();//Ici pour check les ArrayList lors des test
	}
	
	/**
	 * Affiche les routes liees a une ville.
	 * @param x la ville choisie.
	 */
	public void afficheRoute(String x) {
		Agglomeration depart=getVille(x);//On recupere la ville
		if(commune.containsKey(depart)) {//On verifie si la ville existe dans la commune
			System.out.println(commune.get(depart));
		}
		else
			System.out.println("La ville "+x+" n'existe pas.");
		}
	/*
	 * Permet d'afficher la liste d'adjacence c'est a dire qu'elle affiche les villes qui sont liees entre elles.
	 * La methode se trouve dans verifieVilleExiste, Addroute, SupprimerRoute
	 * (et je viens d'y penser il faudra choisir si on met les nom de methodes en anglais ou francais et aussi pour les variable je pense)
	 * 
	 */
	public void afficherHashMap() {
		for(Entry<Agglomeration,ArrayList<Agglomeration>> element : commune.entrySet()) {
			System.out.println("La ville "+element.getKey()+" est liee aux villes"+element.getValue());	
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
}
