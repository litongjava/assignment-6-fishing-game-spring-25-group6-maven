import java.util.ArrayList;
import java.util.Random;
/**
* Driver for Fishable I_a testing.
* @author Lisa Miller
* @since 4/24/22
*/
public class FishingDriver {
   /** Maximum pond capacity for random hooking. */
   static final int POND_CAPACITY = 40;
   /** main method.
   * @param args not used.
   */
   public static void main(String[] args) {
      //ArrayList to simulate a fishpond/lokoi'a
      ArrayList<FishableI_a> lokoi_a = new ArrayList<>();
      boolean debug = true; //for printing
            
      lokoi_a = FishingDriver.fillPond();
      if (debug) {
         System.out.println(lokoi_a);
      }
      //Let fish in pond grow some
      //eat and grow for 24 months
      FishingDriver.growFish(lokoi_a);
   
      //now lets open the pond for fishing!
      FishingDriver.lawai_a(lokoi_a);
   
   } //close main
    
   /**
   * Adds 40 baby I_a to ArrayList.
   * This should match POND_CAPACITY.
   * @return ArrayList with small I_a in it.
   */
   public static ArrayList<FishableI_a> fillPond() {
      
      ArrayList<FishableI_a> al = new ArrayList<>();
      //make 40 fish in the pond
      //10 of baby of each species
      
      for (int i = 0; i < 10; i++) {
         al.add(new MoiLi_i());
      }
//       for (int i = 0; i < 10; i++) {
//          al.add(new Oama());
//       }
//       for (int i = 0; i < 10; i++) {
//          al.add(new Pua_ama());
//       }
      for (int i = 0; i < 10; i++) {
         al.add(new Ohua());
      }
      
      //for testing empty locations
    //   for (int i = 0; i < 30; i++) {
   //          al.remove(i);
   //       }
      return al; 
   
   } //fillPond method
   
   
   /**
   * Runs arraylist of I_a through 24 eating/growing cycles.
   * @param al the list of fish.
   */
   public static void growFish(ArrayList<FishableI_a> al) {
      FishableI_a ia;
      boolean debug = true; //turn printing on and off
      for (int m = 0; m < 24; m++) {
      //all fish in the pond
         for (int i = 0; i < al.size(); i++) {
         //loop over array 
            if (debug) {
               System.out.println("==========================");
               System.out.println("Feeding  the fish" + i);
               System.out.println("==========================\n");
            }
            ia = al.get(i);
            if (debug) {
               System.out.println(ia);
            }
            try { //must check for need to levelUp
            //use EnglishName because doesn't change with size
               if (ia.getEnglishName().equals("Striped Mullet")) {
                  ia.eat("algae");
               } else if (ia.getEnglishName().equals("Goatfish") 
                     || ia.getEnglishName().equals("Yellowfin Goatfish") 
                     || ia.getEnglishName().equals("Square-spot Goatfish"))  {
                  ia.eat("worms");
               } else if (ia.getEnglishName().equals("Parrotfish")) {
                  ia.eat("algae");
                  
               } else if (ia.getEnglishName().equals("Six-fingered threadfin")) {
                  ia.eat("crustaceans");
                  
               }
               if (debug) {
                  System.out.println("****After eat and grow: " + ia.getName() 
                        + ": " + ia.getLength() + "\n");
               }
            } catch (FishSizeException fe) {
               //need to level up
               
               ia = ia.levelUp();
               if (debug) {
                  System.out.println(fe.getMessage());
                  System.out.println("**** After levelUp: " + ia + "\n");
               }
            }
            al.set(i, ia);
         }
      } // close m loop
      
   
   
   } //close growFish method
   
   /**
   * simulate fishing/lawai'a.
   * @param fishPond arrayList of fish to be caught
   */
   public static void lawai_a(ArrayList<FishableI_a> fishPond) {
      Random randGen = new Random();
      FishableI_a ia;
      int chosenFish = 0;
      boolean isCaught = false;
      boolean isLegal = false;
      
      chosenFish = randGen.nextInt(POND_CAPACITY);
      
      try {
         ia = fishPond.get(chosenFish);
      
         System.out.println("You have hooked a fish!");
      //randomly caught or not
         isCaught = randGen.nextBoolean();
         if (isCaught) {
            System.out.println("You have caught a fish!");
            System.out.println(ia);
            System.out.println("You have kept your fish");
            fishPond.remove(chosenFish); //take fish out of the pond
            if (ia.isLegalSize()) {
               System.out.println("Your fish is legal");
            } else {
               System.out.println("You kept an illegal fish!");
               System.out.println("You got a ticket and all of your fish were confiscated!");
            }
         } else {
            System.out.println("Your fish got away!");
         }
      } catch (IndexOutOfBoundsException ie) { //fish has been removed already
         System.out.println("You didn't hook anything.");
      }
   
   } //close lawai_a method
}