package replacementpoliciessim.replacer;

import java.util.LinkedList;

/**
 * @author JanoCota
 */
public abstract class ReplacementPolicy {

    protected LinkedList<PageFrame> history = new LinkedList<>();
    public int inputSize;
    public int replacedCount;
    public String TAG;
    public InputData data;

    public ReplacementPolicy(InputData data) {
        this.data = data;
    }

    public abstract ReplacementPolicy execute();

    public LinkedList<PageFrame> getHistory() {
        return history;
    }
}
