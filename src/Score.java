import java.awt.*;

class Score{
    private final int score;
    private int time;

     Score() {
        score=0;
        time=0;
//        int bonus = 5;
    }

     void paint(Graphics g){
        String s=Integer.toString(score);
        String t=Integer.toString(time);
        Font f=new Font("Arial",Font.BOLD, 50);
        g.setFont(f);
        g.setColor(Color.GREEN);
        g.drawString("SPACESHOOTER",200,80);
        g.drawString("_____________________________",0,100);
        g.drawString("_____________________________",0,900);
        g.drawString("Score:\t"+s,50,975);
        g.drawString("Time:\t"+t,500,975);
    }
     void timer(){
        time++;
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public void update(MainClass mc) {
//        score=score+bonus;
//    }
}