package Client;

public class Content
{
    public int x;
    public int y;

    public int height;
    public int width;

    enum Construction
    {
        FLOOR,
        WALL,
        NONE
    }

    Construction construction = Construction.NONE;

    public Content(int X, int Y, int H, int W, Construction type)
    {
        height = H;
        width = W;
        construction = type;
        x = X;
        y = Y;
    }
}
