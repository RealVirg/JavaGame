package Client;

import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Player
{
    private int x;
    private int y;
    private int speedX;
    private int speedY;
    private int accelerationY;

    enum Direction
    {
        UP,
        DOWN,
        RIGHT,
        LEFT,
        NONE
    }

    Direction playerDirection = Direction.NONE;

    public Player(int X, int Y, int speedX, int speedY, int acc)
    {
        this.x = X;
        this.y = Y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.accelerationY = acc;
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
        final Timer timer;
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                y -= speed;
                speed -= acceleration;

            }
        });
        if (speed<=0)
            timer.setRepeats(false);
        timer.start();
    }
     */

    public boolean inJumping = false;

    public void jump()
    {
        inJumping = true;
        speedY = 25;
        accelerationY = -1;
        TimerTask task = new TimerTask() {
            @Override
            public void run()
            {
                speedY += accelerationY;

                changeY(y- speedY);

                if (speedY == -24 || onFloor())  //GameObject.room.height
                {
                    speedY = 0;
                    accelerationY = 0;
                    inJumping = false;
                    cancel();
                }
            }
        };

        Timer timer = new Timer();
        int delay = 1;
        int period = 20;
        timer.scheduleAtFixedRate(task, delay, period);
    }

    public boolean onFloor()
    {
        for (Content e: Room.content)
        {
            if (y == e.y && x >= e.x && x <= e.x + e.width)
                return true;
        }

       return false;
    }

    public boolean nearEdge(Direction dir)
    {
       if (dir == Direction.LEFT && x == 0)
           return true;
       else if (dir == Direction.RIGHT && x == Room.width - 50)
           return true;
       else
           return false;
    }

    public void move()
    {
        switch(playerDirection) {
            case UP:
                if (inJumping)
                    break;
                this.jump();
                break;
            //case DOWN:
            //    y+=speed;
            //    break;
            case LEFT:
                if (nearEdge(Direction.LEFT))
                    break;
                x-= speedX;
                break;
            case RIGHT:
                if (nearEdge(Direction.RIGHT))
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
