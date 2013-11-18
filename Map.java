//written by Dennis Nenov

public class Map
{
	//instance vars
	private String[][] _map;
	private int _numRows, _numColumns;
	private boolean storeFlag;

	//default constructor
	public Map ()
	{
		_map = new String[10][10];
		_numRows = _map.length;
		_numColumns = _map[0].length;
		storeFlag = false;
		for (int i = 0; i < _numRows; i++)
		{
			for (int j = 0; j < _numColumns; j++)
			{
				plotPoint(i,j," ");
			}
		}		
		initializeMap();
	}

	//non-default constructor
	public Map(int rows, int columns)
	{
		_map = new String[rows][columns];
		_numRows = _map.length;
		_numColumns = _map[0].length;
		storeFlag = false;
		for (int i = 0; i < _numRows; i++)
		{
			for (int j = 0; j < _numColumns; j++)
			{
				plotPoint(i,j," ");
			}
		}		
		initializeMap();
	}

	//accessor and mutator methods
	public String[][] getMap()
	{
		return _map;
	}

	public int getNumRow()
	{
		return _numRows;
	}
	public int getNumColumn()
	{
		return _numColumns;
	}

	//function used to draw on the map
	public void plotPoint (int i, int j, String toPlot)
	{
		_map[i][j] = toPlot;
	}

	//used to create the fences that map uses
	public void initializeMap ()
	{
		for (int i = 0; i < _numRows; i++)
		{
			plotPoint(i,(_numColumns - 1),"=");
			plotPoint(i,0,"=");
		}
		for (int i = 0; i < _numColumns; i++)
		{
			
			plotPoint(0,i,"=");
			plotPoint((_numRows - 1),i,"=");
		}	
	}
	//function that uses the plot function to move the user around the grid. user won't move if they hit a fence. user won't be visible if they hit a store
	public void moveUser (int row, int column, Character user)
	{
		if (_map[row][column] == " ")
		{
			if (storeFlag == false)
			{
				plotPoint(user.getRow(),user.getColumn()," ");
			}
			if (storeFlag == true)
			{
				plotPoint(user.getRow(),user.getColumn(),"+");
				storeFlag = false;
			}
			plotPoint(row,column,"x");
			user.setPosition(row, column);
		}
		if (_map[row][column] == "+")
		{
			storeFlag = true;
			plotPoint(user.getRow(),user.getColumn()," ");
			user.setPosition(row, column);
		}
		else
		{

		}
	}
	//prints the map to the screen with a key
	public void drawMap ()
	{
		for (int i = 0; i < _numRows; i++)
		{
			for (int j = 0; j < _numColumns; j++)
			{
				System.out.print(_map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("Key: '=' is a fence; '+' is a building; 'x' is you ");
	}
}