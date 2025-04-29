/**
 * Abstract parent class for Fishable I_a (fish) species. Subclass of I_a
 * 
 * @author Lisa Miller
 * @since 4/23/22
 */
public abstract class FishableI_a extends I_a implements Fishable {

	/**
	 * Constructor for FishableI_a.
	 * 
	 * @param name           Hawaiian name.
	 * @param englishName    English name.
	 * @param scientificName scientific name.
	 * @param maxLength      maximum length.
	 * @param minLength      minimum length.
	 * @param length         fish's size.
	 * @param weight         fish's weight.
	 * @param diet           diet.
	 * @param bodyColor      body color.
	 * @param finColor       fin color.
	 * @param sex            sex.
	 * @throws FishSizeException FishSizeException.
	 */

	public FishableI_a(String name, String englishName, String scientificName, double maxLength,
			double minLength, double length, double weight, String[] diet, String bodyColor,
			String finColor, String sex) {

		super(name, englishName, scientificName, maxLength, minLength, length, weight, diet,
				bodyColor, finColor, sex);

	}

	/**
	 * returns new FishableI_a of next level When a fish reaches maxSize.
	 * 
	 * @return a new FishableI_a of the next level type, should replace caller
	 */
	protected abstract FishableI_a levelUp();
}