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


    /*
    public void jump()
    {
        inJumping = true;
        speedY = 25;
        accelerationY = -1;


    }
     */

    public boolean inJumping = false;
    public boolean inFalling = false;

    public void jump(final Room room)
    {
        inJumping = true;
        speedY = 80;
        accelerationY = -10;
        TimerTask task = new TimerTask() {
            @Override
            public void run()
            {
                speedY += accelerationY;

                changeY(y- speedY);

                if (speedY < 0 && onFloor(room))
                {
                    speedY = 0;
                    accelerationY = 0;
                    inJumping = false;
                    cancel();
                }
            }
        };

        Timer timer = new Timer();
        int delay = 2;
        int period = 32;
        timer.scheduleAtFixedRate(task, delay, period);
    }

    public void fall(final Room room)
    {
        inFalling = true;
        accelerationY = -10;
        TimerTask task = new TimerTask() {
            @Override
            public void run()
            {
                speedY += accelerationY;

                changeY(y- speedY);

                if (onFloor(room))
                {
                    speedY = 0;
                    accelerationY = 0;
                    inFalling = false;
                    cancel();
                }
            }
        };

        Timer timer = new Timer();
        int delay = 2;
        int period = 32;
        timer.scheduleAtFixedRate(task, delay, period);
    }

    public boolean onFloor(Room room)
    {
        if (y == room.height)
            return true;

        for (Platform e: room.platforms)
        {
            if (y == e.y && x >= e.x && x <= e.x + e.width)
                return true;
        }

       return false;
    }

    public boolean outOfPlatform(Room room)
    {
        Platform tmp = room.getPlatformUnderPlayer(this);
        if (!onFloor(room) && (x < tmp.x || x > tmp.x + tmp.width ))
            return true;
        else
            return false;
    }

    public boolean nearEdge(Room room, Direction dir)
    {
       if (dir == Direction.LEFT && x == 0)
           return true;
       else if (dir == Direction.RIGHT && x == room.width - 50)
           return true;
       else
           return false;
    }

    public void move(Room room)
    {
        switch(playerDirection) {
            case UP:
                if (inJumping)
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
                break;
            case RIGHT:
                if (nearEdge(room, Direction.RIGHT))
                    break;
                x+= speedX;
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
