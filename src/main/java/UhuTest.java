import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
* JUnit test for Uhu Fishable I'a subclass.
* @author Lisa Miller
* @since 3/22/25
*/

public class UhuTest {
   /** constant reproductive mode for this Ia. */
   static final String REPRODUCTIVE_MODE = "protogynous";
   /** constant array of what the fish eats. */
   static final String[] DIET_ITEMS = {"algae"};
   /** constant maximum length for this Ia. */
   static final double MAX_LENGTH = 30.0;
   /** constant minimum length for this Ia. 
   */
   static final double MIN_LENGTH = 12.0;


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
  /************* Fishable methods tests. ****************************/
  //****************** Check for Fishable Implementation. ***********/
   @Test public void isFishableTest() {
      Uhu a = new Uhu();
      Assert.assertTrue(a.getName() + " is not Fishable as required", (a instanceof Fishable));
   }
  //*********** Not yet completed ************
  /** test isLegalSize. */
   @Test public void isLegalSizeTest() {
      Uhu a = new Uhu();
      double len = a.getLength();
      if (len < 12) {
         Assert.assertEquals("isLegalSize is incorrect, fish is too small with length:  " + len
               + " it must be >= 12 to be legal.", a.isLegalSize(), false);
      } else {
         Assert.assertEquals("isLegalSize is incorrect, fish is legal size with length:  " + len
               + " it legal at 12 inches.", a.isLegalSize(), true);
      }
   }
  /** test isBait. */
   @Test public void isBaitTest() {
      Uhu a = new Uhu();
      Assert.assertEquals("This fish is never used for bait", a.isBait(), false);
   }
  
  /** test isGameFish method. */
   @Test public void isGamefishTest() {
      Uhu a = new Uhu();
      Assert.assertEquals("This fish is used for eating", true, a.isGamefish());
   }
  // test getCatchMethods
  /** test getCatchMethods method. */
   @Test public void getCatchMethodsTest() {
      Uhu a = new Uhu();
      String[] validCatchMeth = {"throw net", "spear", "pole"};
      String[] fishCatchMeth = a.getCatchMethods();
      boolean match = false;
      for (int i = 0; i < validCatchMeth.length; i++) {
         for (int j = 0; j < fishCatchMeth.length; j++) {
            if (validCatchMeth[i].equalsIgnoreCase(fishCatchMeth[j])) {
               match = true;
            }
         
         }
         Assert.assertTrue("This fish is missing " + validCatchMeth[i] 
               + "as a catch method", match);
         match = false;
      }
      
   }
   
} ///close test clas
