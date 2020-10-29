
public class Agglomeration {
	private char nom;
	private boolean ecole;
	
	public Agglomeration(char nom) {
		this.nom = nom;
	}
	public String toString() {
		return Character.toString(nom);
	}
	
	public char getNomVille() {
		return this.nom;
	}
	
	public boolean getEcole() {
		return this.ecole;
	}
	
	public void addEcole() {
		this.ecole = true;
	}
	
	public void retireEcole() {
		this.ecole = false;
	}
}
