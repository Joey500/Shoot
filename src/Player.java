import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

class Player implements KeyListener{

    private int x=350;
    private int y=775;
    private final int radius=100;
    private int velX=0;
    private int velY=0;
    private final Image player;
    private final Bullet[] bullets;

    Player(MainClass mc) {
        URL url = mc.getDocumentBase();
        player = mc.getImage(url, "resources/Player.gif");
        x = 350;
        y = 775;
        bullets = new Bullet[50000];
    }

    void update(MainClass mc){
        mc.  addKeyListener(this);
        x+=velX;
        y+=velY;

        // do operations on shots in shots array
        for(int i=0; i<bullets.length; i++)
        {
            if(bullets[i] != null)
            {
                // move shot
                int shotSpeed = -15;
                bullets[i].moveShot(shotSpeed);
                // test if shot is out
                if(bullets[i].getYPos() < 102)
                {
                    // remove shot from array
                    bullets[i] = null;
                }
            }
        }
        try
        {
            Thread.sleep(10);
        }
        catch (InterruptedException ex)
        {
            // do nothing
        }
    }

    private Bullet generateShot()
    {
        return new Bullet(getX(),getY());
    }

    void paint(Graphics g, MainClass mc){
        g.drawImage(player,x,y,radius,radius,mc);

        for (Bullet bullet : bullets) {
            if (bullet != null) {
                bullet.drawBullet(g);
            }
            try {
                Thread.sleep(0);
            } catch (InterruptedException ex) {
                // do nothing
            }
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                velY =-15;
                break;
            case KeyEvent.VK_DOWN:
                velY =15;
                break;
            case KeyEvent.VK_LEFT:
                velX =-15;
                break;
            case KeyEvent.VK_RIGHT:
                velX =15;
                break;
            case KeyEvent.VK_SPACE:
                for(int i=0; i<bullets.length; i++)
                {
                    if(bullets[i] == null)
                    {
                        System.out.print("kogels:" +i+"\t\r");
                        System.out.print("kogels:" +i+"\t");
                        bullets[i] = generateShot();
                        break;
                    }
                }
                break;

        }
    }

    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                velY =0;
                break;
            case KeyEvent.VK_DOWN:
                velY =0;
                break;
            case KeyEvent.VK_LEFT:
                velX =0;
                break;
            case KeyEvent.VK_RIGHT:
                velX =0;
                break;
            case KeyEvent.VK_SPACE:
                break;
        }
    }

     int getY() {
        return y;
    }

     int getX() {
        return x;
    }

     int getRadius() {
        return radius;
    }

     void restart() {
        x=350;
        y=775;
    }
     void check(){
        if (x >= 700){
            x = 700;
        }if(x <= 0){
            x =0;
        }if(y >= 800){
            y = 800;
        }if(y <= 105){
            y = 105;
        }

    }
}