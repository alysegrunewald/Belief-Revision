
import java.util.Scanner;

public class Driver {

  public static void main(String[] args) {

    System.out.println("------ Welcome to Belief Revision! ------");
    System.out.println("By entering new beliefs, your belief base will be appropriately revised based on the new information provided.");
    System.out.println("--------------------------------------------------------\n");

    Scanner scnr = new Scanner(System.in);
    String userInput = null;

    BeliefBaseADT<BeliefADT> beliefBase = new BeliefBaseADT();
    while (userInput != "END") {
      System.out.println("Please enter a new belief in Conjunctive Normal Form, using the following logic symbols.");
      System.out.println("  Lowercase letter(i.e. p,q,r) : Literal");
      System.out.println("  ! : NOT");
      System.out.println("  | : OR");
      System.out.println("  & : AND");
      System.out.println("Or enter the command 'END' to see the final belief base.");

      //      BeliefADT belief = (BeliefADT)new SingleLiteralSentence("!p");
      //      BinarySentence bs = new BinarySentence("!p|!q");
      //      System.out.println("1 " + bs.getNotFirstLiteral());
      //      System.out.println("2 " + bs.getFirstLiteral());
      //      System.out.println("3 " + bs.getNotSecondLiteral());
      //      System.out.println("4 " + bs.getSecondLiteral());
      //      System.out.println(bb.uniqueLiteralsHelper(belief, ""));

      //      BeliefADT b1 = new BinarySentence("q|r");
      //      BeliefADT b2 = new SingleLiteralSentence("p");
      //      BeliefADT b3 = new BinarySentence("p|s");
      //      BeliefADT b4 = new SingleLiteralSentence("r");
      //      BeliefRevision br = new BeliefRevision();
      //      BeliefBaseADT<BeliefADT> bb = new BeliefBaseADT<BeliefADT>();
      //      bb.setBeliefBase(br.expansion(bb, b1));
      //      bb.setBeliefBase(br.expansion(bb, b2));
      //      bb.setBeliefBase(br.expansion(bb, b3));
      //      bb.setBeliefBase(br.expansion(bb, b4));
      //      
      //      ArrayList<BeliefADT> remainderSet = new ArrayList<BeliefADT>();
      //      br.entails(bb, remainder, new BinarySentence("q|p"));


      userInput = scnr.nextLine().trim();

      int numORs = 0;
      int numNOTs = 0;
      int numANDs = 0;

      for(int i=0; i<userInput.length();i++) {
        Character letter = userInput.charAt(i);

        if(letter.equals('|')) {
          numORs++;
        }

        if(letter.equals('!')) {
          numNOTs++;
        }

        if(letter.equals("&")) {
          numANDs++;
        }
      }

      BeliefADT b;
      if (numORs+numANDs == 0) {
        b = new SingleLiteralSentence(userInput);
      } else if (numORs+numANDs == 1) {
        b = new BinarySentence(userInput);
      } else {
        b = new ThreeLiteralSentence(userInput);
      }

      BeliefRevision revise = new BeliefRevision();
      System.out.println(beliefBase.getBeliefBase().size());
      if (beliefBase.consistencyCheck(b) == false) {
        beliefBase.setBeliefBase(revise.contraction(beliefBase, b));
        revise.expansion(beliefBase,b);
      } else {
        revise.expansion(beliefBase,b);
      }

      //Print belief base
      System.out.println("Belief Base: " + beliefBase.toString());
      System.out.println();

      //Error checking for CNF form somehow
    }
  }
}
