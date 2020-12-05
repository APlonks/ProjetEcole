package principal;
/**
 * Agglomeration representer par un nom et la presence ou nom d'une ecole.
 */
public class Agglomeration {
	/**
	 * @param nom Nom de l'Agglomeration.
	 * @param ecole boolean representant la presence d'ecole.
	 */
	private String nom;
	private boolean ecole;

	/**
	 * Constructeur.
	 * @param nom Nom de l'Agglomeration.
	 */
	public Agglomeration(char nom) {
		this.nom = Character.toString(nom);
	}
	/**
	 * Constructeur.
	 * @param nom Nom de l'Agglomeration.
	 * @param ecole True si possede une ecole, false sinon.
	 */
	public Agglomeration(char nom, boolean ecole) {
		this.nom = Character.toString(nom);
		this.ecole = ecole;
	}
	/**
	 * Constructeur
	 * @param nom Nom de l'Agglomeration.
	 */
	public Agglomeration (String nom) {
		this.nom = nom;
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
	 * @return Agglomeration.getNomVille() == this.getNomVille()
	 */
	@Override
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