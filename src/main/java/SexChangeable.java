/**
 * interface for I_a that change sex.
 * 
 * @author Lisa Miller
 * @since 2/11/22
 */
public interface SexChangeable {

	/* ---- constants ------ */
	/** List of possible sex changing type. */
	String[] REPRODUCTIVE_MODES = { "protogynous", "protoandrous", "serial" };

	/**
	 * changes this fish's sex different species have different possible changes if
	 * fish doesn't change sex this method should throw UnsupportedOptionException.
	 */
	void changeSex();

	/**
	 * returns the reproductive mode of this sex changing fish.
	 * @return string.
	 */
   String getReproductiveMode();
}