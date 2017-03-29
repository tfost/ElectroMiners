package com.electrominers.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.electrominers.controllers.MouseInputListener;

public class ElectroMinersMain extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Level level;
	MouseInputListener mouseInput;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		level = new Level();
		mouseInput = new MouseInputListener();
	}

	@Override
	public void render () {
		mouseInput.poll();
		level.update(mouseInput);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		level.render();
		//System.out.println(System.currentTimeMillis() + " we are using " + Gdx.app.getJavaHeap()); // prints memory usage
		
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
