import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Observable;
import java.util.Observer;

import static java.lang.Math.abs;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Platform extends GameFigure implements Serializable, Observer {
    transient KeyEvent key;
    int width;

    private Boolean hasGun;

    private Boolean firBonus;
    private Boolean secBonus;
    private Boolean fifthBonus;

    int invisColor = new Color(171, 149, 39).getRGB();
    int visColor = new Color(36, 53, 164).getRGB();

    public int getGunX() {
        return gunX;
    }

    public void setGunX(int gunX) {
        this.gunX = gunX;
    }

    public int getGunY() {
        return gunY;
    }

    public void setGunY(int gunY) {
        this.gunY = gunY;
    }

    private int gunX = 0;
    private int gunY = 0;

    public Boolean getHasGun() {
        return hasGun;
    }

    public void setHasGun(Boolean hasGun) {
        this.hasGun = hasGun;
    }

    public Platform(int startX, int startY, int endX, int endY, int centerX, int centerY, int color, int drawAmount, boolean isStatic, int dx, int dy, int width, int height) {
        super(startX, startY, endX, endY, centerX, centerY, color, drawAmount, isStatic, dx, dy, height);
        this.width = width;
        hasGun = false;
        firBonus = false;
        secBonus = false;
        fifthBonus = false;
    }

    Platform() {

    }

    @Override
    boolean figureMove() {
        if (key != null) {
            int keyCode = key.getKeyCode();
            if (keyCode == KeyEvent.VK_LEFT) {
                setStartX(getStartX() - drawAmount);
                endX = (endX - drawAmount);
                X = X - drawAmount;
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                setStartX(getStartX() + drawAmount);
                endX = (endX + drawAmount);
                X = X + drawAmount;
            }
            if (getStartX() < 0) {
                endX = (endX - getStartX());
                setStartX(0);
                X = getStartX() + (endX - getStartX()) / 2;
            } else if (endX > width) {
                setStartX(width - (endX - getStartX()));
                endX = width;
                X = getStartX() + (endX - getStartX()) / 2;
            }
            gunX = X - 5;
        }
        return true;
    }

    @Override
    void draw(Graphics2D g2d) {
        g2d.setColor(new Color(color));
        Rectangle rect = new Rectangle(getStartX(), startY, abs(getStartX() - endX), abs(startY - endY));
        g2d.fill(rect);
        g2d.draw(rect);
        if (hasGun) {
            if (getColor() == invisColor) g2d.setColor(new Color(invisColor));
            else g2d.setColor(Color.BLACK);

            rect = new Rectangle(getX() - 5, startY - 30, 5, 30);
            g2d.fill(rect);
            g2d.draw(rect);


            g2d.setColor(Color.WHITE);
            rect = new Rectangle(gunX, gunY, 5, 10);
            g2d.fill(rect);
            g2d.draw(rect);
            gunY -= 10;
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Event event) {
            if (event.bonusType == 1 && !firBonus) {
                firBonus = true;
                endX += 100;
                Thread myThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        long currTime;
                        while (true) {
                            currTime = System.currentTimeMillis();
                            if (currTime >= time + 3000L) {
                                endX -= 100;
                                firBonus = false;
                                break;
                            }
                        }
                    }
                });

                myThread.start();

            } else if (event.bonusType == 2 && !secBonus) {
                secBonus = true;
                setColor(invisColor);
                Thread myThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        long currTime;
                        while (true) {
                            currTime = System.currentTimeMillis();
                            if (currTime >= time + 3000L) {
                                secBonus = false;
                                setColor(visColor);
                                break;
                            }
                        }
                    }
                });
                myThread.start();
            } else if (event.bonusType == 5 && !fifthBonus) {
                fifthBonus = true;
                hasGun = true;
                gunX = getX() - 5;
                gunY = startY - 30;

                Thread myThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long time = System.currentTimeMillis();
                        long currTime;
                        while (true) {
                            currTime = System.currentTimeMillis();
                            if (currTime >= time + 3000L) {
                                fifthBonus = false;
                                hasGun = false;
                                gunX = 0;
                                gunY = 0;
                                break;
                            }
                        }
                    }
                });

                myThread.start();

            }
        }
    }

    public Boolean getFirBonus() {
        return firBonus;
    }

    public void setFirBonus(Boolean firBonus) {
        this.firBonus = firBonus;
    }

    public Boolean getSecBonus() {
        return secBonus;
    }

    public void setSecBonus(Boolean secBonus) {
        this.secBonus = secBonus;
    }

    public Boolean getFifthBonus() {
        return fifthBonus;
    }

    public void setFifthBonus(Boolean fifthBonus) {
        this.fifthBonus = fifthBonus;
    }
}
