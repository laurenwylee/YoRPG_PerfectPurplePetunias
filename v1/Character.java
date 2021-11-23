public class Character{

	public int HP;
	public int strength;
	public int defense;
	public double atkRating;

	public boolean isAlive(){
		return HP > 0;
	}

	public int getDefense(){
		return defense;
	}

	public void lowerHP(int damage){
		HP = HP - damage;
	}

	public int attack(Character name){
		int damage = (int)( (strength * atkRating) - name.defense );
		return damage;
	}
}
