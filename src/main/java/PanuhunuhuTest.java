import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
* JUnit test for Panuhunuhu Fishable I'a subclass.
* @author Lisa Miller
* @since 3/22/25
*/

public class PanuhunuhuTest {
   /** constant reproductive mode for this Ia. */
   static final String REPRODUCTIVE_MODE = "protogynous";
   /** constant array of what the fish eats. */
   static final String[] DIET_ITEMS = {"algae"};
   /** constant maximum length for this Ia. */
   static final double MAX_LENGTH = 12.0;
   /** constant minimum length for this Ia. 
   */
   static final double MIN_LENGTH = 4.0;


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

  
  /************* Fishable methods tests. ****************************/
  /****************** Check for Fishable Implementation. ***********/
   @Test public void isFishableTest() {
      Panuhunuhu a = new Panuhunuhu();
      Assert.assertTrue(a.getName() + " is not Fishable as required", (a instanceof Fishable));
   }
  /** test isLegalSize. */
   @Test public void isLegalSizeTest() {
      Panuhunuhu a = new Panuhunuhu();
      Assert.assertEquals("This fish is never legal size", a.isLegalSize(), false);
   }
  /** test isBait. */
   @Test public void isBaitTest() {
      Panuhunuhu a = new Panuhunuhu();
      Assert.assertEquals("This fish is never used for bait", false, a.isBait());
   }
  
  /** test isGameFish method. */
   @Test public void isGamefishTest() {
      Panuhunuhu a = new Panuhunuhu();
      Assert.assertEquals("This fish is never used for eating", false, a.isGamefish());
   }

  /** test getCatchMethods method. */
   @Test public void getCatchMethodsTest() {
      Panuhunuhu a = new Panuhunuhu();
      String[] validCatchMeth = {"throw net", "spear", "pole"};
      String[] fishCatchMeth = a.getCatchMethods();
      boolean match = false;
      for (int i = 0; i < validCatchMeth.length; i++) {
         for (int j = 0; j < fishCatchMeth.length; j++) {
            if (validCatchMeth[i].equalsIgnoreCase(fishCatchMeth[j])) {
               match = true;
            }
         
         }
         Assert.assertTrue("This fish is missing " + validCatchMeth[i] + "as a catch method", match);
         match = false;
      }
      
   }
  
  
} ///close test clas
