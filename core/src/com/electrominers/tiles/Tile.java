package com.electrominers.tiles;

import com.badlogic.gdx.graphics.Color;

/**
 * A Tile is a position in the world. 
 * @author Tyler
 *
 */
public abstract class Tile {
	
	/**
	 * creates a new Generic Tile.
	 */
	public Tile() {}
	
	/**
	 * Determines if a generic unit can move into this cell by default. 
	 * Tiles can be moved into if their movement cost is non-negative.
	 * @return
	 */
	public boolean canMoveInto() {
		return this.movementCost() >= 0;
	}
	
	public abstract int movementCost();
	
	/**
	 * Determines what color this tile should be displayed as. 
	 * @return
	 */
	public abstract Color getDisplayColor();
	
	
	
}
