import java.util.HashMap;
import java.util.Scanner;

public class Villes {
	
	public static int menu1() {
		int option1 = 0;
		Scanner scan= new Scanner(System.in);
		System.out.println("S�lectionner une option :");
		System.out.println("1) Ajouter une route;\n");
		System.out.println("2) Fin.\n");
		option1 = scan.nextInt();
		return option1;
	}
	
	public static int menu2() {
		int option2 = 0;
		Scanner scan= new Scanner(System.in);
		System.out.println("\n S�lectionner une option :\n");
		System.out.println("1) Ajouter une �cole;\n");
		System.out.println("2) Retirer une �cole;\n");
		System.out.println("3) Fin.\n");
		option2 = scan.nextInt();
		return option2;
	}

	public static void main(String[] args) {
		int nombreVilles = 0;
		boolean arretMenu1 = false;
		boolean arretMenu2 = false;
		char codeAscii = (int) 'A';
		char nomVille;
		
		Scanner scan= new Scanner(System.in);
		do {
			System.out.println("Entrez le nombre de villes de la communaut� d'agglom�ration\n");
			nombreVilles = scan.nextInt();	
		}
		while(nombreVilles < 0 || nombreVilles > 26);
		
		Commune commune = new Commune(nombreVilles);
		for(int i=0; i<nombreVilles; i++) {
			commune.addVille(new Agglomeration(codeAscii));
			codeAscii++;
		}
		
		System.out.println("Vous avez choisi " + nombreVilles + " Villes\n");
		
		Commune.affiche();

            
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
		
		do {
			switch(menu2()) {
			case 1 :
				System.out.println("Dans quel ville voulez-vous ajouter une �cole?\n");
				nomVille = scan.next().charAt(0);
				if(commune.getVille(nomVille).getEcole() == true) {
					System.out.println("Cette ville poss�de d�ja une �cole !\n");
				}
				else {
					commune.getVille(nomVille).addEcole();
				}
			break;
			
			case 2 : 
				System.out.println("Dans quel ville voulez-vous retirer une �cole?\n");
				nomVille = scan.next().charAt(0);
				if(commune.getVille(nomVille).getEcole() == false) {
					System.out.println("Cette ville ne poss�de aucune �cole !\n");
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
