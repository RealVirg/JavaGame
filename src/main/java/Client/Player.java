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

    enum Direction
    {
        UP,
        DOWN,
        RIGHT,
        LEFT,
        NONE
    }

    Direction playerDirection = Direction.NONE;

    public Player(int X, int Y, int speedX)
    {
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
        speedY = 50;
        accelerationY = -5;
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
        int period = 35;
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

        if (speedY > 0 && room.getPlatformTouchedHead(this) != null)
        {
            y = room.getPlatformTouchedHead(this).y + room.getPlatformTouchedHead(this).height + 50;
            return  true;
        }

        if (speedY <= 0 && room.getPlatformTouchedPlayer(this) != null)
        {
            y = room.getPlatformTouchedPlayer(this).y;
            return true;
        }

        return false;
    }

    public boolean nearEdge(Room room, Direction dir)
    {
        if (dir == Direction.LEFT && x <= 0)
            return true;
        else return dir == Direction.RIGHT && x >= room.width - 50;
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
            //case DOWN:
            //    y+=speed;
            //    break;
            case LEFT:
                if (nearEdge(room, Direction.LEFT))
                    break;
                x-= speedX;
                if (!touchedFloor(room) && !isJumping)
                    this.fall(room);
                break;
            case RIGHT:
                if (nearEdge(room, Direction.RIGHT))
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