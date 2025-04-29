/**
* Moi Mana object class.
* This is the second phase of Moi life
* It is strictly male
* The size is 8 - 12 inches
* @author Lisa Miller
* @version 1.0
* @since 2/14/22
*/
public class MoiMana extends MoiLi_i {
   
   // Constant instance variables, only add if changed from superclass

   /** constant maximum length for this Ia. 
   * Private because not inherited.
   */
   private static final double moiManaMaxLength = 12.0;
   /** constant minimum length for this Ia. 
   * Private because not inherited.
   */
   private static final double moiManaMinLength = 8.0;
   
 
   /**
   * Constructor for making MoiMana with random length 
   * uses I_a superclass constructor
   * uses DIET_ITEMS array from super class
   * randomly sets length within allowed values after calling constructor.
   * This is the second phase of the species and is always male.
   * @throws FishSizeException if length exceeds maxLength or is less than minLength
   * Must send minLength  through as a temp length or throws exception
   */
   public MoiMana() {
      super("Moi Mana", moiManaMaxLength, moiManaMinLength, 
         moiManaMinLength, (moiManaMinLength*2), DIET_ITEMS,  "silver", "silver with black tips", "male"); 
      //method is in superclass but will use max, min length set above
      this.initLength();  
   }
   
   /**
   * Constructor for making MoiMana with a given length 
   * uses I_a superclass constructor
   * uses DIET_ITEMS array from super class
   * randomly sets length within allowed values after calling constructor.
   * This is the second phase of the species and is always male.
   * @throws FishSizeException if length exceeds maxLength or is less than minLength
   */
   public MoiMana(double length) {
      super("Moi Mana", moiManaMaxLength, moiManaMinLength, 
         length, (length*2), DIET_ITEMS,  "silver", "silver with black tips", "male"); 

   }
   /** 
   * Protected constructor for subclasses.
   * This allows subclass specific variables  
   * to pass through to I'a superclass constructor.
   *
   * In this species, the English and scientific names
   * remain the same throughout so not needed to pass from subclasses.
   *
   *@param name This fish type's Hawaiian name.
   *@param maxLength The maximum length this name applies.
   *@param minLength The minimum length this name applies.
   *@param length This fish's size.
   *@param diet The diet preference of this fish type.
   *@param bodyColor The body color of this fish.
   *@param finColor The fin color of this fish.
   *@param sex The sex of this fish.
   *@throws FishSizeException if length exceeds maxLength or is less than minLength
   */
   protected MoiMana(String name, double maxLength, double minLength, 
         double length, double weight, String[] diet, String bodyColor, String finColor, String sex) {
       
      super(name, maxLength, minLength, 
         length, weight, diet, bodyColor, finColor, sex);
   }
   
    /** returns new Pala Moi version of this fish
   * When a Moi Mana reaches maxLength, it should levelUp to a MoiMana of  min size
   * min size of next size is always max size of this one
   * @return a new MoiMana
   */
   public PalaMoi levelUp() {
   
      return new PalaMoi(this.maxLength);
   
   }

    /**
    * Returns the methods of catching this fish.
    * overridden here because fish becomes legal at this size
    * @return an array of fishing methods.
    */
   public String[] getCatchMethods() {
      String[] validCatchMeth = {"net", "pole"};
      return validCatchMeth;
   }
}
   
