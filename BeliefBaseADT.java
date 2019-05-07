import java.util.ArrayList;

public class BeliefBaseADT<BeliefADT> {
  private ArrayList<BeliefADT> beliefBase;
  
  //Method for input formula is consistent with the belief base;
  
  public BeliefBaseADT() {
    beliefBase = new ArrayList<BeliefADT>();
  }
  
  public boolean truthTableCheck(BeliefADT newBelief) {
   int numBeliefs = beliefBase.size();
   String uniqueLiterals = "";
   for (BeliefADT b : beliefBase) {
     uniqueLiterals = uniqueLiteralsHelper(b, uniqueLiterals);
   }
   uniqueLiterals = uniqueLiteralsHelper(newBelief, uniqueLiterals);
   
   int numUnique = uniqueLiterals.length();
   
   String[][] truthTable = new String[numUnique + beliefBase.size() + 1][2^numUnique];
   
    
   return true;
  }
  
  public String uniqueLiteralsHelper(BeliefADT b, String uniqueLiterals) {
    if (b instanceof SingleLiteralSentence) {
      char character = ((SingleLiteralSentence)b).getLiteral();
      if (!uniqueLiterals.contains(Character.toString(character))) {
        uniqueLiterals += character;
      } 
    } else if (b instanceof BinarySentence) {
      char character1 = ((BinarySentence)b).getFirstLiteral();
      if (!uniqueLiterals.contains(Character.toString(character1))) {
        uniqueLiterals += character1;
      }
      
      char character2 = ((BinarySentence)b).getSecondLiteral();
      if (!uniqueLiterals.contains(Character.toString(character2))) {
        uniqueLiterals += character2;
      }
    } else if (b instanceof ThreeLiteralSentence) {
      char character1 = ((ThreeLiteralSentence)b).getFirstLiteral();
      if (!uniqueLiterals.contains(Character.toString(character1))) {
        uniqueLiterals += character1;
      } 
      
      char character2 = ((ThreeLiteralSentence)b).getSecondLiteral();
      if (!uniqueLiterals.contains(Character.toString(character2))) {
        uniqueLiterals += character2;
      }  
      
      char character3 = ((ThreeLiteralSentence)b).getSecondLiteral();
      if (!uniqueLiterals.contains(Character.toString(character3))) {
        uniqueLiterals += character3;
      } 
    }
    
    return uniqueLiterals;
  }
  
  public void add(BeliefADT belief) {
    this.beliefBase.add(belief);
    return;
  }
}
