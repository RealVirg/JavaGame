package Client;

import java.awt.event.KeyEvent;

public class Player
{
    private int x;
    private int y;
    private int speed;

    enum Direction
    {
        UP,
        DOWN,
        RIGHT,
        LEFT,
        NONE
    }

    Direction playerDirection = Direction.NONE;

    public Player(int X, int Y, int Speed)
    {
        x = X;
        y = Y;
        speed = Speed;
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

    public void jump()
    {

    }

    public void move()
    {
        switch(playerDirection) {
            case UP:
                this.jump();
                break;
            case DOWN:
                y+=speed;
                break;
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

    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            playerDirection = Direction.UP;
        }
        if (key == KeyEvent.VK_S) {
            playerDirection = Direction.DOWN;
        }
        if (key == KeyEvent.VK_A) {
            playerDirection = Direction.LEFT;
        }
        if (key == KeyEvent.VK_D) {
            playerDirection = Direction.RIGHT;
        }

    }

    public void keyReleased(KeyEvent e) {

    }
}
