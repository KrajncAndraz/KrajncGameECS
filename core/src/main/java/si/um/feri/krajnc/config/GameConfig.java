package si.um.feri.krajnc.config;

public class GameConfig {
    public static final float WYVERN_WIDTH = 128f / 9f;
    public static final float WYVERN_HEIGHT = 118f / 9f;
    public static final float WYVERN_SPEED = 300f / 9f;
    public static final float WYVERN_BANKING_SPEED = 12f;
    public static final float WYVERN_BANK_LIMIT = 15f;

    public static final float BALLISTA_WIDTH = 24f / 9f;
    public static final float BALLISTA_HEIGHT = 128f / 9f;
    public static final float BALLISTA_SPAWN_TIME = 1f;
    public static final float BALLISTA_SPEED_MIN = 250f / 9f;
    public static final float BALLISTA_SPEED_MAX = 350f / 9f;
    public static final float BALLISTA_DAMAGE = 25f;

    public static final float SEAGULL_WIDTH = 128f / 9f;
    public static final float SEAGULL_HEIGHT = 53f / 9f;
    public static final float SEAGULL_SPAWN_TIME = 4f;
    public static final float SEAGULL_SPEED_Y_MIN = 250f / 9f;
    public static final float SEAGULL_SPEED_Y_MAX = 350f / 9f;
    public static final float SEAGULL_FREQUENCY_MIN = 3f;
    public static final float SEAGULL_FREQUENCY_MAX = 6f;
    public static final float SEAGULL_AMPLITUDE_MIN = 100f / 9f;
    public static final float SEAGULL_AMPLITUDE_MAX = 400f / 9f;

    public static final float SOUL_WIDTH = 55f / 9f;
    public static final float SOUL_HEIGHT = 64f / 9f;
    public static final float SOUL_SPAWN_TIME = 4f;
    public static final float SOUL_SPEED = 200f / 9f;
    public static final float SOUL_FREQUENCY_MIN = 2f;
    public static final float SOUL_FREQUENCY_MAX = 4f;
    public static final float SOUL_AMPLITUDE_MIN = 20f / 9f;
    public static final float SOUL_AMPLITUDE_MAX = 40f / 9f;

    public static final float FIREBALL_WIDTH = 92f / 9f;
    public static final float FIREBALL_HEIGHT = 128f / 9f;
    public static final float FIREBALL_SPAWN_TIME = 0.5f;
    public static final float FIREBALL_SPEED_Y_MIN = 600f / 9f;
    public static final float FIREBALL_SPEED_Y_MAX = 700f / 9f;

    public static final float PARTICLE_SCALE = 1f / 9f;

    public static final float WIDTH = 160f;
    public static final float HEIGHT = 90f;

    public static final float BACKGROUND_WIDTH = WIDTH;
    public static final float BACKGROUND_HEIGHT = 1440f * WIDTH / 1280f;
    public static final float BACKGROUND_SPEED = 200f / 9f;

    private GameConfig() {
    }
}
