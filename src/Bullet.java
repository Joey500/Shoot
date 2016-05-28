import java.awt.*;

class Bullet {
    private final int x_pos;
    private int y_pos;

     Bullet(int x, int y)
    {
        x_pos = x+42;
        y_pos = y;
    }

     int getYPos()
    {
        return y_pos;
    }

     void moveShot(int speed)
    {
        y_pos += speed;
    }

     void moveEnemyShot(int speed)
    {
        y_pos += speed;
    }

     void drawBullet(Graphics g)
    {
        g.setColor(Color.YELLOW);
        int radius = 10;
        g.fillOval(x_pos,y_pos, radius, radius);
    }
     void drawEnemyBullet(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillOval(x_pos,y_pos,15,15);
    }
}
