package in.mcxiv.game.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import in.mcxiv.game.components.DynamicObject;
import in.mcxiv.game.components.Position;
import in.mcxiv.game.main.MainEngine;

@All({DynamicObject.class, Position.class})
public class MapCellCollision extends IteratingSystem {

    ComponentMapper<Position> map_Pos;
    MainEngine engine;

//    Array<MapCell> cells = new Array<>();

    public MapCellCollision(MainEngine engine) {
        this.engine = engine;
    }

    @Override
    protected void process(int entityId) {
//        Position position = map_Pos.get(entityId);
//        cells = engine.cellMap.hit(position.x, position.y, cells);
//        if (cells.notEmpty()) {
//            Vector2 vector = new Vector2(0, 0);
//            for (int i = 0, s = cells.size; i < s; i++) {
//                cells.get(i).collide(position.x, position.y, vector);
//            }
//            position.x -= vector.x;
//            position.y -= vector.y;
//        }
    }
}
