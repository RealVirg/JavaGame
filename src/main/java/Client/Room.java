package Client;

import java.util.ArrayList;

public class Room
{
    public int height;
    public int width;

    public ArrayList<Platform> floors;
    public ArrayList<Button> buttons;
    public ArrayList<Platform> walls;
    public Cube cube;

    public int lvlNumber;
    public int roomFinishX;
    public int roomFinishY;
    public ArrayList<Player> players;

    private int startPositionX;
    private int startPositionY;

    public void addObjects(Object obj)
    {
        if (obj.getClass() == Platform.class)
        {
            Platform tmp = (Platform)obj;
            if (tmp.construction == Construction.FLOOR)
                floors.add(tmp);
            else if (tmp.construction == Construction.WALL)
                walls.add(tmp);
        }
        else if (obj.getClass() == Button.class)
            buttons.add((Button)obj);
        else if (obj.getClass() == Cube.class)
            cube = (Cube)obj;
    }

    public void setStart(int X, int Y)
    {
        startPositionX = X;
        players.get(0).changeX(X);
        players.get(1).changeX(X);
        startPositionY = Y;
        players.get(0).changeY(Y);
        players.get(1).changeY(Y);
    }

    public void setFinish(int X, int Y)
    {
        roomFinishX = X;
        roomFinishY = Y;
    }

    public Room(int H, int W)
    {
        buttons = new ArrayList<Button>();
        floors = new ArrayList<Platform>();
        walls = new ArrayList<Platform>();

        players = new ArrayList<Player>();
        players.add(new Player(startPositionX, startPositionY, 20, 50));
        players.add(new Player(startPositionX, startPositionY, 20, 50));

        height = H;
        width = W;
    }

    public boolean reachedFinish(Player player)
    {
        if (roomFinishX > player.getX() - 10 && roomFinishX < player.getX() + player.size + 10 && roomFinishY == player.getY())
            return true;
        return false;
    }

    public void buttonWasUnpressed(Player player)
    {
        for (Button b: buttons)
        {
            if (b.isPressed && (b.x >= player.getX() + player.size || b.x + b.size <= player.getX() || b.y - b.size > player.getY()))
            {
                b.isPressed = false;
                b.y = b.y - 40;
                for (Platform w: walls)
                {
                    if (b.id == w.ID)
                        w.closeTheDoor();
                }
            }
        }
    }

    public void buttonWasPressed(Player player)
    {
        for (Button b: buttons)
        {
            if (!b.isPressed && ((b.x <= player.getX() + player.size && b.x + b.size >= player.getX()
                    && b.y >= player.getY() && b.y - 10 <= player.getY()) ||
                    (b.x <= cube.getX() + cube.size && b.x + b.size >= cube.getX()
                            && b.y >= cube.getY() && b.y - 10 <= cube.getY())))
            {
                b.isPressed = true;
                b.y = b.y + 40;
                for (Platform w: walls)
                {
                    if (b.id == w.ID)
                    {
                        w.openTheDoor();
                        //cube.force(Direction.LEFT, true, this);
                    }
                }
            }
        }
    }

    public Platform getPlatformTouchedByPlayer(Player player)
    {
        for (Platform plt: floors)
        {
            if (plt.y + 30 >= player.getY() && plt.y <= player.getY()
                    && plt.x < player.getX() + player.size && plt.x + plt.width > player.getX())
                return plt;
        }

        for (Platform w: walls)
        {
            if (w.y + 30 >= player.getY() && w.y <= player.getY()
                    && w.x < player.getX() + player.size && w.x + w.width > player.getX())
                return w;
        }

        return null;
    }

    public Platform getPlatformTouchedWithHead(Player player)
    {
        for (Platform plt: floors)
        {
            if (plt.y + plt.height >= player.getY() - player.size && plt.y + plt.height - 30 <= player.getY() - player.size
                    && plt.x < player.getX() + player.size && plt.x + plt.width > player.getX())
                return plt;
        }

        for (Platform w: walls)
        {
            if (w.y + w.height >= player.getY() - player.size && w.y + w.height - 30 <= player.getY() - player.size
                    && w.x < player.getX() + player.size && w.x + w.width > player.getX())
                return w;
        }

        return null;
    }

    // Cube methods

    public Platform getPlatformTouchedByCube(Cube cube)
    {
        for (Platform plt: floors)
        {
            if (plt.y + 30 >= cube.getY() && plt.y - 30 <= cube.getY()
                    && plt.x < cube.getX() + cube.size && plt.x + plt.width > cube.getX())
                return plt;
        }

        for (Platform w: walls)
        {
            if (w.y + 30 >= cube.getY() && w.y - 30 <= cube.getY()
                    && w.x < cube.getX() + cube.size && w.x + w.width > cube.getX())
                return w;
        }

        return null;
    }

    public Platform getPlatformTouchedWithHeadCube(Cube cube)
    {
        for (Platform plt: floors)
        {
            if (plt.y + plt.height + 30 >= cube.getY() - cube.size && plt.y + plt.height - 30 <= cube.getY() - cube.size
                    && plt.x < cube.getX() + cube.size && plt.x + plt.width > cube.getX())
                return plt;
        }

        for (Platform w: walls)
        {
            if (w.y + w.height + 30 >= cube.getY() - cube.size && w.y + w.height - 30 <= cube.getY() - cube.size
                    && w.x < cube.getX() + cube.size && w.x + w.width > cube.getX())
                return w;
        }

        return null;
    }

    public boolean playerOnCube(Player player)
    {
        return ((player.getX() + player.size > cube.getX() && player.getX() + player.size < cube.getX() + cube.size) ||
                (player.getX() > cube.getX() && player.getX() < cube.getX() + cube.size)) &&
                player.getY() <= cube.getY() - cube.size + 25 && player.getY() >= cube.getY() - cube.size - 25;
    }
}
