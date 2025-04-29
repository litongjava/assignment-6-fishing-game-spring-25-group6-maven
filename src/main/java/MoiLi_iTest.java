import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test for MoiLi_i Fishable I'a subclass.
 * 
 * @author Lisa Miller
 * @since 3/22/25
 */

public class MoiLi_iTest {
	/** constant reproductive mode for this Ia. */
	static final String REPRODUCTIVE_MODE = "protoandrous";
	/** constant array of what the fish eats. */
	static final String[] DIET_ITEMS = { "crustaceans" };
	/** constant maximum length for this Ia. */
	static final double MAX_LENGTH = 8.0;
	/**
	 * constant minimum length for this Ia.
	 */
	static final double MIN_LENGTH = 0.0;

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
		MoiLi_i a = new MoiLi_i();
		Assert.assertTrue(a.getName() + " is not Fishable as required", (a instanceof Fishable));
	}

	//*********** Not yet completed ************
	/** test isLegalSize. */
	@Test
	public void isLegalSizeTest() {
		MoiLi_i a = new MoiLi_i();
		Assert.assertEquals("This fish is never legal size", false, a.isLegalSize());
	}

	/** test isBait. */
	@Test
	public void isBaitTest() {
		MoiLi_i a = new MoiLi_i();
		Assert.assertEquals("This fish is never used for bait", false, a.isBait());
	}

	/** test isGameFish method. */
	@Test
	public void isGamefishTest() {
		MoiLi_i a = new MoiLi_i();
		Assert.assertEquals("This fish is never used for eating", false, a.isGamefish());
	}

	// test getCatchMethods
	/** test getCatchMethods method. */
	@Test
	public void getCatchMethodsTest() {
		MoiLi_i a = new MoiLi_i();
		Assert.assertEquals("This fish has no catch methods", null, a.getCatchMethods());
	}

} ///close test clas
