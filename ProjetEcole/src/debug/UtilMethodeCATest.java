package debug;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import principal.UtilMethodeCA;


public class UtilMethodeCATest {
	@Test
	void testNomAutomatique() {
		assertEquals("E", UtilMethodeCA.nomAutomatique(5));
		assertEquals("AB", UtilMethodeCA.nomAutomatique(28));
		assertEquals("KN", UtilMethodeCA.nomAutomatique(300));
	}
}