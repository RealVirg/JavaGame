package Client;

import java.util.Timer;
import java.util.TimerTask;

public class Cube
{
    public int x;
    public int y;
    public int speedX;
    public int speedY = 0;
    public int accelerationY = 0;
    public int size;

    public Cube(int X, int Y)
    {
        this.size = 60;
        this.x = X;
        this.y = Y;
        this.speedX = 0;
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

    public boolean isFalling = false;

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

    public void checkStatus(Room room)
    {
        if (!touchedFloor(room))
            fall(room);
    }
}
