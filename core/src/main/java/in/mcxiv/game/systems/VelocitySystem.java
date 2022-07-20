package in.mcxiv.game.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import in.mcxiv.game.components.Position;
import in.mcxiv.game.components.Velocity;

@All({Velocity.class, Position.class})
public class VelocitySystem extends IteratingSystem {

    protected ComponentMapper<Velocity> velocity;
    protected ComponentMapper<Position> position;

    @Override
    protected void process(int entityId) {
        position.get(entityId).position.mulAdd(
                velocity.get(entityId).velocity,
                world.delta
        );
    }
}
