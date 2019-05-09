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
   
   String[][] truthTable = new String[numUnique + beliefBase.size() + 1][(int)(Math.pow(2, numUnique)+1)];
   
   int comboNum = ((int)(Math.pow(2, numUnique)))/2;
   String comboVar = "0";
   for (int i = 0; i < uniqueLiterals.length(); i++) {
     truthTable[i][0] = Character.toString(uniqueLiterals.charAt(i));
     int counter = 0;
     for (int j = 1; j < truthTable[i].length; j++) {
       truthTable[i][j] = comboVar;
       counter++;
       if (counter == comboNum) {
         if (comboVar.equals("0")) {
           comboVar = "1";
         } else {
           comboVar = "0";
         }
         counter = 0;
       }
     }
     comboNum = comboNum/2;
   }
   
   
   
   
    
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
  
  public void printTruthTable(String[][] truthTable) {
    for(int i = 0; i < truthTable.length; i++) {
      for(int j = 0; j < truthTable[i].length; j++){
          System.out.print(truthTable[i][j]+"~");
          if(j < truthTable[i].length - 1) {
            System.out.print(" ");
          }
      }
      System.out.println();
  }
  }
  
  public void add(BeliefADT belief) {
    this.beliefBase.add(belief);
    return;
  }
}
