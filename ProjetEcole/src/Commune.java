import java.util.ArrayList;
import java.util.HashMap;

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
	 * Ajoute une route entre deux villes (de x vers y).
	 * @param x La Ville de d�part
	 * @param y La ville d'arriv�e 
	 */
	public void addRoute(String x, String y) {
		Agglomeration depart=getVille(x);//On r�cup�re la ville de d�part.
		Agglomeration arrivee = getVille(y);//On r�cup�re la ville d'arriv�e.
		//if(!commune.containsValue(arrivee))//V�rifier si l'ArrayList de la ville ne contient pas la ville d'arrivee, j'arrive pas � y acc�der
			if(commune.containsKey(depart)) {//On v�rifie si la commune la ville de d�part existe.
				commune.get(depart).add(arrivee);
			}
			else {
				System.out.println("La ville "+x+" n'existe pas.");
			}
			System.out.println("L'ArrayList de la ville "+x+" pour check :"+commune.get(depart));//Simplement pour retourner l'ArrayList de la ville de d�part � la fin de la fonction pour tester, on peut l'enlever
	}
	
	/**
	 * Ajoute la route inverse entre deux villes (de y vers x).
	 * @param x La Ville de d�part
	 * @param y La ville d'arriv�e 
	 */
	public void addRouteInversee(String x, String y) {
		Agglomeration depart=getVille(x);//On r�cup�re la ville de d�part
		Agglomeration arrivee = getVille(y);//On r�cup�re la ville d'arriv�e
		//if(!commune.containsValue(depart));//V�rifier si l'ArrayList de la ville de d�part ne contient pas la ville de d�part, j'arrive pas � y acc�der
			if(commune.containsKey(arrivee)) {//On v�rifie si dans la commune la ville d'arriv�e existe
				commune.get(arrivee).add(depart);
			}
			else {
				System.out.println("La ville "+y+" n'existe pas.");
			}
		System.out.println("L'ArrayList de la ville "+y+" pour check :"+commune.get(arrivee));//Simplement pour retourner l'ArrayList de la ville de arrivee � la fin de la fonction pour tester, on peut l'enlever
	}
	
	public void supprimerRoute(String x, String y) {
		Agglomeration depart=getVille(x);//On r�cup�re la ville de d�part.
		Agglomeration arrivee = getVille(y);//On r�cup�re la ville d'arriv�e.
		if(commune.containsKey(depart)) {//On v�rifie si la commune la ville de d�part existe.
			//if(commune.containsValue(d'arriv�e))//Verifier si l'ArrayList detient la ville d'arriv�e avant de la supprim�e mais je n'arrive pas � y acc�der
				commune.get(depart).remove(arrivee);
		}
		else {
			System.out.println("La ville "+x+" n'existe pas.");
		}
		System.out.println("L'ArrayList de la ville "+x+" pour check :"+commune.get(depart));//Simplement pour retourner l'ArrayList de la ville de d�part � la fin de la fonction pour tester, on peut l'enlever
	}
	
	public void supprimerRouteInverse(String x, String y) {
		Agglomeration depart=getVille(x);//On r�cup�re la ville de d�part.
		Agglomeration arrivee = getVille(y);//On r�cup�re la ville d'arriv�e.
		if(commune.containsKey(arrivee)) {//On v�rifie si la commune la ville de d�part existe.
			//if(commune.containsValue(depart))V�rifier si l'ArrayList d�tient la ville de d�part avant de la supprim�e mais je n'arrive pas � y acc�der
				commune.get(arrivee).remove(depart);
		}
		else {
			System.out.println("La ville "+y+" n'existe pas.");
		}
		System.out.println("L'ArrayList de la ville "+y+" pour check :"+commune.get(arrivee));//Simplement pour retourner l'ArrayList de la ville de arrivee � la fin de la fonction pour tester, on peut l'enlever
	}
	
	/**
	 * Affiche les routes li�es � une ville.//Elle n'est pas impll�ment�e
	 * @param x la ville choisie
	 */
	public void afficheRoute(String x) {
		Agglomeration depart=getVille(x);//On r�cup�re la ville
		if(commune.containsKey(depart)) {//On v�rifie si dans la commune la ville d'arriv�e existe
			System.out.println(commune.remove(depart));
		}
		else
			System.out.println("La ville "+x+" n'existe pas.");
	}
	
	
	
	
}