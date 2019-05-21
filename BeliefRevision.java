import java.util.ArrayList;

public class BeliefRevision {
  
  //Epistemic Entrenchment
  //Premises:
  //1. if p logically entails q, give up p and retain q (i.e. higher rank for q)
  //2. p&q has higher entrenchment than p alone and q alone
  //3. sentences already not in B have minimal epistemic entrenchment in relation to B
  //4.  only logically valid sentences can be maximal in ranking
  
//  public void rank(BeliefBaseADT beliefBase) {
//    ArrayList<BeliefADT> beliefs = beliefBase.getBeliefBase();
//    String[][] truthTable = beliefBase.truthTable(null);
//    
////    System.out.println(beliefBase.toString());
//
//    for (int i = 0; i < beliefs.size(); i++) {
//      beliefs.get(i).setRank(0);
//    }
//
//    for (int i = 0; i < beliefs.size(); i++) {
//      for(int j = 0; j < beliefs.size(); j++) {
//        if(i != j) {
//          if (entails(beliefs.get(i), beliefs.get(j), truthTable) == true) {
//            beliefs.get(j).setRank(beliefs.get(j).getRank() + 1);
//          }
//        }
//      }
//    }
//  }
  
  public void selection(ArrayList<String> remainderSet) {
    //Maxichoice or full meet contraction
    //Arbitrary selection of removal, no intersection of remainder set
  }
  
  public ArrayList<ArrayList<String>> remainderSet(BeliefBaseADT beliefBase, BeliefADT newBelief) {
    String[][] truthTable = beliefBase.truthTable(newBelief);
    
    //Go through belief base, find all combiniations of what is in the belief base
    
    //Find inclusion maximal combos of where belief base does not entail new belief
    //Every subset of B that does not entail phi will be added to remainder set
    
    //Find a way to ensure remainder set only includes largest subsets of B (inclusion maximal)
    
    return null;
  }

  public boolean entails(ArrayList<BeliefADT> remainder, String[][] truthTable) {

    boolean entails = true;
    int index = (int)Math.round(Math.log10(truthTable[0].length-1)/Math.log10(2));
    int[] beliefIndex = new int[remainder.size()];

    for(int i = 0; i < remainder.size(); i++) {
      for (int j  = index; j < truthTable.length-1; j++) {
        if (remainder.get(i).toString().equals(truthTable[j][0])) {
          beliefIndex[i] = j;
        }
      }
    }   
    for (int i = 0; i < beliefIndex.length; i++) {
      for (int j = 1; j < truthTable[0].length; j++) {
          if (truthTable[beliefIndex[i]][j].equals("1") && !truthTable[truthTable.length - 1][j].equals("1")) {
              entails = false;
          }
      }
    }
    System.out.println(entails);
    return entails;
  }
  
  public ArrayList<BeliefADT> expansion(BeliefBaseADT<BeliefADT> beliefBase, BeliefADT belief) {
    ArrayList<BeliefADT> beliefs = beliefBase.getBeliefBase();
    
    if(!beliefs.contains(belief)) {
      beliefs.add(belief);
    }  
    
    return beliefs;
  }
  
  public ArrayList<BeliefADT> contraction(BeliefBaseADT<BeliefADT> beliefBase, BeliefADT belief) {
    ArrayList<BeliefADT> beliefs = beliefBase.getBeliefBase();
    
    if(beliefs.contains(belief)) {
      beliefs.add(belief);
    }  
    
    return beliefs;
  }
}
