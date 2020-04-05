package Client;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameObject extends JPanel implements ActionListener
{
    JFrame frame;

    private static int startPositionX;
    private static int startPositionY;

    Image imgPlayer1 = new ImageIcon("player1.jpg").getImage();
    Image imgPlayer2 = new ImageIcon("player2.jpg").getImage();
    Image imgBackground = new ImageIcon("background.jpg").getImage();
    Image imgFloor = new ImageIcon("floor.jpg").getImage();

    Timer timer = new Timer(5, this);

    Player player1;
    Player player2;

    boolean in_playing = false;

    public Room room;

    public GameObject(JFrame Frame) {
        timer.start();
        this.frame = Frame;

        room = new Room(frame.getHeight()-50, frame.getWidth()-50, new ArrayList<Content>());


        startPositionX = 0;
        startPositionY = 50;
        player1 = new Player(startPositionX, startPositionY, 20, 0);
        player2 = new Player(startPositionX, startPositionY, 20, 0);
        Frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player1.keyPressed(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                player1.keyReleased(e);
            }
        });
    }

    public void paint(Graphics g)
    {
        g.drawImage(imgBackground, 0, 0,frame.getWidth(), frame.getHeight(), null);
        if (in_playing) {
            g.drawImage(imgFloor, 100, 900, 100, 50, null);
            g.drawImage(imgPlayer1, player1.getX(), player1.getY(), 50, 50, null);
            g.drawImage(imgPlayer2, player2.getX(), player2.getY(), 50, 50, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        repaint();
        player1.move();
    }

}