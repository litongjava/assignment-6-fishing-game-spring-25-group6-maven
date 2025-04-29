import java.util.Random;
/**
* Panuhunuhu class (juvenile parrotfish).
* Second stage of Parrotfish cycle
* @author Mika Evans
* @since 2/20/25
*/
public class Panuhunuhu extends Ohua {
   
   /** Maximum length. */
   private static final double MAX_LENGTH = 12.0;
   
   /** Minimum length. */
   private static final double MIN_LENGTH = 4.0;
   
   /** What the fish likes to eat. */
   protected static final String[] DIET_ITEMS = {"algae"};
   
   /** Reproductive mode. */
   private static final String REPRODUCTIVE_MODE = "protogynous";

   /** 
   * Constructor for making Panuhunuhu with random length. 
   * Creates basic fish properties
   * Initializes random length and determines sex
   */
  
   public Panuhunuhu() {
      super("Panuhunuhu",
          MAX_LENGTH, MIN_LENGTH,
          MIN_LENGTH, (MIN_LENGTH * 2),
          DIET_ITEMS, "gray", "reddish gray", "none");
      this.initLen();
      this.initSex();
   }

   /**
   * Constructor with given length.
   * @param length The length of this fish
   * @throws FishSizeException if length is invalid
   */
   public Panuhunuhu(double length) {
      super("Panuhunuhu",
         MAX_LENGTH, MIN_LENGTH,
         length, (length * 2),
         DIET_ITEMS, "gray", "reddish gray", "none");
      if (length > MAX_LENGTH || length < MIN_LENGTH) {
         throw new FishSizeException("Panuhunuhu must be between " 
            + MIN_LENGTH + " and " 
            + MAX_LENGTH + " inches long.");
      }
      this.initSex();
   }

   /** 
   *Protected constructor for subclasses.
   * @param name The name of the Fish
   * @param englishName the english name
   * @param scientificName the scientific name
   * @param maxLength Maximum length Panuhunuhu can be
   * @param minLength Minimum length Panuhunuhu can be
   * @param length Current length
   * @param weight Current weight
   * @param diet Diet
   * @param bodyColor Body color
   * @param finColor Fin color
   * @param sex Sex of Fish
   */
   
   protected Panuhunuhu(String name, String englishName, String scientificName,
                     double maxLength, double minLength,
                     double length, double weight,
                     String[] diet, String bodyColor, String finColor, String sex) {
      super(name,
          maxLength, minLength, length, weight,
          diet, bodyColor, finColor, sex);
   }

   /**
   * Levels up to Uhu when fish outgrows juvenile stage.
   * Maintains sex through growth
   * @return new Uhu fish at min adult size
   * @throws exception if not valid length
   */

   public Uhu levelUp() {
      return new Uhu(this.maxLength, this.sex);
   }
   /**
   * Changes fish color (not used in juvenile stage).
   * @param bodyColor new body color
   * @param finColor new fin color
   * @throws ColorException if invalid color combo because it can't change yet
   */
   public void setColor(String bodyColor, String finColor) {
      this.bodyColor = bodyColor;  
      this.finColor = finColor;
   }
    
   /** Changes fish sex at random. */
   protected void initSex() {
      Random ran = new Random();
      int flip = ran.nextInt(2);
      
      if (flip == 0) {
         this.sex = "male";
      } else {
         this.sex = "female";
      }
   }
    
   /**
   * Gets the reproductive mode.
   * @return The reproductive mode
   */
   public String getReproductiveMode() {
      return REPRODUCTIVE_MODE;
   }
}