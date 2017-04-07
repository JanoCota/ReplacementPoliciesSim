package replacementpoliciessim;

import java.util.LinkedList;
import javax.swing.UIManager;
import replacementpoliciessim.replacer.ReplacementPolicy;
import replacementpoliciessim.replacer.OptimalPolicy;
import replacementpoliciessim.replacer.InputReader;
import replacementpoliciessim.replacer.FifoPolicy;
import replacementpoliciessim.replacer.InputData;
import replacementpoliciessim.replacer.LruPolicy;

/**
 * @author JanoCota
 */
public class Main {

    public static void main(String[] args) {
        LinkedList<ReplacementPolicy> policies = new LinkedList<>();
        InputData data = InputReader.read();
        policies.add(new FifoPolicy(data).execute());
        policies.add(new LruPolicy(data).execute());
        policies.add(new OptimalPolicy(data).execute());
        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Window frm = new Window(policies);
            frm.setLocationRelativeTo(null);
            frm.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
