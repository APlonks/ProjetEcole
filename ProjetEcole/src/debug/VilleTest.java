package debug;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import principal.Ville;

@TestInstance(Lifecycle.PER_CLASS)
class VilleTest {
	private Ville ville1,ville2,ville3;
	
	@BeforeAll
	void beforeAll() {
		ville1 = new Ville("Paris", true);
		ville2 = new Ville("Paris", false);
		ville3 = new Ville("P", true);
	}

	@Test
	void testNomVille() {
		assertEquals("Paris", ville1.getNomVille());
	}
	
	@Test
	void testEcoleVille() {
		assertTrue(ville1.getEcole());
	}
	
	@Test
	void testCompareToVille() {
		assertNotEquals(1, ville2.getNomVille().compareTo(ville1.getNomVille()));
	}
	
	@Test
	void testEquals() {
		assertTrue(ville1.equals(ville2));
		assertFalse(ville3.equals(ville1));
	}

}
