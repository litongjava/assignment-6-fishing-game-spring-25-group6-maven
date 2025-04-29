import java.util.Random;

/**
 * Pua'Ama class. First phase of Ama'ama family
 * 
 * @author Brian Emmons & Jay Jr. Cubangbang
 * @since 4/20/2025
 */
public class Pua_ama extends FishableI_a implements SexChangeable {
	//global declared variables that can be accessed within
	//all classes that extend from Pua_ama

	/** static DIET_ITEMS array variable. */
	protected static final String[] DIET_ITEMS = { "zooplankton", "algae", "detritus",
		"invertebrates" };
	/** static REPRODUCTIVE_MODE variable. */
	protected static final String REPRODUCTIVE_MODE = "protoandrous";

	/** max length for pua'ama. */
	private static final double PUAAMA_MAX_LENGTH = 4.00;
	/** min length for pua'ama. */
	private static final double PUAAMA_MIN_LENGTH = 0.01;

	/**
	 * Constructor for making Pua'Ama uses I_a superclass constructor randomly sets
	 * length within allowed values after calling constructor. This is the juvenile
	 * of the species and has no sex determination.
	 */
	public Pua_ama() {
		super("Pua'ama", "Striped mullet", "Mugil Cephalus", PUAAMA_MAX_LENGTH, PUAAMA_MIN_LENGTH,
				0.1, 2.0, DIET_ITEMS, "silver", "silver", "none");
		//set random allowed length after
		this.initLength();

	}

	/**
	 * Constructor for making Pua'Ama with a specific length. uses I_a superclass
	 * constructor
	 * 
	 * @param length The length of this fish
	 * @throws FishSizeException if length > maxLength or < minLength. This is the
	 *                           juvenile of the species and has no sex
	 *                           determination.
	 */
	public Pua_ama(double length) {
		super("Pua'ama", "Striped mullet", "Mugil Cephalus", PUAAMA_MAX_LENGTH, PUAAMA_MIN_LENGTH,
				length, (length * 2), DIET_ITEMS, "silver", "silver", "none");

	}

	/**
	 * Protected constructor for subclasses. This allows subclass specific variables
	 * to pass through to I'a superclass constructor.
	 *
	 * In this species, the English and scientific names remain the same throughout
	 * so not needed to pass from subclasses.
	 *
	 * @param name      This fish type's Hawaiian name.
	 * @param maxLength The maximum length this name applies.
	 * @param minLength The minimum length this name applies.
	 * @param length    This fish's length.
	 * @param weight    This fish's weight
	 * @param diet      The diet preference of this fish type.
	 * @param bodyColor The body color of this fish.
	 * @param finColor  The fin color of this fish.
	 * @param sex       The sex of this fish.
	 * @throws FishSizeException if length > maxLength or < minLength
	 */
	protected Pua_ama(String name, double maxLength, double minLength, double length, double weight,
			String[] diet, String bodyColor, String finColor, String sex) {

		super(name, "Striped Mullet", "Mugil Cephalus", maxLength, minLength, length, weight, diet,
				bodyColor, finColor, sex);
	}

	/**
	 * Sets the fish's initial length to between its max and min allowed values.
	 */
	protected void initLength() {
		Random ran = new Random();
		this.length = this.minLength + (this.maxLength - this.minLength) * ran.nextDouble();
		this.weight = this.length * 2;
	}

	//++++ required implementations of abstract methods ++++++++++

	/**
	 * Sets the fish's length to newLength.
	 * 
	 * @param newLength the new fish length
	 * @throws FishSizeException if new length not within min-max length
	 */
	public void setLength(double newLength) throws FishSizeException {
		if (newLength >= this.minLength && newLength <= this.maxLength) {
			this.length = newLength;
		} else {
			throw new FishSizeException(this.name + " must be within " + this.minLength + " and "
					+ this.maxLength + " inches long.");
		}

	}

