import java.util.Scanner;

public class Villes {

	public static void main(String[] args) {
		int nombreVilles = 0;
		Scanner scan= new Scanner(System.in);
		do {
			System.out.println("Entrez le nombre de villes de la communauté d'agglomération\n");
			nombreVilles = scan.nextInt();	
		}
		while(nombreVilles < 0 || nombreVilles > 26);
		System.out.println("Vous avez choisi " + nombreVilles + " Villes\n");
		
		int option = 0;
		System.out.println("Sélectionner une option :");
		System.out.println("1) ajouter une route;\n");
		System.out.println("2) fin.\n");
		
	}

}
