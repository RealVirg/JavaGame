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

    enum Direction
    {
        UP,
        DOWN,
        RIGHT,
        LEFT,
        NONE
    }

    Direction playerDirection = Direction.NONE;

    private String spell = "nothing";

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

        if (speedY > 0 && room.getPlatformTouchedWithHead(this) != null)
        {
            y = room.getPlatformTouchedWithHead(this).y + room.getPlatformTouchedWithHead(this).height + 50;
            return  true;
        }

        if (speedY <= 0 && room.getPlatformTouchedByPlayer(this) != null)
        {
            y = room.getPlatformTouchedByPlayer(this).y;
            return true;
        }

        return false;
    }

    public boolean wallInFront(Room room, Direction dir)
    {
        for (Platform w: room.walls)
        {
            if (dir == Direction.LEFT && x <= w.x + w.width && x + size >= w.x + w.width &&
                    (y <= w.y + w.height && y >= w.y))
            {
                x = w.x + w.width;
                return true;
            }
            else if (dir == Direction.RIGHT && x + size >= w.x && x <= w.x &&
                    (y <= w.y + w.height && y >= w.y))
            {
                x = w.x - size;
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

        if (key == KeyEvent.VK_W) {
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

    }

    public void keyReleased(KeyEvent e)
    {

    }
}