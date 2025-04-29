import java.util.Random;

/**
 * Moi object class. This is the fourth and final phase of Moi life It is
 * strictly female The size is 16 inches and up
 * 
 * @author Lisa Miller
 * @version 1.0
 * @since 2/14/22
 */
public class Moi extends PalaMoi {

	// Constant instance variables, only add if changed from superclass

	/**
	 * constant maximum length for this Ia. This is the state record Private because
	 * not inherited.
	 */
	private static final double MOI_MAX_LENGTH = 30.0;
	/**
	 * constant minimum length for this Ia. Private because not inherited.
	 */
	private static final double MOI_MIN_LENGTH = 16.0;

	/**
	 * Constructor for making Moi with random length uses I_a superclass constructor
	 * uses DIET_ITEMS array from super class randomly sets length within allowed
	 * values after calling constructor. This is the fourth phase of the species and
	 * always female
	 * 
	 * @throws FishSizeException if length is less than minLength Must send
	 *                           minLength through as a temp length or throws
	 *                           exception
	 */
	public Moi() {
		super("Moi", MOI_MAX_LENGTH, MOI_MIN_LENGTH, MOI_MIN_LENGTH, (MOI_MIN_LENGTH * 2), DIET_ITEMS,
				"silver", "silver with black tips", "female");
		//method is in superclass but will use max, min length set above
		this.initLength();
	}

	/**
	 * Constructor for making Moi with a given length uses I_a superclass
	 * constructor uses DIET_ITEMS array from super class randomly sets length
	 * within allowed values after calling constructor. This is the fourth phase of
	 * the species and always female.
	 * 
	 * @param length length.
	 * @throws FishSizeException if length is less than minLength
	 */
	public Moi(double length) {
		super("Moi", MOI_MAX_LENGTH, MOI_MIN_LENGTH, length, (length * 2), DIET_ITEMS, "silver",
				"silver with black tips", "female");

	}

	//Moi do not change to another type, so we overwrite the grow method from the base class.
	/**
	 * Should be used by eat method to increase length of fish. Should determine a
	 * new length and internally call setLength Does not throw FishSizeException
	 * because this is the final type of this fish
	 */
	protected void grow() {
		Random ran = new Random();
		double lengthIncrease = ran.nextDouble() * growthRate;

		//calculate a new length by adding a random value between 0 and growthRate
		this.length = this.length + lengthIncrease;
		this.weight = this.length * 2;
	}

	/**
	 * returns the same fish Moi don't level up, this is the terminal type. method
	 * is required but shouldn't do anything
	 * @return moi.
	 */
	public Moi levelUp() {
		return this;
	}
}
