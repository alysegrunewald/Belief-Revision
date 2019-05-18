
public class ThreeLiteralSentence extends BeliefADT{
  private boolean notFirstLiteral;
  private char firstLiteral;
  private boolean notSecondLiteral;
  private char secondLiteral;
  private boolean notThirdLiteral;
  private char thirdLiteral;

  public ThreeLiteralSentence(String string) {
    super();
    // TODO Auto-generated constructor stub
    
    String[] seperate = string.split("\\|");
    SingleLiteralSentence l = new SingleLiteralSentence(seperate[0]);
    this.notFirstLiteral = l.getNotLiteral();
    this.firstLiteral = l.getLiteral();
    
    SingleLiteralSentence s = new SingleLiteralSentence(seperate[1]);
    this.notSecondLiteral = s.getNotLiteral();
    this.secondLiteral = s.getLiteral();
    
    SingleLiteralSentence t = new SingleLiteralSentence(seperate[2]);
    this.notThirdLiteral = t.getNotLiteral();
    this.thirdLiteral = t.getLiteral();
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
  
  public boolean getNotThirdLiteral() {
    return this.notThirdLiteral;
  }
  
  public char getThirdLiteral() {
    return this.thirdLiteral;
  }
  
  public String toString() {
    if (notFirstLiteral && notSecondLiteral && notThirdLiteral) {
      return "!" + firstLiteral + "|!" + secondLiteral + "|!" + thirdLiteral;
    } else if (!notFirstLiteral && notSecondLiteral && notThirdLiteral) {
      return firstLiteral + "|!" + secondLiteral + "|!" + thirdLiteral;
    } else if (!notFirstLiteral && !notSecondLiteral && notThirdLiteral) {
      return firstLiteral + "|" + secondLiteral + "|!" + thirdLiteral;
    } else if (!notFirstLiteral && notSecondLiteral && !notThirdLiteral) {
      return firstLiteral + "|!" + secondLiteral + "|" + thirdLiteral;
    }else if (notFirstLiteral && !notSecondLiteral && notThirdLiteral) {
      return "!" + firstLiteral + "|" + secondLiteral + "|!" + thirdLiteral;
    } else if (notFirstLiteral && !notSecondLiteral && !notThirdLiteral) {
      return "!" + firstLiteral + "|" + secondLiteral + "|" + thirdLiteral;
    } else {
      return firstLiteral + "|" + secondLiteral + "|" + thirdLiteral;
    }
  }
}
