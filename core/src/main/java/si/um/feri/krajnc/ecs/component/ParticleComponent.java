package si.um.feri.krajnc.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.utils.Pool;

public class ParticleComponent implements Component, Pool.Poolable {
    public ParticleEffectPool.PooledEffect effect;
    public boolean removeWhenComplete;

    @Override
    public void reset() {
        if (effect != null) {
            effect.free();
            effect = null;
        }
        removeWhenComplete = false;
    }
}
