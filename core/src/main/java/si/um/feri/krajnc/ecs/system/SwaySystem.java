package si.um.feri.krajnc.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import com.badlogic.gdx.math.MathUtils;
import si.um.feri.krajnc.common.Mappers;
import si.um.feri.krajnc.ecs.component.PositionComponent;
import si.um.feri.krajnc.ecs.component.SeagullComponent;

public class SwaySystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            SeagullComponent.class
    ).get();

    public SwaySystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);
        SeagullComponent seagull = Mappers.SEAGULL.get(entity);

        position.x += MathUtils.cos(seagull.time * seagull.frequency) * seagull.amplitude * deltaTime;
        seagull.time += deltaTime;
    }
}
