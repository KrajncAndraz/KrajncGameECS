package si.um.feri.krajnc.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;

import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.MathUtils;
import si.um.feri.krajnc.common.GameManager;
import si.um.feri.krajnc.common.Mappers;
import si.um.feri.krajnc.ecs.component.*;

public class ResizeSystem extends EntitySystem {
    private static final Family SOUL_FAMILY = Family.all(
        PositionComponent.class,
        DimensionComponent.class,
        SoulComponent.class
    ).get();

    private static final Family FIREBALL_FAMILY = Family.all(
        PositionComponent.class,
        DimensionComponent.class,
        FireballComponent.class
    ).get();

    public ResizeSystem() {}

    @Override
    public void update(float deltaTime) {
        if (GameManager.INSTANCE.isGameOver()) return;

        ImmutableArray<Entity> souls = getEngine().getEntitiesFor(SOUL_FAMILY);
        ImmutableArray<Entity> fireballs = getEngine().getEntitiesFor(FIREBALL_FAMILY);

        for (Entity entity : souls) {
            DimensionComponent dim = Mappers.DIMENSION.get(entity);
            PositionComponent pos = Mappers.POSITION.get(entity);
            SoulComponent soul = Mappers.SOUL.get(entity);

            float sizeChange = MathUtils.sin(soul.time * soul.frequency) * soul.amplitude * deltaTime;
            dim.height += sizeChange;
            dim.width += sizeChange;
            pos.x -= sizeChange * 0.5f;
            pos.y -= sizeChange * 0.5f;
            soul.time += deltaTime;
        }

        for (Entity entity : fireballs) {
            DimensionComponent dim = Mappers.DIMENSION.get(entity);
            PositionComponent pos = Mappers.POSITION.get(entity);
            FireballComponent fireball = Mappers.FIREBALL.get(entity);

            float decay = fireball.decayRate * deltaTime,
                newWidth = Math.max(0f, dim.width - decay);
            pos.x += (dim.width - newWidth) * 0.5f;
            dim.width = newWidth;
            dim.height = Math.max(0f, dim.height - decay);
            if (dim.width <= 0f) fireball.hit = true;
        }
    }
}
