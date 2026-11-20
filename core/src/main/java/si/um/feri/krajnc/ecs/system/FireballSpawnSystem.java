package si.um.feri.krajnc.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import si.um.feri.krajnc.common.GameManager;
import si.um.feri.krajnc.config.GameConfig;
import si.um.feri.krajnc.ecs.system.passive.EntityFactorySystem;
import si.um.feri.krajnc.ecs.system.passive.SoundSystem;

public class FireballSpawnSystem extends EntitySystem {

    private EntityFactorySystem factory;
    private float cooldown = GameConfig.FIREBALL_SPAWN_TIME;
    private SoundSystem soundSystem;

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        factory = engine.getSystem(EntityFactorySystem.class);
        soundSystem = engine.getSystem(SoundSystem.class);
    }

    @Override
    public void update(float deltaTime) {
        if (GameManager.INSTANCE.isGameOver()) return;
        cooldown += deltaTime;

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)
            && cooldown >= GameConfig.FIREBALL_SPAWN_TIME
            && GameManager.INSTANCE.getCharges() > 0) {
            cooldown = 0f;
            GameManager.INSTANCE.fire();
            factory.createFireball();
            soundSystem.fire();
        }
    }
}
