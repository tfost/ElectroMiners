package com.electrominers.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.electrominers.controllers.MouseInputListener;
import com.electrominers.ui.SimpleUI;
import com.electrominers.units.Unit;

/**
 * A Level is a self-contained instance of the game. It runs the main loop of a level:
 * handling movement, controlling characters, managing turns, etc.
 * @author Tyler
 *
 */
public class Level {

	private World world; // the World this level is taking place on. 
	private OrthographicCamera camera; // Camera representing the translation of the world. 
	private List<Unit> units;
	private SimpleUI ui;
	private int worldArr[][] = {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,1,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,1},
			{1,0,0,0,1,1,1,1,0,0,0,0,0,0,1,0,0,0,1,0,1,0,0,1},
			{1,0,0,0,0,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,1,0,0,1},
			{1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,1,0,1,1,1,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,1,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,1,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,1,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1,1,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,2,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
			{1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	};
	
	public Level() {
		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.camera.setToOrtho(false); // y axis is up.
		units = new ArrayList<Unit>();
		world = new World(worldArr, units); // TODO: THIS IS AN ARBITRARY WORLD SIZE MEANT TO FIT A 768 X 768 SCREEN.
		//units now contains all units in the world.
		camera.update();
		this.ui = new SimpleUI(this);
	}
	
	/**
	 * Updates the current level.
	 */
	public void update(MouseInputListener input) {
		world.handleInput(input, camera); // DEBUG handle input to check clicking on tiles.
		for (Unit u : this.units) {
			if (u.handleInput(input, this.camera)) { // if the unit handled their input, done with inputs.
				break;
			}
		}
		for (Unit u : this.units) {
			u.update();
		}
		
	}
	
	public void render() {
		world.render(camera);
		for (Unit u : units) {
			u.render(camera);
		}
		
		ui.render();
	}
	
}
