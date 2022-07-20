package in.mcxiv.game.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import in.mcxiv.game.components.Position;
import in.mcxiv.game.components.SpriteRenderable;
import in.mcxiv.game.main.Resources;

@All({SpriteRenderable.class, Position.class})
public class SpritesRenderingSystem extends IteratingSystem {
    protected ComponentMapper<Position> position;
    protected ComponentMapper<SpriteRenderable> spriteRenderable;

    private Batch batch;

    @Override
    // TODO: Should it be `initialize` or `begin`?
    protected void initialize() {
        super.initialize();
        this.batch = Resources.getInstance().batch;
    }

    @Override
    protected void process(int entityId) {
        // TODO: Check if the cell needs drawing if it's in the screen. Draw only if required.
        Vector2 position = this.position.get(entityId).position;
        this.spriteRenderable.get(entityId).draw(batch, position);
    }
}