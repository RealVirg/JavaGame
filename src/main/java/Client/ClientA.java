package Client;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.TreeMap;

public class ClientA
{
    private final static int ServerPort = 1234;

    public static void main(String args[]) throws UnknownHostException, IOException, InterruptedException {
        JFrame frame = new JFrame("Portal 3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        final GameObject gameObject = new GameObject(frame);
        frame.add(gameObject);
        frame.setVisible(true);
        final boolean[] connection = {false, false};

        final Scanner scanner = new Scanner(System.in);

        final String[] mate = new String[2];
        mate[0] = "nothing"; //если никто не подключен
        mate[1] = "";

        InetAddress ip = InetAddress.getByName("localhost");

        Socket socket = new Socket(ip, ServerPort);

        final DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        final DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        final String[] playerSpell = {"nothing"};

//        Thread sendMessage = new Thread(new Runnable()
//        {
//            @Override
//            public void run() {
//                while (true) {
//
//                    String msg = scanner.nextLine();
//
//                    try {
//                        outputStream.writeUTF(msg);
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
                        String msg = inputStream.readUTF();
                        if (msg.equals("Connection complete. Your mate is client 0"))
                        {
                            mate[0] = "client 0";
                            connection[0] = true;
                            playerSpell[0] = "second_spell";
                        }
                        else if (msg.equals("Connection complete. Your mate is client 1")) {
                            mate[0] = "client 1";
                            connection[0] = true;
                            playerSpell[0] = "first_spell";
                        }
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

            Thread.sleep(1);
            if (mate[0].equals("client 1"))
            {
                gameObject.tmp = 1;
                gameObject.firstClient = true;
            }
            if (gameObject.player1.getSpell().equals("nothing"))
            {
                gameObject.player1.makeSpell(playerSpell[0]);
            }
            if (connection[0] && !connection[1])
            {
                //gameObject.player1.changeX(100);
                //gameObject.player1.changeY(100);
                //gameObject.player2.changeX(100);
                //gameObject.player2.changeY(100);
                gameObject.in_playing = true;
                connection[1] = true;
            }
            // System.out.println(mate[1]);
            String currentMessage = mate[1];
            if (currentMessage.length() != 0 && !mate[0].equals("nothing"))
            {
                String[] ar = currentMessage.split(" ");
                gameObject.player2.changeX(Double.parseDouble(ar[0]));
                gameObject.player2.changeY(Double.parseDouble(ar[1]));
                if (mate[0].equals("client 0"))
                {
                    gameObject.cube.changeX(Double.parseDouble(ar[2]));
                    gameObject.cube.changeY(Double.parseDouble((ar[3])));
                    if (Integer.parseInt(ar[4]) != gameObject.currentLvl)
                    {
                        gameObject.changeLevel = true;
                    }
                }
                if (mate[0].equals("client 1"))
                {
                    if (gameObject.player1.usingSpell)
                    {
                        gameObject.player1.usingSpell = false;
                        gameObject.cube.changeGravity();
                    }
                    if (ar[3].equals("1"))
                    {
                        gameObject.recreateLevels();
                        gameObject.loadLevel(gameObject.currentLvl);
                    }
                    if (ar[2].equals("1"))
                    {
                        if (gameObject.player2.getX() > gameObject.cube.getX())
                            gameObject.cube.force(Direction.LEFT, true, gameObject.room);
                        else
                            gameObject.cube.force(Direction.RIGHT, true, gameObject.room);

                    }
                }
            }

            //main game method

            if (!mate[0].equals("nothing"))
            {
                if (mate[0].equals("client 1"))
                {
                    outputStream.writeUTF(gameObject.player1.getX() + " " + gameObject.player1.getY() + " " + gameObject.cube.getX() + " " +
                            gameObject.cube.getY() + " " + gameObject.currentLvl + "#" + mate[0]);
                }
                else if (mate[0].equals("client 0"))
                {
                    if (!gameObject.player1.usingSpell) {
                        if (gameObject.player1.restartLevel) {
                            outputStream.writeUTF(gameObject.player1.getX() + " " + gameObject.player1.getY() + " 0" + " 1" + "#" + mate[0]);
                            gameObject.recreateLevels();
                            gameObject.loadLevel(gameObject.currentLvl);
                            gameObject.player1.restartLevel = false;
                        }
                        else {
                            outputStream.writeUTF(gameObject.player1.getX() + " " + gameObject.player1.getY() + " 0" + " 0" + "#" + mate[0]);
                        }
                        Thread.sleep(30);
                    }
                    else {
                        if (gameObject.player1.restartLevel) {
                            outputStream.writeUTF(gameObject.player1.getX() + " " + gameObject.player1.getY() + " 1" + " 1" + "#" + mate[0]);
                            gameObject.recreateLevels();
                            gameObject.loadLevel(gameObject.currentLvl);
                            gameObject.player1.restartLevel = false;
                        }
                        else
                        {
                            outputStream.writeUTF(gameObject.player1.getX() + " " + gameObject.player1.getY() + " 1" + " 0" + "#" + mate[0]);
                        }
                        gameObject.player1.usingSpell = false;
                        Thread.sleep(30);
                    }
                }
            }
        }
    }
}