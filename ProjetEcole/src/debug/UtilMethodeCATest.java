package debug;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import principal.CA;
import principal.CAException;
import principal.UtilMethodeCA;


public class UtilMethodeCATest {
	private CA commune;
	@Test
	void testNomAutomatique() {
		assertEquals("E", UtilMethodeCA.nomAutomatique(5));
		assertEquals("AB", UtilMethodeCA.nomAutomatique(28));
		assertEquals("KN", UtilMethodeCA.nomAutomatique(300));
	}
	
	@Test
	void testScore() {
		try {
			commune = new CA();
			commune.addVille("A");
        	commune.addVille("B");
        	commune.addVille("C");
        	commune.addEcole("A");
        	commune.addEcole("B");
		} catch (CAException e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals(2, UtilMethodeCA.score(commune));
		assertNotEquals(3, UtilMethodeCA.score(commune));
	}
}