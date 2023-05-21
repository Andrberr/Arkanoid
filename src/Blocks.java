import java.awt.*;
import java.util.ArrayList;

public class Blocks {

    private ArrayList<Block> blocks;

    public Blocks(double koef) {
        blocks = new ArrayList<>();
        Color[] colors = new Color[5];
        colors[0] = new Color(19, 87, 190);
        colors[1] = new Color(54, 213, 166);
        colors[2] = new Color(242, 98, 246);
        colors[3] = new Color(239, 214, 92);
        colors[4] = new Color(255, 137, 0);

        initBlocks((int) (70*koef), (int) (160*koef), (int) (115*koef), colors, koef);
        initBlocks((int) (430*koef), (int) (520*koef), (int) (475*koef), colors, koef);
        initBlocks((int) (790*koef), (int) (880*koef), (int) (835*koef), colors, koef);

        Block block = new Block((int) (100*koef), (int) (290*koef), (int) (190*koef), (int) (330*koef), (int) (145*koef), (int) (310*koef), (new Color(242, 98, 246)).getRGB(), 1, true, 0, 0);
        blocks.add(block);
         block = new Block((int) (220*koef), (int) (290*koef), (int) (310*koef), (int) (330*koef), (int) (265*koef), (int) (310*koef), (new Color(242, 98, 246)).getRGB(), 1, true, 0, 0);
        blocks.add(block);

        block = new Block((int) (510*koef), (int) (290*koef), (int) (600*koef), (int) (330*koef), (int) (555*koef), (int) (310*koef), (new Color(54, 213, 166)).getRGB(), 1, true, 0, 0);
        blocks.add(block);

        block = new Block((int) (820*koef), (int) (290*koef), (int) (910*koef), (int) (330*koef), (int) (865*koef), (int) (310*koef), (new Color(19, 87, 190)).getRGB(), 1, true, 0, 0);
        blocks.add(block);

        block = new Block((int) (940*koef), (int) (290*koef), (int) (1030*koef), (int) (330*koef), (int) (985*koef), (int) (310*koef), (new Color(19, 87, 190)).getRGB(), 1, true, 0, 0);
        blocks.add(block);
    }

    void initBlocks(int x1, int x2, int x, Color[] colors, double koef) {
        int y1 = (int) (90*koef);
        int y2 = (int) (130*koef);
        int y = (int) (110*koef);
        for (int i = 0; i < 5; i++) {
            int rx1 = x1;
            int rx2 = x2;
            int rx = x;
            for (int j = 0; j < 3; j++) {
                Block block = new Block(rx1, y1, rx2, y2, rx, y, colors[i].getRGB(), 1, true, 0, 0);
                blocks.add(block);
                rx1 += (int) (90*koef);
                rx2 += (int) (90*koef);
                rx += (int) (90*koef);
            }
            y1 += (int) (40*koef);
            y2 += (int) (40*koef);
            y += (int) (40*koef);
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
