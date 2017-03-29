package com.electrominers.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.electrominers.controllers.Interactable;
import com.electrominers.controllers.MouseInputListener;
import com.electrominers.game.World;

/**
 * A Unit is a character the player can control. Units can move around the world and do certain actions,
 * like mine or fight.
 * @author Tyler
 *
 */
public class Unit implements Interactable{
	
	private int x;
	private int y;
	private UnitState state;
	private World world;
	
	public Unit(int x, int y, World world) {
		this.x = x;
		this.y = y;
		state = new IdleState(this);
		this.world = world;
		
	}
	
	/**
	 * Returns if this unit is directly above, below, left, or right of a given x, y coordinate.
	 * @param x
	 * @param y
	 * @return true if its adjacent to a coordinate.
	 */
	public boolean isAdjacentTo(int x, int y) {
		return (Math.abs(x - this.x) == 1 && Math.abs(y - this.y) == 0) ||
				(Math.abs(x - this.x) == 0 && Math.abs(y - this.y) == 1);
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public void moveTo(int x, int y) {
		this.x = x; 
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean handleInput(MouseInputListener input, Camera camera) {
		return this.state.handleInput(input, camera);
	}
	
	public void update() {
		this.state.update();
	}
	
	public void setState(UnitState state) {
		this.state = state;
	}
	
	public void render(OrthographicCamera camera) {
		ShapeRenderer renderer = new ShapeRenderer();
		renderer.begin(ShapeType.Filled);
		renderer.setAutoShapeType(true);
		renderer.setColor(Color.BLUE);
		Vector3 worldLoc = new Vector3(x * World.TILE_SIZE, y * World.TILE_SIZE, 0);
		Vector3 renderLoc = camera.project(worldLoc);
		renderer.circle(renderLoc.x + World.TILE_SIZE / 2, Gdx.graphics.getHeight() - renderLoc.y - World.TILE_SIZE / 2, World.TILE_SIZE / 2);
		renderer.end();
		renderer.dispose();
	}
	
	public int miningStrength() {
		return 10;
	}
	

}
