public class Archer extends Protagonist{
  public Archer(){
    _strength+=40;
  }

  public Archer( String name){
    super( name );
    _strength+=40;
  }

  public String about(){
    return "Highly trained and precise shooter";
  }
}
