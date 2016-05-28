import java.applet.Applet;
import java.awt.*;

public class MainClass extends Applet implements Runnable
{
    // variables
    private Thread th;
    private Player player;
    private final Enemy[] enemies= new Enemy[6];
    private Score score;

    // double buffering
    private Image dbImage, background;
    private Graphics dbg;

    public void init()
    {
        int breedte = 800;
        int hoogte = 800;
        setSize(breedte, hoogte);
        setBackground (Color.black);
        background = getImage(getDocumentBase(),"bg.jpg");
        player = new Player(this);
        score = new Score();
        for(int i = 0; i < enemies.length; i++){
            enemies[i] = new Enemy((i*100)+50,(i*100)+150,this);
        }
    }

    public void start ()
    {
        th = new Thread(this);
        th.start ();
    }

    public void stop()
    {
        //noinspection deprecation
        th.stop();
    }

    public void destroy()
    {
        //noinspection deprecation
        th.stop();
    }

    public void run()
    {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        //noinspection InfiniteLoopStatement,InfiniteLoopStatement,InfiniteLoopStatement
        while (true)
        {
            for (Enemy enemy : enemies) {
                enemy.update(this, player);
            }
            player.update(this);
            score.timer();

            // repaint applet
            repaint();

            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
    }

    public void update (Graphics g)
    {
        player.check();
        if (dbImage == null)
        {
            dbImage = createImage (this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics ();
        }

        dbg.setColor (getBackground ());
        dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);

        dbg.setColor (getForeground());
        paint (dbg);

        g.drawImage (dbImage, 0, 0, this);
    }

    public void paint (Graphics g)
    {
        // draw player
        g.drawImage(background,0,101,800,800,this);
        player.paint(g,this);
        for (Enemy enemy : enemies) {
            enemy.paint(g, this);
        }
        score.paint(g);
    }

    //    void Collision(Enemy[]enemies ,Bullet[]bullets) {
//        for(int i=0; i<bullets.length; i++) {
//            int Px = bullets[i].getX_pos();
//            int Py = bullets[i].getY_pos();
//            int Pr = bullets[i].getRadius();;
//
//            for(int x = 0; x < enemies.length; x++) {
//                if (Px - Pr <= enemies[x].getX() && Px + Pr >= enemies[x].getX() && Py - Pr <= enemies[x].getY() && Py + Pr >= enemies[x].getY()) {
//                    System.out.println("Collision detected");
//                }
//            }
//        }
//    }
}
