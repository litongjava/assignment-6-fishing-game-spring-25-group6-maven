import java.util.Random;

/** Anae class.
* Fourth and final phase of Ama'ama family
* @author Brian Emmons
* @since 2/22/2025
*/


public class Anae extends Ama_ama {

// global class variables for Anae

/** anae max length. */

   private static final double ANAE_MAX_LENGTH = 17.00;
/** anae min length. */
   private static final double ANAE_MIN_LENGTH = 12.00;
 




   /**
   * Constructor for making 'Anae
   * uses I_a superclass constructor
   * randomly sets length within allowed values after calling constructor.
   * This is the juvenile of the species and has no sex determination.
   */
   public Anae() {
      super("'Anae", ANAE_MAX_LENGTH, ANAE_MIN_LENGTH, 
      ANAE_MIN_LENGTH, (ANAE_MIN_LENGTH * 2), DIET_ITEMS, "silver", 
         "silver", "male or female");   
      this.initSex();
      this.initLength();
   }
   
   /**
   * Constructor for making 'Anae.
   * uses I_a superclass constructor
   * @param length The length of this fish
   * randomly sets length within allowed values after calling constructor.
   * This is the juvenile of the species and has no sex determination.
   */
   public Anae(double length) {
      super("'Anae", ANAE_MAX_LENGTH, ANAE_MIN_LENGTH, 
      length, (length * 2), DIET_ITEMS, "silver", 
         "silver", "male or female");   
      this.initSex();
   }
      
   /**
   * Constructor for making 'Anae with a specific length.
   * uses I_a superclass constructor
   * @param length The length of this fish
   * @throws FishSizeException if length > maxLength or < minLength.
   * This is the juvenile of the species and has no sex determination.
   * @param sex sex
   */
   public Anae(double length, String sex) {
      super("'Anae", ANAE_MAX_LENGTH, ANAE_MIN_LENGTH, length, (length * 2), 
         DIET_ITEMS, "silver", "silver", sex);  
   
   }
    //Anae do not change to another type
   //overwrite the grow method from the base class.
   /**
   * Should be used by eat method to increase length of fish. 
   * Should determine a new length and internally call setLength
   * Does not throw FishSizeException because this is the final type
   */
   protected void grow() {
      Random ran = new Random();
      double lengthIncrease = ran.nextDouble() * growthRate;
   
      //calculate a new length by adding a random value between 0 and growthRate
      this.length = this.length + lengthIncrease;
      this.weight = this.length * 2;
   }
   /**
   * @return current fish if level up called because max level already
   */
   public Anae levelUp() {
   
      return this;
   
   }
   
}

   
