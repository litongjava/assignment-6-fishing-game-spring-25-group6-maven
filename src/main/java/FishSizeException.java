/**
* Exception for I_a class length errors.
* @author Lisa Miller
* @since 2/11/11
*/
public class FishSizeException extends RuntimeException {
  
  /**
  * Constructor. 
  * @param newMessage The error message to be sent.
  */
   public FishSizeException(String newMessage) {
      super(newMessage);
   }
}
