public class Priest extends Character {
    protected int _mana;

    public Priest() {
	super();
	_hitPts = 70;
	_strength = 10;
	_defense = 35;
	_attack = 0.1;
	_mana = 100;
    }

    public Priest( String name ) {
	this();
	_name = name;
    }

    public int attack (Character opponent) {
	int damage = (int)( (_mana * _attack) - opponent.getDefense() ) 
	    + _strength;

	if ( damage < 0 )
	    damage = 0;

	opponent.lowerHP( damage );
	_mana -= 2;

	return damage;
    }

    public int specialAttack(Character opponent) {
	int damage = (int)( (_strength * _attack) - opponent.getDefense() ) 
	    +(int) ( _mana * (_attack * 1.5));
	//System.out.println( "\t\t**DIAG** damage: " + damage );

	if ( damage < 0 )
	    damage = 0;

	opponent.lowerHP( damage );

	_mana -= 10;

	return damage;
    }

    public int heal (Character c ) {
	int a = (int)(_mana * 0.5);
	c.addHP(a);
	_mana -= 5;
	return  a;
    }

}
