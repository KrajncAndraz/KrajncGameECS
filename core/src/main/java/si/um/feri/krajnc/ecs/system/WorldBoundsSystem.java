package si.um.feri.krajnc.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import com.badlogic.gdx.math.MathUtils;
import si.um.feri.krajnc.common.Mappers;
import si.um.feri.krajnc.config.GameConfig;
import si.um.feri.krajnc.ecs.component.DimensionComponent;
import si.um.feri.krajnc.ecs.component.PositionComponent;
import si.um.feri.krajnc.ecs.component.WorldBoundsComponent;


public class WorldBoundsSystem extends IteratingSystem {
    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            DimensionComponent.class,
            WorldBoundsComponent.class
    ).get();

    public WorldBoundsSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);
        DimensionComponent dimension = Mappers.DIMENSION.get(entity);

        position.x = MathUtils.clamp(position.x, 0, GameConfig.WIDTH - dimension.width);
    }
}
