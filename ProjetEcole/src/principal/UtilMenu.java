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
		int nombreVilles = 0;
		String nomVille;
		CA commune = new CA();
		do {
			/* Tant que l'utilisateur n'as pas fait de choix on lui demande. */
			try {
				System.out.println("Vous voulez créer ou charger un fichier ?");
				choixUtilisateur = Scan.lireMot();
				switch(choixUtilisateur){
				case"creer":
						do {
							System.out.print("Entrez le nombre de villes de la communaute d'agglomeration");
							try {
								nombreVilles = Scan.lireEntier();
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
						menu2(commune);
						choix = 1;
					break;
				case "charger":
					do {
						choix = 1;
						try {
						commune = LectureCA.chargement();
						} catch (LectureException e) {
							if (e.getLigne() != -1) {
								System.out.println("Erreur ligne "+e.getLigne()+" : "+e.getMessage());
								choix = 0;
							} else {
								System.out.println(e.getMessage());
							   	
							}
						} catch (Exception e) {
								System.out.println(e.getMessage());
						}
					} while (choix == 0);
					menu3(commune);
					break;
				default : 
					System.out.println("Entrez créer ou charger.");
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
			System.out.println(" 1: Route\n 2: Ecole\n 3: Ville\n 4: Menu Suivant\n");
			choix = Scan.lireEntier();
		} catch (ScanException e) {
			System.out.println(e.getMessage());
		}
		return choix;
	}
	
	public static int choixMenu3() {
		int choix = 0;
		try {
			System.out.println(" 1: Modifier communaute d'agglomeration\n 2: Résoudre\n 3: Sauvegarder\n 4: Fin\n");
			choix = Scan.lireEntier();
		} catch (ScanException e) {
			System.out.println(e.getMessage());
		}
		return choix;
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
					menu3(commune);
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
			case 2: //Résoudre(commune);
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
	
	public static void route(CA commune) {
		String x;
		String y;
		int option = 0;
		boolean arretMenu = false;
		do {
			try {
				System.out.println("Voulez vous :\n 1 : ajouter une route\n 2 : retirer une route\n 3 : fin");
				option = Scan.lireEntier();
				switch (option) {
					case 1:
							System.out.println("Votre ville depart ?");
							x = (Scan.lireMot());//L'utilisateur entre le nom de la ville de depart.
							System.out.println("Votre ville d'arrivee ?");
							y = (Scan.lireMot());//L'utilisateur entre le nom de la ville d'arrivee.
							commune.addRoute(x,y);
							break;
					case 2: 
							System.out.println("Votre ville depart ?");
							x = (Scan.lireMot());//L'utilisateur entre le nom de la ville de depart.
							System.out.println("Votre ville d'arrivee ?");
							y = (Scan.lireMot());//L'utilisateur entre le nom de la ville d'arrivee.
							commune.supprimerRoute(x,y);
							break;
					case 3:
						if (commune.estConnexe()) {
							arretMenu = true;	
						} else System.out.println("La commune n'est pas connexe");
						break;	
				}
			} catch (ScanException e) {
				System.out.println(e.getMessage());
			} catch (CAException e) {
				System.out.println(e.getMessage());
			}
			
		} while (arretMenu == false);
			
	}
	
	public static void ecole(CA commune) {
		int option;
		boolean arretMenu = false;
		do {
			try {
				System.out.println("Voulez vous :\n 1 : ajouter une ecole\n 2 : retirer une ecole\n 3 : fin");
				option = Scan.lireEntier();
				switch (option) {
					case 1:
							System.out.println("Dans quel ville voulez-vous ajouter une ecole ?");
							commune.addEcole(Scan.lireMot());
							break;
					case 2: 
							System.out.println("Dans quel ville voulez-vous retirer une ecole ?");
							commune.retireEcole(Scan.lireMot());
							break;
					case 3:
							arretMenu = true;
							break;	
				}
			} catch (ScanException e) {
				System.out.println(e.getMessage());
			} catch (CAException e) {
				System.out.println(e.getMessage());
			}	
		} while (arretMenu == false);
			
	}
	
	public static void ville(CA commune) {
		String nomVille;
		try {
			nomVille = UtilMethodeCA.nomVilleUtilisateur();
			commune.addVille(new Ville(nomVille,true));
		} catch (CAException e) {
			System.out.println(e.getMessage());
		}
		commune.affiche();
	}
	
}
