package si.um.feri.krajnc.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import si.um.feri.krajnc.assets.AssetDescriptors;
import si.um.feri.krajnc.common.Mappers;
import si.um.feri.krajnc.config.GameConfig;
import si.um.feri.krajnc.ecs.component.DimensionComponent;
import si.um.feri.krajnc.ecs.component.ParticleComponent;
import si.um.feri.krajnc.ecs.component.PositionComponent;
import si.um.feri.krajnc.ecs.component.SeagullComponent;

public class ParticleSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
        ParticleComponent.class,
        PositionComponent.class
    ).get();

    private final SpriteBatch batch;
    private final Viewport viewport;
    private final ParticleEffectPool trailPool;
    private final ParticleEffectPool explosionPool;

    public ParticleSystem(SpriteBatch batch, Viewport viewport, AssetManager assetManager) {
        super(FAMILY);
        this.batch = batch;
        this.viewport = viewport;

        ParticleEffect trail = assetManager.get(AssetDescriptors.FIRE_TRAIL_PARTICLE);
        ParticleEffect explosion = assetManager.get(AssetDescriptors.EXPLOSION_PARTICLE);

        trail.scaleEffect(GameConfig.PARTICLE_SCALE);
        explosion.scaleEffect(GameConfig.PARTICLE_SCALE);

        trailPool = new ParticleEffectPool(trail, 4, 6);
        explosionPool = new ParticleEffectPool(explosion, 3, 5);
    }

    public ParticleEffectPool.PooledEffect obtainTrail() {
        return trailPool.obtain();
    }

    public ParticleEffectPool.PooledEffect obtainExplosion() {
        return explosionPool.obtain();
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ParticleComponent particle = Mappers.PARTICLE.get(entity);
        if (particle.effect == null) return;

        PositionComponent position = Mappers.POSITION.get(entity);
        DimensionComponent dimension = Mappers.DIMENSION.get(entity);

        float x = position.x, y = position.y;
        if (dimension != null) {
            x += dimension.width * 0.5f;
            y += dimension.height * 0.5f;
        }

        particle.effect.setPosition(x, y);
        particle.effect.draw(batch, deltaTime);

        if (particle.removeWhenComplete && particle.effect.isComplete()) {
            getEngine().removeEntity(entity);
        }
    }
}
