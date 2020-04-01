package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Player
{
    private int x;
    private int y;
    private int speed;
    private int acceleration;

    enum Direction
    {
        UP,
        DOWN,
        RIGHT,
        LEFT,
        NONE
    }

    Direction playerDirection = Direction.NONE;

    public Player(int X, int Y, int Speed, int acc)
    {
        x = X;
        y = Y;
        speed = Speed;
        acceleration = acc;
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

    public int getSpeed() {
        return speed;
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

    public void jump()
    {
        TimerTask task = new TimerTask() {
            @Override
            public void run()
            {
                speed = speed + acceleration;

                y -= speed;

                if (y == 1030)  //GameObject.room.height
                {
                    speed = 20;
                    acceleration = 0;
                    cancel();
                }
            }
        };

        Timer timer = new Timer();
        int delay = 1;
        int period = 20;
        timer.scheduleAtFixedRate(task, delay, period);
    }

    public void move()
    {
        switch(playerDirection) {
            case UP:
                acceleration = -1;
                this.jump();
                break;
            //case DOWN:
            //    y+=speed;
            //    break;
            case LEFT:
                x-=speed;
                break;
            case RIGHT:
                x+=speed;
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
