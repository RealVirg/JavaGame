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

public class Main extends JPanel implements ActionListener{

    Image img = new ImageIcon("2.jpg").getImage();

    Timer timer = new Timer(20, this);

    Player player = new Player();
    Player player2 = new Player();

    JFrame frame;

    public Main(JFrame frame) {
        timer.start();
        this.frame = frame;
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                player.keyReleased(e);
            }
        });
    }

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0,frame.getWidth(), frame.getHeight(), null);
        g.drawImage(img, player.getX(), player.getY(), 100, 50, null);
        g.drawImage(img, player2.getX(), player2.getY(), 100, 50, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        repaint();
        player.move();
    }

}
