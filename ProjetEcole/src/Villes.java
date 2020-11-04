import java.util.HashMap;
import java.util.Scanner;

public class Villes {
// 	/* Menu1 demandant a l'utilisateur quel option il souhaite effectuer et renvoie sa selection
// 	 * via un scan. */
// 	public static int menu1() {
// 		int option1 = 0;
// 		do {
// 			System.out.println("Selectionner une option :");
// 			System.out.println("1) Ajouter une route;\n");
// 			System.out.println("2) Fin.\n");
// 			try {
// 				option1 = Scan.lireEntier();
// 			} catch (ScanException e) {
// 				System.out.println(e.getMessage());
// 			}
// 		} while (option1 == 0);
// 		return option1;
// 	}
	/* Menu2 demandant a l'utilisateur quel option il souhaite effectuer et renvoie sa selection
	 * via un scan. */
/*		public static int menu2() {
		int option2 = 0;
		do {
			System.out.println("Selectionner une option :");
			System.out.println("1) Ajouter une ecole");
			System.out.println("2) Retirer une ecole");
			System.out.println("3) Fin");
			try {
				option2 = Scan.lireEntier();
			} catch (ScanException e) {
				System.out.println(e.getMessage());
			}
		} while (option2 == 0);
		return option2;
	}
*/
	public static void main(String[] args) {
		//Initialisation des variables
		int nombreVilles = 0;
		boolean arretMenu1 = false;
		boolean arretMenu2 = false;
		char codeAscii = (int) 'A';
		String nomVille;
		char nomVilleAscii = (int) 'A';

		//Scanner permettant a l'utilisateur de choisir le nombre de villes.
		Scanner scan= new Scanner(System.in);
		do {
			System.out.println("Entrez le nombre de villes de la communaute d'agglomeration.");
			nombreVilles = scan.nextInt();	
		}
		while(nombreVilles < 1 || nombreVilles > 26);//Le nombre de villes est pour l'instant compris entre 1 et 26
		
		/*
		Creation de la liste de villes en fonction du nombre de villes selectionner par l'utilisateur
		De plus chaque ville sera nommee en fonction d'une lettre de l'alphabet commeneant par 'A'
		*/
		Commune commune = new Commune(nombreVilles);
		for(int i=0; i<nombreVilles; i++) {
			commune.addVille(new Agglomeration(codeAscii,true));
			codeAscii++;
		}

		System.out.println("Vous avez choisi " + nombreVilles + " Villes.");
		commune.affiche();//Affiche la liste de villes, A RETIRER PLUS TARD!!

		//Affiche le menu1 et effectue une action en fonction du choix de l'option de l'utilisateur
		do {
			switch(Scan.questionReponse("Sélectionner une option :\n\t1) Ajouter une route (ajouter)\n\t2) Fin (fin)\n\t3) Afficher les routes d'une ville (afficher)\n\t4) Supprimer une route\n",
				"ajouter","fin","afficher","supprimer","a","f","af","s","1","2","3","4")) {
			case "ajouter" :
			case "a" :
			case "1" :
				String x;//Ville de départ
				String y;//Ville d'arrivée
				
				System.out.println("Votre ville départ ?");
				x = Character.toString(scan.next().charAt(0));//L'utilisateur entre le nom de la ville de départ.
				System.out.println("Votre ville d'arrivée ?");
				y = Character.toString(scan.next().charAt(0));//L'utilisateur entre le nom de la ville d'arrivee.
				if(commune.verifieVilleexiste(x, y)){//Si la route n'existe pas on l'a crée
					commune.addRoute(x,y);//Appel la méthode pour creer une route de la ville de départ vers la ville d'arrivee
				}
			break;
			case "fin" : 
			case "f" :
			case "2" :
				arretMenu1 = true;
			break;
			case "afficher" :
			case "af" :
			case "3" :
				System.out.println("La ville dont vous voulez connaître les routes ?");
				x = Character.toString(scan.next().charAt(0));
				commune.afficheRoute(x);
			break;
			case "supprimer" :
			case "s" :
			case "4" :
				System.out.println("Sélectionner les deux villes liées à la route que vous voulez supprimer :");
				System.out.println("Votre ville départ ?");
				x = Character.toString(scan.next().charAt(0));//L'utilisateur entre le nom de la ville de départ.
				System.out.println("Votre ville d'arrivée ?");
				y = Character.toString(scan.next().charAt(0));//L'utilisateur entre le nom de la ville d'arrivee.
				if(commune.verifieVilleexiste(x, y)) {
				commune.supprimerRoute(x,y);//Appel la méthode pour supprimer une route de la ville de départ à la ville d'arrivee et dans l'autre sens
				}
			break;
			}
		} while(arretMenu1 == false);

		//Affiche le menu2 et effectue une action en fonction du choix de l'option de
		//l'utilisateur.
		do {
			switch(Scan.questionReponse("Sélectionner une options :\n\t1) Ajouter une école (ajouter)\n\t2) Retirer une école (retirer)\n\t3) Fin (fin)\n", 
					"ajouter","a","1","retirer","r","2","fin","f","3")) {
			case "ajouter" : //Permet d'ajouter une ecole dans une ville si il n'y en a pas deja une.
			case "a" :
			case "1" :
				do {
					System.out.println("Dans quel ville voulez-vous ajouter une ecole ?");
					nomVille = Character.toString(scan.next().charAt(0));
					if (commune.getVille(nomVille) == null) {
						System.out.println("Cette ville n'existe pas !");
					}
				} while (commune.getVille(nomVille) == null);
				
				if(commune.getVille(nomVille).getEcole() == true) {
					System.out.println("Cette ville possede deja une ecole !");
				}
				else {
					commune.getVille(nomVille).addEcole();
				}
				commune.afficheEcole();
			break;
			
			case "retirer" : //Permet de retirer une ecole d'une ville.
			case "r" :
			case "2" :
				do {
					System.out.println("Dans quel ville voulez-vous retirer une ecole ?");
					nomVille = Character.toString(scan.next().charAt(0));
					if (commune.getVille(nomVille) == null) {
						System.out.println("Cette ville n'existe pas !");
					}
				} while (commune.getVille(nomVille) == null);
				
			
				if(commune.getVille(nomVille).getEcole() == false) {
					System.out.println("Cette ville ne possede aucune ecole !");
				}
				else {
					commune.getVille(nomVille).retireEcole();
				}
				commune.afficheEcole();
			break;
			
			case "fin" :
			case "f" :
			case "3" :	
				arretMenu2 = true;
			break;
			
			default :
				System.out.println("Veuillez choisir l'option 1, 2 ou 3.");
			break;
			}
		}
		while(arretMenu2 == false);
		
		Scan.fermer();
	}

}
