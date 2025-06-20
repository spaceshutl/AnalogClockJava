package main;

import javax.swing.JFrame;



public class Main
{
    public static void main(String[] args)
    {
        JFrame window = new JFrame();
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Clock");

        ClockPanel clockPanel = new ClockPanel();
        
        window.add(clockPanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        clockPanel.startGameThread();
    }    
}