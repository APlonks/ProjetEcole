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
		public static int menu2() {
		int option2 = 0;
		do {
			System.out.println("\n Selectionner une option :\n");
			System.out.println("1) Ajouter une ecole;\n");
			System.out.println("2) Retirer une ecole;\n");
			System.out.println("3) Fin.\n");
			try {
				option2 = Scan.lireEntier();
			} catch (ScanException e) {
				System.out.println(e.getMessage());
			}
		} while (option2 == 0);
		return option2;
	}

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
			System.out.println("Entrez le nombre de villes de la communaute d'agglomeration\n");
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

		System.out.println("Vous avez choisi " + nombreVilles + " Villes\n");
		
		Commune.affiche();//Affiche la liste de villes, A RETIRER PLUS TARD!!

		/*
		HashMap<String, Character> commune = new HashMap<>();
		for (int i=0; i<nombreVilles; i++) {
			commune.put("Ville", codeAscii);
			codeAscii++;
		}
		System.out.println("Vous avez choisi " + commune.size() + " Villes\n");
		for (String i : commune.keySet()) {
			System.out.println(i + " " + commune.get(i));
		}
		*/
		
		//Affiche le menu1 et effectue une action en fonction du choix de l'option de l'utilisateur
		do {
			switch(Scan.questionReponse("Option :\n\tAjouter une route (ajouter)\n\tFin (fin)\n",
				"ajouter","fin")) {
			case "ajouter" :
				System.out.println("Vous avez ajouter une route\n");
			break;
			case "fin" : 
				arretMenu1 = true;
			}
		} while(arretMenu1 == false);

		//Affiche le menu2 et effectue une action en fonction du choix de l'option de
		//l'utilisateur.
		do {
			switch(menu2()) {
			case 1 : //Permet d'ajouter une ecole dans une ville si il n'y en a pas deja une.
				System.out.println("Dans quel ville voulez-vous ajouter une ecole?\n");
				nomVille = Character.toString(scan.next().charAt(0));
				if(commune.getVille(nomVille).getEcole() == true) {
					System.out.println("Cette ville possede deja une ecole !\n");
				}
				else {
					commune.getVille(nomVille).addEcole();
				}
			break;
			
			case 2 : //Permet de retirer une ecole d'une ville.
				System.out.println("Dans quel ville voulez-vous retirer une ecole?\n");
				nomVille = Character.toString(scan.next().charAt(0));
				if(commune.getVille(nomVille).getEcole() == false) {
					System.out.println("Cette ville ne possede aucune ecole !\n");
				}
				else {
					commune.getVille(nomVille).retireEcole();
				}
			break;
			
			case 3 :
				arretMenu2 = true;
			break;
			
			default :
				System.out.println("Veuillez choisir l'option 1, 2 ou 3\n");
			break;
			}
		}
		while(arretMenu2 == false);
		
		Scan.fermer();
	}

}
