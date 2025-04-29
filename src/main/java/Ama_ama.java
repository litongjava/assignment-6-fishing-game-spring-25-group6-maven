

/** Ama'ama class.
* Third phase of Ama'Ama family
* @author Brian Emmons
* @since 2/22/2025
*/


public class Ama_ama extends Kahaha {

//global static variables for Ama_ama class

/** Ama'Ama max length. */

   private static final double AMAAMA_MAX_LENGTH = 12.00;
   
/** Ama'Ama min length. */  
     
   private static final double AMAAMA_MIN_LENGTH = 8.00;
 
   




/**
   * Constructor for making Ama'ama
   * uses I_a superclass constructor
   * randomly sets length within allowed values after calling constructor.
   * This is the juvenile of the species and has no sex determination.
   */
   public Ama_ama() {
      super("'Ama'ama", AMAAMA_MAX_LENGTH, AMAAMA_MIN_LENGTH, 
         AMAAMA_MIN_LENGTH, (AMAAMA_MIN_LENGTH * 2), DIET_ITEMS, "silver", 
         "silver", "male or female");
      this.initSex();
          
      this.initLength();
      
   }
      /**
   * Constructor for making Pua'Ama with a specific length.
   * uses I_a superclass constructor
   * @param length The length of this fish
   * @throws FishSizeException if length > maxLength or < minLength.
   * This is the juvenile of the species and has no sex determination.
   */
   public Ama_ama(double length) {
      super("'Ama'ama", AMAAMA_MAX_LENGTH, AMAAMA_MIN_LENGTH, length, (length * 2), 
         DIET_ITEMS, "silver", "silver", "male or female"); 
      this.initSex();
   }
      /**
   * Constructor for making Pua'Ama with a given length and sex.
   * uses I_a superclass constructor
   * @param length The length of this fish
   * @param sex The sex of this fish
   * @throws FishSizeException if length > maxLength or < minLength.
   * This is the juvenile of the species and has no sex determination.
   */
   public Ama_ama(double length, String sex) {
      super("'Ama'ama", AMAAMA_MAX_LENGTH, AMAAMA_MIN_LENGTH, length, (length * 2), 
         DIET_ITEMS, "silver", "silver", sex); 
   } 
   
   /**
   * Protected class variables for Ama_ama.
   * @param name name
   * @param maxLength maxLength
   * @param minLength minLength
   * @param length length
   * @param weight weight
   * @param diet diet
   * @param bodyColor bodyColor
   * @param finColor finColor
   * @param sex sex
   */
   protected Ama_ama(String name, double maxLength, double minLength, 
         double length, double weight, String[] diet, String bodyColor, 
         String finColor, String sex) {
       
      super(name, maxLength, minLength, 
         length, weight, diet, bodyColor, finColor, sex);
   }
   /**
   * Level up method from Ama'ama into Anae.
   * @return  Ama'ama max length and sex
   */  
   public Anae levelUp() {
      return new Anae(this.maxLength, this.sex);
   }
      
   /**
   * Disallows sex change.
   * @throws FishSexException always
   */  
   public void changeSex() throws FishSexException {
      throw new FishSexException("Sex can no longer be changed.");
   }
   /**
   * Accessor for REPRODUCTIVE_MODE.
   * @return REPRODUCTIVE_MODE
   */
   public String getReproductiveMode() {
      return REPRODUCTIVE_MODE;
      
   }
}
  