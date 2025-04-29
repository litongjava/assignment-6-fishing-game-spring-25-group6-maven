import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
* JUnit test for Oama Fishable I'a subclass.
* @author Lisa Miller
* @since 3/22/25
*/
public class OamaTest {
   /** constant reproductive mode for this Ia. */
   static final String REPRODUCTIVE_MODE = "serial";
   /** constant array of what the fish eats. */
   static final String[] DIET_ITEMS = {"worms", "mollusks", "invertebrates"};
   /** constant maximum length for this Ia. */
   static final double MAX_LENGTH = 7.0;
   /** constant minimum length for this Ia. 
   */
   static final double MIN_LENGTH = 0.01;


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
  /************* Fishable methods tests. ****************************/
  /****************** Check for Fishable Implementation. ***********/
   @Test public void isFishableTest() {
      Oama a = new Oama();
      Assert.assertTrue(a.getName() + " is not Fishable as required", (a instanceof Fishable));
   }
  //*********** Not yet completed ************
  /** test isLegalSize. */
   @Test public void isLegalSizeTest() {
      Oama a = new Oama();
      Assert.assertEquals("This fish has no minimum size", true, a.isLegalSize());
   }
  /** test isBait. */
   @Test public void isBaitTest() {
      Oama a = new Oama();
      Assert.assertEquals("This fish is used for bait", true, a.isBait());
   }
  
  /** test isGameFish method. */
   @Test public void isGamefishTest() {
      Oama a = new Oama();
   }
  // test getCatchMethods
 /** test getCatchMethods method. */
   @Test public void getCatchMethodsTest() {
      Oama a = new Oama();
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
