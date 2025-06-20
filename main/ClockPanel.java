package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;



public class ClockPanel extends JPanel implements Runnable   
{
    Thread clockThread;

    int x = 250, y = 250, secLength = 200, minLength = 150, hourLength = 100,
    secDegrees = ((int)((((System.currentTimeMillis()+10800000L)%86400000L)/1000)%60)*6)-6,
    minDegrees = ((int)((((System.currentTimeMillis()+10800000L)%86400000L)/60000)%60)*6),
    houDegrees = ((int)((((System.currentTimeMillis()+10800000L)%86400000L)/3600000)%60)*30);
    long previous = 0;



    public ClockPanel()
    {
        this.setPreferredSize(new Dimension(500, 500));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }



    public void startGameThread()
    {
        clockThread = new Thread(this);
        clockThread.start();
    }



    @Override
    public void run()
    {
        while (clockThread != null)
        {
            update();
            repaint();
        }
    }



    public void update()
    {
        // Updates only if a second has passed
        if(previous < System.currentTimeMillis()/1000)
        {
            secDegrees += 6;
            if (secDegrees == 360)
            {
                secDegrees = 0;
                minDegrees += 6;  
            }
            if (minDegrees == 360)
            {
                minDegrees = 0;
                houDegrees += 30;
            }
            if (houDegrees == 360)
            {
                houDegrees = 0;    
            }
            previous = System.currentTimeMillis()/1000;
            // System.out.println(((((System.currentTimeMillis()+10800000L)%86400000L)/1000)%60)); // UTC time + 3h % 24h / 1000 % 60min
        }
    }



    public void paintComponent(Graphics g)
    {
        // Takes parent class of this class (takes JPanel class)
        super.paintComponent(g);
        // Takes Grpahics2D which is a child class of Graphics
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        
        // Seconds
        g2.drawLine(x, y, x + (int) Math.round(secLength*Math.cos((Math.PI/180)*(secDegrees-90))), y + (int) Math.round(secLength*Math.sin((Math.PI/180)*(secDegrees-90))));
        // Minutes
        g2.drawLine(x, y, x + (int) Math.round(minLength*Math.cos((Math.PI/180)*(minDegrees-90))), y + (int) Math.round(minLength*Math.sin((Math.PI/180)*(minDegrees-90))));
        // Hours
        g2.drawLine(x, y, x + (int) Math.round(hourLength*Math.cos((Math.PI/180)*(houDegrees-90))), y + (int) Math.round(hourLength*Math.sin((Math.PI/180)*(houDegrees-90))));
        // Frame
        g2.drawOval(x-secLength, y-secLength, secLength*2, secLength*2);
        
        g2.dispose();
    }
}
