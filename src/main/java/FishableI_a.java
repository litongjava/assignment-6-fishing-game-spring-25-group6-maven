/**
* Abstract parent class for Fishable I_a (fish) species.
* Subclass of I_a
* @author Lisa Miller
* @since 4/23/22
*/
public abstract class FishableI_a extends I_a implements Fishable {

   /**
   * Constructor for FishableI_a. Just passes parameters through.
   *@param name This fish type's Hawaiian name.
   *@param englishName The fish type's English name.
   *@param scientificName The fish species's scientific name.
   *@param maxLength The maximum length this name applies.
   *@param minLength The minimum length this name applies.
   *@param length This fish's size.
   *@param weight This fish's weight.
   *@param diet The diet preference of this fish type.
   *@param bodyColor The body color of this fish.
   *@param finColor The fin color of this fish.
   *@param sex The sex of this fish.
   *@throws FishSizeException if length exceeds maxLength or is less than minLength
   */

   public  FishableI_a(String name, String englishName, String scientificName,
         double maxLength, double minLength, double length, double weight, String[] diet, 
         String bodyColor, String finColor, String sex) {
         
         super(name, englishName, scientificName, maxLength, minLength, length, weight,
               diet, bodyColor, finColor, sex);
         
   }


   /** returns new FishableI_a of next level
   * When a fish reaches maxSize, it should transform to next
   * level Hawaiian type.
   * @return a new FishableI_a of the next level type, should replace caller
   */
   protected abstract FishableI_a levelUp();
}