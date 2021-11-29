/*
Perfect Purple Petunias (John Gupta-She, Emily Ortiz, Lauren Lee)
APCS
L01 -- An Adventurer is You!
2021-11-24
time spent: 1.5 hours

OUR DRIVER MODS
Added new subclasses for Protagonist and Monster.
Allows user to select class of Protagonist.
"random" monsters.
*/
 import java.io.*; // * means to export everthing
 import java.util.*;

 public class YoRPG {

   // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~

   //change this constant to set number of encounters in a game
   public final static int MAX_ENCOUNTERS = 5;

   //each round, a Protagonist and a Monster will be instantiated...
   private Protagonist pat;
   private Monster smaug;

   private int moveCount;
   private boolean gameOver;
   private int difficulty;

   private InputStreamReader isr;
   private BufferedReader in;

   // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


   // ~~~~~~~~~~ DEFAULT CONSTRUCTOR ~~~~~~~~~~~
   public YoRPG() {
     moveCount = 0;
     gameOver = false;
     isr = new InputStreamReader( System.in );
     in = new BufferedReader( isr );
     newGame();
   }
   // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



   // ~~~~~~~~~~~~~~ METHODS ~~~~~~~~~~~~~~~~~~~

   /*=============================================
     void newGame() -- gathers info to begin a new game
     pre:
     post: according to user input, modifies instance var for difficulty
     and instantiates a Protagonist
     =============================================*/
   public void newGame() {
     String s;
     String name = "";
     s = "~~~ Welcome to Ye Olde RPG! ~~~\n";

     s += "\nChoose your difficulty: \n";
     s += "\t1: Easy\n";
     s += "\t2: Not so easy\n";
     s += "\t3: Beowulf hath nothing on me. Bring it on.\n";
     s += "Selection: ";
     System.out.print( s );

     try {
 	    difficulty = Integer.parseInt( in.readLine() );
     }
     catch ( Exception e ) {
	    System.out.println("Thou hath invoked the creator's rage. Difficulty is 3.");
	    difficulty = 3;
     }

     s = "Intrepid protagonist, what doth thy call thyself? (State your name): ";
     System.out.print( s );

     try { //if try doesnt work, default to catch
 	    name = in.readLine();
     }
     catch ( IOException e ) { } //IOException returns null

     //choose subclass and instantiate the player's character
     s = "\nChoose your class: \n";
     s += "\t1: Mage\n";
     s += "\t2: Archer\n";
     s += "\t3: Knight\n";
     s += "Selection: ";
     System.out.print( s );

     try {
            int classNum = Integer.parseInt( in.readLine() );
 	    if (classNum == 1) {
        pat = new Mage( name );
        System.out.println("Class: Mage");
      }
      else if (classNum == 2) {
        pat = new Archer(name);
        System.out.println("Class: Archer");
      }
      else if (classNum == 3) {
        pat = new Knight(name);
        System.out.println("Class: Knight");
      }
     }
     catch ( Exception e ) {
	    System.out.println("Not a number, class set to mage.");
	    pat = new Mage();
     }

   }//end newGame()


   /*=============================================
     boolean playTurn -- simulates a round of combat
     pre:  Protagonist pat has been initialized
     post: Returns true if playiner wins (monster dies).
     Returns false if monster wins (player dies).
     =============================================*/
   public boolean playTurn() {
     int i = 1;
     int d1, d2;

     if ( Math.random() >= ( difficulty / 3.0 ) ) {
 	    System.out.println( "\nNothing to see here. Move along!" );
    }
     else {
 	    System.out.println( "\nLo, yonder monster approacheth!" );
      double x = Math.random() * 3;
      if ( x < 1 ) {
        smaug = new Ogre();
        System.out.println( "\nOgre" );
      }
      else if ( x < 2 &&  x >= 1) {
        smaug = new Goblin();
        System.out.println( "\nGoblin" );
      }
      else if (x >= 2) {
        smaug = new Lizard();
        System.out.println( "\nLizard" );
      }

 	    while( smaug.isAlive() && pat.isAlive() ) {

         // Give user the option of using a special attack:
         // If you land a hit, you incur greater damage,
         // ...but if you get hit, you take more damage.
         try {
           System.out.println( "\nDo you feel lucky?" );
           System.out.println( "\t1: Nay.\n\t2: Aye!" );
           i = Integer.parseInt( in.readLine() );
         }
         catch ( Exception e ) {
	   System.out.println( "Thou choseth incorrectly. Unlucky...");
	   i = 1;
	 }

         if ( i == 2 )
           pat.specialize();
         else
           pat.normalize();

         d1 = pat.attack( smaug );
         d2 = smaug.attack( pat );

         System.out.println( "\n" + pat.getName() + " dealt " + d1 +
                             " points of damage.");

         System.out.println( "\n" + "Ye Olde Monster smacked " + pat.getName() +
                             " for " + d2 + " points of damage.");
 	    }//end while

 	    //option 1: you & the monster perish
 	    if ( !smaug.isAlive() && !pat.isAlive() ) {
         System.out.println( "'Twas an epic battle, to be sure... " +
                             "You cut ye olde monster down, but " +
                             "with its dying breath ye olde monster. " +
                             "laid a fatal blow upon thy skull." );
         return false;
 	    }
 	    //option 2: you slay the beast
 	    else if ( !smaug.isAlive() ) {
         System.out.println( "HuzzaaH! Ye olde monster hath been slain!" );
         return true;
 	    }
 	    //option 3: the beast slays you
 	    else if ( !pat.isAlive() ) {
         System.out.println( "Ye olde self hath expired. You got dead." );
         return false;
 	    }
     }//end else

     return true;
   }//end playTurn()
   // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

   public static void main( String[] args ) {
     //As usual, move the begin-comment bar down as you progressively
     //test each new bit of functionality...

     /*================================================ */
     //loading...
     YoRPG game = new YoRPG();

     int encounters = 0;

     while( encounters < MAX_ENCOUNTERS ) {
     if ( !game.playTurn() )
     break;
     encounters++;
     System.out.println();
     }

     System.out.println( "Thy game doth be over." );
 	  /*================================================*/
   }//end main

 }//end class YoRPG
