package debug;

import gestion.EnregistrementCA;
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
		EnregistrementCA.enregister(ca);
	}
}
