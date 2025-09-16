package zordo.game.systems.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import zordo.LegendOfZordo;
import zordo.game.models.gamePad.ControllerComponent;
import zordo.game.entities.characters.Character;
import zordo.game.entities.world.level.Level;
import zordo.game.systems.health.HealthSystem;
import zordo.game.systems.menu.MenuSystem;

public class HudSystem {
    public static void renderHUD(Character player) {
        SpriteBatch hudBatch = new SpriteBatch();
        hudBatch.begin();
        HealthSystem.healthRender(player.getCharacterComponent().getHearts(), hudBatch);

        hudBatch.end();
    }

    public static void pause(LegendOfZordo game, Level level) {
        SpriteBatch hudBatch = new SpriteBatch();
        BitmapFont font = new BitmapFont();

        hudBatch.begin();
        font.getData().setScale(5);
        font.draw(hudBatch, "PAUSED", Gdx.graphics.getWidth()/2f - 250, Gdx.graphics.getHeight()/2f + 500);
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER) || ControllerComponent.START_BUTTON.isPressed()) {
            level.paused = false;
            ControllerComponent.START_BUTTON.release();
        }

        if(level.paused) {
            MenuSystem.pauseMenu(game);
        }
        hudBatch.end();
    }
}
