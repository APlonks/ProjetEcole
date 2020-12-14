package gestion;

import principal.CA;
import principal.CAException;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Chargement d'une CA a partie d'un fichier.
 */
public class LectureCA {
	/**
	 * @param lecture BufferedReader qui permet de lire le fichier.
	 * @param ligneLu AnalyseChaine qui vas permettre d'analyse les lignes lues.
	 * @param numLgn Numero de la ligne lu.
	 */
	private static BufferedReader lecture = null;
	private static AnalyseChaine ligneLu = new AnalyseChaine("()");
	private static int numLgn = 0;

	/**
	 * Seul fonction accessible pour la lecture d'un fichier.
	 * Demande a l'utilisateur d'entrer le chemin de ce dernier.
	 * S'il n'existe pas retourne une ereur.
	 * @throws LectureException Exception de lecture.
	 * @return CA lu dans le fichier.
	 */
	public static CA chargement() throws LectureException, ScanException, IOException {
		String nomFichier = "";
		File testExist;
		BufferedReader fichier;
		/* Si le buffer d'entre est non vide, on regarde s'il contient le nom
		 * d'un fichier. */
		if (!Scan.estVide()) {
			nomFichier = Scan.motSuivant();
			/* On verifie l'extension. */
			if (!extensionTXT(nomFichier)) {
				throw new LectureException("Extension de fichier incorect, '.txt' attendu");
			}
			testExist = new File(nomFichier);
			if (testExist.exists()) {
				fichier = ouvertureFichier(testExist);
				return creationCA(fichier);
			}
		}
		/* S'il est vide ou ne contient pas le nom d'un fichier.
		 * On demande le nom du fichier a l'utilisateur. */
		System.out.print("Nom du fichier a charger : ");
		nomFichier = Scan.lireMot();
		/* On verifie l'extension. */
		if (!extensionTXT(nomFichier)) {
			throw new LectureException("Extension de fichier incorect, '.txt' attendu");
		}
		testExist = new File(nomFichier);
		if (testExist.exists()) {
			fichier = ouvertureFichier(testExist);
			return creationCA(fichier);
		} else {
			throw new LectureException("Fichier non trouver");
		}
	}

	/**
	 * Verifie que le fichier donner contient l'extension '.txt'.
	 * @param String nom du fichier.
	 * @return true Si '.txt' false finon.
	 */
	private static boolean extensionTXT(String nomFichier) {
		if (nomFichier.length() < 4) {
			return false;
		} else if (nomFichier.substring(nomFichier.length()-4).compareTo(".txt") != 0) {
			return false;
		} else {
			return true;
		}
	}


	/**
	 * Ouverture du BufferedReader.
	 * @throws Exception Exception du a l'initialisation du BufferedReader.
	 * @param fichier File que l'on veut ouvrir.
	 * @return BufferedReader de fichier.
	 */
	private static BufferedReader ouvertureFichier(File fichier) throws IOException {
		return new BufferedReader(new FileReader(fichier));
	}

	/**
	 * Lecture des lignes du fichier.
	 * @throws CAException Exception de CA.
	 * @param fichier BufferedReader permettant la lecture.
	 * @return La Communaute d'Agglomeration lu.
	 */
	private static CA creationCA(BufferedReader lecture) throws IOException, LectureException {
		String tmp;
		int nivFonction=0;
		CA communaute = new CA();
		if (lecture.ready()) {
			/* On lis l'integralite du document. */
			while ((tmp=lecture.readLine()) != null) {
				numLgn++;
				ligneLu.updateTab(tmp);
				/* On regarde la fonction demander. */
				if (ligneLu.hasNext()) {
					/* On utilise une fonction annexe pour appeler les fonctions
					 * en fonction de ce qui as ete lues. */
					nivFonction = corresondanceTxtFnct(communaute,nivFonction);
				}
			}
		}
		return communaute;
	}

	/**
	 * Retourne un string[] contenant les parametrage d'une fonction.
	 * @return Les arguments de la fonction dans un tableau.
	 */
	public static String[] parametre() {
		if (ligneLu.hasNext()) {
			return UtilAnalyseChaine.separationIgnoreVide(ligneLu.motSuivant(),",");
		} else {
			return new String[0];
		}
	}

	/**
	 * Fait correspondre ce qui est lu au fonction reciproque permettant de creer un CA.
	 * @throws LectureException Retourne les ereurs de parametrage dans le fichier.
	 * @param communaute que l'on lis.
	 * @param fonctionMax Plus haut ordre des fonctions utiliser.
	 * @return Plus haut ordre des fonction mise a jour.
	 */
	private static int corresondanceTxtFnct(CA communaute, int fonctionMax)
		throws LectureException {
		String args[];
		int fonctionChoisie;
		try {
			switch (ligneLu.motSuivant()) {
				/* Correspondance de la fonction.
				 * 
				 * Pour chaque cas, on recupere les arguments de la fonction et on verifie leurs
				 * nombres. */

				case "ville" :
					args = parametre();
					if (args.length != 1) {
						throw new LectureException("Arguments invalide pour la fonction ville, "+
							args.length+" recue, 1 attendue",numLgn);
					} else {
						communaute.addVille(args[0]);
					}
					fonctionChoisie = 0;
					break;

				case "route" :
					args = parametre();
					if (args.length != 2) {
						throw new LectureException("Arguments invalide pour la fonction route, "+
							args.length+" recue, 2 attendue",numLgn);
					} else {
						communaute.addRoute(args[0],args[1]);
					}
					fonctionChoisie = 1;
					break;

				case "ecole" :
					args = parametre();
					if (args.length != 1) {
						throw new LectureException("Arguments invalide pour la fonction ecole, "+
							args.length+" recue, 1 attendue",numLgn);
					} else {
						communaute.addEcole(args[0]);
					}
					fonctionChoisie = 2;
					break;

				default :
					throw new LectureException("Action non reconnue",numLgn);
			}
		} catch (CAException e) {
			throw new LectureException(e.getMessage(),numLgn);
		}
		/* Verifie l'ordre des appeles. */
		warningOrdre(fonctionChoisie,fonctionMax);
		return (fonctionMax>fonctionChoisie)?(fonctionMax):(fonctionChoisie);
	}

	/**
	 * Warning pour verifier que fonction sont bien dans le bonne ordre dans le fichier.
	 * @param nivFonction fonction actuelle.
	 * @param nivMax plus haute fonction appeler.
	 */
	private static void warningOrdre(int nivFonction, int nivMax) {
		if (nivMax > nivFonction) {
			System.out.print("Warning ligne "+numLgn+" : fonction ");
			/* Nom fonction appeler.
			 * Seul les fonction ville et route peuvent ne pas respecter ce cas. */
			switch (nivFonction) {
				case 0 :
					System.out.print("ville");
					break;
				case 1 :
					System.out.print("route");
					break;
				default :
					break;
			}
			System.out.print(" apres l'utilisation de ");
			/* Nom fonction maximal.
			 * Seul les fonction route et ecole peuvent ne pas respecter ce cas. */
			switch (nivMax) {
				case 1 :
					System.out.println("route.");
					break;
				case 2 :
					System.out.println("ecole.");
					break;
				default :
					break;
			}
		}
	}
}