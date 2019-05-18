import java.util.ArrayList;

public class BeliefRevision {
  
  //Epistemic Entrenchment
  //Premises:
  //1. if p logically entails q, give up p and retain q (i.e. higher rank for q)
  //2. p&q has higher entrenchment than p alone and q alone
  //3. sentences already not in B have minimal epistemic entrenchment in relation to B
  //4.  only logically valid sentences can be maximal in ranking
  
  public BeliefBaseADT expansion(BeliefBaseADT beliefBase) {
    return null;
  }
  
  public BeliefBaseADT revision(BeliefBaseADT beliefBase) {
    return null;
  }
  
  public BeliefBaseADT contraction(BeliefBaseADT beliefBase) {
    return null;
  }

  public ArrayList<BeliefADT> contraction (ArrayList<BeliefADT> beliefBase, BeliefADT belief) {
      
      return beliefBase;
  }
  
  public void rank(BeliefBaseADT beliefBase) {   
       ArrayList<BeliefADT> beliefs = beliefBase.getBeliefBase();
       String[][] truthTable = beliefBase.truthTable(null);
       int i = 0;
       for (i = 0; i < beliefs.size(); i++) {
           beliefs.get(i).setRank(i + 1);
       }
       
       for (i = 0; i < beliefs.size() - 1; i++) {
           if (entails(beliefs.get(i), beliefs.get(i + 1), truthTable) == true) {
               beliefs.get(i).setRank(beliefs.get(i + 1).getRank() + 1);
           }
       }
  }
  
  public boolean entails(BeliefADT belief1, BeliefADT belief2, String[][] truthTable) {
      boolean entails = false;
      int i = 0;
      int belief1index = 0;
      int belief2index = 0;
      
      for (i = 0; i < truthTable.length; i++) {
          if (belief1.toString().equals(truthTable[i][0])) {
              belief1index = i;
          }
          if (belief2.toString().equals(truthTable[i][0])) {
              belief2index = i;
          }
      }
      
      int counter = 0;
         
      for (i = 0; i < truthTable[0].length; i++) {
          if (truthTable[belief1index][i].equals("1")) {
              counter++;
          }
      }
      
      int counter2 = 0;
      for (i = 0; i < truthTable[0].length; i++) {
          if (truthTable[belief1index][i].equals("1") && truthTable[belief2index][i].equals("1")) {
              counter2++;
          }
      }
      
      if(counter == counter2) {
          entails = true;
      }
      
      return entails;
  }
}
