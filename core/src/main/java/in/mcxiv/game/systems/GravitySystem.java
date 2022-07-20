package in.mcxiv.game.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import in.mcxiv.game.components.DynamicObject;
import in.mcxiv.game.components.Velocity;

@All({DynamicObject.class, Velocity.class})
public class GravitySystem extends IteratingSystem {

    // This assumes, that moving 1 unit in world coordinate (which assumably was 1 pixel) is equivalent to 1 meter.
    public static final float GRAVITATIONAL_ACCELERATION = 9.8f;

    ComponentMapper<Velocity> velocity;

    @Override
    protected void process(int entityId) {
        velocity.get(entityId).velocity.sub(0, GRAVITATIONAL_ACCELERATION * world.delta);
    }
}
