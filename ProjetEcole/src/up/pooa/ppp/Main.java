package up.pooa.ppp;

public class Main {
	public static void main(String[] args) {
		//Initialisation des variables
		int nombreVilles = 0;
		boolean arretMenu1 = false;
		boolean arretMenu2 = false;
		char codeAscii = (int) 'A';
		String nomVille = null;
		String x = null;//Ville de depart
		String y = null;//Ville d'arrivee

		//Scanner permettant a l'utilisateur de choisir le nombre de villes.
		do {
			System.out.print("Entrez le nombre de villes de la communaute d'agglomeration "+
				"(entre 1 et 26).");
			try {
				nombreVilles = Scan.lireEntier();
				if (nombreVilles<1 || nombreVilles>26) {
					System.out.println("Nombre invalide.");
				}
			} catch (ScanException e) {
				System.out.println(e.getMessage());
			}
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
		commune.affiche();//Affiche la liste de villes

		//Affiche le menu1 et effectue une action en fonction du choix de l'option de l'utilisateur
		do {
			switch(Scan.questionReponse("Selectionner une option :\n\t1) Ajouter une route (ajouter)\n\t2) Fin (fin)\n\t"
					+ "3) Afficher les routes d'une ville (afficher)\n\t4) Supprimer une route (supprimer)\n\t5) Aide (aide)\n",
				"ajouter","fin","afficher","supprimer","aide","h","a","f","af","s","1","2","3","4","5")) {
			case "ajouter" :
			case "a" :
			case "1" :
				System.out.println("Votre ville depart ?");
				try {
				x = (Scan.lireMot());//L'utilisateur entre le nom de la ville de depart.
				} catch (ScanException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("Votre ville d'arrivee ?");
				try {
				y = (Scan.lireMot());//L'utilisateur entre le nom de la ville d'arrivee.
				} catch (ScanException e) {
					System.out.println(e.getMessage());
				}
				if(commune.verifieVilleExiste(x, y)){//Si la route n'existe pas on l'a cree
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
				System.out.println("La ville dont vous voulez connaitre les routes ?");
				try {
					x = (Scan.lireMot());
				} catch (ScanException e) {
					System.out.println(e.getMessage());
				}
				
				commune.afficheRoute(x);
			break;
			case "supprimer" :
			case "s" :
			case "4" :
				System.out.println("Selectionner les deux villes liees a la route que vous voulez supprimer :");
				System.out.println("Votre ville depart ?");
				try {
				x = (Scan.lireMot());//L'utilisateur entre le nom de la ville de depart.
				} catch (ScanException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("Votre ville d'arrivee ?");
				try {
				y = (Scan.lireMot());//L'utilisateur entre le nom de la ville d'arrivee.
				} catch (ScanException e) {
					System.out.println(e.getMessage());
				}				if(commune.verifieVilleExiste(x, y)) {
				commune.supprimerRoute(x,y);//Appel la methode pour supprimer une route de la ville de depart a la ville d'arrivee et dans l'autre sens
				}
			break;
			case "aide" :
			case "h" :
			case "5" :
				System.out.println("Vous pouvez selectioner une option en tapant le numero de l'option, "
						+ "son nom entre parenthèse ou son initial ('af' pour afficher, 'h' pour help).\n"
					+ "Il est possible d'ajouter ou retirer plusieurs routes d'un coup. Exemple :\n"
					+ "Pour ajouter 2 routes, entre A - B et C - D, il suffit au moment de la selection de l'option d'ecrire 'ajouter A|B C|D'");
			}
		} while(arretMenu1 == false);

		//Affiche le menu2 et effectue une action en fonction du choix de l'option de
		//l'utilisateur.
		do {
			switch(Scan.questionReponse("Selectionner une options :\n\t1) Ajouter une ecole"+
				" (ajouter)\n\t2) Retirer une ecole (retirer)\n\t3) Fin (fin)\n\t4) Aide (aide)\n", 
				"ajouter","a","1","retirer","r","2","fin","f","3","aide","h","4")) {
			case "ajouter" : //Permet d'ajouter une ecole dans une ville si il n'y en a pas deja une.
			case "a" :
			case "1" :
				do {
					System.out.println("Dans quel ville voulez-vous ajouter une ecole ?");
					try {
						/* Entrer de la ville dans laquelle on veut une ecole. */
						nomVille = (Scan.lireMot());
						} catch (ScanException e) {
							System.out.println(e.getMessage());
						}
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
				System.out.println("Dans quel ville voulez-vous retirer une ecole ?");
				try {
					if (Scan.estVide()) {
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
				arretMenu2 = true;
			break;
			case "aide" :
			case "h" :
			case "4" :
				System.out.println("Vous pouvez selectioner une option en tapant le numero de l'option, "
						+ "son nom entre parenthèse ou son initial('h' pour help).\n"
					+ "Il est possible d'ajouter ou retirer plusieurs ecoles d'un coup. Exemple :\n"
					+ "Pour ajouter 2 ecoles, dans les villes A et B par exemple, "
					+ "il suffit au moment de la selection de l'option d'ecrire 'ajouter A B'");
			break;
			}
		}
		while(arretMenu2 == false);
		Scan.fermer();
	}

}
