import java.io.*;
import java.util.*;

public class YoRPG
{
	//instance vars
	private Character user;
	private Character monster;
	private Map worldMap;
	private boolean gameOver;
	private Building[] buildingList;

    private int difficulty;


    private InputStreamReader isr;
    private BufferedReader in;

    // constructor
    public YoRPG() 
    {
		gameOver = false;
		isr = new InputStreamReader( System.in );
		in = new BufferedReader( isr );
		newGame();
    }

    // creates a new game by creating a new map and getting user input
    public void newGame() 
    {

		String s;
		String name = "";

		// choose difficulty
		s = "Welcome to Ye Olde RPG!\n";

		s += "\nChoose your difficulty: \n";
		s += "\t1: Easy\n";
		s += "\t2: Not so easy\n";
		s += "\t3: Beowulf hath nothing on me. Bring it on.\n";
		s += "Selection: ";
		System.out.print( s );

		try {
		    difficulty = Integer.parseInt( in.readLine() );
		}
		catch ( IOException e ) { }
		s = "";
		int userInput = 0;
		//choose character you want to be
		s += "\nChoose your character: \n";
		s += "\t1: Warrior\n";
		s += "\t2: Mage\n";
		s += "\t3: Priest.\n";
		s += "\t4: Archer.\n";
		s += "\t5: Rogue.\n";
		s += "Selection: ";
		System.out.print( s );

		try {
		    userInput = Integer.parseInt( in.readLine() );
		}
		catch ( IOException e ) { }

		s = "Intrepid adventurer, what doth thy call thyself? (State your name): ";
		System.out.print( s );

		try {
		    name = in.readLine();
		}
		catch ( IOException e ) { }

		//create the user character

		if (userInput == 1)
		{
			user = new Warrior(name);
		}
		else if (userInput == 2)
		{
			user = new Mage(name);
		}
		else if (userInput == 3)
		{
			user = new Priest(name);
		}
		else if (userInput == 4)
		{
			user = new Archer(name);
		}
		else if (userInput == 5)
		{
			user = new Rogue(name);
		}

		//create the map

		worldMap = new Map(10,10);
		buildingList = new Building[3];
		buildingList[0] = new Store(worldMap);
		buildingList[1] = new Store(worldMap);
		buildingList[2] = new Store(worldMap);
		user.randomPosition(worldMap);


    }

    /*=============================================
      boolean playTurn -- simulates a round of combat
      pre:  Warrior user has been initialized
      post: Returns true if player wins (monster dies).
            Returns false if monster wins (player dies).
      =============================================*/
    public boolean playTurn() {

	int i = 1;
	int direction = 0;
	int d1, d2;
	//draw the map, and ask the user to move
	try {
			System.out.println("");
			worldMap.drawMap();
			System.out.println("");
		    System.out.println( "Where do you want to move? You can't pass through fences." );
		    System.out.println( "\t1: Up.\n\t2: Down.\n\t3: Right.\n\t4. Left.\n\t5. Don't move.");
		    direction = Integer.parseInt( in.readLine() );
		}
	catch ( IOException e ) { }
	//move the user
	if (direction == 1 )
	{
		worldMap.moveUser((user.getRow()-1),user.getColumn(), user);
	}
	if (direction == 2 )
	{
		worldMap.moveUser((user.getRow()+1),user.getColumn(), user);
	}
	if (direction == 3 )
	{
		worldMap.moveUser((user.getRow()),(user.getColumn()+1), user);
	}
	if (direction == 4 )
	{
		worldMap.moveUser((user.getRow()),(user.getColumn()-1), user);
	}
	//if the user tries to move on top of a fence, he just doesn't move
	System.out.println("You moved, but only if you didn't hit a fence.");
	worldMap.drawMap();

	System.out.println("");

	//check if the user is in a building, if he is the flag will be set true
	boolean buildingFlag = false;

	for (Building someBuilding : buildingList )
	{
		if ((someBuilding.getRow() == user.getRow()) && (someBuilding.getColumn() == user.getColumn()))
		{
			buildingFlag = true;
			//have the building and the user interact
			while(someBuilding.buildingOperation(user)) {}
		}
	}
	//if the user is in a building he can't encounter a monster or other events
	if (buildingFlag == true)
		return true;

	else if ( Math.random() >= ( difficulty / 3.0 ) )
	    System.out.println( "Nothing to see here. Move along!" );

	else {
	    System.out.println( "Lo, yonder a villain approacheth!" );

	    int randomNum = (int) (Math.random() * 4);
	    if (randomNum == 1)
	    {
	    	monster = new Monster();
	    }
	    if (randomNum == 2)
	    {
	    	monster = new Goblin();
	    }
	    if (randomNum == 3)
	    {
	    	monster = new VillainWizard();
	    }

	    while( monster.isAlive() && user.isAlive() ) {

		// Give user the option of using a special attack:
		// If you land a hit, you incur greater damage,
		// ...but if you get hit, you take more damage.
		try {
		    System.out.println( "Do you feel lucky?" );
		    System.out.println( "\t1: Nay.\n\t2: Aye!" );
		    i = Integer.parseInt( in.readLine() );
		}
		catch ( IOException e ) { }

		if ( i == 2 )
		    user.specialize();
		else
		    user.normalize();

		d1 = user.attack( monster );
		d2 = monster.attack( user );

		System.out.println( user.getName() + " dealt " + d1 +
				    " points of damage.");

		System.out.println( "Ye Olde Villain hit back for " + d2 +
				    " points of damage.");
	    }//end while

	    //option 1: you & the villain perish
	    if ( !monster.isAlive() && !user.isAlive() ) {
		System.out.println( "'Twas an epic battle, to be sure... " + 
				    "You cut ye olde villain down, but " +
				    "with its dying breath ye olde villain. " +
				    "laid a fatal blow upon thy skull." );
		return false;
	    }
	    //option 2: you slay the villain
	    else if ( !monster.isAlive() ) {
		System.out.println( "HuzzaaH! Ye olde villain hath been slain!" );
		int moneyGained = (int) (Math.random() * 20);
		System.out.println("You pick up " + moneyGained + " dollars!");
		user.earnMoney(moneyGained);
		return true;
	    }
	    //option 3: the villain slays you
	    else if ( !user.isAlive() ) {
		System.out.println( "Ye olde self hath expired. You got dead." );
		return false;
	    }
	}//end else

	return true;
    }//end playTurn()
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    public static void main( String[] args ) {

	YoRPG game = new YoRPG();
	//the game can go on for as long as the user can survive
	while( true ) {
	    if ( !game.playTurn() )
		break;
	    System.out.println();
	}

	System.out.println( "Thy game doth be over." );

    }//end main
}