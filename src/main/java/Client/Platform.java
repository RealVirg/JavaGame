package Client;

class Platform
{
    int x;
    int y;

    int height;
    int width;

    int ID;

    Construction construction = Construction.NONE;

    Platform(int X, int Y, int H, int W, Construction type, int id)
    {
        height = H;
        width = W;
        construction = type;
        x = X;
        y = Y;
        ID = id;
    }

    void openTheDoor()
    {
        y = y + 1000;
    }

    void closeTheDoor()
    {
        y = y - 1000;
    }

}
