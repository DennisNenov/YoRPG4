//Hyunwoo Jeung
//pd 9
//HW25
//2013-11-14

public class Mage extends Character {

    public Mage() {
	super();
	_hitPts = 70;
	_strength = 10;
	_defense = 35;
	_attack = 0.7;
	_mana = 100;
    }

    public Mage( String name ) {
	this();
	_name = name;
    }

    public int attack (Character opponent) {
	int damage = (int)( (_mana * _attack) - opponent.getDefense() ) + _strength;

	if ( damage < 0 )
	    damage = 0;

	opponent.lowerHP( damage );
	_mana -= 2;

	return damage;
    }

    public int specialAttack(Character opponent) {
	int damage = (int)( (_strength * _attack) - opponent.getDefense() ) + (int) ( _mana * (_attack * 1.5));
	//System.out.println( "\t\t**DIAG** damage: " + damage );

	if ( damage < 0 )
	    damage = 0;

	opponent.lowerHP( damage );

	_mana -= 30;

	return damage;
    }

    public int heal(Character c){
	return 0;
    }
}

