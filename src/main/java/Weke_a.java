import java.util.Random;

/**
 * Sibling in Weke family.
 * 
 * @author Charles Thompson
 * @since 2/24/2025
 */
public class Weke_a extends Oama {

	/** constant minimum length for the Weke'a. */
	private static final double WEKEA_MIN_LENGTH = 7;
	/** constant maximum length for the Weke'a. */
	private static final double WEKEA_MAX_LENGTH = 30;

	/**
	 * constructor with no parameters.
	 */
	public Weke_a() {
		super("Weke'a", "Square-spot Goatfish", "Mulloidichthys flavolineatus", WEKEA_MAX_LENGTH,
				WEKEA_MIN_LENGTH, WEKEA_MIN_LENGTH, (WEKEA_MIN_LENGTH * 2), DIET_ITEMS,
				"white with yellow stripe and black spot", "white", "male or female");
		this.initLength();
		this.initSex();
	}

	/**
	 * constructor that takes a given length.
	 * 
	 * @param length The fish's new length.
	 */
	public Weke_a(double length) {
		super("Weke'a", "Square-spot Goatfish", "Mulloidichthys flavolineatus", WEKEA_MAX_LENGTH,
				WEKEA_MIN_LENGTH, length, (length * 2), DIET_ITEMS,
				"white with yellow stripe and black spot", "white", "male or female");
		this.initSex();
	}

	/**
	 * method for simulating the fish growing. different from Oama.java because it
	 * does not call levelUp().
	 */
	protected void grow() {
		Random ran = new Random();
		double lengthIncrease = ran.nextDouble() * growthRate;

		//calculate a new length by adding a random value between 0 and growthRate
		this.length = this.length + lengthIncrease;
		this.weight = this.length * 2;
	}

	/**
	 * new levelUp() method.
	 * 
	 * @return Weke_a No further subclasses, so it returns itself.
	 */
	public Weke_a levelUp() {
		return this;
	}

} //end class