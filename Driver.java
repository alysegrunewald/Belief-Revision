
import java.util.Scanner;

public class Driver {

  public static void main(String[] args) {

    System.out.println("------ Welcome to Belief Revision! ------");
    System.out.println("By entering new beliefs, your belief base will be appropriately revised based on the new information provided.");
    System.out.println("--------------------------------------------------------\n");

    Scanner scnr = new Scanner(System.in);
    String userInput = null;

    BeliefBaseADT<BeliefADT> beliefBase = new BeliefBaseADT();
    outerloop:
      while (true) {
        System.out.println("Please enter a new belief in Conjunctive Normal Form, using the following logic symbols.");
        System.out.println("  Lowercase letter(i.e. p,q,r) : Literal");
        System.out.println("  ! : NOT");
        System.out.println("  | : OR");
        System.out.println("  & : AND");
        System.out.println("Or enter the command 'END' to see the final belief base.");

        userInput = scnr.nextLine().trim();

        if (userInput.equals("END")) {
          break;
        }

        int numORs = 0;
        int numNOTs = 0;
        int numANDs = 0;

        for(int i=0; i<userInput.length();i++) {
          Character letter = userInput.charAt(i);

          if(letter.equals('|')) {
            numORs++;
          } else if(letter.equals('!')) {
            numNOTs++;
          } else if (letter.equals('&')) {
            numANDs++;
          } else if (Character.isLetter(letter)) {
            continue;
          } else {
            System.out.println();
            System.out.println("*Please enter a valid propositional logic formula*\n");
            continue outerloop;
          }
        }
        
        String[] stringArray = userInput.split("\\&");
        
        for (String s : stringArray) {
          BeliefADT b;
          if (numORs == 0) {
            b = new SingleLiteralSentence(s);
          } else if (numORs == 1) {
            b = new BinarySentence(s);
          } else if (numORs == 2) {
            b = new ThreeLiteralSentence(s);
          } else {
            System.out.println();
            System.out.println("*Please enter a valid propositional logic formula*\n");
            continue outerloop;
          }

          BeliefRevision revise = new BeliefRevision();

          if(!beliefBase.consistencyCheck(b)) {
            revise.contraction(beliefBase, b);
          }
          revise.expansion(beliefBase, b);
        }

        //Print belief base
        System.out.println("Belief Base: " + beliefBase.toString());
        System.out.println();

        //Error checking for CNF form somehow
      }

    System.out.println("Final Belief Base: " + beliefBase.toString());
    System.out.println("--------------------------------------------------------\n");
    System.out.print("Thank you for using the Belief Revision Agent!");
  }
}


