package replacementpoliciessim.replacer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author JanoCota
 */
public class OptimalPolicy extends ReplacementPolicy {

    public OptimalPolicy(InputData data) {
        super(data);
        TAG = "Optimal";
    }

    @Override
    public OptimalPolicy execute() {
        ArrayList<Page> queue = new ArrayList<>();
        inputSize = data.inputs.size();
        history.add(new PageFrame(data.frameSize));

        IntStream.range(0, data.inputs.size()).forEach(i -> {
            PageFrame prev = (PageFrame) history.peekLast().clone();
            Page page = data.inputs.get(i);
            Page nxt = null;
            prev.input = page.ID;
            if (!prev.contains(page)) {
                if (prev.size() < data.frameSize) {
                    prev.add(page);
                } else {
                    List<Page> sublist = data.inputs.subList(i, data.inputs.size());
                    nxt = getNext(sublist, prev, queue);
                    prev.set(prev.indexOf(nxt), page);
                    prev.replaced = true;
                    prev.replacedIndex = prev.indexOf(page);
                    replacedCount++;
                }
            }
            if (!queue.contains(page)) {
                queue.add(page);
            }
            if (queue.contains(nxt)) {
                queue.remove(nxt);
            }
            history.add(prev);
        });
        return this;
    }

    private Page getNext(List<Page> inputs, PageFrame prev, List<Page> stack) {
        PageFrame frm = (PageFrame) prev.clone();
        frm.sort((p1, p2) -> {
            int i1 = inputs.indexOf(p1);
            int i2 = inputs.indexOf(p2);
            if (i1 == -1 && i2 == -1) {
                return Integer.compare(stack.indexOf(p1), stack.indexOf(p2));
            } else if (i1 == -1) {
                return -1;
            } else if (i2 == -1) {
                return 1;
            } else {
                return Integer.compare(i2, i1);
            }
        });
        return frm.get(0);
    }
}
