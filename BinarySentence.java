
public class BinarySentence extends BeliefADT{
  private boolean notFirstLiteral;
  private char firstLiteral;
  private boolean notSecondLiteral;
  private char secondLiteral;

  public BinarySentence(String string) {
    super();
    // TODO Auto-generated constructor stub

    String[] seperate = string.split("\\|");
//    for (String s : seperate) {
//      System.out.println("***" + s);
//    }
    SingleLiteralSentence s1 = new SingleLiteralSentence(seperate[0]);
    this.notFirstLiteral = s1.getNotLiteral();
    this.firstLiteral = s1.getLiteral();
    
    SingleLiteralSentence s2 = new SingleLiteralSentence(seperate[1]);
    this.notSecondLiteral = s2.getNotLiteral();
    this.secondLiteral = s2.getLiteral();
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
