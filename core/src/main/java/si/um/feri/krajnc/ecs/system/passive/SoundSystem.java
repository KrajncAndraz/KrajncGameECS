package si.um.feri.krajnc.ecs.system.passive;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import si.um.feri.krajnc.assets.AssetDescriptors;
import si.um.feri.krajnc.assets.AssetPaths;

public class SoundSystem extends EntitySystem {

    private final AssetManager assetManager;

    private Sound hitSound;
    private Sound eatingSound;
    private Sound fireballSound;
    private Sound fireballHitSound;
    private Sound soulCollectSound;

    public SoundSystem(AssetManager assetManager) {
        this.assetManager = assetManager;
        setProcessing(false); // passive system
        init();
    }

    private void init() {
        hitSound = assetManager.get(AssetPaths.HIT_SOUND, Sound.class);
        eatingSound = assetManager.get(AssetPaths.EATING_SOUND, Sound.class);
        fireballSound = assetManager.get(AssetPaths.FIREBALL_SOUND, Sound.class);
        fireballHitSound = assetManager.get(AssetPaths.FIREBALL_HIT_SOUND, Sound.class);
        soulCollectSound = assetManager.get(AssetDescriptors.SOUL_COLLECT_SOUND);
    }

    public void fire() {
        fireballSound.play();
    }

    public void destroy() {
        fireballHitSound.play();
    }

    public void eat() {
        eatingSound.play();
    }

    public void hit() {
        hitSound.play();
    }

    public void collect() {
        soulCollectSound.play();
    }
}
