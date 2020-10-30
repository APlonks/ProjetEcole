import java.util.ArrayList;
import java.util.List;

public class Commune {
	//Initialisation des variables
	private static List<Agglomeration>villes;
	private int nombresVilles;
	private Agglomeration ville;
	
	public Commune(int nombresVilles) {
		this.nombresVilles = nombresVilles;
		villes= new ArrayList<Agglomeration>(nombresVilles);
	}
	
	public Commune(Agglomeration ville) {
		this.ville=ville;
	}
	
	public void addVille(Agglomeration v) {//Methode permettant d'ajouter une ville a la liste.
		villes.add(v);
	}
	public static void affiche() {//Methode affichant la liste de villes.
		for (int i=0; i<villes.size(); i++) {
			System.out.println(villes.get(i));
		}
	}
	
	public Agglomeration getVille(char c) {//Methode permettant de renvoyer le nom de la ville
		for(Agglomeration a : villes) {
			if(a.getNomVille() == c) {
				return a;
			}
		}
		return null;
	}
}
