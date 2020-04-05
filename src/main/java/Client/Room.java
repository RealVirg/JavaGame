package Client;

import java.util.ArrayList;

public class Room
{

    public static int height;
    public static int width;

    public static ArrayList<Content> content;

    public void addObjects(Content obj)
    {
        content.add(obj);
    }

    public Room(int H, int W)
    {
        content = new ArrayList<Content>();
        height = H;
        width = W;
    }
}
