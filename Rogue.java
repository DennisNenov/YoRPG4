public class Rogue extends Character {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~
    // other inerited from superclass
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    /*=============================================
      default constructor
      pre:  instance vars are declared
      post: initializes instance vars.
      =============================================*/
    public Rogue() {
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
    public Rogue( String name ) {
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
