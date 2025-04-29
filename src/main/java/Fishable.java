/**
* interface for I_a that are caught for food.
* @author Lisa Miller
* @since 2/11/22
*/
public interface Fishable {

   /** 
   * determines if the fish is legal to keep due to length.
   * different species have different legal sizes
   * @return true If this fish is legal size to keep.
   */
   public abstract boolean isLegalSize();
   
   /**
   * determines if this fish can be used as a baitfish.
   * @return true if this fish type is used for bait for other fishing.
   */
   public abstract boolean isBait();
   
   /**
   * determines if this fish can be used to eat
   * @return true if this fish is edible, and allowed to be eaten.
   */
   public abstract boolean isGamefish();
   
   /**
   * Determines if this fish is in season or not.
   * @param month the current month of the year.
   * @return true if fish is okay to take in the month.
   */
   public abstract boolean isInSeason(String month);
   
   /**
   * Returns the methods of catching this fish.
   * @return an array of fishing methods.
   */
   public abstract String[] getCatchMethods();
   
   
   /**
   * Returns the maximum number of this fish you are allowed at one time.
   * @return int representing the maximum catch or Integer.MAX_VALUE if no limit
   */
   public abstract int getBagLimit();
   
}