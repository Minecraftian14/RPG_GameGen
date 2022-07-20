package in.mcxiv.game.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.annotations.One;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import in.mcxiv.game.components.AnimationRenderable;
import in.mcxiv.game.components.Position;
import in.mcxiv.game.components.TextureRegionRenderable;
import in.mcxiv.game.main.Resources;
import in.mcxiv.game.util.GdxUtils;

@One({TextureRegionRenderable.class, AnimationRenderable.class})
@All(Position.class)
public class GeneralRenderingSystem extends IteratingSystem {
    protected ComponentMapper<Position> position;
    protected ComponentMapper<TextureRegionRenderable> spriteRenderable;
    protected ComponentMapper<AnimationRenderable> animationRenderable;

    private Batch batch;
    private Camera camera = null;
    private Vector3 lowerBound = new Vector3();
    private Vector3 upperBound = new Vector3();

    @Override
    protected void initialize() {
        super.initialize();
        this.batch = Resources.getInstance().batch;
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
        TextureRegionRenderable renderable1 = this.spriteRenderable.get(entityId);
        if (renderable1 != null) renderable1.draw(batch, position);
        AnimationRenderable renderable2 = this.animationRenderable.get(entityId);
        if (renderable2 != null) renderable2.draw(batch, position);
    }
}