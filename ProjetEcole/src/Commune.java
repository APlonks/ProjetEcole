import java.util.ArrayList;
import java.util.List;

public class Commune {
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
	
	public void addVille(Agglomeration v) {
		villes.add(v);
	}
	public static void affiche() {
		for (int i=0; i<villes.size(); i++) {
			System.out.println(villes.get(i));
		}
	}
	
	public Agglomeration getVille(char c) {
		for(Agglomeration a : villes) {
			if(a.getNomVille() == c) {
				return a;
			}
		}
		return null;
	}
}
