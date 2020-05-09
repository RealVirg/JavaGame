package Client;

public class Button
{
    public int x;
    public int y;
    public int size;

    public int id;

    public boolean isPressed;

    Construction construction = Construction.BUTTON;

    public Button(int X, int Y, int ID, int Size)
    {
        size = Size;
        id = ID;
        x = X;
        y = Y;
        isPressed = false;
    }

}
