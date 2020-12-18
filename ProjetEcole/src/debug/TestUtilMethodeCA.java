package debug;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import principal.CA;
import principal.Ville;
import principal.CAException;
import principal.UtilMethodeCA;
import java.util.HashMap;

public class TestUtilMethodeCA {
	@Test
	void testNomAutomatique() {
		assertEquals("E", UtilMethodeCA.nomAutomatique(5));
		assertEquals("AB", UtilMethodeCA.nomAutomatique(28));
		assertEquals("KN", UtilMethodeCA.nomAutomatique(300));
	}
	
}
