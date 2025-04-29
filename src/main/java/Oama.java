import java.util.Random;

/**
 * Smallest fish in Weke family.
 * 
 * @author Charles Thompson
 * @since 2/24/2025
 */
public class Oama extends FishableI_a implements ColorChangeable {

	/** constant array of diet items for the 'Oama. */
	protected static final String[] DIET_ITEMS = { "worms", "mollusks", "invertebrates" };
	/** constant minimum length for the 'Oama. */
	private static final double OAMA_MIN_LENGTH = 0.01;
	/** constant maximum length for the 'Oama. */
	private static final double OAMA_MAX_LENGTH = 7;

	/**
	 * constructor with no parameters.
	 */
	public Oama() {
		super("'Oama", "Goatfish", "Mulloidichthys", OAMA_MAX_LENGTH, OAMA_MIN_LENGTH, 0.1, 2.0,
				DIET_ITEMS, "white with yellow stripe", "yellow", "none");
		//set random allowed length after
		this.initLength();

	}

	/**
	 * constructor that takes a length.
	 * 
	 * @param length The given length for the 'Oama
	 */
	public Oama(double length) {
		super("'Oama", "Goatfish", "Mulloidichthys", OAMA_MAX_LENGTH, OAMA_MIN_LENGTH, length,
				(length * 2), DIET_ITEMS, "white with yellow stripe", "yellow", "none");
	} //end constructor

	/**
	 * protected constructor for the 'Oama subclasses.
	 * 
	 * @param name           This fish's name.
	 * @param englishName    The common or English name for the fish.
	 * @param scientificName The scientific name for the fish.
	 * @param maxLength      The fish's maximum length.
	 * @param minLength      The fish's minimum length.
	 * @param length         The length of the fish.
	 * @param weight         The weight of the fish.
	 * @param diet           The fish's diet.
	 * @param bodyColor      The fish's body color.
	 * @param finColor       The fish's fin color.
	 * @param sex            The sex of the fish.
	 */
	protected Oama(String name, String englishName, String scientificName, double maxLength,
			double minLength, double length, double weight, String[] diet, String bodyColor,
			String finColor, String sex) {

		super(name, englishName, scientificName, maxLength, minLength, length, weight, diet,
				bodyColor, finColor, sex);
	}

	/**
	 * method to initialize the length of the fish.
	 */
	protected void initLength() {
		Random ran = new Random();
		this.length = this.minLength + (this.maxLength - this.minLength) * ran.nextDouble();
		this.weight = this.length * 2;
	} //end initLength

	/**
	 * mutator method for length.
	 * 
	 * @param newLength The new length to be assigned to the fish.
	 * @throws FishSizeException when the given length is too large or small.
	 */
	public void setLength(double newLength) throws FishSizeException {
		if (newLength >= this.minLength && newLength < this.maxLength) {
			this.length = newLength;
		} else {
			throw new FishSizeException(this.name + " must be within " + this.minLength + " and "
					+ this.maxLength + " inches long.");
		}
	} //end setLength

	/**
	 * mutator method for Weight.
	 * 
	 * @param newWeight the new weight for the fish
	 */
	public void setWeight(double newWeight) {
		this.weight = newWeight;
	} //end setWeight

	/**
	 * method for simulating the fish eating.
	 * 
	 * @param food The food attempting to be fed.
	 */
	public void eat(String food) {

		boolean isEdible = false;

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
	} //end eat

	/**
	 * method for simulating the fish growing. is used by the eat method.
	 */
	protected void grow() {
		Random ran = new Random();
		double lengthIncrease = ran.nextDouble() * growthRate;

		//calculate a new length by adding a random value between 0 and growthRate
		double newLen = this.length + lengthIncrease;

		//check to see if this fish needs to level up
		if (newLen > this.maxLength) {
			throw new FishSizeException("This fish has outgrown its name," + " it must level up!");
		} else {
			this.length = newLen;
			this.weight = 2 * newLen; //rudimentary weight calc
		}
	} //end grow

	/**
	 * method for levelling the fish up to one of two subclasses.
	 * 
	 * @return The upgraded fish- either a Weke'a or Weke'ula.
	 */
	public FishableI_a levelUp() {
		Random ran = new Random();
		int i = ran.nextInt(2);
		if (i == 1) {
			return new Weke_a(this.maxLength);
		} else {
			return new Weke_ula(this.maxLength);
		}
	} //end Weke_a levelUp

	//============= Required by Fishable ==================
	/**
	 * determines if the fish is legal to keep due to length. No minimum legal size
	 * 
	 * @return true If this fish is legal size to keep.
	 */
	public boolean isLegalSize() {
		return true;
	}

	/**
	 * determines if Oama can be used as a baitfish.
	 * 
	 * @return true this fish type is used for bait for other fishing.
	 */
	public boolean isBait() {
		return true;
	}

	/**
	 * determines if Oama can be used to eat.
	 * 
	 * @return true if is legal size, false otherwise.
	 */
	public boolean isGamefish() {
		return true;
	}

	/**
	 * Oama season always open.
	 * 
	 * @param month the current month of the year.
	 * @return true if fish is okay to take, false otherwise.
	 */
	public boolean isInSeason(String month) {
		return true;
	}

	/**
	 * Returns the methods of catching this fish.
	 * 
	 * @return an array of fishing methods.
	 */
	public String[] getCatchMethods() {
		return new String[] { "net", "trap", "pole" };
	}

	/**
	 * Returns the limit on number of Oama you're allowed to catch.
	 * 
	 * @return int representing the maximum number of Oama you are allowed.
	 */
	public int getBagLimit() {
		return 50;
	}

	/**
	 * mutator method for color.
	 * 
	 * @param newBodyColor The fish's new body color.
	 * @param newFinColor  The fish's new fin color.
	 */
	public void setColor(String newBodyColor, String newFinColor) {
		this.bodyColor = newBodyColor;
		this.finColor = newFinColor;
	}

} //end class