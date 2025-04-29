import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test for PalaMoi Fishable I'a subclass.
 * 
 * @author Lisa Miller
 * @since 3/22/25
 */

public class PalaMoiTest {
	/** constant reproductive mode for this Ia. */
	static final String REPRODUCTIVE_MODE = "protoandrous";
	/** constant array of what the fish eats. */
	static final String[] DIET_ITEMS = { "crustaceans" };
	/** constant maximum length for this Ia. */
	static final double MAX_LENGTH = 16.0;
	/**
	 * constant minimum length for this Ia.
	 */
	static final double MIN_LENGTH = 12.0;

	/**
	 * Fixture initialization (common initialization for all tests).
	 **/
	@Before
	public void setUp() {
	}

	/**
	 * Fishable methods tests.
	 *  Check for Fishable Implementation.
	 */
	@Test
	public void isFishableTest() {
		PalaMoi a = new PalaMoi();
		Assert.assertTrue(a.getName() + " is not Fishable as required", (a instanceof Fishable));
	}

	/** test isLegalSize. */
	@Test
	public void isLegalSizeTest() {
		PalaMoi a = new PalaMoi();
		double len = a.getLength();
		if (len < 11) {
			Assert.assertEquals("LegalSize is incorrect, fish is too small with length:  " + len
					+ " it must be >= 11 to be legal.", false, a.isLegalSize());
		} else {
			Assert.assertEquals("LegalSize is incorrect, fish is legal size with length:  " + len
					+ " it legal at 11 inches.", true, a.isLegalSize());
		}
	}

	/** test isBait. */
	@Test
	public void isBaitTest() {
		PalaMoi a = new PalaMoi();
		Assert.assertEquals("This fish is never used for bait", false, a.isBait());
	}

	/** test isGameFish method. */
	@Test
	public void isGamefishTest() {
		PalaMoi a = new PalaMoi();
		Assert.assertEquals("This fish is used for eating", true, a.isGamefish());
	}

	// test getCatchMethods
	/** test getCatchMethods method. */
	@Test
	public void getCatchMethodsTest() {
		PalaMoi a = new PalaMoi();
		String[] validCatchMeth = { "net", "pole" };
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
