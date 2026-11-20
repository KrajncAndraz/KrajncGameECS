package si.um.feri.krajnc;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import si.um.feri.krajnc.assets.AssetDescriptors;
import si.um.feri.krajnc.common.GameManager;
import si.um.feri.krajnc.config.GameConfig;
import si.um.feri.krajnc.ecs.system.*;
import si.um.feri.krajnc.ecs.system.debug.DebugCameraSystem;
import si.um.feri.krajnc.ecs.system.debug.DebugInputSystem;
import si.um.feri.krajnc.ecs.system.debug.DebugRenderSystem;
import si.um.feri.krajnc.ecs.system.debug.GridRenderSystem;
import si.um.feri.krajnc.ecs.system.debug.support.ViewportUtils;
import si.um.feri.krajnc.ecs.system.passive.EntityFactorySystem;
import si.um.feri.krajnc.ecs.system.passive.SoundSystem;
import si.um.feri.krajnc.ecs.system.passive.StartUpSystem;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class KrajncGameECS extends ApplicationAdapter {

    private static final Logger log = new Logger(KrajncGameECS.class.getSimpleName(), Logger.DEBUG);

    private AssetManager assetManager;
    private SpriteBatch batch;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Viewport hudViewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;
    private BitmapFont font;
    private boolean debug = false;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WIDTH, GameConfig.HEIGHT, camera);
        hudViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        renderer = new ShapeRenderer();

        assetManager = new AssetManager();
        assetManager.getLogger().setLevel(Logger.DEBUG);

        assetManager.load(AssetDescriptors.IMAGE_ATLAS);
        assetManager.load(AssetDescriptors.HIT_SOUND);
        assetManager.load(AssetDescriptors.EATING_SOUND);
        assetManager.load(AssetDescriptors.FIREBALL_SOUND);
        assetManager.load(AssetDescriptors.FIREBALL_HIT_SOUND);
        assetManager.load(AssetDescriptors.SOUL_COLLECT_SOUND);
        assetManager.load(AssetDescriptors.FONT32);
        assetManager.load(AssetDescriptors.FIRE_TRAIL_PARTICLE);
        assetManager.load(AssetDescriptors.EXPLOSION_PARTICLE);
        assetManager.finishLoading();

        font = assetManager.get(AssetDescriptors.FONT32);

        engine = new PooledEngine();

        // 'passive' systems
        engine.addSystem(new EntityFactorySystem(assetManager));
        engine.addSystem(new SoundSystem(assetManager));
        engine.addSystem(new StartUpSystem());  // called only at the start, to generate first entities

        // 'active' systems
        engine.addSystem(new WyvernInputSystem());
        engine.addSystem(new FireballSpawnSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new SwaySystem());
        engine.addSystem(new ResizeSystem());
        engine.addSystem(new WorldBoundsSystem());
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new BallistaSpawnSystem());
        engine.addSystem(new SeagullSpawnSystem());
        engine.addSystem(new BackgroundCycleSystem());
        engine.addSystem(new SoulSpawnSystem());
        engine.addSystem(new CleanUpSystem());
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new RenderSystem(batch, viewport));
        engine.addSystem(new ParticleSystem(batch, viewport, assetManager));
        engine.addSystem(new HudRenderSystem(batch, hudViewport, font,
            assetManager.get(AssetDescriptors.IMAGE_ATLAS), renderer));

        // debug systems
        if (debug) {
            ViewportUtils.DEFAULT_CELL_SIZE = 16;
            engine.addSystem(new GridRenderSystem(viewport, renderer));
            engine.addSystem(new DebugRenderSystem(viewport, renderer));
            engine.addSystem(new DebugCameraSystem(
                GameConfig.WIDTH * 0.5f, GameConfig.HEIGHT * 0.5f,
                camera
            ));
            engine.addSystem(new DebugInputSystem());   // show/hide grid on 'F5'
        }

        GameManager.INSTANCE.reset();
        logAllSystemsInEngine();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();

        if (GameManager.INSTANCE.isGameOver()) {
            engine.update(0);
        } else {
            engine.update(Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        renderer.dispose();
        assetManager.dispose();
        engine.removeAllEntities();
    }

    public void logAllSystemsInEngine() {
        for (EntitySystem system : engine.getSystems()) {
            log.debug(system.getClass().getSimpleName());
        }
    }
}
