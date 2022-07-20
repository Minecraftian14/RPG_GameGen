package in.mcxiv.game.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import in.mcxiv.game.main.MainEngine;

public class SpriteRenderable extends Component {
    public Vector2 offset = new Vector2();
    public Vector2 shape = new Vector2(1, 1);
    public TextureRegion image;

    public SpriteRenderable construct(TextureRegion image, int w, int h) {
        this.image = image;

        return this;
    }

    public SpriteRenderable construct(TextureRegion image, float x_o, float y_o, float w, float h) {
        this.image = image;
        this.offset.set(x_o, y_o).scl(MainEngine.TILE);
        this.shape.set(w, h).scl(MainEngine.TILE);
        return this;
    }

    public void draw(Batch batch, Vector2 position) {
        batch.draw(this.image,
                position.x + this.offset.x,
                position.y + this.offset.y,
                this.shape.x,
                this.shape.y
        );
    }
}
