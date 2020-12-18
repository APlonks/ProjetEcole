package debug;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import gestion.LectureException;
import principal.CA;
import principal.CAException;

@TestInstance(Lifecycle.PER_CLASS)
class CATest {
	private CA comm;

	@BeforeAll
    void beforeAll() {
        try {
            comm = new CA();
            comm.addVille("A");
            comm.addVille("B");
            comm.addVille("C");
            comm.addRoute("A","B");
            comm.addRoute("B","C");
            comm.addEcole("B");
        } catch (CAException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testExcepVille() {
        assertThrows(CAException.class, () -> {
        	comm.addVille("A");
        });
    }

    @Test
    void testExcepRoute() {
		assertThrows(CAException.class, () -> {
			comm.addRoute("A","B");
		});
	}

	@Test
	void testExcepRoute2() {
		assertThrows(CAException.class, () -> {
			comm.addRoute("A","A");
		});
	}

	@Test
	void testExcepRoute3() {
		assertThrows(CAException.class, () -> {
			comm.supprimerRoute("D","A");
		});
	}

	@Test
	void testExcepRoute4() {
		assertThrows(CAException.class, () -> {
			comm.supprimerRoute("C","A");
		});
	}

	@Test
	void testExcepAffiche() {
		assertThrows(CAException.class, () -> {
			comm.afficheRoute("E");
		});
	}

	@Test
	void testExcepEcole() {
		assertThrows(CAException.class, () -> {
			comm.retireEcole("D");
		});
	}

	@Test
	void testExcepEcole2() {
		assertFalse(comm.retireEcole("B");
	}

}