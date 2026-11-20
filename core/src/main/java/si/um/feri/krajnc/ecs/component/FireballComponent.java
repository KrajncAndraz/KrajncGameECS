package si.um.feri.krajnc.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;

public class FireballComponent implements Component, Pool.Poolable {

    public boolean hit;
    public float decayRate;

    FireballComponent() {
        hit = false;
        decayRate = MathUtils.random(2.5f, 3.5f);
    }

    @Override
    public void reset() {
        hit = false;
        decayRate = MathUtils.random(2.5f, 3.5f);
    }
}
