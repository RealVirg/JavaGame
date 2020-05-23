package Client;

public class Cube
{
    public double x;
    public double y;
    public double speedX;
    public double speedY = 0;
    public double accelerationY = 0;
    public int size;

    public boolean inForce = false;

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

    public void fall(final Room room)
    {
        isFalling = true;
        accelerationY = -0.01;
        if (!touchedFloor(room))
        {
            speedY += accelerationY;

            changeY(y- speedY);
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
        if (y >= room.height)
        {
            y = room.height + 60;
            return true;
        }

        if (speedY > 0 && room.getPlatformTouchedWithHeadCube(this) != null)
        {
            y = room.getPlatformTouchedWithHeadCube(this).y + room.getPlatformTouchedWithHeadCube(this).height + 60;
            return  true;
        }

        if (speedY <= 0 && room.getPlatformTouchedByCube(this) != null)
        {
            y = room.getPlatformTouchedByCube(this).y;
            return true;
        }

        return false;
    }

    public void force(int direction, boolean usingForceSpell, Room room)
    {
        if (!inForce && usingForceSpell)
        {
            inForce = true;
            speedX = 3 * direction;

        }

        if (wallInFrontCube(room, direction))
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

    public boolean wallInFrontCube(Room room, int dir)
    {
        for (Platform wall: room.walls)
        {
            if (dir == -1 && x <= wall.x + wall.width && x + size >= wall.x + wall.width &&
                    (y <= wall.y + wall.height && y >= wall.y))
            {
                x = wall.x + wall.width;
                return true;
            }
            else if (dir == 1 && x + size >= wall.x && x <= wall.x &&
                    (y <= wall.y + wall.height && y >= wall.y))
            {
                x = wall.x - size;
                return true;
            }
        }

        if (dir == -1 && x <= 0)
        {
            x = 0;
            return true;
        }
        else if (dir == 1 && x + size >= room.width)
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

}