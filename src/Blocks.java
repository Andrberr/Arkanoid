import java.awt.*;

public class Blocks {
    private Block[] blocks;

    private int index = 0;

    public Blocks() {
        blocks = new Block[45];
        addBlocks();
    }

    public Block[] getBlocks() {
        return blocks;
    }

    void addBlocks() {
        Color[] colors = new Color[5];
        colors[0] = new Color(19, 87, 190);
        colors[1] = new Color(54, 213, 166);
        colors[2] = new Color(242, 98, 246);
        colors[3] = new Color(239, 214, 92);
        colors[4] = new Color(255, 137, 0);

        initBlocks(90, 180, 135, colors);
        initBlocks(450, 540, 495, colors);
        initBlocks(810, 900,855, colors );
    }

    void initBlocks(int x1, int x2, int x, Color[] colors) {
        int y1 = 90;
        int y2 = 130;
        int y = 110;
        for (int i = 0; i < 5; i++) {
            int rx1 = x1;
            int rx2 = x2;
            int rx = x;
            for (int j = 0; j < 3; j++) {
                Block block = new Block(rx1, y1, rx2, y2, rx, y, null, colors[i].getRGB(), false);
                blocks[index++] = block;
                rx1 += 90;
                rx2 += 90;
                rx += 90;
            }
            y1 += 40;
            y2 += 40;
            y += 40;
        }
    }
}
