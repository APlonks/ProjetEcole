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
		try {
			ca.addVille("A");
			ca.addVille("B");
			ca.addVille("C");
			ca.addRoute("A","B");
			ca.addRoute("B","C");
			ca.addEcole("B");
			EnregistrementCA.enregister(ca);
			ca = LectureCA.chargement();
		} catch (LectureException e) {
			if (e.getLigne() != -1) {
				System.out.println("Ereur ligne "+e.getLigne()+" : "+e.getMessage());
			} else {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ca.algoQueue(1);
		System.out.println("Supp tout");
		ca.algoQueue(0);
		System.out.println("Verifie");
		ca.algoQueue(1);
	}
}
