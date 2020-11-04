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

	/**
	 * Constructeur.
	 */
	public Commune() {
		commune = new HashMap<Agglomeration,ArrayList<Agglomeration>>();
		nombresVilles = 0;
	}
	public Commune(int nombresVilles) {
		commune = new HashMap<Agglomeration,ArrayList<Agglomeration>>();
		this.nombresVilles = nombresVilles;
	}

	/**
	 * Ajout d'une agglomeration dans la commune.
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
	 * Affiche la liste des villes (en ligne).
	 */
	public void affiche() {
		for (Agglomeration k : commune.keySet()) {
			System.out.print(k+" ");
		}
		System.out.print("\n");
	}
	
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
	 * Cette méthode vérifie si les villes entrées par l'utilisateur existent ou non.
	 * @param x La Ville de départ
	 * @param y La ville d'arrivée
	 * @return
	 */
	public boolean verifieVilleexiste(String x, String y) {
		boolean existe =true;
		Agglomeration depart=getVille(x);//On récupère la ville de départ.
		Agglomeration arrivee = getVille(y);//On récupère la ville d'arrivée.
		if(!commune.containsKey(depart)) { //On vérifie si la la ville de départ existe.
			System.out.println("La ville "+x+" n'existe pas.");
			existe = false;
		}
		if(!commune.containsKey(arrivee)) {//On vérifie si la la ville d'arrivée existe.
			System.out.println("La ville "+y+" n'existe pas.");
			existe = false;
		}
		System.out.println("Avant l'ajout ou le retrait d'une route : ");	//Ces trois lignes
		afficherlaHashMap();												//sont ici pour check les ArrayList 
		System.out.println("--------------------\n");						//lors des test
		return(existe);
	}
	
	/**
	 * Ajoute une route entre deux villes (de x vers y et de y vers x ).
	 * @param x La Ville de départ.
	 * @param y La ville d'arrivée. 
	 */
	public void addRoute(String x, String y) {
		Agglomeration depart=getVille(x);//On récupère la ville de départ.
		Agglomeration arrivee = getVille(y);//On récupère la ville d'arrivée.
		if((commune.get(depart).contains(arrivee))&&(commune.get(arrivee).contains(depart))) //On vérifie si il existe une route de la ville de départ à la ville d'arriver.il me semble que l'on peut tester seulement dans un sens
			System.out.println("La route de la ville "+depart+" à la ville "+arrivee+" existe déjà donc on ne peut pas en ajouter une.");
		else {
			commune.get(depart).add(arrivee);
			commune.get(arrivee).add(depart);
			System.out.println("Vous avez ajouté une route entre la ville "+x+" et la ville "+y+".");
		}
		afficherlaHashMap();//Ici pour check les ArrayList lors des test
	}
	
	/**
	 * Supprime une route entre deux villes (de x vers y et de y vers x ).
	 * @param x La ville de départ.
	 * @param y La ville d'arrivée.
	 */
	public void supprimerRoute (String x, String y) {
		Agglomeration depart=getVille(x);//On récupère la ville de départ.
		Agglomeration arrivee = getVille(y);//On récupère la ville d'arrivée.
		if(!(commune.get(depart).contains(arrivee))||!(commune.get(arrivee).contains(depart)))	//Dans le cas ou il n'existe pas de route.il me semble que l'on peut tester seulement dans un sens
			System.out.println("Il n'y a pas de route de la ville "+depart+" à la ville "+arrivee+" donc elle ne peut pas être supprimée.");
		else {
			commune.get(depart).remove(arrivee);//Supprime la ville d'arrivée de l'ArrayList de la ville de départ, c'est à dire qu'on supprime la route de la ville de départ vers la route d'arrivée
			commune.get(arrivee).remove(depart);//Supprime la ville de départ de l'ArrayList de la ville d'arrivée, c'est à dire qu'on supprime la route de la ville d'arrivée vers la route de départ
			System.out.print("Vous avez supprimé une route entre la ville "+x+" et la ville "+y+".\n");
		}
		afficherlaHashMap();//Ici pour check les ArrayList lors des test
	}
	
	/**
	 * Affiche les routes liées à une ville.
	 * @param x la ville choisie.
	 */
	public void afficheRoute(String x) {
		Agglomeration depart=getVille(x);//On récupère la ville
		if(commune.containsKey(depart)) {//On vérifie si la ville existe dans la commune
			System.out.println(commune.get(depart));
		}
		else
			System.out.println("La ville "+x+" n'existe pas.");
		}
	/*
	 * Permet d'afficher la liste d'adjacence c'est à dire qu'elle affiche les villes qui sont liées entre elles.
	 * La méthode se trouve dans verifieVilleExiste, Addroute, SupprimerRoute
	 * (et je viens d'y penser il faudra choisir si on met les nom de méthodes en anglais ou francais et aussi pour les variable je pense)
	 * 
	 */
	public void afficherlaHashMap() {
		for(Entry<Agglomeration,ArrayList<Agglomeration>> element : commune.entrySet()) {
			System.out.println("La ville "+element.getKey()+" est liée aux villes"+element.getValue());	
		}	
		System.out.println();
	}	
}