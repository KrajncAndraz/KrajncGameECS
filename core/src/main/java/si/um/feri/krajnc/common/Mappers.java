package si.um.feri.krajnc.common;

import com.badlogic.ashley.core.ComponentMapper;
import si.um.feri.krajnc.ecs.component.*;

public final class Mappers {

    public static final ComponentMapper<BallistaComponent> BALLISTA =
            ComponentMapper.getFor(BallistaComponent.class);

    public static final ComponentMapper<SeagullComponent> SEAGULL =
        ComponentMapper.getFor(SeagullComponent.class);

    public static final ComponentMapper<SoulComponent> SOUL =
        ComponentMapper.getFor(SoulComponent.class);

    public static final ComponentMapper<BoundsComponent> BOUNDS =
            ComponentMapper.getFor(BoundsComponent.class);

    public static final ComponentMapper<DimensionComponent> DIMENSION =
            ComponentMapper.getFor(DimensionComponent.class);

    public static final ComponentMapper<MovementComponentXYR> MOVEMENT =
            ComponentMapper.getFor(MovementComponentXYR.class);

    public static final ComponentMapper<PositionComponent> POSITION =
            ComponentMapper.getFor(PositionComponent.class);

    public static final ComponentMapper<WyvernComponent> WYVERN =
            ComponentMapper.getFor(WyvernComponent.class);

    public static final ComponentMapper<TextureComponent> TEXTURE =
            ComponentMapper.getFor(TextureComponent.class);

    public static final ComponentMapper<ZOrderComponent> Z_ORDER =
        ComponentMapper.getFor(ZOrderComponent.class);

    public static final ComponentMapper<FireballComponent> FIREBALL =
        ComponentMapper.getFor(FireballComponent.class);

    public static final ComponentMapper<ParticleComponent> PARTICLE =
        ComponentMapper.getFor(ParticleComponent.class);

    private Mappers() {
    }
}
