package replacementpoliciessim;

import java.awt.*;
import java.util.LinkedList;
import replacementpoliciessim.replacer.Page;
import replacementpoliciessim.replacer.PageFrame;
import replacementpoliciessim.replacer.ReplacementPolicy;

/**
 * @author JanoCota
 */
public class Canvas extends javax.swing.JPanel {

    private LinkedList<ReplacementPolicy> policies;

    public Canvas(LinkedList<ReplacementPolicy> policies) {
        super();
        this.policies = policies;

        PAGE_HEIGHT = this.getFontMetrics(PLAIN).getAscent() + 12;
        PAGE_WIDTH = this.getFontMetrics(PLAIN).stringWidth(
                ((LinkedList<Page>) policies.getFirst().data.inputs.clone())
                        .stream()
                        .sorted((p1, p2) -> {
                            return p2.ID.compareTo(p1.ID);
                        }).findFirst().get().ID
        ) + 30;
        PAGE_FRAME_HEIGHT = (policies
                .getFirst()
                .getHistory()
                .getFirst().frameSize + 1)
                * PAGE_HEIGHT;

        this.setPreferredSize(
                new Dimension(
                        LEFT_BORDER_SPACING
                        + policies.getFirst().getHistory().size()
                        * (PAGE_WIDTH + HORIZONTAL_PAGE_SPACING),
                        policies.size()
                        * (PAGE_HEIGHT
                        + PAGE_FRAME_HEIGHT
                        + FRAME_SUBTITLE_SPACING
                        + PAGE_HEIGHT
                        + VERTICAL_POLICY_SPACING)
                )
        );
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(TEXT_COLOR);

        int y = TOP_BORDER_SPACING;
        for (ReplacementPolicy pol : policies) {
            int x = LEFT_BORDER_SPACING;

            g2d.setColor(TEXT_COLOR);
            g.setFont(BOLD);
            drawCenteredString(String.format("Política: %s", pol.TAG),
                    LEFT_BORDER_SPACING, y, 0, PAGE_HEIGHT, g2d);
            y += PAGE_HEIGHT;

            for (PageFrame pf : pol.getHistory()) {
                paintPageFrame(pf, x, y, g2d);
                x += HORIZONTAL_PAGE_SPACING + PAGE_WIDTH;
            }
            y += PAGE_FRAME_HEIGHT + FRAME_SUBTITLE_SPACING;

            g2d.setColor(TEXT_COLOR);
            g2d.setFont(PLAIN);
            drawCenteredString(
                    String.format("Entradas: %d    Reemplazos: %d", pol.inputSize, pol.replacedCount),
                    LEFT_BORDER_SPACING, y, 0, PAGE_HEIGHT, g2d);
            y += PAGE_HEIGHT + VERTICAL_POLICY_SPACING;
        }

    }

    private void paintPageFrame(PageFrame frame, int x, int y, Graphics2D g) {
        g.setFont(PLAIN);
        int dy = y;
        if (frame.input != null) {
            g.setFont(BOLD);
            drawCenteredString(frame.input, x, dy, PAGE_WIDTH, PAGE_HEIGHT, g);
            g.setFont(PLAIN);
        }
        dy += PAGE_HEIGHT;
        y += PAGE_HEIGHT;
        for (Page p : frame) {
            if (frame.replaced && frame.indexOf(p) == frame.replacedIndex) {
                g.setColor(FILL_COLOR_REPLACED);
                g.fillRect(x, y, PAGE_WIDTH, PAGE_HEIGHT);
                g.setColor(TEXT_COLOR_REPLACED);
                g.setFont(BOLD);
            }
            drawCenteredString(p.ID, x, y, PAGE_WIDTH, PAGE_HEIGHT, g);
            g.setFont(PLAIN);
            g.setColor(DRAWING_COLOR);
            y += PAGE_HEIGHT;
        }
        for (int i = 0; i < frame.frameSize; i++) {
            g.setColor(DRAWING_COLOR);
            g.drawRect(x, dy, PAGE_WIDTH, PAGE_HEIGHT);
            dy += PAGE_HEIGHT;
        }
    }

    private void drawCenteredString(String str, int xOffset, int yOffset, int width, int height, Graphics g) {
        g.drawString(str,
                width != 0 ? xOffset + ((width - g.getFontMetrics().stringWidth(str)) / 2) : xOffset,
                yOffset + ((height - g.getFontMetrics().getHeight()) / 2) + g.getFontMetrics().getAscent()
        );
    }

    private int PAGE_WIDTH = 0;
    private int PAGE_HEIGHT = 0;
    private int PAGE_FRAME_HEIGHT = 0;
    private final int LEFT_BORDER_SPACING = 20;
    private final int TOP_BORDER_SPACING = 10;
    private final int HORIZONTAL_PAGE_SPACING = 20;
    private final int VERTICAL_POLICY_SPACING = 20;
    private final int FRAME_SUBTITLE_SPACING = 5;

    private final float FONT_SIZE = 14f;
    private final Font BOLD = this.getFont().deriveFont(Font.BOLD, FONT_SIZE);
    private final Font PLAIN = this.getFont().deriveFont(Font.PLAIN, FONT_SIZE);

    private final Color FILL_COLOR_REPLACED = Color.black;
    private final Color TEXT_COLOR_REPLACED = Color.white;
    private final Color DRAWING_COLOR = Color.black;
    private final Color TEXT_COLOR = Color.black;

}
