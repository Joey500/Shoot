import java.awt.*;
import java.net.URL;

class Enemy {
    private int x=100;
    private int y=50;
    private final int radius=80;
    private int speed=20;
    private final Image enemy;
    private final Bullet[] bullets;

     Enemy(int i, int j, MainClass mc) {
        URL url = mc.getDocumentBase();
        enemy = mc.getImage(url,"resources/Boss.gif");
        x=i;
        y=j;
        bullets = new Bullet[1];
    }

    private int getX() {
        return x;
    }

    private int getY() {
        return y;
    }

     void update(MainClass mc, Player p){
        x+=speed;
        if (x<=0){
            speed = 15;
        }else if(x>= mc.getWidth()-radius) {
            speed = -speed;
        }
        for(int i=0; i<bullets.length; i++)
        {
            if(bullets[i] != null)
            {
                // move shot
                int shotSpeed = 25;
                bullets[i].moveEnemyShot(shotSpeed);
                // test if shot is out
                if(bullets[i].getYPos() > 890)
                {
                    // remove shot from array
                    bullets[i] = null;
                }
            }
        }
        try
        {
            Thread.sleep(5);
        }
        catch (InterruptedException ex)
        {
            // do nothing
        }

        for(int i=0; i<bullets.length; i++)
        {
            if(bullets[i] == null)
            {
                bullets[i] = generateShot();
                break;
            }
        }
        try
        {
            Thread.sleep(5);
        }
        catch (InterruptedException ex)
        {
            // do nothing
        }

        Collision(p);
    }

    private Bullet generateShot()
    {
        return new Bullet(getX(),getY()+50);
    }

    private void Collision(Player p) {
        int Px = p.getX();
        int Py = p.getY();
        int Pr = p.getRadius();

        if (Px-Pr <= x && Px+Pr >= x && Py-Pr <= y && Py+Pr >= y){
            p.restart();
            System.out.println("Collision detected");
        }

    }

     void paint(Graphics g, MainClass mc){
        g.drawImage(enemy,x,y,radius,radius,mc);
        for (Bullet bullet : bullets) {
            if (bullet != null) {
                bullet.drawEnemyBullet(g);
            }
            try {
                Thread.sleep(0);
            } catch (InterruptedException ex) {
                // do nothing
            }
        }
    }
}
