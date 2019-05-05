
public class BinarySentence extends BeliefADT{
  private boolean notFirstLiteral;
  private char firstLiteral;
  private boolean notSecondLiteral;
  private char secondLiteral;

  public BinarySentence(String string) {
    super(string);
    // TODO Auto-generated constructor stub

    String[] seperate = string.split("|");
    SingleLiteralSentence l = new SingleLiteralSentence(seperate[0]);
    this.notFirstLiteral = l.getNotLiteral();
    this.firstLiteral = l.getLiteral();
    
    SingleLiteralSentence s = new SingleLiteralSentence(seperate[1]);
    this.notFirstLiteral = s.getNotLiteral();
    this.firstLiteral = s.getLiteral();
  }
  
  public boolean getNotFirstLiteral() {
    return this.notFirstLiteral;
  }
  
  public char getFirstLiteral() {
    return this.firstLiteral;
  }
  
  public boolean getNotSecondLiteral() {
    return this.notSecondLiteral;
  }
  
  public char getSecondLiteral() {
    return this.secondLiteral;
  }

}
