package Client;

import java.util.ArrayList;

class Levels
{
    static ArrayList<Room> levels = new ArrayList<Room>();

    static void createLevel2()
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

        lvl.lvlNumber = 2;

        levels.add(lvl);
    }

    static void createLevel1()
    {
        Room lvl = new Room(1080, 1920);

        lvl.setFinish(1870, 850);
        lvl.setStart(50,1030);

        lvl.addObjects(new Platform(0, 1030, 50, 1920, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(520, 900, 50, 500, Construction.FLOOR, 0));
        lvl.addObjects(new Platform(970, 660, 50, 500, Construction.FLOOR, 0));

        lvl.addObjects(new Platform(520, 950, 80, 50, Construction.WALL, 1));
        lvl.addObjects(new Platform(820, 820, 80, 50, Construction.WALL, 1));
        lvl.addObjects(new Platform(970, 710, 190, 50, Construction.WALL, 1));

        lvl.addObjects(new Cube(1300, 700));

        lvl.lvlNumber = 1;

        levels.add(lvl);
    }
}
