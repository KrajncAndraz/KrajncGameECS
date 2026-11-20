package si.um.feri.krajnc.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import si.um.feri.krajnc.common.Mappers;
import si.um.feri.krajnc.ecs.component.MovementComponentXYR;
import si.um.feri.krajnc.ecs.component.PositionComponent;

public class MovementSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            MovementComponentXYR.class
    ).get();

    public MovementSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);
        MovementComponentXYR movement = Mappers.MOVEMENT.get(entity);

        position.x += movement.xSpeed * deltaTime;
        position.y += movement.ySpeed * deltaTime;
        position.r += movement.rSpeed * deltaTime;
    }
}
