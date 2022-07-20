package in.mcxiv.game.systems;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.ComponentMapper;
import com.artemis.World;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import in.mcxiv.game.components.AnimationRenderable;
import in.mcxiv.game.components.MapCell;
import in.mcxiv.game.components.Position;
import in.mcxiv.game.components.SpawnPoint;
import in.mcxiv.game.main.Resources;
import in.mcxiv.game.util.GdxUtils;

import java.util.Scanner;

import static in.mcxiv.game.main.MainEngine.INV_TILE;
import static in.mcxiv.game.main.MainEngine.TILE;

@All({MapCell.class, Position.class})
public class MapRenderingSystem extends IteratingSystem {

    private static final ArchetypeBuilder MAP_CELL_ARCHETYPE = new ArchetypeBuilder()
            .add(Position.class)
            .add(MapCell.class);

    private static final ArchetypeBuilder SPAWN_POINT_ARCHETYPE = new ArchetypeBuilder()
            .add(Position.class)
            .add(SpawnPoint.class)
            .add(AnimationRenderable.class);

    protected ComponentMapper<Position> position;
    protected ComponentMapper<MapCell> mapCell;
    protected ComponentMapper<AnimationRenderable> animationRenderable;

    private Batch batch;
    private Archetype mapCellArchetype;
    private Archetype spawnPointArchetype;

    private Camera camera = null;
    private Vector3 lowerBound = new Vector3();
    private Vector3 upperBound = new Vector3();

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
        this.spawnPointArchetype = SPAWN_POINT_ARCHETYPE.build(world);
    }

    public void loadMap(String path) {

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
                    int entity = this.world.create(spawnPointArchetype);
                    this.position.create(entity).position.set(x, y);
                    this.animationRenderable.get(entity).construct(Resources.getInstance().flagAnimation, Color.GREEN, -0.5f, 1, 2, 2);
                }
            }
        }
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    @Override
    protected void begin() {
        if (camera == null) return;
        GdxUtils.getLowerBoundOfTheVisibleRectangle(camera, lowerBound);
        GdxUtils.getUpperBoundOfTheVisibleRectangle(camera, upperBound);
    }

    @Override
    protected void process(int entityId) {
        Vector2 position = this.position.get(entityId).position;
        if (camera != null && GdxUtils.isPointNotInVisibleRectangle(position, lowerBound, upperBound)) return;
        batch.draw(
                this.mapCell.get(entityId).texture,
                position.x * TILE, position.y * TILE,
                TILE, TILE
        );
    }
}
