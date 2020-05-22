package Client;

public class Platform
{
    public int x;
    public int y;

    public int height;
    public int width;

    public int ID;

    Construction construction = Construction.NONE;

    public Platform(int X, int Y, int H, int W, Construction type, int id)
    {
        height = H;
        width = W;
        construction = type;
        x = X;
        y = Y;
        ID = id;
    }

    public void openTheDoor()
    {
        y = y + 1000;
    }

    public void closeTheDoor()
    {
        y = y - 1000;
    }

}
