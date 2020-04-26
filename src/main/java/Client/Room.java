package Client;

import java.util.ArrayList;

public class Room
{
    public int height;
    public int width;

    public ArrayList<Platform> platforms;

    public void addObjects(Platform obj)
    {
        platforms.add(obj);
    }

    public Room(int H, int W)
    {
        platforms = new ArrayList<Platform>();
        height = H;
        width = W;
    }

    public Platform getPlatformTouchedPlayer(Player player)
    {
        for (Platform plt: platforms)
        {
            if (plt.y + 30 >= player.getY() && plt.y - 30 <= player.getY()
                    && plt.x <= player.getX() + 50 && plt.x + plt.width >= player.getX())
                return plt;
        }

        return null;
    }

    public Platform getPlatformTouchedHead(Player player)
    {
        for (Platform plt: platforms)
        {
            if (plt.y + plt.height + 30 >= player.getY() - 50 && plt.y + plt.height - 30 <= player.getY() - 50
                    && plt.x <= player.getX() + 50 && plt.x + plt.width >= player.getX())
                return plt;
        }

        return null;
    }
}
