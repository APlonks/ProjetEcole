package gestion;

import principal.CA;

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
	public static CA chargement() throws LectureException, ScanException, Exception {
		String nomFichier = "";
		File testExist;
		BufferedReader fichier;
		/* Si le buffer d'entre est non vide, on regarde s'il contient le nom
		 * d'un fichier. */
		if (!Scan.estVide()) {
			nomFichier = Scan.motSuivant();
			testExist = new File(nomFichier);
			if (testExist.exists()) {
				System.out.println("Le fichier exist next.");
				fichier = ouvertureFichier(testExist);
				return creationCA(fichier);
			}
		}
		/* S'il est vide ou ne contient pas le nom d'un fichier.
		 * On demande le nom du fichier a l'utilisateur. */
		System.out.print("Nom du fichier a charger : ");
		nomFichier = Scan.lireMot();
		testExist = new File(nomFichier);
		if (testExist.exists()) {
			System.out.println("Le fichier exist next.");
			fichier = ouvertureFichier(testExist);
			return creationCA(fichier);
		} else {
			throw new LectureException("Fichier non trouver");
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
		if (lecture.ready()) {
			/* On lis l'integralite du document. */
			while ((tmp=lecture.readLine()) != null) {
				numLgn++;
				ligneLu.updateTab(tmp);
				/* On regarde la fonction demander. */
				if (ligneLu.hasNext()) {
					switch (ligneLu.motSuivant()) {
						case "ville" :
							System.out.println("Ville 1 arg attendu");
							break;
						case "route" :
							System.out.println("Route 2 arg attendu");
							break;
						case "ecole" :
							System.out.println("Ecole 1 arg attendu");
							break;
						default :
							throw new LectureException("Action non reconnue",numLgn);
					}
				}
			}
		}
		return new CA();
	}
}