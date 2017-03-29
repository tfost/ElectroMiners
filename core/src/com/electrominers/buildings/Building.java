package com.electrominers.buildings;

/**
 * A Building is an object within the game that can do certain things that effect the world in a meaningful way. 
 * @author Tyler
 *
 */
public abstract class Building {
	
	protected int[][] layout; // the building layout. 0's represent empty, 1's represent filled.
	
	public abstract void update();
	
	public abstract void render();
	
}
