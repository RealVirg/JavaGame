package Client;

import java.awt.event.KeyEvent;

public class Cube
{
    public double x;
    public double y;
    public double speedX;
    public double speedY = 0;
    public double accelerationY = 0;
    public int size;
    public boolean changedGravity = false;

    public boolean inForce = false;
    public Direction direction = Direction.NONE;

    public Cube(int X, int Y)
    {
        this.size = 60;
        this.x = X;
        this.y = Y;
        this.speedX = 0;
    }

    public void changeY(double newY)
    {
        y = newY;
    }

    public void changeX(double newX)
    {
        x = newX;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isFalling = false;

    public void changeGravity()
    {
        changedGravity = !changedGravity;
        speedY = 0;
    }

    public void fall(final Room room)
    {
        isFalling = true;
        if (!changedGravity)
            accelerationY = -0.01;
        else
            accelerationY = 0.01;
        if (!touchedFloor(room))
        {
            speedY += accelerationY;

            changeY(y - speedY);
        }
        else
        {
            speedY = 0;
            accelerationY = 0;
            isFalling = false;
        }
    }

    /*
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
*/

    public boolean touchedFloor(Room room)
    {
        if (y >= room.height) {
            y = room.height + size;
            return true;
        }
        if (changedGravity) {
            if (room.getPlatformTouchedWithHeadCube(this) != null) {
                y = room.getPlatformTouchedWithHeadCube(this).y + room.getPlatformTouchedWithHeadCube(this).height + size;
                return true;
            }
        }
        else {
            if (room.getPlatformTouchedByCube(this) != null) {
                y = room.getPlatformTouchedByCube(this).y;
                return true;
            }
        }

        return false;
    }

    public void keyPressed(KeyEvent e)
    {
        System.out.println(e.getKeyCode());
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_E)
        {
            changeGravity();
        }

    }

    public void force(Direction dir, boolean usingForceSpell, Room room)
    {
        int direction = 0;
        if (dir == Direction.LEFT)
            direction = -1;
        else if (dir == Direction.RIGHT)
            direction = 1;

        if (!inForce && usingForceSpell)
        {
            inForce = true;
            speedX = 3 * direction;

        }

        if (wallInFrontCube(room, dir))
        {
            speedX = 0;
            inForce = false;
        }

        if (inForce && speedX * direction > 0)
        {
            changeX(x + speedX);
            speedX -= 0.1 * direction;
            if (speedX * direction <= 0)
            {
                speedX = 0;
                inForce = false;
            }
        }
    }

    public boolean wallInFrontCube(Room room, Direction dir)
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

        return false;
    }



    public void checkStatus(Room room)
    {
        if (!touchedFloor(room))
            fall(room);
    }

    public void keyReleased(KeyEvent e)
    {

    }
}