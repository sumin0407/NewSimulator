package agents;

import core.ImageObject;
import core.World;
import misc.Position;

public class SafeZone extends CS {

    int scale = 5;

    public SafeZone(World world, String name) {
        super(world, name);
        addChild(new ImageObject("src/engine/resources/safezone.png", scale));

//        Position[] positions = new Position[]{
//                new Position(-1, -1),
//                new Position(0, -1),
//                new Position(1, -1),
//
//                new Position(-1, 0),
//                new Position(0, 0),
//                new Position(1, 0),
//
//                new Position(-1, 1),
//                new Position(0, 1),
//                new Position(1, 1),
//        };
//        for(int i = 0; i < 9; ++i) {
//            SoSObject object = new ImageObject("src/engine/resources/safezone.png");
//            object.setPosition(positions[i]);
//            addChild(object);
//        }
    }

    public boolean isSafeZone(Position position) {
        return isSafeZone(position.x, position.y);
    }

    public boolean isSafeZone(int x, int y) {
        int distanceX = Math.abs(position.x - x);
        int distanceY = Math.abs(position.y - y);

        return distanceX <= (scale / 2) && distanceY <= (scale / 2);
    }
}