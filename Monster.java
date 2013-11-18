//Hyunwoo Jeung
//pd 9
//HW25
//2013-11-14

/*=============================================
  class Monster -- Represents random incarnations of 
  the adventurer's natural enemy in Ye Olde RPG
  =============================================*/

public class Monster extends Character {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    /*=============================================
      default constructor
      pre:  instance vars are declared
      post: initializes instance vars.
      =============================================*/
    public Monster() {
	super();
	_name = "Monster";
	_hitPts = 150;
	_strength = 20 + (int)( Math.random() * 45 ); // [20,65)
	_defense = 20;
	_attack = 1;
    }

    public int attack(Character opponent) {
	int damage = (int)( (_strength * _attack) - opponent.getDefense() );
	//System.out.println( "\t\t**DIAG** damage: " + damage );

	if ( damage < 0 )
	    damage = 0;

	opponent.lowerHP( damage );

	return damage;
    }

    public int specialAttack(Character opponent){
	return 0;
    }

    public int heal(Character c){
	return 0;
    }

}//end class Monster
