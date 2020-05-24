package Client;

import java.awt.event.KeyEvent;

public class Player
{
    private double x;
    private double y;
    private double speedX;
    private double speedY = 0;
    int size;

//    enum Direction
//    {
//        UP,
//        DOWN,
//        RIGHT,
//        LEFT,
//        NONE
//    }

    private Direction playerDirection = Direction.NONE;

    private String spell = "nothing";
    boolean usingSpell = false;

    void makeSpell(String nameSpell)
    {
        spell = nameSpell;
    }


    String getSpell()
    {
        return spell;
    }

    Player(int X, int Y, int speedX, int Size)
    {
        this.size = Size;
        this.x = X;
        this.y = Y;
        this.speedX = speedX;
    }

    void changeX(double newX)
    {
        x = newX;
    }

    void changeY(double newY)
    {
        y = newY;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    private boolean isJumping = false;
    private boolean isFalling = false;

//    public void jump(final Room room)
//    {
//        isJumping = true;
//        speedY = 40;
//        accelerationY = -4;
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run()
//            {
//                if (speedY < 0)
//                    playerDirection = Direction.DOWN;
//
//                if (speedY > 0 && touchedFloor(room))
//                {
//                    speedY = 0;
//                }
//
//                changeY(y- speedY);
//
//                speedY += accelerationY;
//
//                if (speedY <= 0 && touchedFloor(room))
//                {
//                    speedY = 0;
//                    accelerationY = 0;
//                    isJumping = false;
//                    cancel();
//                }
//            }
//        };
//
//        Timer timer = new Timer();
//        int delay = 1;
//        int period = 40;
//        timer.scheduleAtFixedRate(task, delay, period);
//    }

// Падение с платформы

    private void fall(Room room)
    {
        double accelerationY = -0.01;
        if (!touchedFloor(room))
        {
            isJumping = true;
            speedY += accelerationY;

            changeY(y - speedY);
        }
        else
        {
            speedY = 0;
            accelerationY = 0;
            isFalling = false;
            isJumping = false;
        }
    }

//    public void fall(final Room room)
//    {
//        isFalling = true;
//        accelerationY = -2;
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run()
//            {
//                speedY += accelerationY;
//
//                changeY(y- speedY);
//
//                if (touchedFloor(room))
//                {
//                    speedY = 0;
//                    accelerationY = 0;
//                    isFalling = false;
//                    cancel();
//                }
//            }
//        };
//
//        Timer timer = new Timer();
//        int delay = 1;
//        int period = 50;
//        timer.scheduleAtFixedRate(task, delay, period);
//    }

    private boolean touchedFloor(Room room)
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

    private boolean wallInFront(Room room, Direction dir)
    {
        for (Platform wall: room.floors)
        {
            if (dir == Direction.LEFT && x < wall.x + wall.width && x + size > wall.x + wall.width &&
                    (y < wall.y + wall.height && y > wall.y))
            {
                x = wall.x + wall.width;
                return true;
            }
            else if (dir == Direction.RIGHT && x + size > wall.x && x < wall.x &&
                    (y < wall.y + wall.height && y > wall.y))
            {
                x = wall.x - size;
                return true;
            }
        }

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
                x >= room.cube.getX() + room.cube.size && room.cube.getX() + room.cube.size + 30 >= x)
        {
            changeX((int)room.cube.getX() + room.cube.size);
            return true;
        }

        if (dir == Direction.RIGHT && y == room.cube.getY() &&
                x + size <= room.cube.getX() && room.cube.getX() - 30 <= x + size)
        {
            changeX((int)room.cube.getX() - size);
            return true;
        }

        return false;
    }

    void move(Room room)
    {
        switch(playerDirection)
        {
            case UP:
//                if (isJumping || isFalling)
//                    break;
//                this.jump(room);
                break;
            case LEFT:
                if (wallInFront(room, Direction.LEFT))
                    break;
                x-= speedX;
//                if (!touchedFloor(room) && !isJumping)
//                    this.fall(room);
                break;
            case RIGHT:
                if (wallInFront(room, Direction.RIGHT))
                    break;
                x+= speedX;
//                if (!touchedFloor(room) && !isJumping)
//                    this.fall(room);
                break;
            default:
                break;
        }
        playerDirection = Direction.NONE;
    }

    void keyPressed(KeyEvent e)
    {
        System.out.println(e.getKeyCode());
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            if (!isJumping) {
                speedY = 2;
                isJumping = true;
            }
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

    void checkStatus(Room room)
    {
        fall(room);
    }

    void keyReleased(KeyEvent e)
    {

    }
}