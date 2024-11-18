package com.zordo.game.levels.intro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zordo.game.LegendOfZordo;
import com.zordo.game.platforms.Platform;

public class PlayScreen implements Screen{

	private SpriteBatch batch;
	private final Texture backgroundTexture;
	
	OrthographicCamera camera;
	
	final LegendOfZordo game;

	private final Platform slat;
	
	
	public PlayScreen(final LegendOfZordo game) {
		this.game = game;

		this.slat = new Platform();
		this.slat.setCoordinates(50, 50);

		ScreenUtils.clear(0, 0, 0.2f, 1);

		TextureRegion background = new TextureRegion();
		backgroundTexture = new Texture("background_32.png");
		background.setTexture(backgroundTexture);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800,400);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
    	Gdx.gl20.glClearColor(0, 0, 0.2f, 0.0f);
    	Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		batch.draw(backgroundTexture,0,0,1920,1080);

		this.slat.render(batch);

		batch.end();
		camera.update();		
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
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
	}

}
