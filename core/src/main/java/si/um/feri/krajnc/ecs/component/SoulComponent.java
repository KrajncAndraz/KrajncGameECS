package si.um.feri.krajnc.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;
import si.um.feri.krajnc.config.GameConfig;

public class SoulComponent implements Component, Pool.Poolable {

    public boolean hit;
    public float frequency;
    public float amplitude;
    public float time;

    SoulComponent() {
        hit = false;
        time = 0f;
        frequency = MathUtils.random(GameConfig.SOUL_FREQUENCY_MIN, GameConfig.SOUL_FREQUENCY_MAX);
        amplitude = MathUtils.random(GameConfig.SOUL_AMPLITUDE_MIN, GameConfig.SOUL_AMPLITUDE_MAX);
    }

    @Override
    public void reset() {
        hit = false;
        time = 0f;
        frequency = MathUtils.random(GameConfig.SOUL_FREQUENCY_MIN, GameConfig.SOUL_FREQUENCY_MAX);
        amplitude = MathUtils.random(GameConfig.SOUL_AMPLITUDE_MIN, GameConfig.SOUL_AMPLITUDE_MAX);
    }
}
