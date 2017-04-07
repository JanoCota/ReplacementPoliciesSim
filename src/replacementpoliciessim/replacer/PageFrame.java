package replacementpoliciessim.replacer;

import java.util.ArrayList;

/**
 * @author JanoCota
 */
public class PageFrame extends ArrayList<Page> {

    public final int frameSize;
    public boolean replaced;
    public int replacedIndex;
    public String input;

    public PageFrame(int frameSize) {
        super(frameSize);
        this.frameSize = frameSize;
        this.replaced = false;
        this.input = null;
    }

    @Override
    public Object clone() {
        PageFrame pf = new PageFrame(this.frameSize);
        this.forEach(p -> pf.add(p));
        return pf;
    }

    @Override
    public String toString() {
        return super.toString() + (replaced ? " - " + replacedIndex : "");
    }

}
