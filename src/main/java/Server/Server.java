package Server;

import java.io.*;
import java.util.*;
import java.net.*;

public class Server
{

    static Vector<ClientHandler> ar = new Vector<ClientHandler>();

    static int i = 0;

    public static void main(String[] args) throws IOException
    {
        ServerSocket ss = new ServerSocket(1234);

        Socket s;

        while (true)
        {
            s = ss.accept();

            System.out.println("New client request received : " + s);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.println("Creating a new handler for this client...");

            ClientHandler mtch = new ClientHandler(s,"client " + i, dis, dos);

            Thread t = new Thread(mtch);

            System.out.println("Adding this client to active client list");

            ar.add(mtch);

            t.start();

            i++;

            if (i == 2)
            {
                int l = 1;
                for (ClientHandler mc : ar)
                {
                    mc.dos.writeUTF("Connection complete. Your mate is client " + l);
                    l--;
                }
            }
        }
    }
}
