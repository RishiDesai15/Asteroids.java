
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class Asteriods extends Applet implements KeyListener, ActionListener {

    int[] x, y;
    Spacecraft ship;
    Spacecraft ship2;
    Timer timer;
    Image offscreen;
    Graphics offg;
    boolean upkey, leftkey, rightkey, spacekey;
    boolean upkey2, leftkey2, rightkey2, shootkey;
    boolean start;
    boolean enterkey;
    boolean singlePlayer;
    ArrayList<Rocks> rockList;
    ArrayList<Bullet> bulletList;
    ArrayList<Debris> debrisList;
    int lives;
    int lives2;
    int points;
    int points2;
    int level;

    public void init() {
        this.setSize(1300, 600);
        this.addKeyListener(this);
        ship = new Spacecraft();

        ship2 = new Spacecraft();
        ship2.spawnxpos = 550;
        ship2.spawnypos = 300;
        ship2.xposition = ship2.spawnxpos;
        ship2.yposition = ship2.spawnypos;
        timer = new Timer(20, this);
        offscreen = createImage(this.getWidth(), this.getHeight());
        offg = offscreen.getGraphics();
        rockList = new ArrayList();
        bulletList = new ArrayList();
        debrisList = new ArrayList();
        lives = 4;
        lives2 = 4;
        points = 0;
        points2 = 0;
        startLevel();
        start = false;
        enterkey = false;
        singlePlayer = true;
        level = 1;
    }

    public void paint(Graphics g) {
        offg.setColor(Color.BLACK);
        offg.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        offg.fillRect(0, 0, 1400, 600);

        if (start) {
            offg.setColor(Color.magenta);
            ship.paint(offg);
            offg.setColor(Color.magenta);
            offg.drawString("Lives: " + lives, 1200, 20);
            offg.drawString("Points: " + points, 1200, 50);
            offg.setColor(Color.white);
            offg.drawString("Level: " + level, 650, 50);

            offg.setColor(Color.cyan);
            if (singlePlayer == false) {
                ship2.paint(offg);
            }
            offg.setColor(Color.white);
            for (int i = 0; i < rockList.size(); i++) {
                rockList.get(i).paint(offg);
            }
            offg.setColor(Color.yellow);
            for (int i = 0; i < bulletList.size(); i++) {
                bulletList.get(i).paint(offg);
            }
            offg.setColor(Color.yellow);
            offg.setColor(Color.white);
            for (int i = 0; i < debrisList.size(); i++) {
                debrisList.get(i).paint(offg);
            }
            offg.setColor(Color.cyan);
            offg.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            if (rockList.isEmpty()) {
                startLevel();
                level++;
            }
            if (singlePlayer == false) {
                offg.setColor(Color.cyan);
                offg.setFont(new Font("TimesRoman", Font.PLAIN, 18));
                offg.drawString("Lives: " + lives2, 10, 20);
                offg.drawString("Points: " + points2, 10, 50);
                if (lives == 0 && lives2 == 0) {
                    offg.drawString(" GAME OVER ", 600, 400);
                }
            } else {
                if (lives == 0) {
                    offg.drawString(" GAME OVER ", 600, 400);
                }
            }

        } else {
            offg.setFont(new Font("TimesRoman", Font.PLAIN, 48));
            offg.setColor(Color.orange);
            offg.drawString("Asteriods", 550, 200);
            offg.setFont(new Font("TimesRoman", Font.PLAIN, 24));
            offg.drawString("Created By: Rishi", 555, 250);
            offg.setColor(Color.green);
            offg.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            offg.drawString("Single Player", 400, 350);
            offg.setColor(Color.red);
            offg.drawString("Multiplayer", 750, 350);
        }

        g.drawImage(offscreen, 0, 0, this);
        repaint();
    }

    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    public void keyCheck() {
        if (start == true) {
            if (upkey) {
                ship.accelerate();
            }
            if (rightkey) {
                ship.rotateright();
            }
            if (leftkey) {
                ship.rotateleft();
            }
            if (spacekey && ship.counter > 5 && ship.active) {
                ship.counter = 0;
                bulletList.add(new Bullet(ship.xposition, ship.yposition, ship.angle, 1));
            }
            // For Ship 2
            if (singlePlayer == false) {
                if (upkey2) {
                    ship2.accelerate();
                }
                if (rightkey2) {
                    ship2.rotateright();
                }
                if (leftkey2) {
                    ship2.rotateleft();
                }
                if (shootkey && ship2.counter > 5 && ship2.active) {
                    ship2.counter = 0;
                    bulletList.add(new Bullet(ship2.xposition, ship2.yposition, ship2.angle, 2));
                }
            }

        } else {
            if (enterkey == true) {
                start = true;
            }
            if (rightkey == true) {
                singlePlayer = false;
            }
            if (leftkey == true) {
                singlePlayer = true;
            }
            if (singlePlayer == false) {
                ship.spawnxpos = 750;
                ship.spawnypos = 300;
            } else {
                ship.spawnxpos = 650;
                ship.spawnypos = 300;
            }
            ship.xposition = ship.spawnxpos;
            ship.yposition = ship.spawnypos;
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            enterkey = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightkey = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            leftkey = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            upkey = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            ship.yspeed = 5;
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            spacekey = true;
        }

        // For Ship 2
        if (singlePlayer == false) {
            if (ke.getKeyCode() == KeyEvent.VK_A) {
                leftkey2 = true;
            }
            if (ke.getKeyCode() == KeyEvent.VK_D) {
                rightkey2 = true;
            }
            if (ke.getKeyCode() == KeyEvent.VK_W) {
                upkey2 = true;
            }
            if (ke.getKeyCode() == KeyEvent.VK_S) {
                ship2.yspeed = 5;
            }
            if (ke.getKeyCode() == KeyEvent.VK_E) {
                shootkey = true;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            enterkey = false;
        }

        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightkey = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            leftkey = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            upkey = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            spacekey = false;
        }

        // For Ship 2
        if (singlePlayer == false) {
            if (ke.getKeyCode() == KeyEvent.VK_A) {
                leftkey2 = false;
            }
            if (ke.getKeyCode() == KeyEvent.VK_D) {
                rightkey2 = false;
            }
            if (ke.getKeyCode() == KeyEvent.VK_W) {
                upkey2 = false;
            }
            if (ke.getKeyCode() == KeyEvent.VK_E) {
                shootkey = false;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        keyCheck();
        if (start) {
            ship.updatePosition();
            if (singlePlayer == false) {
                ship2.updatePosition();
            }
            for (int i = 0; i < rockList.size(); i++) {
                rockList.get(i).updatePosition();
                if (rockList.get(i).active == false) {
                    rockList.remove(i);
                }
            }
            for (int i = 0; i < bulletList.size(); i++) {
                bulletList.get(i).updatePosition();

                if (bulletList.get(i).counter == 50 || bulletList.get(i).active == false) {
                    bulletList.remove(i);
                }
            }
            for (int i = 0; i < debrisList.size(); i++) {
                debrisList.get(i).updatePosition();

                if (debrisList.get(i).counter == 25) {
                    debrisList.remove(i);
                }
            }
            checkCollision();
            if (checkRespawn()) {
                ship.respawnShip();
            }
            if (singlePlayer == false) {
                if (checkRespawn2()) {
                    ship2.respawnShip();
                }
            }
        }

    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();

    }

    public boolean collision(VectorSprite thing1, VectorSprite thing2) {
        int x;
        int y;
        for (int i = 0; i < thing1.drawShape.npoints; i++) {
            x = thing1.drawShape.xpoints[i];
            y = thing1.drawShape.ypoints[i];
            if (thing2.drawShape.contains(x, y)) {
                return true;
            }
        }
        for (int i = 0; i < thing2.drawShape.npoints; i++) {
            x = thing2.drawShape.xpoints[i];
            y = thing2.drawShape.ypoints[i];
            if (thing1.drawShape.contains(x, y)) {
                return true;
            }
        }

        return false;
    }

    public void checkCollision() {
        for (int i = 0; i < rockList.size(); i++) {
            if (collision(ship, rockList.get(i)) && ship.active) {
                ship.active = false;
                lives--;
                points -= 100;
                ship.counter = 0;
                for (int k = 0; k < Math.random() * 5 + 5; k++) {
                    debrisList.add(new Debris(ship.xposition, ship.yposition));
                }
            }
            // For Ship 2
            if (singlePlayer == false) {
                if (collision(ship2, rockList.get(i)) && ship2.active) {
                    ship2.active = false;
                    lives2--;
                    points2 -= 100;
                    ship2.counter = 0;
                    for (int k = 0; k < Math.random() * 5 + 5; k++) {
                        debrisList.add(new Debris(ship2.xposition, ship2.yposition));
                    }
                }
            }
            for (int j = 0; j < bulletList.size(); j++) {
                if (collision(rockList.get(i), bulletList.get(j))) {
                    destroyAsteriods(i);
                    rockList.get(i).active = false;
                    bulletList.get(j).active = false;
                    if (bulletList.get(j).ship == 1) {
                        points += 300 - (rockList.get(i).size * 50);
                    } else if (bulletList.get(j).ship == 2) {
                        points2 += 300 - (rockList.get(i).size * 50);
                    }
                    for (int k = 0; k < Math.random() * 5 + 5; k++) {
                        debrisList.add(new Debris(rockList.get(i).xposition, rockList.get(i).yposition));
                    }
                }
            }
        }
    }

    public boolean checkRespawn() {
        int x;
        int y;
        int h;
        if (lives == 0) {
            return false;
        }
        for (int i = 0; i < rockList.size(); i++) {
            x = (int) Math.round(rockList.get(i).xposition - 750);
            y = (int) Math.round(rockList.get(i).yposition - 300);
            h = (int) Math.sqrt(x * x + y * y);
            if (h < 75) {
                return false;
            }
        }
        return true;
    }

    public boolean checkRespawn2() {
        int x;
        int y;
        int h;
        if (lives2 == 0) {
            return false;
        }
        for (int i = 0; i < rockList.size(); i++) {
            x = (int) Math.round(rockList.get(i).xposition - 550);
            y = (int) Math.round(rockList.get(i).yposition - 300);
            h = (int) Math.sqrt(x * x + y * y);
            if (h < 75) {
                return false;
            }
        }
        return true;

    }

    public void destroyAsteriods(int position) {

        for (int i = 0; i <(level/3)+ 1; i++ ){
            if (rockList.get(position).size - 1 > 0) {
                rockList.add(new Rocks(rockList.get(position).xposition, rockList.get(position).yposition, rockList.get(position).size - 1, level));
                rockList.add(new Rocks(rockList.get(position).xposition, rockList.get(position).yposition, rockList.get(position).size - 1, level));
            }
        }

    }

    public void startLevel() {

        for (int i = 0; i < 6; i++) {
            rockList.add(new Rocks(level));
        }
        lives++;
        if (singlePlayer == false) {
            lives2++;
        }
    }

}
 