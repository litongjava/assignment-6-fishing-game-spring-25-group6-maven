import java.util.Random;

/**
 * Sibling in Weke family.
 * 
 * @author Charles Thompson
 * @since 3/6/2025
 */
public class Weke_ula extends Oama {

	/** constant minimum length for the Weke'ula. */
	private static final double WEKEULA_MIN_LENGTH = 7;
	/** constant minimum length for the Weke'ula. */
	private static final double WEKEULA_MAX_LENGTH = 30;

	/**
	 * constructor with no parameters.
	 */
	public Weke_ula() {
		super("Weke'ula", "Yellowfin Goatfish", "Mulloidichthys vanicolensis", WEKEULA_MAX_LENGTH,
				WEKEULA_MIN_LENGTH, WEKEULA_MIN_LENGTH, (WEKEULA_MIN_LENGTH * 2), DIET_ITEMS, "tbd",
				"tbd", "male or female");
		this.initLength();
		this.initSex();
		this.initColor();
	}

	/**
	 * constructor that takes a given length.
	 * 
	 * @param length The fish's new length.
	 */
	public Weke_ula(double length) {
		super("Weke'ula", "Yellowfin Goatfish", "Mulloidichthys vanicolensis", WEKEULA_MAX_LENGTH,
				WEKEULA_MIN_LENGTH, length, (length * 2), DIET_ITEMS, "tbd", "yellow or red",
				"male or female");
		this.initSex();
		this.initColor();
	}

	/**
	 * method to initialize the body and fin color of the fish.
	 */
	protected void initColor() {
		Random ran = new Random();

		int flip = ran.nextInt(2);
		if (flip == 0) {
			this.bodyColor = "white with yellow stripe";
		} else {
			this.bodyColor = "red";
		}

		flip = ran.nextInt(2);
		if (flip == 0) {
			this.finColor = "yellow";
		} else {
			this.finColor = "red";
		}
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
	 * @return Weke_ula No further subclasses, so it returns itself.
	 */
	public Weke_ula levelUp() {
		return this;
	}

} //end class