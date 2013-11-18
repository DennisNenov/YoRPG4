//written by Dennis

public abstract class Building
{
	private int[] position;
	//constructor creates the building and plots it on the map
	public Building( Map mapToWorkWith )
	{
		int numRows = mapToWorkWith.getNumRow();
		int numColumns = mapToWorkWith.getNumColumn();
		position = new int[2];
		position[0] = (int) (Math.random() * numRows);
		position[1] = (int) (Math.random() * numColumns);
		while (mapToWorkWith.getMap()[position[0]][position[1]] != " ")
		{
			position[0] = (int) (Math.random() * numRows);
			position[1] = (int) (Math.random() * numColumns);
		}
		mapToWorkWith.plotPoint(position[0],position[1], "+");

	}
	public int getRow()
	{
		return position[0];
	}
	public int getColumn()
	{
		return position[1];
	}
	//this abstract method is implemented in each building subclass, and it's how each building and the charatcer interact
	public abstract Boolean buildingOperation(Character userCharacter);
}