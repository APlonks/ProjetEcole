import java.util.HashMap;
import java.util.Scanner;

public class Villes {
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
			switch(Scan.questionReponse("Selectionner une option :\n\t1) Ajouter une route (ajouter)\n\t2) Fin (fin)\n\t3) Afficher les routes d'une ville (afficher)\n\t4) Supprimer une route\n",
				"ajouter","fin","afficher","supprimer","a","f","af","s","1","2","3","4")) {
			case "ajouter" :
			case "a" :
			case "1" :
				String x;//Ville de depart
				String y;//Ville d'arrivee
				
				System.out.println("Votre ville depart ?");
				x = Character.toString(scan.next().charAt(0));//L'utilisateur entre le nom de la ville de depart.
				System.out.println("Votre ville d'arrivee ?");
				y = Character.toString(scan.next().charAt(0));//L'utilisateur entre le nom de la ville d'arrivee.
				if(commune.verifieVilleexiste(x, y)){//Si la route n'existe pas on l'a cree
					commune.addRoute(x,y);//Appel la methode pour creer une route de la ville de depart vers la ville d'arrivee
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
				System.out.println("La ville dont vous voulez connaetre les routes ?");
				x = Character.toString(scan.next().charAt(0));
				commune.afficheRoute(x);
			break;
			case "supprimer" :
			case "s" :
			case "4" :
				System.out.println("Selectionner les deux villes liees a la route que vous voulez supprimer :");
				System.out.println("Votre ville depart ?");
				x = Character.toString(scan.next().charAt(0));//L'utilisateur entre le nom de la ville de depart.
				System.out.println("Votre ville d'arrivee ?");
				y = Character.toString(scan.next().charAt(0));//L'utilisateur entre le nom de la ville d'arrivee.
				if(commune.verifieVilleexiste(x, y)) {
				commune.supprimerRoute(x,y);//Appel la methode pour supprimer une route de la ville de depart a la ville d'arrivee et dans l'autre sens
				}
			break;
			}
		} while(arretMenu1 == false);

		//Affiche le menu2 et effectue une action en fonction du choix de l'option de
		//l'utilisateur.
		do {
			switch(Scan.questionReponse("Selectionner une options :\n\t1) Ajouter une ecole"+
				"(ajouter)\n\t2) Retirer une ecole (retirer)\n\t3) Fin (fin)\n", 
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
				try {
					commune.retireEcole(Scan.lireMot());
				} catch (ScanException e) {
					System.out.println(e.getMessage());
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
