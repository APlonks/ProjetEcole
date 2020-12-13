package principal;
/**
 * Ville representer par un nom et la presence ou nom d'une ecole.
 */
public class Ville {
	/**
	 * @param nom Nom de la ville.
	 * @param ecole boolean representant la presence d'ecole.
	 */
	private String nom;
	private boolean ecole;

	/**
	 * Constructeur
	 * @param nom Nom de la ville.
	 */
	public Ville (String nom) {
		this.nom = nom;
		ecole = false;
	}
	/**
	 * Constructeur.
	 * @param nom Nom de la ville.
	 * @param ecole True si possede une ecole, false sinon.
	 */
	public Ville(String nom, boolean ecole) {
		this(nom);
		this.ecole = ecole;
	}

	/**
	 * Getter.
	 * @return Champs variable nom.
	 */
	public String getNomVille() {
		return this.nom;
	}
	/**
	 * Getter
	 * @return Champs variable ecole.
	 */
	public boolean getEcole() {
		return this.ecole;
	}

	/**
	 * Convertie en string.
	 * @return Le string de notre object.
	 */
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
	/**
	 * Redefinition de hashCode pour pouvoir redifinir equals et sont utilisation dans les
	 * fonctions de hashMap.
	 * @return hashCode(nom)
	 */
	@Override
	public int hashCode() {
		return nom.hashCode();
	}
	/**
	 * Fonction equals avec un autre object.
	 * @param v Object v avec lequel on compare.
	 * @return Ville.getNomVille() == this.getNomVille()
	 */
	@Override
	public boolean equals(Object v) {
		if (v==null && !(v instanceof Ville)) {
			return false;
		} else if (v == this) {
			return true;
		} else {
			Ville trans = (Ville) v;
			return nom.equals(trans.getNomVille());
		}
	}
}