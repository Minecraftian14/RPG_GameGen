package in.mcxiv.game.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.annotations.One;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import in.mcxiv.game.components.AnimationRenderable;
import in.mcxiv.game.components.Position;
import in.mcxiv.game.components.TextureRegionRenderable;
import in.mcxiv.game.main.Resources;

@One({TextureRegionRenderable.class, AnimationRenderable.class})
@All(Position.class)
public class TextureRegionRenderingSystem extends IteratingSystem {
    protected ComponentMapper<Position> position;
    protected ComponentMapper<TextureRegionRenderable> spriteRenderable;
    protected ComponentMapper<AnimationRenderable> animationRenderable;

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
        TextureRegionRenderable renderable1 = this.spriteRenderable.get(entityId);
        if (renderable1 != null) renderable1.draw(batch, position);
        AnimationRenderable renderable2 = this.animationRenderable.get(entityId);
        if (renderable2 != null) renderable2.draw(batch, position);
    }
}