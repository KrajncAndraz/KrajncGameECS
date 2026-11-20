package si.um.feri.krajnc.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;

import si.um.feri.krajnc.common.GameManager;
import si.um.feri.krajnc.common.Mappers;
import si.um.feri.krajnc.ecs.component.*;
import si.um.feri.krajnc.ecs.system.passive.EntityFactorySystem;
import si.um.feri.krajnc.ecs.system.passive.SoundSystem;

public class CollisionSystem extends EntitySystem {

    private static final Family WYVERN_FAMILY = Family.all(WyvernComponent.class, BoundsComponent.class).get();
    private static final Family BALLISTA_FAMILY = Family.all(BallistaComponent.class, BoundsComponent.class).get();
    private static final Family SEAGULL_FAMILY = Family.all(SeagullComponent.class, BoundsComponent.class).get();
    private static final Family SOUL_FAMILY = Family.all(SoulComponent.class, BoundsComponent.class).get();
    private static final Family FIREBALL_FAMILY = Family.all(FireballComponent.class, BoundsComponent.class).get();

    private SoundSystem soundSystem;
    private EntityFactorySystem factory;

    public CollisionSystem() {
    }

    @Override
    public void addedToEngine(Engine engine) {
        soundSystem = engine.getSystem(SoundSystem.class);
        factory = engine.getSystem(EntityFactorySystem.class);
    }

    @Override
    public void update(float deltaTime) {
        if (GameManager.INSTANCE.isGameOver()) return;

        ImmutableArray<Entity> wyvern = getEngine().getEntitiesFor(WYVERN_FAMILY);
        ImmutableArray<Entity> ballistas = getEngine().getEntitiesFor(BALLISTA_FAMILY);
        ImmutableArray<Entity> seagulls = getEngine().getEntitiesFor(SEAGULL_FAMILY);
        ImmutableArray<Entity> souls = getEngine().getEntitiesFor(SOUL_FAMILY);
        ImmutableArray<Entity> fireballs = getEngine().getEntitiesFor(FIREBALL_FAMILY);

        for (Entity wyvernEntity : wyvern) {
            BoundsComponent wyvernBounds = Mappers.BOUNDS.get(wyvernEntity);

            for (Entity ballistaEntity : ballistas) {
                BallistaComponent ballista = Mappers.BALLISTA.get(ballistaEntity);

                if (ballista.hit) {
                    continue;
                }

                BoundsComponent ballistaBounds = Mappers.BOUNDS.get(ballistaEntity);

                for (Entity fireballEntity : fireballs) {
                    FireballComponent fireball = Mappers.FIREBALL.get(fireballEntity);
                    if(fireball.hit) {
                        continue;
                    }

                    BoundsComponent fireballBounds = Mappers.BOUNDS.get(fireballEntity);
                    if (Intersector.overlaps(fireballBounds.rectangle, ballistaBounds.rectangle)) {
                        ballista.hit = true;
                        fireball.hit = true;
                        soundSystem.destroy();
                        factory.createExplosion(
                            ballistaBounds.rectangle.x + ballistaBounds.rectangle.width * 0.5f,
                            ballistaBounds.rectangle.y);
                        getEngine().removeEntity(ballistaEntity);
                        getEngine().removeEntity(fireballEntity);
                        break;
                    }
                }
                if (ballista.hit) {
                    continue;
                }

                if (Intersector.overlaps(wyvernBounds.rectangle, ballistaBounds.rectangle)) {
                    ballista.hit = true;
                    GameManager.INSTANCE.damage();
                    soundSystem.hit();
                    getEngine().removeEntity(ballistaEntity);
                }
            }

            for (Entity seagullEntity : seagulls) {
                SeagullComponent seagull = Mappers.SEAGULL.get(seagullEntity);

                if (seagull.hit) {
                    continue;
                }

                BoundsComponent seagullBounds = Mappers.BOUNDS.get(seagullEntity);

                if (Intersector.overlaps(wyvernBounds.rectangle, seagullBounds.rectangle)) {
                    seagull.hit = true;
                    GameManager.INSTANCE.eatSeagull();
                    soundSystem.eat();
                    getEngine().removeEntity(seagullEntity);
                }
            }

            for (Entity soulEntity : souls) {
                SoulComponent soul = Mappers.SOUL.get(soulEntity);

                if (soul.hit) {
                    continue;
                }

                BoundsComponent soulBounds = Mappers.BOUNDS.get(soulEntity);

                if (Intersector.overlaps(wyvernBounds.rectangle, soulBounds.rectangle)) {
                    soul.hit = true;
                    GameManager.INSTANCE.collectSoul();
                    soundSystem.collect();
                    getEngine().removeEntity(soulEntity);
                }
            }
        }
    }
}
