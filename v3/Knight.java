public class Knight extends Protagonist{
  public Knight(){
    _hitPts+=25;
    _defense+=10;
  }

  public Knight( String name){
    super( name );
    _hitPts+=25;
    _defense+=10;
  }

  public void normalize() {
      _attack = .4;
      _defense = 50;
  }

  public void specialize() {
      _attack = .75;
      _defense = 30;
  }

  public String about(){
    return "The bravest warrior this side of the sea";
  }
}
