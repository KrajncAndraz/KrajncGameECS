package si.um.feri.krajnc.ecs.system.passive;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import si.um.feri.krajnc.assets.AssetDescriptors;
import si.um.feri.krajnc.assets.RegionNames;
import si.um.feri.krajnc.common.Mappers;
import si.um.feri.krajnc.config.GameConfig;
import si.um.feri.krajnc.ecs.component.*;
import si.um.feri.krajnc.ecs.system.ParticleSystem;

public class EntityFactorySystem extends EntitySystem {

    private static final int BACKGROUND_Z_ORDER = 1;
    private static final int SOUL_Z_ORDER = 2;
    private static final int BALLISTA_Z_ORDER = 3;
    private static final int SEAGULL_Z_ORDER = 4;
    private static final int WYVERN_Z_ORDER = 5;
    private static final int FIREBALL_Z_ORDER = 6;

    private final AssetManager assetManager;

    private PooledEngine engine;
    private TextureAtlas imageAtlas;

    public EntityFactorySystem(AssetManager assetManager) {
        this.assetManager = assetManager;
        setProcessing(false);   // passive system
        init();
    }

    private void init() {
        imageAtlas = assetManager.get(AssetDescriptors.IMAGE_ATLAS);
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = (PooledEngine) engine;
    }

