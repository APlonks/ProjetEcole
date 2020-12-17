package principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;

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
	private static boolean contientEcole(ArrayList<Ville> lst) {
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
	 * @throws CAException Si la ville existe deja.
	 * @param v Une nouvelle ville
	 */
	public void addVille(Ville v) throws CAException{
		if (!(communaute.containsKey(v))) {
			/* Ville nom deja presente. */
			communaute.put(v,new ArrayList<Ville>());
		} else {
			throw new CAException(v.getNomVille()+" existe deja");
		}
	}

	/**
	 * Ajoute une ville a la Communaute d'Agglomeration.
	 * @throws CAException Si la ville existe deja.
	 * @param nom Une nouvelle ville
	 */
	public void addVille(String nom) throws CAException {
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
	 * @throws CAException si les villes n'existe pas ou que la route existe deja.
	 * @param x La Ville de depart.
	 * @param y La ville d'arrivee. 
	 */
	public void addRoute(String x, String y) throws CAException {
		Ville depart, arriver;
		if ((depart=getVille(x))==null) {
			throw new CAException(x+" n'existe pas");
		} else if ((arriver=getVille(y))==null) {
			throw new CAException(y+" n'existe pas");
		} else if (arriver.equals(depart)) {
			throw new CAException("Ville identique, parametre invalide");
		} else if ((communaute.get(depart).contains(arriver)) &&
			(communaute.get(arriver).contains(depart))) {
			/* On verifie si il existe une route de la ville de depart à la ville d'arriver.
			 * On ne verifie qu'un sens de la route car la liste d'adjacence est symetrique. */
			throw new CAException("la route "+x+"|"+y+" existe deja");
		} else {
			/* Ajoute chaque ville a la liste d'adjacence de l'autre. */
			communaute.get(depart).add(arriver);
			communaute.get(arriver).add(depart);
		}
	}

	/**
	 * Supprime une route entre deux villes (de x vers y et de y vers x ).
	 * @throws CAException si les villes n'existe pas ou que la route n'existe pas.
	 * @param x La ville de depart.
	 * @param y La ville d'arrivee.
	 */
	public void supprimerRoute (String x, String y) throws CAException{
		Ville depart, arriver;
		if ((depart=getVille(x))==null) {
			throw new CAException(x+" n'existe pas");
		} else if ((arriver=getVille(y))==null) {
			throw new CAException(y+" n'existe pas");
		} else if (arriver.equals(depart)) {
			throw new CAException("Ville identique, parametre invalide");
		} else if ((communaute.get(depart).contains(arriver)) &&
			(communaute.get(arriver).contains(depart))) {
			/* Retire chaque ville de la liste d'adjacence de l'autre. */
			communaute.get(depart).remove(arriver);
			communaute.get(arriver).remove(depart);
		} else {
			/* On verifie si il existe une route de la ville de depart à la ville d'arriver.
			 * On ne verifie qu'un sens de la route car la liste d'adjacence est symetrique. */
			throw new CAException("la route "+x+"|"+y+" n'existe pas");
		}
	}

	/**
	 * Affiche les routes liees a une ville.
	 * @throws CAException Si la ville n'existe pas.
	 * @param x la ville choisie.
	 */
	public void afficheRoute(String x) throws CAException{
		Ville depart;
		if ((depart=getVille(x))==null) {
			throw new CAException(x+" n'existe pas");
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
	 * @throws CAException Si la ville n'existe pas.
	 * @param x une Ville
	 * @return true si on as put retirer l'ecole false sinon.
	 */
	public boolean retireEcole(String x) throws CAException {
		Ville ville;
		if ((ville=getVille(x))==null) {
			throw new CAException(x+" n'existe pas");
		} else if (!contientEcole(communaute.get(ville))) {
			return false;
		} else {
			/* Parcour des adjacent de ville pur verifier que l'ecole de ville n'est pas la seul
			 * ecole a laquelle ils ont acces. */
			for (Ville b : communaute.get(ville)) {
				if (!accesAutreEcole(b,ville)) {
					return false;
				}
			}
			ville.retireEcole();
			return true;
		}
	}

	/**
	 * On considere une CA vide comme etant non connexe.
	 * @return true si la CA est connexe false sinon.
	 */
	public boolean estConnexe() {
		if (communaute.size() < 1) {
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
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Ajoute une ecole.
	 * @throws CAException Si la ville n'existe pas.
	 * @param nomVille Ville ou l'on veut ajouter une ecole.
	 */
	public void addEcole(String nomVille) throws CAException {
		Ville ville;
		if ((ville=getVille(nomVille)) == null ) {
			throw new CAException(nomVille+" n'existe pas");
		} else if (ville.getEcole() == true) {
			throw new CAException(nomVille+" possede deja une ecole");
		} else {
			ville.addEcole();
		}
	}
	
	public CA algoApproximation(CA communauteUtilisateur, int k) throws CAException {
		Ville ville;
		CA communauteOpti = new CA();
		int i = 0;
		int scoreCourant = UtilMethodeCA.score(communauteUtilisateur);
		
		while (i<k) {
			ville = UtilMethodeCA.random(communauteUtilisateur);
			if (ville.getEcole() == true) {
				retireEcole(ville.toString()); 
			}
			else ville.addEcole();
			
			if (UtilMethodeCA.score(communauteUtilisateur)<scoreCourant) {
				communauteOpti = communauteUtilisateur;
				i=0;
				scoreCourant = UtilMethodeCA.score(communauteUtilisateur);
			}
			else i++;
		}
		return communauteOpti;
	}

	/**
	 * Algo qui calcul le nombre d'ecole minimal a ajouter.
	 * On y inclus 2 mode : 0 si on initialiser toutes les villes sans ecole, sinon on tient
	 * compte de celle deja creer.
	 * Pour cela on utilise les couple (ville , nombreVoisinsSansAccesEcole) en ajoutant petit
	 * a petit des ecoles au ville ayant la plus grande valeur associer.
	 * @param mode de resolution.
	 */
	public void algoQueue(int mode) {
		HashMap<Ville,Boolean> accesE = UtilMethodeCA.creationAccesE(this,mode);
		PriorityQueue<SimpleEntry<Ville,Integer>> ordrePrio =
					new PriorityQueue<>(queueCompare);
		Set<Ville> cle = communaute.keySet();
		int nbVoisinsDependant;

		/* On initialise la queue.
		 * On y ajoute que les villes qui n'ont pas d'ecole, dont aux moins un voisins n'as pas
		 * d'acces a une ecole ou lui meme. */
		for (Ville v : cle) {
			if (!v.getEcole()) {
				nbVoisinsDependant = UtilMethodeCA.compteNouvAccesE(communaute.get(v),accesE);
				if (nbVoisinsDependant!=0 || accesE.get(v)==false) {
					ordrePrio.add(new SimpleEntry<Ville,Integer>(v,nbVoisinsDependant));
				}
			}
		}
		/* Tant que toutes les ville non pas acces a une ecole. On cherche a en ajouter. */
		while (!UtilMethodeCA.accesPartout(accesE)) {
			analysteTeteQueue(ordrePrio,accesE);
		}
		afficheEcole();
	}

	private void analysteTeteQueue(PriorityQueue<SimpleEntry<Ville,Integer>> ordrePrio,
									 HashMap<Ville,Boolean> accesE) {
		SimpleEntry<Ville,Integer> tete = ordrePrio.remove();
		int nbVoisinsUpdate = UtilMethodeCA.compteNouvAccesE(communaute.get(tete.getKey()),
			accesE);
		/* On verifie que les changement entre temps n'ont pas modifier l'acces des voisins. */
		if (nbVoisinsUpdate == tete.getValue()) {
			tete.getKey().addEcole();
			UtilMethodeCA.majAccesE(communaute.get(tete.getKey()),accesE);
			accesE.put(tete.getKey(),true);
		} else {
			ordrePrio.add(new SimpleEntry<Ville,Integer>(tete.getKey(),nbVoisinsUpdate));
		}
	}

	/**
	 * Definition local de la class Comparator car c'est le seul endroit ou l'on en as besoin.
	 */
	public static Comparator<SimpleEntry<Ville,Integer>> queueCompare =
				new Comparator<SimpleEntry<Ville,Integer>>(){
		@Override
		public int compare(SimpleEntry<Ville,Integer> c1, SimpleEntry<Ville,Integer> c2) {
            return c2.getValue() - c1.getValue();
        }
	};
}
