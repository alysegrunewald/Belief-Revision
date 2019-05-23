import java.util.ArrayList;

public class BeliefBaseADT<BeliefADT> {
  private ArrayList<BeliefADT> beliefBase;
  
  //Method for input formula is consistent with the belief base;
  
  public BeliefBaseADT() {
    beliefBase = new ArrayList<BeliefADT>();
  }
  
  public String[][] truthTable(BeliefADT newBelief) {
   String uniqueLiterals = "";
   for (BeliefADT b : beliefBase) {
     uniqueLiterals = uniqueLiteralsHelper(b, uniqueLiterals);
   }
//   uniqueLiterals = uniqueLiteralsHelper(newBelief, uniqueLiterals);
   
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
   
   if(!beliefBase.contains(newBelief)) {
     this.beliefBase.add(newBelief);
   }

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
       
       if(truthTable[k][1] == null) {
         for (int j = 1; j < truthTable[k].length; j++) {
           truthTable[k][j] = "1";
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

       int row1 = -1;
       int row2 = -1;
       for (int i = 0; i < numUnique; i++) {
         if (truthTable[i][0].equals(first)) {
           row1 = i;
         } else if (truthTable[i][0].equals(second)) {
           row2 = i;
         }
       }

       if (row1 == -1 || row2 == -1) {
         for(int j = 1; j < truthTable[0].length; j++) {
           truthTable[k][j] = "1";
         } 
         k++;
         continue;
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

       int row1 = -1;
       int row2 = -1;
       int row3 = -1;
       for (int i = 0; i < numUnique; i++) {
         if (truthTable[i][0].equals(first)) {
           row1 = i;
         } else if (truthTable[i][0].equals(second)) {
           row2 = i;
         } else if (truthTable[i][0].equals(third)) {
           row3 = i;
         }
       }
       
       if (row1 == -1 || row2 == -1 || row3 == -1) {
         for(int j = 1; j < truthTable[0].length; j++) {
           truthTable[k][j] = "1";
         }
         k++;
         continue;
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
   
//   if (newBelief == null) {
//     beliefBase.remove(newBelief);
//   }

   this.beliefBase.remove(newBelief);
   return truthTable;
  }
  
  public boolean consistencyCheck(BeliefADT newBelief) {

    if(beliefBase.isEmpty()) {
      beliefBase.add(newBelief);
      return true;
    }

    String uniqueLiterals = "";
    for (BeliefADT b : beliefBase) {
      uniqueLiterals = uniqueLiteralsHelper(b, uniqueLiterals);
    }
    int numUnique = uniqueLiterals.length();

    String[][] truthTable = truthTable(newBelief);

    //Consistency Check
    int count1 = 0;
    int count2 = 0;
    for(int i = 1; i < truthTable[0].length; i++) {
      int kbCounter = 0;
      for (int j = numUnique; j < truthTable.length;j++) {
        if (truthTable[j][i].equals("1")) {
          kbCounter++;
        }
      }
      if (kbCounter == beliefBase.size()) {
        count1++;
        if(truthTable[truthTable.length-1][i].equals("0")) {
          count2++;
        }
      }
    }

    if (count1 == count2 && count1!=0 && count2!=0) {
      return false;
    } else {
      return true;
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
  
  public String toString() {
    String string = "{";
    if (beliefBase.size() > 0) {
      for (int i = 0; i < beliefBase.size()-1; i++) {
        string += beliefBase.get(i).toString() + ", ";
      }
      string += beliefBase.get(beliefBase.size()-1).toString() + "}";
    } else {
      string += "}";
    }
    return string;
  }
  
  public void add(BeliefADT belief) {
    this.beliefBase.add(belief);
    return;
  }
  
  public ArrayList<BeliefADT> getBeliefBase() {
    return this.beliefBase;
  }

  public void setBeliefBase(ArrayList<BeliefADT> beliefBase) {
    this.beliefBase = beliefBase;
  }

  public boolean contains(BeliefADT b) {
    int i = 0;
    int j = 0;

    for (i = 0; i < this.beliefBase.size(); i++) {
      if (b.toString().equals(this.beliefBase.get(i).toString())) {
        j++;         
      }
    }     
    if (j > 0) { 
      return true;
    } else {
      return false;
    }
  }
}
