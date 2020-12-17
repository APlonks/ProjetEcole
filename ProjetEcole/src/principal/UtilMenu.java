package principal;

import gestion.EnregistrementCA;
import gestion.LectureCA;
import gestion.LectureException;
import gestion.Scan;
import gestion.ScanException;

public class UtilMenu {
	public static void Menu1() {
		String choixUtilisateur = new String();
		int choix = 0;
		CA commune;
		do {
			/* Tant que l'utilisateur n'as pas fait de choix on lui demande. */
			try {
				System.out.println("Vous voulez creer ou charger un fichier ?");
				choixUtilisateur = Scan.lireMot();
				switch(choixUtilisateur) {

				case "creer":
						commune = new CA();
						creer(commune);
						menu2(commune);
						choix = 1;
					break;

				case "charger":
					commune = charger();
					menu3(commune);
					choix = 1;
					break;

				default :
					System.out.println("Entrez creer ou charger.");
				}
			} catch (ScanException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("");
		} while (choix==0);
	}

	public static int choixMenu2() {
		int choix = 0;
		try {
			System.out.println(" 1: Route\n 2: Ecole\n 3: Ville\n 4: Menu Suivant");
			switch (Scan.lireMot()) {
				case "1":
				case "route":
				case "r":
					choix = 1;
					break;
				case "2":
				case "ecole":
				case "e":
					choix = 2;
					break;
				case "3":
				case "ville":
				case "v":
					choix = 3;
					break;
				case "4":
				case "suivant":
				case "menu":
				case "m":
				case "s":
					choix = 4;
					break;
				default :
					System.out.println("Entre incorrect.");
			}
		} catch (ScanException e) {
			System.out.println(e.getMessage());
		}
		return choix;
	}

	public static int choixMenu3() {
		int choix = 0;
		try {
			System.out.println(" 1: Modifier communaute d'agglomeration\n 2: Resoudre\n"+
				" 3: Sauvegarder\n 4: Fin");
			switch (Scan.lireMot()) {
				case "1":
				case "modifier":
				case "m":
					choix = 1;
					break;
				case "2":
				case "resoudre":
				case "r":
					choix = 2;
					break;
				case "3":
				case "sauvegarder":
				case "s":
					choix = 3;
					break;
				case "4":
				case "fin":
				case "f":
					choix = 4;
					break;
				default :
					System.out.println("Entre incorrect.");
			}
		} catch (ScanException e) {
			System.out.println(e.getMessage());
		}
		return choix;
	}

		/**
	 * Demande a L'utilisateur si il veut ecraser le fichier cite.
	 * @return 1 si oui 0 si non.
	 */
	private static int ignorer() {
		String nomFichier = new String("");
		int choix = 0;
		System.out.print("La communaute d'agglomeration n'est pas connexe, ignorer : ");
		do {
			try {
				/* Verifie le retour. */
				switch (Scan.lireMot()) {
					case "oui":
					case "o":
						choix = 1;
						break;
					case "non":
					case "n":
						choix = -1;
						break;
					default :
						System.out.println("Reponse incorecte.");
						break;
				}
			} catch (ScanException e) {
				System.out.println(e.getMessage());
			}
		} while (choix==0);
		if (choix == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	public static void menu2(CA commune) {
		int choix;
		boolean arretMenu = false;
		do {
			choix = choixMenu2();
			switch(choix) {
			case 1: route(commune);
					break;
			case 2: ecole(commune);
					break;
			case 3: ville(commune);
					break;
			case 4: arretMenu = true;
					if (commune.estConnexe()) {
						menu3(commune);
					} else {
						switch (ignorer()) {
							case 1:
								menu3(commune);
								break;
							default:
								arretMenu = false;
						}
					}
					break;
			default : System.out.println("Choix incorrect");
					break;
			}
		} while(arretMenu == false);
	}

	public static void menu3(CA commune) {
		int choix;
		boolean arretMenu = false;
		do {
			choix = choixMenu3();
			switch(choix) {
			case 1: menu2(commune);
					break;
			case 2: resoudre(commune);
					break;
			case 3: EnregistrementCA.enregister(commune);
					break;
			case 4: arretMenu = true;
					break;
			default : System.out.println("Choix incorrect");
					break;
			} 
		} while(arretMenu == false);
	}

	private static int choixAjSup (String choix) {
		switch (choix) {
			case "1":
			case "ajouter":
			case "a":
				return 1;
			case "2":
			case "retirer":
			case "r":
				return 2;
			case "3":
			case "fin":
			case "f":
				return 3;
			default:
				return 4;
		}
	}

	private static String[] paramUnique() {
		String[] param = new String[2];
		try {
			System.out.println("Votre ville depart ?");
			param[0] = (Scan.lireMot());
			System.out.println("Votre ville d'arrivee ?");
			param[1] = (Scan.lireMot());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return param;
	}

	private static void traitementRoute(CA communaute, String[] param, int choix)
					throws ScanException, CAException {
		do {
			if (param.length != 2) {
				throw new CAException("Nombre d'argument invalide, 2 attendu, "+param.length+
					" founie");
			} else {
				if (choix == 1) {
					communaute.addRoute(param[0],param[1]);
				} else {
					communaute.supprimerRoute(param[0],param[1]);
				}
			}
			if (Scan.estVide()) {
				param = null;
			} else {
				param = Scan.parametre();
			}
		} while (param != null);
	}

	public static void route(CA commune) {
		int option = 0;
		do {
			try {
				System.out.println("Voulez vous :\n 1 : ajouter une route\n"+
						" 2 : retirer une route\n 3 : fin");
				switch (choixAjSup(Scan.lireMot())) {
					case 1:
						if (Scan.estVide()) {
							traitementRoute(commune,paramUnique(),1);
						} else {
							traitementRoute(commune,Scan.parametre(),1);
						}
						break;
					case 2:
						if (Scan.estVide()) {
							traitementRoute(commune,paramUnique(),0);
						} else {
							traitementRoute(commune,Scan.parametre(),0);
						}
						break;
					case 3:
						option = 3;
						break;
					default : System.out.println("Choix incorrect");
						break;
				}
			} catch (ScanException e) {
				System.out.println(e.getMessage());
			} catch (CAException e) {
				System.out.println(e.getMessage());
			}
		} while (option != 3);
	}

	private static void traitementEcole(CA communaute, int choix, String ville)
					throws ScanException, CAException {
		do {
			if (choix == 1) {
				communaute.addEcole(ville);
			} else {
				communaute.retireEcole(ville);
			}
			if (Scan.estVide()) {
				ville = null;
			} else {
				ville = Scan.motSuivant();
			}
		} while (ville != null);
	}

	public static void ecole(CA commune) {
		int option = 0;
		do {
			try {
				System.out.println("Voulez vous :\n 1 : ajouter une ecole\n"+
							" 2 : retirer une ecole\n 3 : fin");
				switch (choixAjSup(Scan.lireMot())) {
					case 1:
						if (Scan.estVide()) {
							System.out.println("Dans quel ville voulez-vous ajouter une ecole ?");
							traitementEcole(commune,1,Scan.lireMot());
						} else {
							traitementEcole(commune,1,Scan.motSuivant());
						}
						break;
					case 2:
						if (Scan.estVide()) {
							System.out.println("Dans quel ville voulez-vous retirer une ecole ?");
							traitementEcole(commune,0,Scan.lireMot());
						} else {
							traitementEcole(commune,0,Scan.motSuivant());
						}
						break;
					case 3:
						option = 3;
						break;
					default : System.out.println("Choix incorrect");
						break;
				}
			} catch (ScanException e) {
				System.out.println(e.getMessage());
			} catch (CAException e) {
				System.out.println(e.getMessage());
			}
		} while (option != 3);
	}

	public static void ville(CA commune) {
		String nomVille;
		try {
			if (Scan.estVide()) {
				nomVille = UtilMethodeCA.nomVilleUtilisateur();
				commune.addVille(new Ville(nomVille,true));
			} else {
				do {
					nomVille = Scan.motSuivant();
					commune.addVille(new Ville(nomVille,true));
				} while (!Scan.estVide());
			}
		} catch (CAException e) {
			System.out.println(e.getMessage());
		} catch (ScanException e) {
			System.out.println(e.getMessage());
		}
		commune.affiche();
	}

	public static void creer(CA commune) {
		int nombreVilles = 0;
		String nomVille;
		do {
			try {
				if (Scan.estVide()) {
					System.out.print("Entrez le nombre de villes de la communaute"+
								" d'agglomeration : ");
					nombreVilles = Scan.lireEntier();
				} else {
					nombreVilles = Scan.entierSuivant();
				}
			} catch (ScanException e) {
				System.out.println(e.getMessage());
			}
		} while (nombreVilles < 1);

		for(int i=1; i<nombreVilles + 1; i++) {
			nomVille = UtilMethodeCA.nomAutomatique(i);
			try {
				commune.addVille(new Ville(nomVille,true));
			} catch (CAException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Vous avez choisi " + nombreVilles + " Villes.");
		commune.affiche();//Affiche la liste de villes
	}

	public static CA charger() {
		int choix =0;
		CA commune = null;
		do {
			try {
				commune = LectureCA.chargement();
				choix = 1;
			} catch (LectureException e) {
				if (e.getLigne() != -1) {
					System.out.println("Erreur ligne "+e.getLigne()+" : "+e.getMessage());
				} else {
					System.out.println(e.getMessage());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (choix == 0);
		return commune;
	}

	public static int choixResoudre() {
		int option = 0;
		do {
			try {
				System.out.println("Choix de l'algorithme de resolution :\n 1: Naif\n 2: Algo "+
						"approcher plus efficace sans garder les ecole\n"+
						" 3: Algo approcher plus efficace en gardant les ecole");
				switch(Scan.lireMot()) {
					case "1":
					case "naif":
						option = 1;
						break;
					case "2":
					case "sans":
						option = 2;
						break;
					case "3":
					case "avec":
						option = 3;
						break;
					default : System.out.println("Choix incorrect");
						break;
				}
			} catch (ScanException e) {
				System.out.println(e.getMessage());
			}
		} while (option == 0);
		return option;
	}

	public static void resoudre(CA commune) {
		switch(choixResoudre()) {
			case 1:
				/* Ya des truc byzarre. */
				break;
			case 2:
				commune.algoQueue(0);
				break;
			default:
				commune.algoQueue(1);
				break;
		}
	}
}
