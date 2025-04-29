import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
* JUnit test for Ama_ama Fishable I'a subclass.
* @author Lisa Miller
* @since 3/22/25
*/

public class Ama_amaTest {
   /** constant reproductive mode for this Ia. */
   static final String REPRODUCTIVE_MODE = "serial";
   /** constant array of what the fish eats. */
   static final String[] DIET_ITEMS = {"zooplankton", "algae", "detritus",  "invertebrates"};
   /** constant maximum length for this Ia. */
   static final double MAX_LENGTH = 12.0;
   /** constant minimum length for this Ia. 
   */
   static final double MIN_LENGTH = 8.0;


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

  /************* Fishable methods tests. ****************************/
    /****************** Check for Fishable Implementation. ***********/
   @Test public void isFishableTest() {
      Ama_ama a = new Ama_ama();
      Assert.assertTrue(a.getName() + " is not Fishable as required.", (a instanceof Fishable));
   }
  /** test isLegalSize. */
   @Test public void isLegalSizeTest() {
      Ama_ama a = new Ama_ama();
      double len = a.getLength();
      if (len < 11) {
         Assert.assertEquals("This fish is " + len + " it must be >= 11 to be legal size", 
               false, a.isLegalSize());
      } else {
         Assert.assertEquals("This fish is " + len + " which is >= 11, the legal size", 
               true, a.isLegalSize());
      }
   }
  /** test isBait. */
   @Test public void isBaitTest() {
      Ama_ama a = new Ama_ama();
      Assert.assertEquals("This fish is never used for bait", false, a.isBait());
   }
  
  /** test isGameFish method. */
   @Test public void isGamefishTest() {
      Ama_ama a = new Ama_ama();
      if (a.getLength() >= 11) {
         Assert.assertEquals("This fish is allowed for eating", true, a.isGamefish());
      } else {
         Assert.assertEquals("This fish is too small for eating", false, a.isGamefish());
      }
   }
  // test getCatchMethods
  /** test getCatchMethods method. */
   @Test public void getCatchMethodsTest() {
      Ama_ama a = new Ama_ama();
      String[] validCatchMeth = {"net", "pole"};
      String[] fishCatchMeth = a.getCatchMethods();
      boolean match = false;
      for (int i = 0; i < validCatchMeth.length; i++) {
         for (int j = 0; j < fishCatchMeth.length; j++) {
            if (validCatchMeth[i].equalsIgnoreCase(fishCatchMeth[j])) {
               match = true;
            }
         
         }
         Assert.assertTrue(a.getName() + "is missing " + validCatchMeth[i] 
               + "as a catch method", match);
         match = false;
      }
      
   }
    
} ///close test clas
