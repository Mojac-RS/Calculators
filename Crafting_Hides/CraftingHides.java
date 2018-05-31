/**
 *@author Michael Palme
 *
 * Calculator to determine how much gold we lose to get 99 Crafting.
 * Date: May 2018
 *
 **/

import java.lang.*;                  // For advanced Math usage
import java.text.DecimalFormat;      // Commas in output

public class CraftingHides
{
   private int xpMaxOne = 13034431,  // EXP needed for level 99
               xpMaxTwo = 200000000; // EXP needed for level 127
   
   private int xpStart,              // Our EXP
               xpEnd,                // To get EXP
               buyHidePrice,         // Price we pay for each Hide
               costTan,              // Price we pay to tan each Hide
               costNatureRune,       // Price we pay for each Nature Rune
               alchPrice;            // Gold we get back from High Level Alchemy
          
   private double costThread,        // Price we pay for each Thread
                  xpRate;            // EXP earned in the craft
   
   private DecimalFormat commaFormatter;   // format commas in
   
   /**
    * Constructor # 1 for CraftingHides class.
    * No defined parameters for constructor.
    */
   public CraftingHides()
   {
      commaFormatter = new DecimalFormat("#,###");
      
      int[][] colorDHides = {{1545, 4680, 186}, //Green - Price, Alch, EXP
                             {1878, 5616, 210}, //Blue  - Price, Alch, EXP
                             {2421, 6738, 234}, //Red   - Price, Alch, EXP
                             {3096, 8088, 258}};//Black - Price, Alch, EXP
      
      xpStart = 1810117;
      //xpEnd = 3972294; // Lv. 87
      xpEnd = xpMaxOne;
      costTan = 20;
      costThread = 0.25;
      costNatureRune = 200;
      xpDifference();
      
      for(int whichColor = 0; whichColor < colorDHides.length; whichColor++)
      {
         setValues(getStrToSend(whichColor),   colorDHides[whichColor][0],
                   colorDHides[whichColor][1], colorDHides[whichColor][2]);
         findCostOfCraft();
         
      }//End for loop - Color look up
      
   }//End Constructor #1
   
   
   
   
   
   /**
    * Method setValues initialized the class variables with data 
    * that gets passed into it. 
    */
   public void setValues(String name, int dHidePrice, int alchemy, double rate)
   {
      System.out.println("=============== " + name + " Dragon Hide Bodies ===============");
      buyHidePrice = dHidePrice;  // Price we pay for each Hide
      alchPrice = alchemy;   // Gold we get back from High Level Alchemy
      xpRate = rate; // EXP earned in the craft
      
   }//End setValues
   
   
   /**
    * Method getStrToSend takes an int, and returns one of five strings.
    */
   public String getStrToSend(int whichColor)
   {
      switch(whichColor) {
         case 0:  
            return "Green";
         case 1:  
            return "Blue";
         case 2:  
            return "Red";
         case 3:  
            return "Black";
         default: 
            return "N/A";
      }
         
   }//End getStrToSend
   
   
   /**
    * Method xpDifference shows the starting, ending, and difference of the 
    * experience values set.
    */
   public void xpDifference()
   {
      int xpDiff = xpEnd - xpStart;
      System.out.println("Experience: " + commaFormatter.format(xpStart) + " --> " 
                         + commaFormatter.format(xpEnd) + ".\nThis will be [" + 
                         commaFormatter.format(xpDiff) + "] experience.\n");
   }//End xpDifference
   
   
   /**
    * Method findCostOfCraft does all the calculations and outputs the results. 
    */
   public void findCostOfCraft()
   {
      double xpDiff,
             leatherPrice,
             craftCost,
             numToCraft,
             totalCraftCost,
             profit;
      
      xpDiff = xpEnd - xpStart;
      leatherPrice = buyHidePrice + costTan;
      craftCost = ( leatherPrice * 3) + costThread;
      numToCraft = (int) Math.ceil(xpDiff / xpRate);
      totalCraftCost = 0 - (numToCraft * craftCost);
      profit = totalCraftCost + (numToCraft * (alchPrice - costNatureRune));
      
      // How much we pay for each craft, and total profit
      System.out.println("Crafting costs: [" + 
                         commaFormatter.format( craftCost ) + 
                         "] gold for each craft.\nCrafting needed: [" + 
                         commaFormatter.format(numToCraft) +
                         "] crafts. \nThis will be [" + 
                         commaFormatter.format( totalCraftCost ) + 
                         "] gold.\nTotal Profit: [" + 
                         commaFormatter.format( profit ) + 
                         "] gold after alching all crafts. \n");
   }//End findCostOfCraft
   
}//End CraftingHides