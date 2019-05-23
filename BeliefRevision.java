
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

  public ArrayList<String[]> generate(String[] beliefs, int r) {
      ArrayList<String[]> remainders = new ArrayList<String[]>();
      helper(remainders, beliefs, new String[r], 0, beliefs.length - 1, 0);
      return remainders;
  }
  
  private void helper(ArrayList<String[]> remainders, String[] beliefs, String data[], int start, int end, int index) {
      if (index == data.length) {
          String[] remainder = data.clone();
          remainders.add(remainder);
      } else if (start <= end) {
          data[index] = beliefs[start];
          helper(remainders, beliefs, data, start + 1, end, index + 1);
          helper(remainders, beliefs, data, start + 1, end, index);
      }
  }
  
  public ArrayList<ArrayList<String>> remainderSet(BeliefBaseADT beliefBase, BeliefADT newBelief) {
    String[][] truthTable = beliefBase.truthTable(newBelief);
    ArrayList<BeliefADT> beliefBaseList = beliefBase.getBeliefBase();
    ArrayList<String[]> remainderSet = new ArrayList<String[]>();
    
    //Go through belief base, find all combiniations of what is in the belief base
    String[] beliefs = new String[beliefBaseList.size()];
    for (int i = 0; i < beliefs.length; i++) {
        beliefs[i] = beliefBaseList.get(i).toString();
    }
    
    for (int i = 0; i < beliefs.length; i++) {
    ArrayList<String[]> remainders = generate(beliefs, i);
    for (int j = 0; j < remainders.size(); j++) {
    remainderSet.add(remainders.get(j));
    }
    }
    
    for (int i = 0; i < remainderSet.size(); i++) {
        System.out.println(remainderSet.get(i));
    }
    return null;
  }
  public boolean entails(BeliefBaseADT beliefBase, ArrayList<BeliefADT> remainder, BeliefADT phi) {

    String[][] truthTable = beliefBase.truthTable(phi);

    int index = (int)Math.round(Math.log10(truthTable[0].length-1)/Math.log10(2));
    int[] beliefIndex = new int[remainder.size()];

    for(int i = 0; i < remainder.size(); i++) {
      for (int j  = index; j < truthTable.length-1; j++) {
        if (remainder.get(i).toString().equals(truthTable[j][0])) {
          beliefIndex[i] = j;
        }
      }
    }   

    int count1=0;
    int count2=0;
    for (int i = 1; i < truthTable[0].length; i++) {
      int kbCounter = 0;
      for (int j = 0; j < beliefIndex.length; j++) {
        if (truthTable[beliefIndex[j]].equals("1")) {
          kbCounter++;
        }
      }
      if (kbCounter == remainder.size()) {
        count1++;
        if(truthTable[truthTable.length-1][i].equals("1")) {
          count2++;
        }
      }
    }

    //    beliefBase.printTruthTable(truthTable);
    if(count1 == count2 && count1 != 0 && count2 != 0) {
      return true;
    } else {
      return false;
    }
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
        
        ArrayList<String[]> remainderSet = remainderSet(beliefBase, belief);
        String[] removal = selection(remainderSet);
                
        for (int i = 0; i < removal.length; i++) {
            for (int j = 0; j < beliefs.size(); j++) {
                if (removal[i].equals(beliefs.get(j).toString())) {
                    beliefs.remove(j);
                    break;
                }
            }       
        }
        return beliefs;
    
}
