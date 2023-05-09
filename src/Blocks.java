import java.awt.*;
import java.util.ArrayList;

public class Blocks {

    private ArrayList<Block> blocks;

    public Blocks() {
        blocks = new ArrayList<>();
        Color[] colors = new Color[5];
        colors[0] = new Color(19, 87, 190);
        colors[1] = new Color(54, 213, 166);
        colors[2] = new Color(242, 98, 246);
        colors[3] = new Color(239, 214, 92);
        colors[4] = new Color(255, 137, 0);

        initBlocks(70, 160, 115, colors);
        initBlocks(430, 520, 475, colors);
        initBlocks(790, 880, 835, colors);
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
                Block block = new Block(rx1, y1, rx2, y2, rx, y, colors[i].getRGB(), 1, true, 0, 0);
                blocks.add(block);
                rx1 += 90;
                rx2 += 90;
                rx += 90;
            }
            y1 += 40;
            y2 += 40;
            y += 40;
        }
    }

    public ArrayList<Block> getGameBlocks() {
        return blocks;
    }

    public Block getBlock(int index) {
        return null;
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public void removeBlock(Block block) {
    }

    public void changeVisibility(Platform platform) {
    }

    public boolean isAllDestroyed() {
        return false;
    }
}
