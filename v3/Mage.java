public class Mage extends Protagonist{
  public Mage(){
    _attack+=.2;
  }

  public Mage( String name){
    super( name );
    _attack+=.2;
  }

  public void normalize() {
      _attack = .6;
      _defense = 40;
  }

  public void specialize() {
      _attack = .95;
      _defense = 20;
  }

  public String about(){
    return "Practitioner of the ancient magic";
  }
}
