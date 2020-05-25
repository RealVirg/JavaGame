package Client;

import java.util.ArrayList;

class Levels
{
    static ArrayList<Room> levels = new ArrayList<Room>();

    static void createLevel1()
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

    static void createLevel2()
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

    static void createLevel3()
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

    static void createLevelDemo()
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

        levels.add(lvl);
    }
}
