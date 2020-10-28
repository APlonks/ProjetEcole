import java.util.ArrayList;
import java.util.List;

public class Commune {
	private List<Agglomeration>villes;
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
       
}
