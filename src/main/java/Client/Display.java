package Client;

import javax.swing.*;

public class Display extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("JustGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        GameObject game = new GameObject(frame);
        game.in_playing = true;
        game.firstClient = true;
        frame.add(game);
        frame.setVisible(true);
    }
}
