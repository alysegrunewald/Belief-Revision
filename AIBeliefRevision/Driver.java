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
      System.out.println("Or enter the command 'END' to see the final belief base.");
      
      userInput = scnr.nextLine().trim();
      
      //Error checking for CNF form somehow
    }

  }
}
