public class Goblin extends Character{

    public Goblin() {
	super();
	_name = "Goblin";
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

}
