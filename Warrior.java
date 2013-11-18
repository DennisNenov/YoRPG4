//Hyunwoo Jeung
//pd 9
//HW25
//2013-11-14

/*=============================================
  class Warrior -- the protagonist of Ye Olde RPG
  =============================================*/

public class Warrior extends Character {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~
    // other inerited from superclass
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    /*=============================================
      default constructor
      pre:  instance vars are declared
      post: initializes instance vars.
      =============================================*/
    public Warrior() {
	_hitPts = 125;
	_strength = 100;
	_defense = 40;
	_attack = .4;
	_mana = 40;
    }


    /*=============================================
      overloaded constructor
      pre:  instance vars are declared
      post: initializes instance vars. _name is set to input String.
      =============================================*/
    public Warrior( String name ) {
	this();
	_name = name;
    }

    public int attack(Character opponent) {
	int damage = (int)( (_strength * _attack) - opponent.getDefense() );
	//System.out.println( "\t\t**DIAG** damage: " + damage );

	if ( damage < 0 )
	    damage = 0;

	opponent.lowerHP( damage );

	return damage;
    }

    public int specialAttack(Character opponent) {
	int damage = (int)( (_strength * _attack) - opponent.getDefense() ) 
	    +(int) ( _mana * _attack);
	//System.out.println( "\t\t**DIAG** damage: " + damage );

	if ( damage < 0 )
	    damage = 0;
	/*
	if (equipped[0].equals(""))
	    damage = 0;
	*/
	opponent.lowerHP( damage );

	_mana -= 20;

	return damage;
    }



    public int heal(Character c){
	return 0;
    }

}//end class Warrior
