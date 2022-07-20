package in.mcxiv.game.systems;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.ComponentMapper;
import com.artemis.World;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import in.mcxiv.game.components.MapCell;
import in.mcxiv.game.components.Position;
import in.mcxiv.game.components.SpawnPoint;
import in.mcxiv.game.main.Resources;

import java.util.Scanner;

import static in.mcxiv.game.main.MainEngine.TILE;

@All({MapCell.class, Position.class})
public class MapRenderingSystem extends IteratingSystem {

    private static final ArchetypeBuilder MAP_CELL_ARCHETYPE = new ArchetypeBuilder()
            .add(Position.class)
            .add(MapCell.class);

    protected ComponentMapper<Position> position;
    protected ComponentMapper<MapCell> mapCell;
    protected ComponentMapper<SpawnPoint> spawnPoint;

    private Batch batch;
    private Archetype mapCellArchetype;

    @Override
    // TODO: Should it be `initialize` or `begin`?
    protected void initialize() {
        super.initialize();
        this.batch = Resources.getInstance().batch;
    }

    @Override
    protected void setWorld(World world) {
        super.setWorld(world);
        this.mapCellArchetype = MAP_CELL_ARCHETYPE.build(world);
    }

    public void loadMap(String path, World world) {

        Scanner sc = new Scanner(Gdx.files.internal(path).read(64));

        while (sc.hasNext()) {
            String[] split = sc.nextLine().split(",");
            int x = Integer.parseInt(split[0].trim());
            int y = Integer.parseInt(split[1].trim());

            for (int i = 2; i < split.length; i++) {
                int v = Integer.parseInt(split[i].trim());

                if (v > 0) {
                    int entity = this.world.create(mapCellArchetype);
                    this.position.get(entity).position.set(x, y);
                    this.mapCell.get(entity).construct(v);
                } else if (v == -1) {
                    int entity = this.world.create();
                    this.position.create(entity).position.set(x, y);
                    this.spawnPoint.create(entity);
                }
            }
        }
    }

    @Override
    protected void process(int entityId) {
        // TODO: Check if the cell needs drawing if it's in the screen. Draw only if required.
        Vector2 position = this.position.get(entityId).position;
        batch.draw(
                this.mapCell.get(entityId).texture,
                position.x * TILE, position.y * TILE,
                TILE, TILE
        );
    }
}
