package principal;

import gestion.Scan;
import gestion.ScanException;

/**
 * @author Pinto, Poirier, Planque
 * @version 1.0
 */

/**
 * Class principale d'execution.
 */
public class Main {
	/**
	 * Methode main.
	 * @param args Entre parametrage ligne de commande.
	 */
	public static void main(String[] args) {
		//Initialisation des variables
		int nombreVilles = 0;
		boolean arretMenu = false;
		String nomVille;
		String x = null, y = null; //Variable pour le parrametrage des villes.

		
			System.out.print("Entrez le nombre de villes de la communaute d'agglomeration");
			try {
				nombreVilles = Scan.lireEntier();
			} catch (ScanException e) {
				System.out.println(e.getMessage());
			}
		//Le nombre de villes est pour l'instant compris entre 1 et 26
		
		/* Creation de la liste de villes en fonction du nombre de villes selectionner par
		 * l'utilisateur.
		 * De plus chaque ville sera nommee en fonction d'une lettre de l'alphabet commeneant par
		 * 'A' et initialiser avec une ecole. */
		CA commune = new CA();
		for(int i=0; i<nombreVilles; i++) {
			nomVille = UtilMethodeCA.nomVilleUtilisateur();
			commune.addVille(new Ville(nomVille,true));
		}

		System.out.println("Vous avez choisi " + nombreVilles + " Villes.");
		commune.affiche();//Affiche la liste de villes

		//Affiche le menu1 et effectue une action en fonction du choix de l'option de l'utilisateur
		do {
			switch(Scan.questionReponse("Selectionner une option :\n\t1) Ajouter une route "+
				"(ajouter)\n\t2) Fin (fin)\n\t3) Afficher les routes d'une ville (afficher)\n\t"
				+"4) Supprimer une route (supprimer)\n\t5) Aide (aide)\n","ajouter","fin"
				,"afficher","supprimer","aide","h","a","f","af","s","1","2","3","4","5")) {
			case "ajouter" :
			case "a" :
			case "1" :
				try {
					if (Scan.estVide()) {
						System.out.println("Votre ville depart ?");
						x = (Scan.lireMot());//L'utilisateur entre le nom de la ville de depart.
						System.out.println("Votre ville d'arrivee ?");
						y = (Scan.lireMot());//L'utilisateur entre le nom de la ville d'arrivee.
						commune.addRoute(x,y);
						/* Appel la methode pour creer une route de la ville de depart vers la
						 * ville d'arrivee. */
					} else {
						do {
							commune.addRoute(Scan.motDelimiter('|'),Scan.motSuivant());
						} while (!Scan.estVide());
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "fin" :
			case "f" :
			case "2" :
				if (commune.estConnexe()) {
					arretMenu = true;
				}
				break;
			case "afficher" :
			case "af" :
			case "3" :
				try {
					if (Scan.estVide()) {
						System.out.println("La ville dont vous voulez connaitre les routes ?");
						commune.afficheRoute(Scan.lireMot());
					} else {
						do {
							commune.afficheRoute(Scan.motSuivant());
						} while (!Scan.estVide());
					}
				} catch (ScanException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "supprimer" :
			case "s" :
			case "4" :
				try {
					if (Scan.estVide()) {
						System.out.println("Votre ville depart ?");
						x = (Scan.lireMot());//L'utilisateur entre le nom de la ville de depart.
						System.out.println("Votre ville d'arrivee ?");
						y = (Scan.lireMot());//L'utilisateur entre le nom de la ville d'arrivee.
						commune.supprimerRoute(x,y);
						/* Appel la methode pour creer une route de la ville de depart vers la
						 * ville d'arrivee. */
					} else {
						do {
							commune.supprimerRoute(Scan.motDelimiter('|'),Scan.motSuivant());
						} while (!Scan.estVide());
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "aide" :
			case "h" :
			case "5" :
				System.out.println("Vous pouvez selectionner une option en tapant le numero de"+
				" l'option, son nom entre parenthese ou son initiale ('af' pour afficher, 'h' pour"+
				" help).\nIl est possible d'ajouter ou retirer plusieurs routes d'un coup."+
				" Exemple :\nPour ajouter 2 routes, entre A - B et C - D, il suffit au moment de"+
				" la selection de l'option d'ecrire 'ajouter A|B C|D'");
				break;
			}
		} while(arretMenu == false);
		commune.afficherHashMap();
		commune.estConnexe();

		/* Affiche le menu2 et effectue une action en fonction du choix de l'option de
		 * l'utilisateur. */
		arretMenu = false;
		do {
			switch(Scan.questionReponse("Selectionner une options :\n\t1) Ajouter une ecole"+
				" (ajouter)\n\t2) Retirer une ecole (retirer)\n\t3) Fin (fin)\n\t4) Aide (aide)\n",
				"ajouter","a","1","retirer","r","2","fin","f","3","aide","h","4")) {
			case "ajouter" :
			case "a" :
			case "1" :
				//Permet d'ajouter une ecole dans une ville si il n'y en a pas deja une.
				try {
					if (Scan.estVide()) {
						System.out.println("Dans quel ville voulez-vous ajouter une ecole ?");
 						commune.addEcole(Scan.lireMot());
					} else {
						do {
							commune.addEcole(Scan.motSuivant());
						} while (!Scan.estVide());
					}
				} catch (ScanException e) {
					System.out.println(e.getMessage());
				}
				commune.afficheEcole();
			break;
			case "retirer" :
			case "r" :
			case "2" :
				//Permet de retirer une ecole d'une ville.
				try {
					if (Scan.estVide()) {
						System.out.println("Dans quel ville voulez-vous retirer une ecole ?");
						commune.retireEcole(Scan.lireMot());
					}
					else {
						while (!Scan.estVide()) {
							commune.retireEcole(Scan.motSuivant());
						}
					}
				} catch (ScanException e) {
					System.out.println(e.getMessage());
				}
				commune.afficheEcole();
			break;
			
			case "fin" :
			case "f" :
			case "3" :
				arretMenu = true;
			break;
			case "aide" :
			case "h" :
			case "4" :
				System.out.println("Vous pouvez selectionner une option en tapant le numero de"+
					" l'option, son nom entre parenthese ou son initiale('h' pour help).\n"
					+ "Il est possible d'ajouter ou retirer plusieurs ecoles d'un coup."+
					" Exemple :\nPour ajouter 2 ecoles, dans les villes A et B par exemple, "
					+ "il suffit au moment de la selection de l'option d'ecrire 'ajouter A B'.");
			break;
			}
		}
		while(arretMenu == false);
		Scan.fermer();
	}

}
