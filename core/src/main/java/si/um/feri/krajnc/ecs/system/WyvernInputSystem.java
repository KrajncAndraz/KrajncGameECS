package si.um.feri.krajnc.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import si.um.feri.krajnc.common.GameManager;
import si.um.feri.krajnc.common.Mappers;
import si.um.feri.krajnc.config.GameConfig;
import si.um.feri.krajnc.ecs.component.MovementComponentXYR;
import si.um.feri.krajnc.ecs.component.PositionComponent;
import si.um.feri.krajnc.ecs.component.WyvernComponent;


public class WyvernInputSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            WyvernComponent.class,
            MovementComponentXYR.class,
            PositionComponent.class
    ).get();

    public WyvernInputSystem() {
        super(FAMILY);
    }

    // we don't need to override the update Iterating system method because there is no batch begin/end

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MovementComponentXYR movement = Mappers.MOVEMENT.get(entity);
        PositionComponent position = Mappers.POSITION.get(entity);
        movement.xSpeed = 0;
        movement.rSpeed = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            movement.xSpeed = GameConfig.WYVERN_SPEED;
            position.r = MathUtils.lerp(position.r, -GameConfig.WYVERN_BANK_LIMIT, GameConfig.WYVERN_BANKING_SPEED * deltaTime);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            movement.xSpeed = -GameConfig.WYVERN_SPEED;
            position.r = MathUtils.lerp(position.r, GameConfig.WYVERN_BANK_LIMIT, GameConfig.WYVERN_BANKING_SPEED * deltaTime);
        } else {
            position.r = MathUtils.lerp(position.r, 0f, GameConfig.WYVERN_BANKING_SPEED * 0.75f * deltaTime);
        }

    }
}
