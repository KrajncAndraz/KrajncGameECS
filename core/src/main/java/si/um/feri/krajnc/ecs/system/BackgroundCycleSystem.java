package si.um.feri.krajnc.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;

import com.badlogic.ashley.utils.ImmutableArray;
import si.um.feri.krajnc.common.Mappers;
import si.um.feri.krajnc.config.GameConfig;
import si.um.feri.krajnc.ecs.component.BackgroundComponent;
import si.um.feri.krajnc.ecs.component.PositionComponent;

public class BackgroundCycleSystem extends EntitySystem {

    private static final Family BACKGROUND_FAMILY = Family.all(
            PositionComponent.class,
            BackgroundComponent.class
    ).get();

    public BackgroundCycleSystem() {}

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> backgrounds = getEngine().getEntitiesFor(BACKGROUND_FAMILY);

        Entity b1 = backgrounds.get(0);
        Entity b2 = backgrounds.get(1);

        PositionComponent b1Position = Mappers.POSITION.get(b1);
        PositionComponent b2Position = Mappers.POSITION.get(b2);

        if(b1Position.y + GameConfig.BACKGROUND_HEIGHT < 0) {
            b1Position.y = b2Position.y + GameConfig.BACKGROUND_HEIGHT;
        } else if(b2Position.y + GameConfig.BACKGROUND_HEIGHT < 0) {
            b2Position.y = b1Position.y + GameConfig.BACKGROUND_HEIGHT;
        }
    }
}
