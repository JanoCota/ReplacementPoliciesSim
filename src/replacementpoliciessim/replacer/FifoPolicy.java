package replacementpoliciessim.replacer;

import java.util.ArrayDeque;

/**
 * @author JanoCota
 */
public class FifoPolicy extends ReplacementPolicy {

    public FifoPolicy(InputData data) {
        super(data);
        TAG = "FIFO";
    }

    @Override
    public FifoPolicy execute() {
        ArrayDeque<Page> q = new ArrayDeque<>();
        inputSize = data.inputs.size();
        history.add(new PageFrame(data.frameSize));

        data.inputs.forEach(page -> {
            PageFrame prev = (PageFrame) history.peekLast().clone();
            prev.input = page.ID;
            if (!prev.contains(page)) {
                if (prev.size() < data.frameSize) {
                    q.add(page);
                    prev.add(page);
                } else {
                    prev.set(prev.indexOf(q.poll()), page);
                    q.add(page);
                    prev.replaced = true;
                    prev.replacedIndex = prev.indexOf(page);
                    prev = prev;
                    replacedCount++;
                }
            }
            history.add(prev);
        });
        return this;
    }

}