    public void createWyvern() {
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = GameConfig.WIDTH * 0.5f - GameConfig.WYVERN_WIDTH * 0.5f;
        position.y = 5;

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.WYVERN_WIDTH;
        dimension.height = GameConfig.WYVERN_HEIGHT;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        MovementComponentXYR movement = engine.createComponent(MovementComponentXYR.class);

        WyvernComponent wyvern = engine.createComponent(WyvernComponent.class);

        WorldBoundsComponent worldBounds = engine.createComponent(WorldBoundsComponent.class);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = imageAtlas.findRegion(RegionNames.WYVERN);

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = WYVERN_Z_ORDER;

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movement);
        entity.add(wyvern);
        entity.add(worldBounds);
        entity.add(texture);
        entity.add(zOrder);
        engine.addEntity(entity);
    }

    public void createBallista() {
        PositionComponent position = engine.createComponent(PositionComponent.class);

        position.x = MathUtils.random(0, GameConfig.WIDTH - GameConfig.BALLISTA_WIDTH);
        position.y = GameConfig.HEIGHT;

        MovementComponentXYR movementComponent = engine.createComponent(MovementComponentXYR.class);
        movementComponent.ySpeed = -MathUtils.random(GameConfig.BALLISTA_SPEED_MIN, GameConfig.BALLISTA_SPEED_MAX);
        movementComponent.xSpeed = 0;
        movementComponent.rSpeed = 0;

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.BALLISTA_WIDTH;
        dimension.height = GameConfig.BALLISTA_HEIGHT;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        BallistaComponent ballista = engine.createComponent(BallistaComponent.class);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = imageAtlas.findRegion(RegionNames.BALLISTA);

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = BALLISTA_Z_ORDER;

        CleanUpComponent cleanUp = engine.createComponent(CleanUpComponent.class);

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movementComponent);
        entity.add(ballista);
        entity.add(texture);
        entity.add(zOrder);
        entity.add(cleanUp);
        engine.addEntity(entity);
    }

    public void createSeagull() {
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = MathUtils.random(0, GameConfig.WIDTH - GameConfig.SEAGULL_WIDTH);
        position.y = GameConfig.HEIGHT;

        MovementComponentXYR movementComponent = engine.createComponent(MovementComponentXYR.class);
        movementComponent.ySpeed = -MathUtils.random(GameConfig.SEAGULL_SPEED_Y_MIN, GameConfig.SEAGULL_SPEED_Y_MAX);
        movementComponent.xSpeed = 0;
        movementComponent.rSpeed = 0;

        SeagullComponent seagull = engine.createComponent(SeagullComponent.class);

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.SEAGULL_WIDTH;
        dimension.height = GameConfig.SEAGULL_HEIGHT;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = imageAtlas.findRegion(RegionNames.SEAGULL);

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = SEAGULL_Z_ORDER;

        CleanUpComponent cleanUp = engine.createComponent(CleanUpComponent.class);

        WorldBoundsComponent worldBounds = engine.createComponent(WorldBoundsComponent.class);

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movementComponent);
        entity.add(seagull);
        entity.add(texture);
        entity.add(zOrder);
        entity.add(worldBounds);
        entity.add(cleanUp);
        engine.addEntity(entity);
    }

    public void createSoul() {
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = MathUtils.random(0, GameConfig.WIDTH - GameConfig.SOUL_WIDTH);
        position.y = GameConfig.HEIGHT;

        MovementComponentXYR movementComponent = engine.createComponent(MovementComponentXYR.class);
        movementComponent.ySpeed = -GameConfig.SOUL_SPEED;
        movementComponent.xSpeed = 0;
        movementComponent.rSpeed = 0;

        SoulComponent soul = engine.createComponent(SoulComponent.class);

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.SOUL_WIDTH;
        dimension.height = GameConfig.SOUL_HEIGHT;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = imageAtlas.findRegion(RegionNames.SOUL);

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = SOUL_Z_ORDER;

        CleanUpComponent cleanUp = engine.createComponent(CleanUpComponent.class);

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movementComponent);
        entity.add(soul);
        entity.add(texture);
        entity.add(zOrder);
        entity.add(cleanUp);
        engine.addEntity(entity);
    }

    public void createFireball() {
        ImmutableArray<Entity> wyvern = getEngine().getEntitiesFor(Family.all(WyvernComponent.class, PositionComponent.class).get());
        PositionComponent wyvernPosition = Mappers.POSITION.get(wyvern.first());
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = wyvernPosition.x + GameConfig.WYVERN_WIDTH * 0.5f - GameConfig.FIREBALL_WIDTH * 0.5f;
        position.y = wyvernPosition.y + GameConfig.WYVERN_HEIGHT;

        MovementComponentXYR movementComponent = engine.createComponent(MovementComponentXYR.class);
        movementComponent.ySpeed = MathUtils.random(GameConfig.FIREBALL_SPEED_Y_MIN, GameConfig.FIREBALL_SPEED_Y_MAX);
        movementComponent.xSpeed = 0;
        movementComponent.rSpeed = 0;

        FireballComponent fireball = engine.createComponent(FireballComponent.class);

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.FIREBALL_WIDTH;
        dimension.height = GameConfig.FIREBALL_HEIGHT;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = imageAtlas.findRegion(RegionNames.FIREBALL);

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = FIREBALL_Z_ORDER;

        CleanUpComponent cleanUp = engine.createComponent(CleanUpComponent.class);

        ParticleComponent particle = engine.createComponent(ParticleComponent.class);
        particle.effect = engine.getSystem(ParticleSystem.class).obtainTrail();

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movementComponent);
        entity.add(fireball);
        entity.add(texture);
        entity.add(zOrder);
        entity.add(cleanUp);
        entity.add(particle);
        engine.addEntity(entity);
    }

    public void createBackground() {
        ImmutableArray<Entity> backgrounds = getEngine().getEntitiesFor(Family.all(BackgroundComponent.class, PositionComponent.class).get());
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = 0;
        position.y = 0;
        if (backgrounds.size() > 0) {
            PositionComponent pos = Mappers.POSITION.get(backgrounds.first());
            position.y = pos.y + GameConfig.BACKGROUND_HEIGHT;
        }

        MovementComponentXYR movementComponent = engine.createComponent(MovementComponentXYR.class);
        movementComponent.ySpeed = -GameConfig.BACKGROUND_SPEED;
        movementComponent.xSpeed = 0;
        movementComponent.rSpeed = 0;

        BackgroundComponent background = engine.createComponent(BackgroundComponent.class);

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.BACKGROUND_WIDTH;
        dimension.height = GameConfig.BACKGROUND_HEIGHT;

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = imageAtlas.findRegion(RegionNames.BACKGROUND);

        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = BACKGROUND_Z_ORDER;

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(movementComponent);
        entity.add(background);
        entity.add(texture);
        entity.add(zOrder);
        engine.addEntity(entity);
    }

    public void createExplosion(float x, float y) {
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = x;
        position.y = y;

        MovementComponentXYR movement = engine.createComponent(MovementComponentXYR.class);
        movement.ySpeed = -GameConfig.BACKGROUND_SPEED;

        ParticleComponent particle = engine.createComponent(ParticleComponent.class);
        particle.effect = engine.getSystem(ParticleSystem.class).obtainExplosion();
        particle.removeWhenComplete = true;

        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(movement);
        entity.add(particle);
        engine.addEntity(entity);
    }
}
