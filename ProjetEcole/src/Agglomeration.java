/**
 * Creation de la classe aglomeration.
 * @param nom Nom de la ville.
 * @param ecole Boolean representant la presence d'ecole.
 * 		Initialiser a true.
 */
public class Agglomeration {
	private String nom;
	private boolean ecole;

	/**
	 * Constructeur.
	 */
	public Agglomeration(char nom) {
		this.nom = Character.toString(nom);
	}

	/**
	 * Getter.
	 */
	public String getNomVille() {
		return this.nom;
	}
	public boolean getEcole() {
		return this.ecole;
	}

	@Override
	public String toString() {
		return nom;
	}
	/**
	 * Set ecole to true.
	 */
	public void addEcole() {
		this.ecole = true;
	}
	/**
	 * Set ecole to false.
	 */
	public void retireEcole() {
		this.ecole = false;
	}
	@Override
	/**
	 * Redéfinition de hashCode pour pouvoir redifinir equals et sont utilisation dans les
	 * fonctions de hashMap.
	 * @return hashCode(nom)
	 */
	public int hashCode() {
		return nom.hashCode();
	}
	@Override
	/**
	 * @param Agglomeration
	 * @return Agglomeration.getNomVille() == this.getNomVille()
	 */
	public boolean equals(Object v) {
		if (v==null && !(v instanceof Agglomeration)) {
			return false;
		} else if (v == this) {
			return true;
		} else {
			Agglomeration trans = (Agglomeration) v;
			return nom.equals(trans.getNomVille());
		}
	}
}
