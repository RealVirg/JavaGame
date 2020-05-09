package Client;

import java.util.ArrayList;

public class Room
{
    public int height;
    public int width;

    public ArrayList<Platform> platforms;
    public ArrayList<Button> buttons;

    public void addObjects(Object obj)
    {
        if (obj.getClass() == Platform.class)
            platforms.add((Platform)obj);
        else if (obj.getClass() == Button.class)
            buttons.add((Button)obj);

    }

    public Room(int H, int W)
    {
        buttons = new ArrayList<Button>();
        platforms = new ArrayList<Platform>();
        height = H;
        width = W;
    }

    public void checkAllButtons(Player player)
    {
        for (Button b: buttons)
        {
            if (b.isPressed && ((b.x > player.getX() + player.size || b.x + b.size < player.getX())
                    //&& b.y >= player.getY()  && b.y - 10 <= player.getY()
            ))
            {
                    b.isPressed = false;
                    b.y = b.y - 40;
            }
        }
    }

    public int steppedButtonNumber(Player player)
    {
        for (Button b: buttons)
        {
            if (!b.isPressed && (b.x <= player.getX() + player.size && b.x + b.size >= player.getX() &&
                b.y >= player.getY() && b.y - 10 <= player.getY()))
            {
                b.isPressed = true;
                return b.id;
            }

        }

        return 0;
    }

    public Platform getPlatformTouchedByPlayer(Player player)
    {
        for (Platform plt: platforms)
        {
            if (plt.y + 30 >= player.getY() && plt.y - 30 <= player.getY()
                    && plt.x <= player.getX() + player.size && plt.x + plt.width >= player.getX())
                return plt;
        }

        return null;
    }

    public Platform getPlatformTouchedWithHead(Player player)
    {
        for (Platform plt: platforms)
        {
            if (plt.y + plt.height + 30 >= player.getY() - player.size && plt.y + plt.height - 30 <= player.getY() - player.size
                    && plt.x <= player.getX() + player.size && plt.x + plt.width >= player.getX())
                return plt;
        }

        return null;
    }
}
