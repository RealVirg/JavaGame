package Client;

import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Player
{
    private int x;
    private int y;
    private int speedX;
    private int speedY = 0;
    private int accelerationY = 0;
    public int size;

//    enum Direction
//    {
//        UP,
//        DOWN,
//        RIGHT,
//        LEFT,
//        NONE
//    }

    Direction playerDirection = Direction.NONE;

    private String spell = "nothing";
    public boolean usingSpell = false;

    public void makeSpell(String nameSpell)
    {
        spell = nameSpell;
    }


    public String getSpell()
    {
        return spell;
    }

    public Player(int X, int Y, int speedX, int Size)
    {
        this.size = Size;
        this.x = X;
        this.y = Y;
        this.speedX = speedX;
    }

    public void changeX(int newX)
    {
        x = newX;
    }

    public void changeY(int newY)
    {
        y = newY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public boolean isJumping = false;
    public boolean isFalling = false;

    public void jump(final Room room)
    {
        isJumping = true;
        speedY = 40;
        accelerationY = -4;
        TimerTask task = new TimerTask() {
            @Override
            public void run()
            {
                if (speedY < 0)
                    playerDirection = Direction.DOWN;

                if (speedY > 0 && touchedFloor(room))
                {
                    speedY = 0;
                }

                changeY(y- speedY);

                speedY += accelerationY;

                if (speedY <= 0 && touchedFloor(room))
                {
                    speedY = 0;
                    accelerationY = 0;
                    isJumping = false;
                    cancel();
                }
            }
        };

        Timer timer = new Timer();
        int delay = 1;
        int period = 40;
        timer.scheduleAtFixedRate(task, delay, period);
    }

// Падение с платформы

    public void fall(final Room room)
    {
        isFalling = true;
        accelerationY = -2;
        TimerTask task = new TimerTask() {
            @Override
            public void run()
            {
                speedY += accelerationY;

                changeY(y- speedY);

                if (touchedFloor(room))
                {
                    speedY = 0;
                    accelerationY = 0;
                    isFalling = false;
                    cancel();
                }
            }
        };

        Timer timer = new Timer();
        int delay = 1;
        int period = 50;
        timer.scheduleAtFixedRate(task, delay, period);
    }

    public boolean touchedFloor(Room room)
    {
        if (y >= room.height)
        {
            y = room.height + 50;
            return true;
        }

        Platform tmp1 = room.getPlatformTouchedWithHead(this);
        if (speedY > 0 && tmp1 != null)
        {
            y = tmp1.y + tmp1.height + size;
            return  true;
        }

        Platform tmp2 = room.getPlatformTouchedByPlayer(this);
        if (speedY <= 0 && tmp2 != null)
        {
            y = tmp2.y;
            return true;
        }

        if (speedY <= 0 && room.playerOnCube(this))
        {
            changeY((int)room.cube.getY() - room.cube.size);
            return true;
        }

        return false;
    }

    public boolean wallInFront(Room room, Direction dir)
    {
        for (Platform wall: room.walls)
        {
            if (dir == Direction.LEFT && x <= wall.x + wall.width && x + size >= wall.x + wall.width &&
                    (y <= wall.y + wall.height && y >= wall.y))
            {
                x = wall.x + wall.width;
                return true;
            }
            else if (dir == Direction.RIGHT && x + size >= wall.x && x <= wall.x &&
                    (y <= wall.y + wall.height && y >= wall.y))
            {
                x = wall.x - size;
                return true;
            }
        }

        if (dir == Direction.LEFT && x <= 0)
        {
            x = 0;
            return true;
        }
        else if (dir == Direction.RIGHT && x + size >= room.width)
        {
            x = room.width - size;
            return true;
        }

        if (dir == Direction.LEFT && y == room.cube.getY() &&
                x <= room.cube.getX() + room.cube.size && room.cube.getX() + room.cube.size <= x + size)
        {
            changeX((int)room.cube.getX() + room.cube.size);
            return true;
        }

        if (dir == Direction.RIGHT && y == room.cube.getY() &&
                x + size >= room.cube.getX() && room.cube.getX() >= x)
        {
            changeX((int)room.cube.getX() - size);
            return true;
        }

        return false;
    }

    public void move(Room room)
    {
        switch(playerDirection)
        {
            case UP:
                if (isJumping || isFalling)
                    break;
                this.jump(room);
                break;
            case LEFT:
                if (wallInFront(room, Direction.LEFT))
                    break;
                x-= speedX;
                if (!touchedFloor(room) && !isJumping)
                    this.fall(room);
                break;
            case RIGHT:
                if (wallInFront(room, Direction.RIGHT))
                    break;
                x+= speedX;
                if (!touchedFloor(room) && !isJumping)
                    this.fall(room);
                break;
            default:
                break;
        }
        playerDirection = Direction.NONE;
    }

    public void keyPressed(KeyEvent e)
    {
        System.out.println(e.getKeyCode());
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            playerDirection = Direction.UP;
        }
        //if (key == KeyEvent.VK_S) {
        //    playerDirection = Direction.DOWN;
        //}
        if (key == KeyEvent.VK_A) {
            playerDirection = Direction.LEFT;
        }
        if (key == KeyEvent.VK_D) {
            playerDirection = Direction.RIGHT;
        }
        if (key == KeyEvent.VK_E)
        {
            usingSpell = true;
        }

    }

    public void keyReleased(KeyEvent e)
    {

    }
}