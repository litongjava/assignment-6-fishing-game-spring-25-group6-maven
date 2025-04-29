import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
* JUnit test for Anae Fishable I'a subclass.
* @author Lisa Miller
* @since 3/22/25
*/

public class AnaeTest {
   /** constant reproductive mode for this Ia. */
   static final String REPRODUCTIVE_MODE = "serial";
   /** constant array of what the fish eats. */
   static final String[] DIET_ITEMS = {"zooplankton", "algae", "detritus",  "invertebrates"};
   /** constant maximum length for this Ia. */
   static final double MAX_LENGTH = 27.0;
   /** constant minimum length for this Ia. 
   */
   static final double MIN_LENGTH = 12.0;


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
  
  /************* Fishable methods tests ****************************/
      /****************** Check for Fishable Implementation. ***********/
   @Test public void isFishableTest() {
      Anae a = new Anae();
      Assert.assertTrue(a.getName() + " is not Fishable as required.", (a instanceof Fishable));
   }
  /** test isLegalSize. */
   @Test public void isLegalSizeTest() {
      Anae a = new Anae();
      Assert.assertEquals("This fish is always legal size >= 11", true, a.isLegalSize());
   }
  /** test isBait. */
   @Test public void isBaitTest() {
      Anae a = new Anae();
      Assert.assertEquals("This fish is never used for bait", false, a.isBait());
   }
  
  /** test isGameFish method. */
   @Test public void isGamefishTest() {
      Anae a = new Anae();
      System.out.println(a);
      Assert.assertEquals("This fish is used for eating", true, a.isGamefish());
   }
  // test getCatchMethods
  /** test getCatchMethods method. */
   @Test public void getCatchMethodsTest() {
      Anae a = new Anae();
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
