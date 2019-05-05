
public class SingleLiteralSentence extends BeliefADT{
  private boolean notLiteral;
  private char literal;

  public SingleLiteralSentence(String string) {
    super(string);
    // TODO Auto-generated constructor stub
    
    if (string.contains("!")) {
      this.notLiteral = true;
      string = string.replace("!", "");
    } else {
      this.notLiteral = false;
    }
    
    string = string.trim();
    this.literal = string.charAt(0);
  }
  
  public boolean getNotLiteral() {
    return this.notLiteral;
  }
  
  public char getLiteral() {
    return this.literal;
  }

}
