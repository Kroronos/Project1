/*
 * Name: Jesse Martinez
 * Class Section: 6909
 * Instructor: Kyla McMullen
 * Due Date: September 20, 2016
 * Brief Description: Monitors the location, exist time, and appear time of a monster. 
 * Monitors a player's walking speed, location, ID, and notice time. 
 * Based on these factors the program calculates the player's likelihood of capturing a monster. 
 */
import java.util.Scanner;
public class CaptureCalculator {
    public static void main(String[] args) {
        System.out.println("Hello and welcome to the Monster Capture Possibility Calculator.");
        
        Scanner input = new Scanner (System.in);
        //Lines 8 to 33 define and request variables related to console inputs
        System.out.println("Please enter the latitude of the monster (1-10):");
        double monsterLatitude = input.nextDouble();
        
        System.out.println("Please enter the longitude of the monster (1-10):");
        double monsterLongitude = input.nextDouble();
        
        System.out.println("Please enter the time of the monster appear (1-1440):");
        int monsterAppear = input.nextInt();
        
        System.out.println("Please enter the possible time of the monster will exist (10-59):");
        int monsterExist = input.nextInt();
    
        System.out.println("Please enter the player's ID (8 digits):");
        int playerID = input.nextInt();
        
        System.out.println("Please enter the time of the player noticing monster (1-1440 and greater than the time the monster appears):");
        int playerNotice = input.nextInt();
        
        System.out.println("Please enter the latitude of the player (1-10):");
        double playerLatitude = input.nextDouble();
        
        System.out.println("Please enter the longitude of the player (1-10):");
        double playerLongitude = input.nextDouble();
        
        System.out.println("Please enter the player's walking speed (10-200):");
        int playerSpeed = input.nextInt();
        
        String listType;
        //listType used to define output regarding player ID
        boolean playerType; 
        //playerType used to define capture chance conditions
        if (playerID % 100 <= 49) {
            listType = "lucky list";
            playerType = true;
        }
        else {
             listType = "normal list";
             playerType = false; 
         }
        
        //Declare variable total distance, converts distances to m, then calculates total distance using converted variables
        double totalDistance;
        double mplayerLongitude = playerLongitude * 1000;
        double mplayerLatitude = playerLatitude * 1000;
        double mmonsterLongitude = monsterLongitude * 1000;
        double mmonsterLatitude = monsterLatitude * 1000;

        totalDistance = Math.sqrt((Math.pow((mplayerLongitude - mmonsterLongitude),2) + (Math.pow((mplayerLatitude - mmonsterLatitude),2))));
        
        //Declare variable arrival time and calculate it. 
        double arrivalTime;
        arrivalTime = (playerNotice + (totalDistance/playerSpeed));
        
        
        //Defines estimated monster disappear time for console and for capture rate calculation
        int monsterDisappear;
        monsterDisappear = monsterAppear + monsterExist;
        
        //Calculations for capture rate using if/else selectors based on boolean playerID to set parameters
        String captureProbability = null;
        //Set catch rate calculation as double 
        double catchPercent = ((arrivalTime - monsterDisappear)/monsterExist) * 100;
            
      //Universal, "definitely" catch rate calculation  
        if (arrivalTime <= monsterDisappear)
                 captureProbability = "definitely";
            //Catch rate calculation for lucky list 
            else if (playerType == true) {
                
                if ( catchPercent <=10)
                 captureProbability = "highly likely";
                
                else if ((catchPercent <=30) && (catchPercent >10))
                 captureProbability = "likely";
                
                else if ((catchPercent <=40) && (catchPercent >30))
                    captureProbability = "unsure";
                
                else if ((catchPercent <=50) && (catchPercent >40))
                 captureProbability = "unlikely";
               
                else if (catchPercent >50)
                 captureProbability = "highly unlikely";
            }
            //Catch rate calculation for normal list
            else if (playerType == false){
                
                if ( catchPercent <=5)
                    captureProbability = "highly likely";
                
                else if ((catchPercent <=20) && (catchPercent >5))
                    captureProbability = "likely";
                
                else if ((catchPercent <=35) && (catchPercent >20))
                    captureProbability = "unsure";
                
                else if ((catchPercent <=40) && (catchPercent >35))
                    captureProbability = "unlikely";
                
                else if (catchPercent >40)
                    captureProbability = "highly unlikely";
            }
                   
        
        //Prints final output to console    
        //Must round arrivalTime and totalDistance to nearest tenth 
        System.out.println
            ("Player " + playerID + " who is on the " + listType + ",");
        System.out.println
            ("noticed the monster at time " + playerNotice + ",");
        System.out.println
        ("is " + Math.round(totalDistance*10)/10.0 + " m away from the monster,");
        System.out.println       
        ("and will arrive at time " + Math.round(arrivalTime*10)/10.0 + ".");
        System.out.println
        ("The monster's disappear time is about " + monsterDisappear + "."); 
        System.out.println
        ("So the player will capture this monster with " + captureProbability + " possibility.");
        
    }

}