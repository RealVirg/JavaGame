package Client;

public class Content
{
    private int x;
    private int y;

    private int height;
    private int width;

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
