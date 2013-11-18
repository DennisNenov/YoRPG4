//Hyunwoo Jeung
//pd 9
//HW25
//2013-11-14

/*=============================================
  class Character -- superclass for combatants in Ye Olde RPG
  =============================================*/

public abstract class Character {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~
    protected String[] inventory;
    protected String[] equipped;
    protected int [] position;
    protected String _name;
    protected int _hitPts;
    protected int _strength;
    protected int _defense;
    protected int _mana;
    protected double _attack;
    protected int _money;
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    /*=============================================
      default constructor
      pre:  instance vars are declared
      post: initializes instance vars.
      =============================================*/
    public Character() {
	_hitPts = 125;
	_strength = 100;
	_defense = 40;
	_attack = .4;
  position = new int[2];
  position[0] = 0;
  position[1] = 0;
    }


    // ~~~~~~~~~~~~~~ ACCESSORS ~~~~~~~~~~~~~~~~~
    public int getDefense() { return _defense; }

    public String getName() { return _name; }

    public int getRow() {return position[0];}
    public int getColumn(){return position[1];}

    public int getMoney(){return _money;}

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void setPosition (int row, int column)
    {
      position[0] = row;
      position[1] = column;
    }

    public Boolean spendMoney (int toSpend)
    {
      if (_money > toSpend)
      {
        _money -= toSpend;
        return true;
      }
      else
      {
        return false;
      }
    }
    
    public Boolean earnMoney (int toEarn)
    {
      if (toEarn < 0)
      {
        return false;
      }
      else
      {
        _money += toEarn;
        return true;
      }
    }
    public void randomPosition(Map mapToWorkWith)
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
      mapToWorkWith.plotPoint(position[0],position[1], "x");
    }
    
    /*=============================================
      boolean isAlive() -- tell whether I am alive
      post: returns boolean indicated alive or dead
      =============================================*/
    public boolean isAlive() {
	return _hitPts > 0;
    }


    /*=============================================
      int attack(Character) -- simulates attack on instance of class Monster
      pre:  Input not null
      post: Calculates damage to be inflicted, flooring at 0. 
            Calls opponent's lowerHP() method to inflict damage. 
	    Returns damage dealt.
      =============================================*/
    public abstract int attack( Character opponent );

    public abstract int heal( Character c );


    /*=============================================
      void lowerHP(int) -- lowers life by input value
      pre:  Input >= 0
      post: Life instance var is lowered by input ammount.
      =============================================*/
    public void lowerHP( int damageInflicted ) {
	_hitPts = _hitPts - damageInflicted;
    }

    public void addHP(int h) {
	_hitPts = _hitPts + h;
    }

    public void normalize(){
	_defense = 40;
	_attack = 0.4;
    }

    public void specialize(){
	_defense = 20;
	_attack = 0.75;
    }

    /*
    public static String about(){
	return getName();
    }
    */

}//end class Character
