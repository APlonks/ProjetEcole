package debug;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import gestion.UtilAnalyseChaine;

class UtilAnalyseChaineTest extends UtilAnalyseChaine {

	@ParameterizedTest
	@ValueSource(strings = {"peut-etre", "peut--etre", "peut-----etre", "peut--()--etre", "-peut-etre"})
	void testSeparationIgnoreVide(String word) {
		String[] tab = {"peut", "etre"};
		System.out.println(tab.length);
		assertArrayEquals(tab,UtilAnalyseChaine.separationIgnoreVide(word, "-()"));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {""})
	void testSeparationIgnoreVide2(String word) {
		String[] tab = {};
		System.out.println(tab.length);
		assertArrayEquals(tab,UtilAnalyseChaine.separationIgnoreVide(word, "-()"));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"21","--21"})
	void testStringEnInt(String word) {
		int test = 21;
		assertEquals(test,UtilAnalyseChaine.stringEnInt(word));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"bernard","","5-3","##"})
	void testStringEnInt2(String word) {
		String test = null;
		assertEquals(test,UtilAnalyseChaine.stringEnInt(word));
	}

}
