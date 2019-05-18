import java.util.ArrayList;

public class BeliefRevision {
  
  //Epistemic Entrenchment
  //Premises:
  //1. if p logically entails q, give up p and retain q (i.e. higher rank for q)
  //2. p&q has higher entrenchment than p alone and q alone
  //3. sentences already not in B have minimal epistemic entrenchment in relation to B
  //4.  only logically valid sentences can be maximal in ranking
  
  public void rank(BeliefBaseADT beliefBase) {
    ArrayList<BeliefADT> beliefs = beliefBase.getBeliefBase();
    String[][] truthTable = beliefBase.truthTable(null);
    
//    System.out.println(beliefBase.toString());

    for (int i = 0; i < beliefs.size(); i++) {
      beliefs.get(i).setRank(0);
    }

    for (int i = 0; i < beliefs.size(); i++) {
      for(int j = 0; j < beliefs.size(); j++) {
        if(i != j) {
          if (entails(beliefs.get(i), beliefs.get(j), truthTable) == true) {
            beliefs.get(j).setRank(beliefs.get(j).getRank() + 1);
          }
        }
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
  
  public ArrayList<BeliefADT> expansion(BeliefBaseADT beliefBase) {
    return null;
  }
  
  public ArrayList<BeliefADT> revision(BeliefBaseADT beliefBase) {
    return null;
  }
  
  public ArrayList<BeliefADT> contraction(BeliefBaseADT beliefBase, BeliefADT newBelief) {
    ArrayList<BeliefADT> beliefs = beliefBase.getBeliefBase();
    String[][] truthTable = beliefBase.truthTable(newBelief);
    int index = (int)Math.round(Math.log10(truthTable[0].length-1)/Math.log10(2));
    
    ArrayList<String> contradictions = new ArrayList<String>();
    
    //Rank of current beliefs
    rank(beliefBase);
    
    //Find contradictions in belief base from new new belief
    for(int i = 0; i < truthTable.length; i++) {
      if (truthTable[truthTable.length-1][i].equals("1")) {
        for (int j = index; j < truthTable.length-2; j++) {
          if (truthTable[j][i].equals("0") && !contradictions.contains(truthTable[j][0])) {
            contradictions.add(truthTable[j][0]);
          }
        }
      }
    }
    
    //convert contradictions String to contradictions of type BeliefADT
    ArrayList<BeliefADT> contradictions2 = new ArrayList<BeliefADT>();
    for (int i = 0; i < contradictions.size(); i++) {
        for (int j = 0; j < beliefs.size(); j++) {
            if (contradictions.get(i).equals(beliefs.get(j).toString())) {
                contradictions2.add(beliefs.get(j));
            }
        }
    }
    
    //Of the of contradicting beliefs, find lowest rank
    BeliefADT minRank = contradictions2.get(0);
    for (int i = 1; i < contradictions2.size(); i++) {
        if (contradictions2.get(i).getRank() < minRank.getRank()) {
            minRank = contradictions2.get(i);
        }
        else if (contradictions2.get(i).getRank() == minRank.getRank()) {
            int b1 = 0;
            int b2 = 0;
            for (int j = 0; j < beliefs.size(); j++) {
                if (minRank.equals(beliefs.get(j))) {
                    b1 = j;
                }
                if (contradictions2.get(i).equals(beliefs.get(j))) {
                    b2 = j;
                }
            }
            if (b2 < b1) {
                minRank = contradictions2.get(i); 
            }
        }
    }
    
    //Remove belief from belief base with lowest rank
    for (int i = 0; i < beliefs.size(); i++) {
        if (minRank.equals(beliefs.get(i))) {
            beliefs.remove(i);
        }
    }
    
    return beliefs;
  }
}
