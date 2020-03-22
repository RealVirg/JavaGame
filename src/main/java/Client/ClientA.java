package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientA {
    final static int ServerPort = 1234;

    public static void main(String args[]) throws UnknownHostException, IOException
    {
        final Scanner scn = new Scanner(System.in);

        String mate = "nothing";

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

//        Thread readMessage = new Thread(new Runnable()
//        {
//            @Override
//            public void run() {
//
//                while (true) {
//                    try {
//                        String msg = dis.readUTF();
//                        if (msg.equals("Connection complete. Your mate is client 0"))
//                            mate[0] = "client 0";
//                        if (msg.equals("Connection complete. Your mate is client 1"))
//                            mate[0] = "client 1";
//                        System.out.println(msg);
//                    } catch (IOException e) {
//
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });

//        sendMessage.start();
//        readMessage.start();
        boolean run = true;
        while (run)
        {
            String currentMessage = "";
            try {
                String msg = dis.readUTF();
                if (msg.equals("Connection complete. Your mate is client 0"))
                    mate = "client 0";
                else if (msg.equals("Connection complete. Your mate is client 1"))
                    mate = "client 1";
                else {
                    currentMessage = msg;
                }
                System.out.println(msg);
            } catch (IOException e) {

                e.printStackTrace();
            }
            if (currentMessage.length() != 0 && !mate.equals("nothing"))
            {
                //изменение переменных от сообщения
            }

            
            //main game method


            if (!mate.equals("nothing"))
            {
                dos.writeUTF("test#" + mate);
            }
        }
    }
}
