package debug;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import principal.Ville;

class VilleTest {

	@Test
	void testNomVille() {
		Ville test = new Ville("Paris");
		assertEquals("Paris", test.getNomVille());
	}
	
	@Test
	void testEcoleVille() {
		Ville test = new Ville("Paris", true);
		assertTrue(test.getEcole());
	}
	
	@Test
	void testCompareToVille() {
		Ville ville1 = new Ville("Paris");
		Ville ville2 = new Ville("Paris");
		assertNotEquals(1, ville2.getNomVille().compareTo(ville1.getNomVille()));
	}
	
	@Test
	void testEquals() {
		Ville ville1 = new Ville("Paris");
		Ville ville2 = new Ville("Paris");
		Ville ville3 = new Ville("P");
		assertTrue(ville1.equals(ville2));
		assertFalse(ville3.equals(ville1));
	}

}
