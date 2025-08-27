package zordo.entities.player_interface.menu.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Screen;
import zordo.LegendOfZordo;
import zordo.components.camera.CameraComponent;
import zordo.components.Component;
import zordo.systems.utilities.GifDecoder;

import java.util.HashMap;

public class TitleMenu implements Screen {
	final LegendOfZordo game;
    public SpriteBatch batch;
    public Animation<TextureRegion> animation;
    float elapsed;

	HashMap<String, Component> components;

    public TitleMenu(final LegendOfZordo game) {
    	this.game = game;
        this.game.isOnTitleMenu = true;
    	animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("environment/lozTitle.gif").read());

		components = new HashMap<>();
		components.put("Camera", new CameraComponent());

        Gdx.app.log("Controller", "ControllerListener added.");
    }

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
    	Gdx.gl20.glClearColor(0, 0, 0.2f, 0.0f);
    	Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

	    batch = new SpriteBatch();
		CameraComponent camera = (CameraComponent) components.get("Camera");
	    batch.setProjectionMatrix(camera.getCamera().combined);

        elapsed += Gdx.graphics.getDeltaTime();

        batch.begin();
        batch.draw(animation.getKeyFrame(elapsed), 0, 0);
        batch.end();

        try {
            this.game.controllerListener.handleInput(Gdx.graphics.getDeltaTime(), game);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		//dispose();

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
	}

}
