import cs1.Keyboard;
//Written by Dennis; the store is a type of building
public class Store extends Building
{
	public Store(Map mapToWorkWith )
	{
		super(mapToWorkWith);
	}
	//once false is returned the user has left the building
	public Boolean buildingOperation(Character userCharacter)
	{
		while (true)
		{
			System.out.println("\nWelcome to the Store!");
			System.out.println("You have " + userCharacter.getMoney() + " dollars to spend.");
			System.out.println("1. Heal 10 points for 50 dollars.");
			System.out.println("2. Leave.");
			int userInput = Keyboard.readInt();
			if (userInput == 1)
			{
				if (userCharacter.spendMoney(50))
				{
					userCharacter.addHP(10);
					System.out.println("\nYou spend 50 dollars, and gain 10 HP.");
				}
				else
				{
					System.out.println("\nYou don't have enough to buy that.");
					return false;
				}
			}
			else
			{
				System.out.println("\nYou leave the store.");
				return false;
			}
		}
	}
}