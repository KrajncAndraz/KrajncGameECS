package si.um.feri.krajnc.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.systems.IntervalSystem;
import si.um.feri.krajnc.config.GameConfig;
import si.um.feri.krajnc.ecs.system.passive.EntityFactorySystem;


public class BallistaSpawnSystem extends IntervalSystem {

    private EntityFactorySystem factory;

    public BallistaSpawnSystem() {
        super(GameConfig.BALLISTA_SPAWN_TIME);
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        factory = engine.getSystem(EntityFactorySystem.class);
    }

    @Override
    protected void updateInterval() {
        factory.createBallista();
    }
}