	/**
	 * Sets the fish's weight to newWeight.
	 * 
	 * @param newWeight The fish's new weight.
	 */
	public void setWeight(double newWeight) {

		this.weight = newWeight;

	}

	/**
	 * Models eating, should call grow to cause length increases.
	 * 
	 * @param food A String with a potential food name
	 * @throws FishFoodException if the food is not the fish's diet
	 */
	public void eat(String food) {

		boolean isEdible = false;
		//loop through what fish eats and determine if edible
		for (int i = 0; i < DIET_ITEMS.length; i++) {
			if (food.equals(DIET_ITEMS[i])) {
				isEdible = true;
			}
		}
		if (isEdible) {
			this.grow();
		} else {
			throw new FishFoodException(this.name + " does not eat " + food);
		}
	}

	/**
	 * Should be used by eat method to increase length of fish. Should determine a
	 * new length and internally call setLength
	 * 
	 * @throws FishSizeException if new length not within min-max length indicates
	 *                           levelUp needs to be called.
	 */
	protected void grow() throws FishSizeException {
		Random ran = new Random();
		double lengthIncrease = ran.nextDouble() * growthRate;

		//calculate a new length by adding a random value between 0 and growthRate
		double newLength = this.length + lengthIncrease;

		//check to see if this fish needs to level up
		if (newLength > this.maxLength) {
			throw new FishSizeException("This fish has outgrown its name," + " it must level up!");
		} else {
			this.length = newLength;
			this.weight = 2 * newLength; //weight calc
		}
	}

	/**
	 * returns new Kahaha version of this fish. When pua'ama reaches maxLength, it
	 * levelUps to Kahaha of minLength min size of next size is always max length of
	 * this one
	 * 
	 * @return a new MoiMana
	 */
	public Kahaha levelUp() {

		initSex();
		return new Kahaha(this.maxLength, this.sex);

	}

	//============= Required by Fishable ==================
	/**
	 * determines if the fish is legal to keep due to length. Legal size is 11
	 * inches and above
	 * 
	 * @return true If this fish is legal size to keep.
	 */
	public boolean isLegalSize() {
		if (this.length >= 11) {
			return true;
		}
		return false;
	}

	/**
	 * determines if Pua_ama can be used as a baitfish.
	 * 
	 * @return true this fish type is used for bait for other fishing.
	 */
	public boolean isBait() {
		return false;
	}

	/**
	 * determines if Pua_ama can be used to eat.
	 * 
	 * @return true if is legal size, false otherwise.
	 */
	public boolean isGamefish() {
		if (this.isLegalSize()) {
			return true;
		}
		return false;
	}

	/**
	 * Oama season is April - November, closed December - March.
	 * 
	 * @param month the current month of the year.
	 * @return true if fish is okay to take, false otherwise.
	 */
	public boolean isInSeason(String month) {
		month = month.toLowerCase();
		boolean isOk = true;

		switch (month) {
			case "december":
			case "january":
			case "february":
			case "march":
				isOk = false;
				break;
			default:
				isOk = true;
				break;
		}
		return isOk;
	}

	/**
	 * Returns the methods of catching this fish.
	 * 
	 * @return an array of fishing methods.
	 */
	public String[] getCatchMethods() {
		return new String[] { "net", "pole" };
	}

	/**
	 * Returns the limit on number of Oama you're allowed to catch.
	 * 
	 * @return int representing the maximum number of Oama you are allowed.
	 */
	public int getBagLimit() {
		return Integer.MAX_VALUE;
	}

	//============== required by SexChangable interface ==============

	/**
	 * changes this Pua'Ama s sex. Pua'Ama cannot change sex. Can only change at
	 * levelUp
	 * 
	 * @throws FishSexException always
	 */
	public void changeSex() throws FishSexException {
		throw new FishSexException("Pua'ama cannot change sex unless levelling up.");
	}

	/**
	 * returns the reproductive mode of this sex changing fish.
	 * 
	 * @return The fish's reproductive mode.
	 */
	public String getReproductiveMode() {
		return REPRODUCTIVE_MODE;
	}

}