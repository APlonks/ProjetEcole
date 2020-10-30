import java.util.HashMap;
import java.util.Scanner;

public class Villes {
	
	public static int menu1() { //Menu1 demandant a l'utilisateur quel option il souhaite effectuer et renvoie sa selection via un scan
		int option1 = 0;
		Scanner scan= new Scanner(System.in);
		System.out.println("Sélectionner une option :");
		System.out.println("1) Ajouter une route;\n");
		System.out.println("2) Fin.\n");
		option1 = scan.nextInt();
		return option1;
	}
	
	public static int menu2() { //Menu2 demandant a l'utilisateur quel option il souhaite effectuer et renvoie sa selection via un scan
		int option2 = 0;
		Scanner scan= new Scanner(System.in);
		System.out.println("\n Sélectionner une option :\n");
		System.out.println("1) Ajouter une école;\n");
		System.out.println("2) Retirer une école;\n");
		System.out.println("3) Fin.\n");
		option2 = scan.nextInt();
		return option2;
	}

	public static void main(String[] args) {
		//Initialisation des variables
		int nombreVilles = 0;
		boolean arretMenu1 = false;
		boolean arretMenu2 = false;
		char codeAscii = (int) 'A';
		char nomVille;
		char nomVilleAscii = (int) 'A';
		
		//Scanner permettant a l'utilisateur de choisir le nombre de villes.
		Scanner scan= new Scanner(System.in);
		do {
			System.out.println("Entrez le nombre de villes de la communauté d'agglomération\n");
			nombreVilles = scan.nextInt();	
		}
		while(nombreVilles < 1 || nombreVilles > 26);//Le nombre de villes est pour l'instant compris entre 1 et 26
		
		/*
		Creation de la liste de villes en fonction du nombre de villes sélectionner par l'utilisateur
		De plus chaque ville sera nommée en fonction d'une lettre de l'alphabet commençant par 'A'
		*/
		Commune commune = new Commune(nombreVilles);
		for(int i=0; i<nombreVilles; i++) {
			commune.addVille(new Agglomeration(codeAscii));
			codeAscii++;
		}
		
		//Ajoute par défaut une école dans chacune des villes.
		for (int i=0; i<nombreVilles; i++) {
			nomVille = nomVilleAscii;
			commune.getVille(nomVille).addEcole();
			nomVilleAscii++;
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
			switch(menu1()) {
			case 1 :
				System.out.println("Vous avez ajouter une route\n");
			break;
			
			case 2 : 
				arretMenu1 = true;
			break;
			
			default :
				System.out.println("Veuillez choisir l'option 1 ou 2\n");
			break;
			}
		}
		while(arretMenu1 == false);	
		
		//Affiche le menu2 et effectue une action en fonction du choix de l'option de l'utilisateur.
		do {
			switch(menu2()) {
			case 1 : //Permet d'ajouter une école dans une ville si il n'y en a pas deja une.
				System.out.println("Dans quel ville voulez-vous ajouter une école?\n");
				nomVille = scan.next().charAt(0);
				if(commune.getVille(nomVille).getEcole() == true) {
					System.out.println("Cette ville possède déja une école !\n");
				}
				else {
					commune.getVille(nomVille).addEcole();
				}
			break;
			
			case 2 : //Permet de retirer une école d'une ville.
				System.out.println("Dans quel ville voulez-vous retirer une école?\n");
				nomVille = scan.next().charAt(0);
				if(commune.getVille(nomVille).getEcole() == false) {
					System.out.println("Cette ville ne possède aucune école !\n");
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
		scan.close();
	}

}
