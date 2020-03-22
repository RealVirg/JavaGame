package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JFrame;

public class ClientA {
    final static int ServerPort = 1234;

    public static void main(String args[]) throws UnknownHostException, IOException
    {
        JFrame frame = new JFrame("JustGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        Main m = new Main(frame);
        frame.add(m);
        frame.setVisible(true);
        
        final Scanner scn = new Scanner(System.in);

        final String[] mate = new String[2];
        mate[0] = "nothing";
        mate[1] = "";

        InetAddress ip = InetAddress.getByName("localhost");

        Socket s = new Socket(ip, ServerPort);

        final DataInputStream dis = new DataInputStream(s.getInputStream());
        final DataOutputStream dos = new DataOutputStream(s.getOutputStream());

//        Thread sendMessage = new Thread(new Runnable()
//        {
//            @Override
//            public void run() {
//                while (true) {
//
//                    String msg = scn.nextLine();
//
//                    try {
//                        dos.writeUTF(msg);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });

        Thread readMessage = new Thread(new Runnable()
        {
            @Override
            public void run() {

                while (true) {
                    try {
                        String msg = dis.readUTF();
                        if (msg.equals("Connection complete. Your mate is client 0"))
                            mate[0] = "client 0";
                        else if (msg.equals("Connection complete. Your mate is client 1"))
                            mate[0] = "client 1";
                        else
                        {
                            mate[1] = msg;
                        }
                        System.out.println(msg);
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }
        });

//        sendMessage.start();
        readMessage.start();
        boolean run = true;
        while (run)
        {
            System.out.println(mate[1]);
            String currentMessage = mate[1];
            if (currentMessage.length() != 0 && !mate[0].equals("nothing"))
            {
                String[] ar = mate[1].split(" ");
                m.player2.changeX(Integer.parseInt(ar[0]));
                m.player2.changeY(Integer.parseInt(ar[1]));
            }


            //main game method

            if (!mate[0].equals("nothing"))
            {
                dos.writeUTF(m.player.getX() + " " + m.player.getY() + "#" + mate[0]);
            }
        }
    }
}
