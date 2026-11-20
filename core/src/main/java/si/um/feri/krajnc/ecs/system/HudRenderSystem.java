package si.um.feri.krajnc.ecs.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import si.um.feri.krajnc.assets.RegionNames;
import si.um.feri.krajnc.common.GameManager;

public class HudRenderSystem extends EntitySystem {

    private static final float PADDING = 20.0f;

    private final SpriteBatch batch;
    private final Viewport hudViewport;
    private final BitmapFont font;
    private final GlyphLayout layout = new GlyphLayout();
    private ShapeRenderer healthBar;
    private Sprite healthBarBorder;
    TextureAtlas atlas;

    public HudRenderSystem(SpriteBatch batch, Viewport hudViewport, BitmapFont font,
                           TextureAtlas atlas, ShapeRenderer shapeRenderer) {
        this.batch = batch;
        this.hudViewport = hudViewport;
        this.font = font;
        healthBar = shapeRenderer;
        this.atlas = atlas;
        healthBarBorder = new Sprite(atlas.findRegion(RegionNames.HEALTH_BAR_BORDER));
        healthBarBorder.setPosition(
            5f,
            hudViewport.getWorldHeight() - PADDING - healthBarBorder.getHeight() - 25f
        );
    }

    @Override
    public void update(float deltaTime) {
        hudViewport.apply();
        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.begin();

        font.setColor(Color.RED);
        String healthString = "HEALTH: " + (int)GameManager.INSTANCE.getHealth();
        layout.setText(font, healthString);
        float y = hudViewport.getWorldHeight() - PADDING;
        font.draw(batch, layout, PADDING, y);

        healthBarBorder.draw(batch);

        font.setColor(Color.ORANGE);
        String chargesString = "CHARGES: " + GameManager.INSTANCE.getCharges();
        layout.setText(font, chargesString);
        font.draw(batch, layout, PADDING, y - 100);

        font.setColor(Color.GREEN);
        String scoreString = "SOULS: " + GameManager.INSTANCE.getSouls();
        layout.setText(font, scoreString);
        font.draw(batch, layout, PADDING, y - 150);

        if (GameManager.INSTANCE.isGameOver()) {
            font.setColor(Color.RED);
            layout.setText(font, "GAME OVER");
            float endX = (hudViewport.getWorldWidth() + layout.width) * 0.5f - layout.width;
            float endY = (hudViewport.getWorldHeight() + layout.height) * 0.5f;
            font.draw(batch, layout, endX, endY);
        }

        batch.end();

        healthBar.setProjectionMatrix(hudViewport.getCamera().combined);
        healthBar.begin(ShapeRenderer.ShapeType.Filled);
        healthBar.setColor(Color.GREEN);
        healthBar.rect(28f, hudViewport.getWorldHeight() - 84f, Math.max(0, GameManager.INSTANCE.getHealth() * 2f), 20f);
        healthBar.end();
    }
}
