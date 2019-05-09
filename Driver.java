import java.util.Scanner;

public class Driver {
  
  public static void main(String[] args) {
    
    System.out.println("------ Welcome to Belief Revision! ------");
    System.out.println("By entering new beliefs, your belief base will be appropriately revised based on the new information provided.");
    System.out.println("--------------------------------------------------------\n");

    Scanner scnr = new Scanner(System.in);
    String userInput = null;
    while (userInput != "END") {
      System.out.println("Please enter a new belief in Conjunctive Normal Form, using the following logic symbols.");
      System.out.println("  Lowercase letter(i.e. p,q,r) : Literal");
      System.out.println("  ! : NOT");
      System.out.println("  | : OR");
      System.out.println("  & : AND");
      System.out.println("Or enter the command 'END' to see the final belief base.");
      
      BeliefADT belief = (BeliefADT)new BinarySentence("!p|!q");
      BinarySentence bs = new BinarySentence("!p|!q");
      BeliefBaseADT bb = new BeliefBaseADT();
//      System.out.println("1 " + bs.getNotFirstLiteral());
//      System.out.println("2 " + bs.getFirstLiteral());
//      System.out.println("3 " + bs.getNotSecondLiteral());
//      System.out.println("4 " + bs.getSecondLiteral());
//      System.out.println(bb.uniqueLiteralsHelper(belief, ""));
      
//      bb.add(new SingleLiteralSentence("r"));
//      bb.add(new SingleLiteralSentence("q"));
//      bb.truthTableCheck(belief);
      
      
      
      
      userInput = scnr.nextLine().trim();
      
        String[] stringArray = userInput.split("&");
        
        for (String s : stringArray) {
          int numORs = 0;
          int numNOTs = 0;

          for(int i=0; i<userInput.length();i++) {
            Character letter = userInput.charAt(i);
            System.out.println("letter " +letter);
            if(letter.equals('|')) {
              numORs++;
            }

            if(letter.equals('!')) {
              numNOTs++;
            }
          }
          
          BeliefADT b;
          if (numORs == 0) {
            b = new SingleLiteralSentence(userInput);
          } else if (numORs == 1) {
            b = new BinarySentence(userInput);
          } else if (numORs == 2) {
            b = new ThreeLiteralSentence(userInput);
          }
        }
      
      //Error checking for CNF form somehow
    }

  }
}
