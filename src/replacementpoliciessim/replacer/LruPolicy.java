package replacementpoliciessim.replacer;

import java.util.ArrayList;

/**
 * @author JanoCota
 */
public class LruPolicy extends ReplacementPolicy {

    public LruPolicy(InputData data) {
        super(data);
        TAG = "LRU";
    }

    @Override
    public LruPolicy execute() {
        ArrayList<Page> queue = new ArrayList<>();
        inputSize = data.inputs.size();
        history.add(new PageFrame(data.frameSize));

        data.inputs.forEach(page -> {
            PageFrame prev = (PageFrame) history.peekLast().clone();
            prev.input = page.ID;
            if (!prev.contains(page)) {
                if (prev.size() < data.frameSize) {
                    prev.add(page);
                } else {

                    prev.set(prev.indexOf(queue.remove(0)), page);
                    prev.replaced = true;
                    prev.replacedIndex = prev.indexOf(page);
                    replacedCount++;
                }
            }
            if (queue.contains(page)) {
                queue.remove(queue.indexOf(page));
            }
            queue.add(page);
            history.add(prev);
        });
        return this;
    }

}
