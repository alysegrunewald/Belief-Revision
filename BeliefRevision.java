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
  
  public void rank(ArrayList<BeliefADT> beliefBase) {   
       int i = 0;
       for (i = 0; i < beliefBase.size(); i++) {
           beliefBase.get(i).setRank(i + 1);
       }
       
       for (i = 0; i < beliefBase.size() - 1; i++) {
           if (entails(beliefBase.get(i), beliefBase.get(i + 1)) == true) {
               beliefBase.get(i).setRank(beliefBase.get(i + 1).getRank() + 1);
           }
       }
  }
  
  public boolean entails(BeliefADT belief1, BeliefADT belief2, tring[][]) {
      
      return true;
  }
