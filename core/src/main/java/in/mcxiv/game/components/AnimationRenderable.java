package in.mcxiv.game.components;

import com.artemis.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import in.mcxiv.game.main.MainEngine;

public class AnimationRenderable extends Component {
    public Vector2 offset = new Vector2();
    public Vector2 shape = new Vector2(1, 1);
    public Animation<TextureRegion> animation;
    public Color tint = Color.WHITE;

    private float time;

    public AnimationRenderable construct(Animation<TextureRegion> animation, Color tint, int w, int h) {
        return construct(animation, tint, 0, 0, w, h);
    }

    public AnimationRenderable construct(Animation<TextureRegion> animation, Color tint, float x_o, float y_o, float w, float h) {
        this.animation = animation;
        this.tint = tint;
        this.offset.set(x_o, y_o).scl(MainEngine.TILE);
        this.shape.set(w, h).scl(MainEngine.TILE);
        this.time = 0;
        return this;
    }

    public void draw(Batch batch, Vector2 position) {
        batch.setColor(tint);
        batch.draw(this.animation.getKeyFrame(this.time, true),
                position.x + this.offset.x,
                position.y + this.offset.y,
                this.shape.x,
                this.shape.y
        );
        batch.setColor(1, 1, 1, 1);
        time += Gdx.graphics.getDeltaTime();
    }
}
