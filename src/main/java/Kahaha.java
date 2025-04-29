/**
 * Kahaha class. Second phase of Ama'Ama family
 * 
 * @author Brian Emmons
 * @since 2/22/2025
 */
public class Kahaha extends Pua_ama {

	//global class variables for Kahaha

	/** max length for Kahaha. */

	private static final double KAHAHA_MAX_LENGTH = 8.00;
	/** min length for Kahaha. */
	private static final double KAHAHA_MIN_LENGTH = 4.00;

	/**
	 * Constructor for making Kahaha.
	 */
	public Kahaha() {
		super("Kahaha", KAHAHA_MAX_LENGTH, KAHAHA_MIN_LENGTH, KAHAHA_MIN_LENGTH,
				(KAHAHA_MIN_LENGTH * 2), DIET_ITEMS, "silver", "silver", "male or female");

		//set random allowed length
		this.initLength();
		this.initSex();

	}

	/**
	 * Constructor for making Pua'Ama with a specific length. 
	 * 
	 * @param length The length of this fish
	 * @throws FishSizeException FishSizeException.
	 */
	public Kahaha(double length) {
		super("Kahaha", KAHAHA_MAX_LENGTH, KAHAHA_MIN_LENGTH, length, (length * 2), DIET_ITEMS,
				"silver", "silver", "male or female");
		this.initSex();

	}

	/**
	 * Constructor for making Pua'Ama with a specific length. 
	 * 
	 * @param length The length of this fish
	 * @param sex    The sex of this fish
	 * @throws FishSizeException  FishSizeException.
	 */
	public Kahaha(double length, String sex) {
		super("Kahaha", KAHAHA_MAX_LENGTH, KAHAHA_MIN_LENGTH, length, (length * 2), DIET_ITEMS,
				"silver", "silver", sex);
	}

	/**
	 * protected variables for Kahaha class.
	 * 
	 * @param name      name
	 * @param maxLength maxLength
	 * @param minLength minLength
	 * @param length    length
	 * @param weight    weight
	 * @param diet      diet
	 * @param bodyColor bodyColor
	 * @param finColor  finColor
	 * @param sex       sex
	 */
	protected Kahaha(String name, double maxLength, double minLength, double length, double weight,
			String[] diet, String bodyColor, String finColor, String sex) {

		super(name, maxLength, minLength, length, weight, diet, bodyColor, finColor, sex);
	}

	/**
	 * level up method sends maxlength and sex.
	 * 
	 * @return maxLength and sex
	 */
	public Ama_ama levelUp() {
		return new Ama_ama(this.maxLength, this.sex);

	}

	/**
	 * disallowing any changes to sex variable.
	 * 
	 * @throws FishSexException always
	 */
	public void changeSex() throws FishSexException {
		throw new FishSexException("Sex can no longer be changed this stage and on.");
	}

	/**
	 * accessor for REPRODUCTIVE_MODE.
	 * 
	 * @return REPRODUCTIVE_MODE
	 */
	public String getReproductiveMode() {
		return REPRODUCTIVE_MODE;
	}
}
