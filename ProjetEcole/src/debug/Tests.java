package debug;

import gestion.EnregistrementCA;
import gestion.LectureCA;
import gestion.LectureException;
import principal.CA;

import java.util.ArrayList;
import java.lang.CharSequence;

public class Tests {
	public static void main (String[] args) {
		CA ca = new CA();
		ca.addVille("A");
		ca.addVille("B");
		ca.addVille("C");
		ca.addRoute("A","B");
		ca.addRoute("B","C");
		ca.addEcole("B");
// 		EnregistrementCA.enregister(ca);
		try {
			ca = LectureCA.chargement();
			ca.afficherHashMap();
			ca.afficheEcole();
		} catch (LectureException e) {
			if (e.getLigne() != -1) {
				System.out.println("Ereur ligne "+e.getLigne()+" : "+e.getMessage());
			} else {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
