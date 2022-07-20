package in.mcxiv.game.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

public class CollidableBody extends Component {
    public Shape2D shape;
    public Vector2 cog = new Vector2();
    public float mass; // NOTE: Just a concept, I am not using it at the moment.

    public CollidableBody construct(float x, float y, float w, float h) {
        return construct(new Rectangle(x, y, w, h), x + w / 2, y + h / 2, 1f);
    }

    public CollidableBody construct(Shape2D shape, float cog_x, float cog_y, float mass) {
        this.shape = shape;
        this.cog.set(cog_x, cog_y);
        this.mass = mass;
        return this;
    }
}
