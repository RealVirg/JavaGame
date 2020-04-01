package Client;

import java.util.ArrayList;

public class Room
{

    public int height;
    public int width;

    public ArrayList<Content> content;

    public Room(int H, int W, ArrayList<Content> cont)
    {
        height = H;
        width = W;
        content = cont;
    }
}
