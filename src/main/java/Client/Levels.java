package Client;

import java.util.ArrayList;

class Levels
{
    static ArrayList<Room> levels = new ArrayList<Room>();

    public static int levelsCount = 5;

    private static void createLevel0()
    {

    }

    private static void createLevel1()
    {
        Room lvl = new Room(1080, 1920);

        lvl.setFinish(1400, 660);
        lvl.setStart(50,1030);

        lvl.addObjects(new Platform(0, 1030, 50, 1920, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(520, 900, 50, 500, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(970, 660, 50, 500, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(790, 770, 50, 100, Construction.FLOOR, 0));

        lvl.addObjects(new Platform(520, 950, 80, 50, Construction.WALL, 1));
        lvl.addObjects(new Platform(820, 820, 80, 50, Construction.WALL, 1));
        lvl.addObjects(new Platform(970, 710, 190, 50, Construction.WALL, 1));

        lvl.addObjects(new Platform(2000, 2000, 50, 500, Construction.FLOOR, 0));
        lvl.addObjects(new Cube(2050, 2000));

        lvl.lvlNumber = 1;

        levels.add(lvl);
    }

    private static void createLevel2()
    {
        Room lvl = new Room(1080, 1920);

        lvl.setFinish(1830, 1030);
        lvl.setStart(50,1030);

        lvl.addObjects(new Platform(0, 1030, 50, 1920, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(0, 0, 50, 1920, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(820, 280, 50, 700, Construction.FLOOR, 0));

        lvl.addObjects(new Platform(1220, 330, 600, 50, Construction.WALL, 0));
        lvl.addObjects(new Platform(1220, 930, 100, 50, Construction.WALL, 1));

        lvl.addObjects(new Button(1220, 280, 1, 50));

        lvl.addObjects(new Cube(400, 1030));

        lvl.lvlNumber = 2;

        levels.add(lvl);
    }

    private static void createLevel3()
    {
        Room lvl = new Room(1080, 1920);

        lvl.setFinish(1830, 1030);
        lvl.setStart(50,1030);

        lvl.addObjects(new Platform(0, 1030, 50, 1920, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(800, 800, 50, 1920, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(450, 900, 50, 200, Construction.FLOOR, 0));

        lvl.addObjects(new Platform(800, 850, 180, 50, Construction.WALL, 1));

        lvl.addObjects(new Button(1300, 1030, 1, 50));

        lvl.addObjects(new Cube(1600, 1030));

        lvl.lvlNumber = 3;

        levels.add(lvl);
    }

    private static void createLevel4()
    {
        Room lvl = new Room(1080, 1920);

        lvl.setFinish(1500, 300);
        lvl.setStart(100,300);

        lvl.addObjects(new Platform(0, 1030, 50, 1920, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(0, 0, 50, 1920, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(0, 300, 50, 1920, Construction.FLOOR, 0));

        lvl.addObjects(new Platform(0, 700, 50, 600, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(600, 610, 50, 400, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(1000, 450, 50, 400, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(1200, 600, 50, 400, Construction.FLOOR, 0));


        lvl.addObjects(new Platform(300, 860, 170, 50, Construction.WALL, 0));
        lvl.addObjects(new Platform(600, 660, 170, 50, Construction.WALL, 0));
        lvl.addObjects(new Platform(800, 760, 270, 50, Construction.WALL, 0));
        lvl.addObjects(new Platform(1000, 500, 200, 50, Construction.WALL, 0));
        lvl.addObjects(new Platform(1200, 650, 380, 50, Construction.WALL, 0));

        lvl.addObjects(new Platform(500, 50, 250, 50, Construction.WALL, 1));
        lvl.addObjects(new Platform(1400, 450, 150, 50, Construction.WALL, 2));
        lvl.addObjects(new Platform(1000, 50, 250, 50, Construction.WALL, 3));

        lvl.addObjects(new Button(1250, 600, 1, 50));
        lvl.addObjects(new Button(750, 300, 2, 50));
        lvl.addObjects(new Button(1700, 1030, 3, 50));

        lvl.addObjects(new Cube(150, 1030));

        lvl.lvlNumber = 4;

        levels.add(lvl);
    }

    private static void createLevel5()
    {
        Room lvl = new Room(1080, 1920);

        lvl.setFinish(1500, 1030);
        lvl.setStart(800,400);

        lvl.addObjects(new Platform(0, 1030, 50, 1920, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(0, 0, 50, 1920, Construction.FLOOR, 0));

        lvl.addObjects(new Platform(0, 400, 50, 300, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(1150, 400, 50, 770, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(750, 880, 50, 50, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(920, 700, 50, 50, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(1050, 550, 50, 50, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(750, 400, 50, 200, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(1500, 150, 50, 150, Construction.FLOOR, 0));

        lvl.addObjects(new Platform(300, 50, 200, 50, Construction.WALL, 0));
        lvl.addObjects(new Platform(300, 400, 630, 50, Construction.WALL, 0));
        lvl.addObjects(new Platform(700, 50, 880, 50, Construction.WALL, 0));
        lvl.addObjects(new Platform(1100, 50, 200, 50, Construction.WALL, 0));
        lvl.addObjects(new Platform(1100, 400, 530, 50, Construction.WALL, 0));
        lvl.addObjects(new Platform(1450, 300, 100, 50, Construction.WALL, 0));
        lvl.addObjects(new Platform(1450, 50, 150, 50, Construction.WALL, 0));

        lvl.addObjects(new Platform(700, 930, 100, 50, Construction.WALL, 1));
        lvl.addObjects(new Platform(1100, 930, 100, 50, Construction.WALL, 2));

        lvl.addObjects(new Button(1550, 150, 1, 50));
        lvl.addObjects(new Button(50, 400, 2, 50));

        lvl.addObjects(new Cube(1500, 400));

        lvl.lvlNumber = 5;

        levels.add(lvl);
    }

    private static void createLevelDemo()
    {
        Room lvl = new Room(1080, 1920);

        lvl.setFinish(1870, 850);
        lvl.setStart(50,1030);

        lvl.addObjects(new Platform(0, 850, 50, 400, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(400, 650, 50, 400, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(800, 450, 50, 200, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(0, 400, 50, 400, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(1500, 650, 50, 100, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(1700, 850, 50, 220, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(1000, 850, 50, 250, Construction.FLOOR, 0));

        lvl.addObjects(new Platform(0, 1030, 50, 1920, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(0, 0, 50, 1920, Construction.FLOOR, 0));

        lvl.addObjects(new Platform(1000, 900, 130, 50, Construction.WALL, 1));
        lvl.addObjects(new Platform(1200, 900, 130, 50, Construction.WALL, 2));

        lvl.addObjects(new Button(450, 650, 1, 50));
        lvl.addObjects(new Button(650, 650, 2, 50));

        lvl.addObjects(new Cube(1300, 700));

        lvl.lvlNumber = 322;

        //levels.add(lvl);
    }

    public static void createLevel(int number)
    {
        if (number == 0)
            createLevel0();
        else if (number == 1)
            createLevel1();
        else if (number == 2)
            createLevel2();
        else if (number == 3)
            createLevel3();
        else if (number == 4)
            createLevel4();
        else if (number == 5)
            createLevel5();
    }
}
