package si.um.feri.krajnc.common;

import si.um.feri.krajnc.config.GameConfig;

public class GameManager {

    public static final GameManager INSTANCE = new GameManager();

    private float health;
    private int soulsCollected;
    private int charges;

    private GameManager() {
    }

    public int getSouls() {
        return soulsCollected;
    }

    public int getCharges() {
        return charges;
    }

    public void reset() {
        soulsCollected = 0;
        charges = 3;
        health = 100f;
    }

    public void collectSoul() {
        soulsCollected++;
    }

    public void eatSeagull() {
        charges++;
    }

    public boolean isGameOver() {
        return health <= 0f;
    }

    public float getHealth() {
        return health;
    }

    public void damage() {
        health -= GameConfig.BALLISTA_DAMAGE;
    }

    public void fire() {
        charges--;
    }
}
