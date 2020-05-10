package Client;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameObject extends JPanel implements ActionListener
{
    JFrame frame;

    private static int startPositionX;
    private static int startPositionY;

    Image imgPlayer1 = new ImageIcon("sprites/player1.jpg").getImage();
    Image imgPlayer2 = new ImageIcon("sprites/player2.jpg").getImage();
    Image imgBackground = new ImageIcon("sprites/background.jpg").getImage();
    Image imgFloor = new ImageIcon("sprites/floor.jpg").getImage();
    Image imgButton = new ImageIcon("sprites/button.jpg").getImage();

    Timer timer = new Timer(1, this);

    boolean in_playing = false;

    Player player1;
    Player player2;
    public Room room;

    public GameObject(JFrame Frame) {
        timer.start();
        this.frame = Frame;

        //room = new Room(frame.getHeight() - 50, frame.getWidth() - 50);
        room = new Room(1080, 1920);

        room.addObjects(new Platform(0, 850, 50, 400, Construction.FLOOR));
        room.addObjects(new Platform(400, 650, 50, 400, Construction.FLOOR));
        room.addObjects(new Platform(800, 450, 50, 200, Construction.FLOOR));
        room.addObjects(new Platform(0, 400, 50, 400, Construction.FLOOR));
        room.addObjects(new Platform(1500, 650, 50, 100, Construction.FLOOR));
        room.addObjects(new Platform(1700, 850, 50, 220, Construction.FLOOR));

        room.addObjects(new Button(450, 650, 1, 50));
        room.addObjects(new Button(650, 650, 2, 50));

        room.addObjects(new Platform(0, 1030, 50, 1920, Construction.FLOOR));

        startPositionX = 0;
        startPositionY = 1030;

        player1 = new Player(startPositionX, startPositionY, 20, 50);
        player2 = new Player(startPositionX, startPositionY, 20, 50);
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

    public void refresh()
    {

    }

    public void paint(Graphics g)
    {
        g.drawImage(imgBackground, 0, 0,frame.getWidth(), frame.getHeight(), null);
        if (in_playing) {
            for (Platform e: room.platforms)
            {
                g.drawImage(imgFloor, e.x, e.y, e.width, e.height, null);
            }
            for (Button e: room.buttons)
            {
                g.drawImage(imgButton, e.x, e.y - 50, 50, 50, null);
            }
            g.drawImage(imgPlayer1, player1.getX(), player1.getY() - player1.size, player1.size, player1.size, null);
            g.drawImage(imgPlayer2, player2.getX(), player2.getY() - player2.size, player2.size, player2.size, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        repaint();
        player1.move(room);
    }

}