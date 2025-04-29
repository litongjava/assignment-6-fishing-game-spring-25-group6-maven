import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
* JUnit test for Weke_ula Fishable I'a subclass.
* @author Lisa Miller
* @since 3/22/25
*/

public class Weke_ulaTest {
   /** constant reproductive mode for this Ia. */
   static final String REPRODUCTIVE_MODE = "serial";
   /** constant array of what the fish eats. */
   static final String[] DIET_ITEMS = {"worms", "mollusks", "invertebrates"};
   /** constant maximum length for this Ia. */
   static final double MAX_LENGTH = 30.0;
   /** constant minimum length for this Ia. 
   */
   static final double MIN_LENGTH = 7.0;


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
  
  /************* Fishable methods tests. ****************************/
  /****************** Check for Fishable Implementation. ***********/
   @Test public void isFishableTest() {
      Weke_ula a = new Weke_ula();
      Assert.assertTrue(a.getName() + " is not Fishable as required", (a instanceof Fishable));
   }
  //*********** Not yet completed ************
  /** test isLegalSize. */
   @Test public void isLegalSizeTest() {
      Weke_ula a = new Weke_ula();
      Assert.assertEquals("This fish has no minimum size", true, a.isLegalSize());
   }
  /** test isBait. */
   @Test public void isBaitTest() {
      Weke_ula a = new Weke_ula();
      Assert.assertEquals("This fish is used for bait", true, a.isBait());
   }
  
  /** test isGameFish method. */
   @Test public void isGamefishTest() {
      Weke_ula a = new Weke_ula();
      Assert.assertEquals("This fish is used for eating", true, a.isGamefish());
   }
  // test getCatchMethods
  /** test getCatchMethods method. */
   @Test public void getCatchMethodsTest() {
      Weke_ula a = new Weke_ula();
      String[] validCatchMeth = {"net", "trap", "pole"};
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
