import java.util.ArrayList;

public class BeliefBaseADT<BeliefADT> {
  private ArrayList<BeliefADT> beliefBase;
  
  public void add(BeliefADT belief) {
    this.beliefBase.add(belief);
    return;
  }
}
