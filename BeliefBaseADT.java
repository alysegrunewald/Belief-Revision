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
   
   //Filling in 0s and 1s to each unique literal in the set
   int comboNum = ((int)(Math.pow(2, numUnique)))/2;
   String comboVar = "0";
   for (int i = 0; i < numUnique; i++) {
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
   
   //Filling in 0s and 1s for each belief in the belief base
   int k = numUnique;
   //Counter for index in 2d array
   
   this.add(newBelief);
   for (BeliefADT b : beliefBase) {
     if (b instanceof SingleLiteralSentence) {
       boolean not = ((SingleLiteralSentence)b).getNotLiteral();
       String literal = Character.toString(((SingleLiteralSentence)b).getLiteral());
       
       truthTable[k][0] = b.toString();

       for (int i = 0; i < numUnique; i++) {
         if (truthTable[i][0].equals(literal)) {
           if (not == false) {
             truthTable[k] = truthTable[i];
           } else {
             for (int j = 1; j < truthTable[i].length; j++) {
               if(truthTable[i][j].equals("0")) {
                 truthTable[k][j] = "1";
               } else {
                 truthTable[k][j] = "0";
               }
             }
           }
         } 
       }
       k++;
       continue;
     } else if(b instanceof BinarySentence) {
       boolean notFirst = ((BinarySentence)b).getNotFirstLiteral();
       String first = Character.toString(((BinarySentence)b).getFirstLiteral());
       boolean notSecond = ((BinarySentence)b).getNotSecondLiteral();
       String second = Character.toString(((BinarySentence)b).getSecondLiteral());

       truthTable[k][0] = b.toString();

       int row1 = 0;
       int row2 = 0;
       for (int i = 0; i < numUnique; i++) {
         if (truthTable[i][0].equals(first)) {
           row1 = i;
         } else if (truthTable[i][0].equals(second)) {
           row2 = i;
         }
       }

       for (int i = 1; i < truthTable[row1].length; i++) {
         if (notFirst == true) {
           if (notSecond == true) {
             if (truthTable[row1][i].equals("0") || truthTable[row2][i].equals("0")) {
               truthTable[k][i] = "1";
             } else {
               truthTable[k][i] = "0";
             }
           } else {
             if (truthTable[row1][i].equals("0") || truthTable[row2][i].equals("1")) {
               truthTable[k][i] = "1";
             } else {
               truthTable[k][i] = "0";
             }
           }
         } else {
           if (notSecond == true) {
             if (truthTable[row1][i].equals("1") || truthTable[row2][i].equals("0")) {
               truthTable[k][i] = "1";
             } else {
               truthTable[k][i] = "0";
             }
           } else {
             if (truthTable[row1][i].equals("1") || truthTable[row2][i].equals("1")) {
               truthTable[k][i] = "1";
             } else {
               truthTable[k][i] = "0";
             }
           }
         }
       }
       k++;
       continue;
     } else if (b instanceof ThreeLiteralSentence) {
       boolean notFirst = ((ThreeLiteralSentence)b).getNotFirstLiteral();
       String first = Character.toString(((ThreeLiteralSentence)b).getFirstLiteral());
       boolean notSecond = ((ThreeLiteralSentence)b).getNotSecondLiteral();
       String second = Character.toString(((ThreeLiteralSentence)b).getSecondLiteral());
       boolean notThird = ((ThreeLiteralSentence)b).getNotThirdLiteral();
       String third = Character.toString(((ThreeLiteralSentence)b).getThirdLiteral());

       truthTable[k][0] = b.toString();

       int row1 = 0;
       int row2 = 0;
       int row3 = 0;
       for (int i = 0; i < numUnique; i++) {
         if (truthTable[i][0].equals(first)) {
           row1 = i;
         } else if (truthTable[i][0].equals(second)) {
           row2 = i;
         } else if (truthTable[i][0].equals(third)) {
           row3 = i;
         }
       }

       for (int i = 1; i < truthTable[row1].length; i++) {
         if (notFirst == true) {
           if (notSecond == true) {
             if (notThird == true) {
               if (truthTable[row1][i].equals("0") || truthTable[row2][i].equals("0") || truthTable[row3][i].equals("0")) {
                 truthTable[k][i] = "1";
               } else {
                 truthTable[k][i] = "0";
               }
             } else {
               if (truthTable[row1][i].equals("0") || truthTable[row2][i].equals("0") || truthTable[row3][i].equals("1")) {
                 truthTable[k][i] = "1";
               } else {
                 truthTable[k][i] = "0";
               }
             }
           } else {
             if (notThird == true) {
               if (truthTable[row1][i].equals("0") || truthTable[row2][i].equals("1") || truthTable[row3][i].equals("0")) {
                 truthTable[k][i] = "1";
               } else {
                 truthTable[k][i] = "0";
               }
             } else {
               if (truthTable[row1][i].equals("0") || truthTable[row2][i].equals("1") || truthTable[row3][i].equals("1")) {
                 truthTable[k][i] = "1";
               } else {
                 truthTable[k][i] = "0";
               }
             }
           }
         } else {
           if (notSecond == true) {
             if (notThird == true) {
               if (truthTable[row1][i].equals("1") || truthTable[row2][i].equals("0") || truthTable[row3][i].equals("0")) {
                 truthTable[k][i] = "1";
               } else {
                 truthTable[k][i] = "0";
               }
             } else {
               if (truthTable[row1][i].equals("1") || truthTable[row2][i].equals("0") || truthTable[row3][i].equals("1")) {
                 truthTable[k][i] = "1";
               } else {
                 truthTable[k][i] = "0";
               }
             }
           } else {
             if (notThird == true) {
               if (truthTable[row1][i].equals("1") || truthTable[row2][i].equals("1") || truthTable[row3][i].equals("0")) {
                 truthTable[k][i] = "1";
               } else {
                 truthTable[k][i] = "0";
               }
             } else {
               if (truthTable[row1][i].equals("1") || truthTable[row2][i].equals("1") || truthTable[row3][i].equals("1")) {
                 truthTable[k][i] = "1";
               } else {
                 truthTable[k][i] = "0";
               }
             }
           }
         }
       }
     }
     k++;
     continue;
   }

   //Consistency Check
   int kbCounter1 = 0;
   int kbCounter2 = 0;
   for(int i = 1; i < truthTable[0].length; i++) {
     if (truthTable[truthTable.length-1][i].equals("1")) {
       kbCounter1++;
       
       int tempKbCounter = 0;
       for(int j = numUnique; j < truthTable.length-1; j++) {
         if (truthTable[j][i].equals("1")) {
           tempKbCounter++;
         }
       }
       if (tempKbCounter == beliefBase.size()-1) {
         kbCounter2++;
       }
     }
   }

   printTruthTable(truthTable);
   
   System.out.println("***" + kbCounter1 + " " + kbCounter2);
   
   if(kbCounter1 == kbCounter2) {
     return true;
   } else {
     this.beliefBase.remove(beliefBase.size()-1);
     return false;
   }
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
      
      char character3 = ((ThreeLiteralSentence)b).getThirdLiteral();
      if (!uniqueLiterals.contains(Character.toString(character3))) {
        uniqueLiterals += character3;
      } 
    }
    
    return uniqueLiterals;
  }
  
  public void printTruthTable(String[][] truthTable) {
    for(int i = 0; i < truthTable.length; i++) {
      for(int j = 0; j < truthTable[i].length; j++){
          System.out.print(truthTable[i][j]);
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
