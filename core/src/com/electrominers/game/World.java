package com.electrominers.game;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.electrominers.controllers.MouseInputListener;
import com.electrominers.tiles.MineableTile;
import com.electrominers.tiles.StandardRock;
import com.electrominers.tiles.StoneFloor;
import com.electrominers.tiles.Tile;
import com.electrominers.units.Unit;

/**
 * A World is a data structure representing the parts of the environment a level of the game
 * occurs in. 
 * @author Tyler
 *
 */
public class World {
	public static final int TILE_SIZE = 32; 
	
	private Tile[][] world; // the actual array that stores the underlying world information.
							// represents the tiles at positions [y][x].
							// world.length = height, world[x].length = width
	
	/**
	 * Creates a new world by parsing the values of an integer array into 
	 * tiles.
	 * @param worldArr an array to read data from
	 * @param units an output list that becomes filled with units created in this world.
	 * @modifies units
	 * @effects units contains all units created during World initialization.
	 * @requires worldArr != null, worldArr is a rectangular array.
	 */
	public World(int[][] worldArr, List<Unit> units) {
		world = new Tile[worldArr.length][worldArr[0].length];
		for (int y = 0; y < world.length; y++) {
			for (int x = 0; x < world[0].length; x++) {
				Tile t;
				switch (worldArr[y][x]) {
					case 0:
						t = new StoneFloor();
						break;
					case 1:
						t = new StandardRock();
						break;
					case 2:
						t = new StoneFloor();
						units.add(new Unit(x, y, this));
						break;
					default:
						throw new IllegalArgumentException("Value unsupported in current build:" + worldArr[x][y]);
					
				}				
				world[y][x] = t;
			}
		}
	}
	
	public int getWidth() {
		return world[0].length;
	}
	
	public int getHeight() {
		return world.length;

	}
	
	
	/**
	 * returns the tile at x, y. 
	 * @requires x, y are non-negative and < width or height, respectively.
	 */
	public Tile getTile(int x, int y) {
		return world[y][x];
	}
	
	/*
	 * anyone can get a tile, but only we can set the tile.
	 */
	private void setTile(int x, int y, Tile t) {
		world[y][x] = t;
	}
	
	
	public void handleInput(MouseInputListener input, Camera camera) {
		if (input.justPressed(0)) {
			Vector3 finalTileLocation = getTileCoordsFromScreen(camera, input.getX(), input.getY());
			System.out.println("Clicked on tile" + (int) finalTileLocation.x + "," + (int) finalTileLocation.y);
		}
	}
	
	public Vector3 getTileCoordsFromScreen(Camera c, int x, int y) {
		Vector3 unprojectedScreen = c.unproject(new Vector3(x, y, 0)).scl((float) (1.0 / TILE_SIZE));
		return new Vector3((int) unprojectedScreen.x, (int) (world[0].length - unprojectedScreen.y), 0);
	}
	
	
	/**
	 * Draws this particular world to a screen.
	 */
	public void render(OrthographicCamera camera) {
		ShapeRenderer renderer = new ShapeRenderer();
		renderer.begin(ShapeType.Filled);
		renderer.setAutoShapeType(true);
		for (int y = 0; y < world.length; y++) {
			for (int x = 0; x < world[0].length; x++) {
				//draw initial tile.
				renderer.set(ShapeType.Filled);
				renderer.setColor(world[y][x].getDisplayColor());
				Vector3 location = new Vector3(x * TILE_SIZE, y * TILE_SIZE, 0);
				Vector3 screenLocation = camera.project(location);
				renderer.rect(screenLocation.x, Gdx.graphics.getHeight() - screenLocation.y, TILE_SIZE, -TILE_SIZE);
				
				//draw outline.
				renderer.setColor(Color.BLACK);
				renderer.set(ShapeType.Line);
				renderer.rect(screenLocation.x, Gdx.graphics.getHeight() - screenLocation.y, TILE_SIZE, -TILE_SIZE);
				
			}
		}
		renderer.end();
		renderer.dispose();
	}
	
	/**
	 * Mines a tile at a given x, y by using the unit's default
	 * mining strength. 
	 * @requires the tile at x, y can be mined.
	 * @param u the unit doing the tile mining.
	 * @param x the x location of the tile
	 * @param y the y location of the tile
	 */
	public void mineTile(Unit u, int x, int y) {
		if (getTile(x, y) instanceof MineableTile) {
			MineableTile toMine = (MineableTile) getTile(x, y);
			toMine.mine(u.miningStrength());
			if (toMine.getStrengthLeft() <= 0) {
				setTile(x, y, new StoneFloor());
			}
		} else {
			throw new IllegalArgumentException("Cannot mine tile at " + x + ", " + y);
		}
	}
	
	/**
	 * Determines if a tile can be mined by a unit.
	 * @param u
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean canMine(Unit u, int x, int y) {
		return u.isAdjacentTo(x, y) && this.getTile(x, y) instanceof MineableTile;
	}
	
}
