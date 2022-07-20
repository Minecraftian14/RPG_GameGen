package in.mcxiv.game.main.maps;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.ComponentMapper;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import in.mcxiv.game.components.*;
import in.mcxiv.game.main.Resources;

import java.util.Scanner;

import static in.mcxiv.game.main.MainEngine.TILE;

public class MapLoader {

    private static final ArchetypeBuilder MAP_CELL_ARCHETYPE = new ArchetypeBuilder()
            .add(Position.class)
            .add(MapCell.class)
            .add(TextureRegionRenderable.class);

    private static final ArchetypeBuilder SPAWN_POINT_ARCHETYPE = new ArchetypeBuilder()
            .add(Position.class)
            .add(SpawnPoint.class)
            .add(AnimationRenderable.class);

    protected ComponentMapper<Position> position;
    protected ComponentMapper<MapCell> mapCell;
    protected ComponentMapper<TextureRegionRenderable> textureRegionRenderable;
    protected ComponentMapper<AnimationRenderable> animationRenderable;

    private Archetype mapCellArchetype;
    private Archetype spawnPointArchetype;
    private World world;

    public MapLoader(World world) {
        this.world = world;
        initialize();
    }

    public void initialize() {
        world.inject(this);
        mapCellArchetype = MAP_CELL_ARCHETYPE.build(world);
        spawnPointArchetype = SPAWN_POINT_ARCHETYPE.build(world);
    }

    public MapSpecs loadMap(String path) {

        Scanner sc = new Scanner(Gdx.files.internal(path).read(64));

        while (sc.hasNext()) {
            String[] split = sc.nextLine().split(",");
            int x = Integer.parseInt(split[0].trim());
            int y = Integer.parseInt(split[1].trim());

            for (int i = 2; i < split.length; i++) {
                int v = Integer.parseInt(split[i].trim());

                if (v > 0) {
                    int entity = this.world.create(mapCellArchetype);
                    this.position.get(entity).position.set(x, y).scl(TILE);
                    TextureRegion texture = this.mapCell.get(entity).construct(v).texture;
                    this.textureRegionRenderable.get(entity).construct(texture, 1, 1);
                } else if (v == -1) {
                    int entity = this.world.create(spawnPointArchetype);
                    this.position.create(entity).position.set(x, y);
                    this.animationRenderable.get(entity).construct(Resources.getInstance().flagAnimation, Color.GREEN, -0.5f, 1, 2, 2);
                }
            }
        }

        return new MapSpecs();
    }

    public static class MapSpecs {
        // TODO: Store variables which contain information about which tile was present the lowest in a given y
        //  Then use that to draw more tiles of same type one after another to fill the better bottom of the screen.
    }
}
