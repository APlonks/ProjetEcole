import java.util.Scanner;

public class Villes {
	
	public static int menu1() {
		int option1 = 0;
		Scanner scan= new Scanner(System.in);
		System.out.println("Sélectionner une option :");
		System.out.println("1) Ajouter une route;\n");
		System.out.println("2) Fin.\n");
		option1 = scan.nextInt();
		return option1;
	}
	
	public static int menu2() {
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
		int nombreVilles = 0;
		boolean arretMenu1 = false;
		boolean arretMenu2 = false;
		Scanner scan= new Scanner(System.in);
		do {
			System.out.println("Entrez le nombre de villes de la communauté d'agglomération\n");
			nombreVilles = scan.nextInt();	
		}
		while(nombreVilles < 0 || nombreVilles > 26);
		System.out.println("Vous avez choisi " + nombreVilles + " Villes\n");
		
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
				System.out.println("Vous avez ajouté une école\n");
			break;
			
			case 2 : 
				System.out.println("Vous avez retiré une école\n");
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
