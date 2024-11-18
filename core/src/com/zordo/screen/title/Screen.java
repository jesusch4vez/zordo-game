package com.zordo.screen.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zordo.game.LegendOfZordo;
import com.zordo.game.menus.LevelSelect;
import com.zordo.screen.utils.GifDecoder;

public class Screen implements com.badlogic.gdx.Screen {

	final LegendOfZordo game;
    public SpriteBatch batch;
    public Animation<TextureRegion> animation;
    float elapsed;
    
    OrthographicCamera camera;
    
    public Screen(final LegendOfZordo game) {
    	this.game = game;
    	
    	animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("lozTitle.gif").read());	
    	camera = new OrthographicCamera();
    	camera.setToOrtho(false, 1920,1080);
    }
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
    	Gdx.gl20.glClearColor(0, 0, 0.2f, 0.0f);
    	Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	
	    batch = new SpriteBatch();
	    batch.setProjectionMatrix(camera.combined);

		// TODO Auto-generated method stub
        elapsed += Gdx.graphics.getDeltaTime();

        batch.begin();
        batch.draw(animation.getKeyFrame(elapsed), 0, 0);
        batch.end();
        
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
        	ScreenUtils.clear(0, 0, 0.2f, 1);
        	//this.dispose();
        	this.game.setScreen(new LevelSelect(game));
        }
        // camera.update();
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
