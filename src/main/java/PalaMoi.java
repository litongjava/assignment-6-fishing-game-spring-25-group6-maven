/**
 * Pala Moi object class. This is the third phase of Moi life It is strictly a
 * hermaphrodite (intersexed) The size is 12 - 16 inches
 * 
 * @author Lisa Miller
 * @version 1.0
 * @since 2/14/22
 */
public class PalaMoi extends MoiMana {

	// Constant instance variables, only add if changed from superclass
	/**
	 * constant maximum length for this Ia. Private because not inherited.
	 */
	private static final double PALA_MOI_MAX_LENGTH = 16.0;
	/**
	 * constant minimum length for this Ia. Private because not inherited.
	 */
	private static final double PALA_MOI_MIN_LENGTH = 12.0;

	/**
	 * Constructor for making PalaMoi with random length uses I_a superclass
	 * constructor uses DIET_ITEMS array from super class randomly sets length
	 * within allowed values after calling constructor. This is the third phase of
	 * the species and is always a hermaphrodite (intersexed).
	 * 
	 * @throws FishSizeException if length exceeds maxLength or is less than
	 *                           minLength Must send minLength through as a temp
	 *                           length or throws exception
	 */
	public PalaMoi() {
		super("Pala Moi", PALA_MOI_MAX_LENGTH, PALA_MOI_MIN_LENGTH, PALA_MOI_MIN_LENGTH,
				(PALA_MOI_MIN_LENGTH * 2), DIET_ITEMS, "silver", "silver with black tips",
				"hermaphrodite");
		//method is in superclass but will use max, min length set above
		this.initLength();
	}

	/**
	 * Constructor for making PalaMoi with a given length uses I_a superclass
	 * constructor uses DIET_ITEMS array from super class This is the third phase of
	 * the species and is always a hermaphrodite (intersexed).
	 * @param length length.
	 * @throws FishSizeException FishSizeException.
	 */
	public PalaMoi(double length) {
		super("Pala Moi", PALA_MOI_MAX_LENGTH, PALA_MOI_MIN_LENGTH, length, (length * 2), DIET_ITEMS,
				"silver", "silver with black tips", "hermaphrodite");

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
	 * @param length    This fish's size.
	 * @param diet      The diet preference of this fish type.
	 * @param bodyColor The body color of this fish.
	 * @param finColor  The fin color of this fish.
	 * @param sex       The sex of this fish.
    * @param weight weight.
	 * @throws FishSizeException if length exceeds maxLength or is less than
	 *                           minLength
	 */
	protected PalaMoi(String name, double maxLength, double minLength, double length, double weight,
			String[] diet, String bodyColor, String finColor, String sex) {

		super(name, maxLength, minLength, length, weight, diet, bodyColor, finColor, sex);
	}

	/**
	 * returns new Moi version of this fish When a Pala Moi reaches maxLength, it
	 * should levelUp to a Moi of min size min size of next size is always max size
	 * of this one.
	 * 
	 * @return a new Moi
	 */
	public Moi levelUp() {

		return new Moi(this.maxLength);

	}

}
