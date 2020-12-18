package debug;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gestion.AnalyseChaine;

class AnalyseChaineTest extends AnalyseChaine {
	
	public AnalyseChaineTest() {
		super("-()");
	}
	
	@Test
	void testHasNext() {
		AnalyseChaineTest test = new AnalyseChaineTest();
		test.updateTab("Je-commence()a(avoir)--faim");
		test.motSuivant();
		test.motSuivant();
		test.motSuivant();
		test.motSuivant();
		test.motSuivant();
		assertFalse(test.hasNext());
	}
	
	@Test
	void testHasNext2() {
		AnalyseChaineTest test = new AnalyseChaineTest();
		test.updateTab("Je-commence()a(avoir)--faim");
		test.motSuivant();
		test.motSuivant();
		assertTrue(test.hasNext());
	}
	
	@Test
	void testHasNext3() {
		AnalyseChaineTest test = new AnalyseChaineTest();
		test.updateTab("");
		assertFalse(test.hasNext());
	}
	
	@Test
	void testMotPrecedent() {
		AnalyseChaineTest test = new AnalyseChaineTest();
		test.updateTab("Je-commence()a(avoir)--faim");
		test.motSuivant();
		test.motSuivant();
		assertEquals("commence", test.motPrecedent());
	}
	
	@Test
	void testMotPrecedent2() {
		AnalyseChaineTest test = new AnalyseChaineTest();
		test.updateTab("Je-commence()a(avoir)--faim");
		assertEquals(null, test.motPrecedent());
	}

}
