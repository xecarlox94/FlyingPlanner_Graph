package F28DA_CW2;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class FlyingPlannerTest {

	FlyingPlanner fi;

	@Before
	public void initialize() {
		fi = new FlyingPlanner();
		try {
			fi.populate(new FlightsReader());
		} catch (FileNotFoundException | FlyingPlannerException e) {
			e.printStackTrace();
		}
	}

	// Add your own tests here
	// You can get inspiration from FlyingPlannerProvidedTest

	@Test
	public void anotherTest() {
		fail("To be implemented");
	}

}
