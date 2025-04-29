import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test for Kahaha Fishable I'a subclass.
 * 
 * @author Lisa Miller
 * @since 3/22/25
 */

public class KahahaTest {
	/** constant reproductive mode for this Ia. */
	static final String REPRODUCTIVE_MODE = "serial";
	/** constant array of what the fish eats. */
	static final String[] DIET_ITEMS = { "zooplankton", "algae", "detritus", "invertebrates" };
	/** constant maximum length for this Ia. */
	static final double MAX_LENGTH = 8.0;
	/**
	 * constant minimum length for this Ia.
	 */
	static final double MIN_LENGTH = 4.0;

	/**
	 * Fixture initialization (common initialization for all tests).
	 **/
	@Before
	public void setUp() {
	}

	/************* Fishable methods tests. ****************************/
	/****************** Check for Fishable Implementation. ***********/
	@Test
	public void isFishableTest() {
		Kahaha a = new Kahaha();
		Assert.assertTrue(a.getName() + " is not Fishable", (a instanceof Fishable));
	}

	//*********** Not yet completed ************
	/** test isLegalSize. */
	@Test
	public void isLegalSizeTest() {
		Kahaha a = new Kahaha();
		Assert.assertEquals("This fish is never legal size", a.isLegalSize(), false);
	}

	/** test isBait. */
	@Test
	public void isBaitTest() {
		Kahaha a = new Kahaha();
		Assert.assertEquals("This fish is never used for bait", a.isBait(), false);
	}

	/** test isGameFish method. */
	@Test
	public void isGamefishTest() {
		Kahaha a = new Kahaha();
		Assert.assertEquals("This fish is never used for eating", a.isGamefish(), false);
	}

	// test getCatchMethods
	/** test getCatchMethods method. */
	@Test
	public void getCatchMethodsTest() {
		Kahaha a = new Kahaha();
		String[] validCatchMeth = { "net", "pole" };
		String[] fishCatchMeth = a.getCatchMethods();
		boolean match = false;
		for (int i = 0; i < validCatchMeth.length; i++) {
			for (int j = 0; j < fishCatchMeth.length; j++) {
				if (validCatchMeth[i].equalsIgnoreCase(fishCatchMeth[j])) {
					match = true;
				}

			}
			Assert.assertTrue(a.getName() + "is missing " + validCatchMeth[i] + "as a catch method",
					match);
			match = false;
		}

	}

} ///close test clas
