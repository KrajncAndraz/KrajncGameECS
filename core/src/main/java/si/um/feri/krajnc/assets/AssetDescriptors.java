package si.um.feri.krajnc.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetDescriptors {

    public static final AssetDescriptor<BitmapFont> FONT32 =
            new AssetDescriptor<BitmapFont>(AssetPaths.UI_FONT32, BitmapFont.class);

    public static final AssetDescriptor<TextureAtlas> IMAGE_ATLAS =
            new AssetDescriptor<TextureAtlas>(AssetPaths.IMAGE_ATLAS, TextureAtlas.class);

    public static final AssetDescriptor<Sound> HIT_SOUND =
        new AssetDescriptor<Sound>(AssetPaths.HIT_SOUND, Sound.class);

    public static final AssetDescriptor<Sound> EATING_SOUND =
        new AssetDescriptor<Sound>(AssetPaths.EATING_SOUND, Sound.class);

    public static final AssetDescriptor<Sound> FIREBALL_SOUND =
        new AssetDescriptor<Sound>(AssetPaths.FIREBALL_SOUND, Sound.class);

    public static final AssetDescriptor<Sound> FIREBALL_HIT_SOUND =
        new AssetDescriptor<Sound>(AssetPaths.FIREBALL_HIT_SOUND, Sound.class);

    public static final AssetDescriptor<Sound> SOUL_COLLECT_SOUND =
        new AssetDescriptor<Sound>(AssetPaths.SOUL_COLLECT_SOUND, Sound.class);

    public static final AssetDescriptor<ParticleEffect> FIRE_TRAIL_PARTICLE =
        new AssetDescriptor<ParticleEffect>(AssetPaths.FIRE_TRAIL_PARTICLE, ParticleEffect.class, atlasParameter());

    public static final AssetDescriptor<ParticleEffect> EXPLOSION_PARTICLE =
        new AssetDescriptor<ParticleEffect>(AssetPaths.EXPLOSION_PARTICLE, ParticleEffect.class, atlasParameter());

    private static ParticleEffectLoader.ParticleEffectParameter atlasParameter() {
        ParticleEffectLoader.ParticleEffectParameter parameter = new ParticleEffectLoader.ParticleEffectParameter();
        parameter.atlasFile = AssetPaths.IMAGE_ATLAS;
        return parameter;
    }

    private AssetDescriptors() {
    }
}
