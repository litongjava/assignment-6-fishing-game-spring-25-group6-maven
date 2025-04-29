import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test for Ohua Fishable I'a subclass.
 * 
 * @author Lisa Miller
 * @since 3/22/25
 */
public class OhuaTest {
	/** constant reproductive mode for this Ia. */
	static final String REPRODUCTIVE_MODE = "protogynous";
	/** constant array of what the fish eats. */
	static final String[] DIET_ITEMS = { "algae" };
	/** constant maximum length for this Ia. */
	static final double MAX_LENGTH = 4.0;
	/**
	 * constant minimum length for this Ia.
	 */
	static final double MIN_LENGTH = 0.01;

	/**
	 * Fixture initialization (common initialization for all tests).
	 **/
	@Before
	public void setUp() {
	}

	/************* Fishable methods tests. ****************************/
	//****************** Check for Fishable Implementation. ***********/
	@Test
	public void isFishableTest() {
		Ohua a = new Ohua();
		Assert.assertTrue(a.getName() + " is not Fishable as required", (a instanceof Fishable));
	}

	//*********** Not yet completed ************
	/** test isLegalSize. */
	@Test
	public void isLegalSizeTest() {
		Ohua a = new Ohua();
		Assert.assertEquals("This fish is never legal size", a.isLegalSize(), false);
	}

	/** test isBait. */
	@Test
	public void isBaitTest() {
		Ohua a = new Ohua();
		Assert.assertEquals("This fish is never used for bait", a.isBait(), false);
	}

	/** test isGameFish method. */
	@Test
	public void isGamefishTest() {
		Ohua a = new Ohua();
		Assert.assertEquals("This fish is never used for eating", a.isGamefish(), false);
	}

	// test getCatchMethods
	/** test getCatchMethods method. */
	@Test
	public void getCatchMethodsTest() {
		Ohua a = new Ohua();
		String[] validCatchMeth = { "throw net", "spear", "pole" };
		String[] fishCatchMeth = a.getCatchMethods();
		boolean match = false;
		for (int i = 0; i < validCatchMeth.length; i++) {
			for (int j = 0; j < fishCatchMeth.length; j++) {
				if (validCatchMeth[i].equalsIgnoreCase(fishCatchMeth[j])) {
					match = true;
				}

			}
			Assert.assertTrue("This fish is missing " + validCatchMeth[i] + "as a catch method",
					match);
			match = false;
		}

	}

} ///close test clas
