import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test for MoiMana Fishable I'a subclass.
 * 
 * @author Lisa Miller
 * @since 3/22/25
 */

public class MoiManaTest {
	/** constant reproductive mode for this Ia. */
	static final String REPRODUCTIVE_MODE = "protoandrous";
	/** constant array of what the fish eats. */
	static final String[] DIET_ITEMS = { "crustaceans" };
	/** constant maximum length for this Ia. */
	static final double MAX_LENGTH = 12.0;
	/**
	 * constant minimum length for this Ia.
	 */
	static final double MIN_LENGTH = 8.0;

	/**
	 * Fixture initialization (common initialization for all tests).
	 **/
	@Before
	public void setUp() {
	}

	/**
	 * Fishable methods tests.
	 * Check for Fishable Implementation.
	 */
	@Test
	public void isFishableTest() {
		MoiMana a = new MoiMana();
		Assert.assertTrue(a.getName() + " is not Fishable as required", (a instanceof Fishable));
	}

	//*********** Not yet completed ************
	/** test isLegalSize. */
	@Test
	public void isLegalSizeTest() {
		MoiMana a = new MoiMana();
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
		MoiMana a = new MoiMana();
		Assert.assertEquals("This fish is never used for bait", false, a.isBait());
	}

	/** test isGameFish method. */
	@Test
	public void isGamefishTest() {
		MoiMana a = new MoiMana();
		if (a.getLength() >= 11) {
			Assert.assertEquals("This fish is big enough to be a gamefish", true, a.isGamefish());
		} else {
			Assert.assertEquals("This fish is too small to be a gamefish", false, a.isGamefish());
		}
	}

	// test getCatchMethods
	/** test getCatchMethods method. */
	@Test
	public void getCatchMethodsTest() {
		MoiMana a = new MoiMana();
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
